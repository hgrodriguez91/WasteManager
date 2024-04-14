package com.technical.test.wastemanager.repository;

import com.technical.test.wastemanager.model.WasteCenterAuthorization;
import com.technical.test.wastemanager.model.WasteManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WasteCenterAuthorizationRepository extends JpaRepository<WasteCenterAuthorization, Long> {

    @Query(value = "SELECT * FROM WASTE_CENTER_AUTHORIZATION where waste_manager_id = :wasteManagerId", nativeQuery = true)
    List<WasteCenterAuthorization> getByWasteManagerId(@Param("wasteManagerId") Long wasteManagerId);
}
