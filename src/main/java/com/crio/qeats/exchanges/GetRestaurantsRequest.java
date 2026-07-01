/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.exchanges;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: CRIO_TASK_MODULE_RESTAURANTSAPI
// Implement GetRestaurantsRequest.
// Complete the class such that it is able to deserialize the incoming query params.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantsRequest {

  @NotNull
  @Min(value = -90, message = "Latitude can not be less than -90")
  @Max(value = 90, message = "Latitude can not be greater than 90")
  private Double latitude;

  @NotNull
  @Min(value = -180, message = "Longitude can not be less than -180")
  @Max(value = 180, message = "Longitude can not be greater than 180")
  private Double longitude;


  private String searchFor;

  public GetRestaurantsRequest(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

}


