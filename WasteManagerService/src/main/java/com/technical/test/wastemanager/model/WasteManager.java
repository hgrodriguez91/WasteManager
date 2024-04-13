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
public class WasteManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String nif;
    private Boolean isEnabled = Boolean.TRUE;
    private Long version = 0L;
    private Date createdDate;
    private Date lastModifiedDate;

    @OneToMany
    private List<WasteCenterAuthorization> listOfWasteCenterAuthorization;
}
