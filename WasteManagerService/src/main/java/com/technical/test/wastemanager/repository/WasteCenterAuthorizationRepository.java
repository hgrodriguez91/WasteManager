package com.technical.test.wastemanager.repository;

import com.technical.test.wastemanager.model.WasteCenterAuthorizationEntity;
import com.technical.test.wastemanager.model.WasteManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WasteCenterAuthorizationRepository extends JpaRepository<WasteManagerEntity, Long> {

    @Query(value = "SELECT * FROM WASTE_CENTER_AUTHORIZATION WHERE waste_manager_id = :wasteManagerId", nativeQuery = true)
    List<WasteCenterAuthorizationEntity> getByWasteManagerId(@Param("wasteManagerId") Long wasteManagerId);
}
