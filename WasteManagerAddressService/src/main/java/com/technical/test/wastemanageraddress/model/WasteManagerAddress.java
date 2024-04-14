package com.technical.test.wastemanageraddress.model;

import jakarta.persistence.*;
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
@Table(name = "WASTE_MANAGER_ADDRESS")
public class WasteManagerAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @Builder.Default
    private Boolean isEnabled = Boolean.TRUE;
    @Builder.Default
    private Long version = 0L;
    private Date createdDate;
    private Date lastModifiedDate;

}
