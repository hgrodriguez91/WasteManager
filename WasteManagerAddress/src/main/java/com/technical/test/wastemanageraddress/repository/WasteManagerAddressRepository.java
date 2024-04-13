package com.technical.test.wastemanageraddress.repository;

import com.technical.test.wastemanageraddress.model.WasteManagerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WasteManagerAddressRepository extends JpaRepository<WasteManagerAddress, Long> {

    Optional<WasteManagerAddress> findWasteManagerAddressById(Long id);
}
