package com.technical.test.wastemanager.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WasteManagerAddress {
    private Long id;
    private String address;
    private Boolean isEnabled ;
    private Long version ;
    private Date createdDate;
    private Date lastModifiedDate;
}
