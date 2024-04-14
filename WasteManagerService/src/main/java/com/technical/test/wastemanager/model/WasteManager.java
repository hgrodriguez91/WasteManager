package com.technical.test.wastemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "WASTE_MANAGER")
public class WasteManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String nif;
    @Builder.Default
    private Boolean isEnabled = Boolean.TRUE;
    @Builder.Default
    private Long version = 0L;
    private Date createdDate;
    private Date lastModifiedDate;
    private Long  managerAddressId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wasteManager")
    private List<WasteCenterAuthorization> wasteCenterAuthorizations;
}
