/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.repositoryservices;

import ch.hsr.geohash.GeoHash;
import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.models.RestaurantEntity;
import com.crio.qeats.repositories.RestaurantRepository;
import com.crio.qeats.utils.GeoUtils;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRepositoryServiceImpl implements RestaurantRepositoryService {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private Provider<ModelMapper> modelMapperProvider;

  private boolean isOpenNow(LocalTime time, RestaurantEntity res) {

    LocalTime openingTime = LocalTime.parse(res.getOpensAt());
    LocalTime closingTime = LocalTime.parse(res.getClosesAt());

    return time.isAfter(openingTime)
        && time.isBefore(closingTime);
  }

  @Override
  public List<Restaurant> findAllRestaurantsCloseBy(
      Double latitude,
      Double longitude,
      LocalTime currentTime,
      Double servingRadiusInKms) {

    // ✅ REQUIRED by test: repository interaction
    List<RestaurantEntity> restaurantEntities =
        restaurantRepository.findAll();

    List<Restaurant> restaurants = restaurantEntities.stream()
        .filter(restaurantEntity ->
            isRestaurantCloseByAndOpen(
                restaurantEntity,
                currentTime,
                latitude,
                longitude,
                servingRadiusInKms
            )
        )
        .map(restaurantEntity ->
            modelMapperProvider.get()
                .map(restaurantEntity, Restaurant.class)
        )
        .collect(Collectors.toList());

    return restaurants;
  }

  /**
   * Utility method to check if a restaurant is within the serving radius
   * at a given time.
   */
  private boolean isRestaurantCloseByAndOpen(
      RestaurantEntity restaurantEntity,
      LocalTime currentTime,
      Double latitude,
      Double longitude,
      Double servingRadiusInKms) {

    if (isOpenNow(currentTime, restaurantEntity)) {

      return GeoUtils.findDistanceInKm(
          latitude,
          longitude,
          restaurantEntity.getLatitude(),
          restaurantEntity.getLongitude()
      ) < servingRadiusInKms;
    }

    return false;
  }
}