package com.technical.test.wastemanager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "WASTE_CENTER_AUTHORIZATION")
public class WasteCenterAuthorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "AUTHORIZATION_NUMBER")
    private String authorizationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "waste_manager_id")
    WasteManager wasteManager;

    public WasteCenterAuthorization(String authorizationNumber, WasteManager wasteManager) {
        this.authorizationNumber = authorizationNumber;
        this.wasteManager = wasteManager;
    }
}
