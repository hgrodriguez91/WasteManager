package com.technical.test.wastemanager.repository;

import com.technical.test.wastemanager.model.WasteManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteManagerRepository extends JpaRepository<WasteManager, Long> {
}
