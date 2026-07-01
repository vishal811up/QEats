
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// TODO: CRIO_TASK_MODULE_SERIALIZATION
//  Implement Restaurant class.

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    @NotNull
    private String restaurantId;

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String imageUrl;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private String opensAt;

    @NotNull
    private String closesAt;

    @NotNull
    private List<String> attributes = new ArrayList<>();
}

