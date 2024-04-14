package com.technical.test.wastemanager.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "WASTE_CENTER_AUTHORIZATION")
public class WasteCenterAuthorization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authorizationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waste_manager_id")
    WasteManager wasteManager;

}
