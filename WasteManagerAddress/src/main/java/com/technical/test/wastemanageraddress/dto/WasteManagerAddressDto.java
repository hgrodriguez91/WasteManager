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
    @Builder.Default
    private Boolean isEnabled = Boolean.TRUE;
    @Builder.Default
    private Long version = 0L;
}
