package com.technical.test.wastemanageraddress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WasteManagerAddressDto {

    private String address;
    private Boolean isEnabled ;
    private Long version;
}
