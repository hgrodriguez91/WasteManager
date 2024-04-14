package com.technical.test.wastemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WasteManagerAddressDTO {

    private String address;
    private Boolean isEnabled;
    private Long version;
}
