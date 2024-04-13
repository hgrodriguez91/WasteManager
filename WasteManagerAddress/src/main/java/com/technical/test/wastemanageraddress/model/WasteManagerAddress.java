package com.technical.test.wastemanageraddress.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WasteManagerAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    @Builder.Default
    private Boolean isEnabled = Boolean.TRUE;
    @Builder.Default
    private Long version = 0L;
    private Date createdDate;
    private Date lastModifiedDate;

}