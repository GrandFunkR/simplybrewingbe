/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.dto;

import lombok.Data;

/**
 * The type Address dto.
 */
@Data
public class AddressDto {

    /**
     * The Street.
     */
    public String street;
    /**
     * The City.
     */
    public String city;
    /**
     * The Zip code.
     */
    public String zipCode;
    /**
     * The Lat.
     */
    public Double lat;
    /**
     * The Lng.
     */
    public Double lng;
}
