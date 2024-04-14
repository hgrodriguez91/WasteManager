package com.technical.test.wastemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WasteManagerDTO {
    private String name;
    private String nif;
    private WasteManagerAddressDTO wasteManagerAddress;
    private List<String> wasteCenterAuthorizations;
}
