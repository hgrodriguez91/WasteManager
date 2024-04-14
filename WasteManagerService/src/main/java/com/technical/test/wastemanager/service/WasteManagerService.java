package com.technical.test.wastemanager.service;

import com.technical.test.wastemanager.dto.GenericResponseDTO;
import com.technical.test.wastemanager.dto.WasteManagerDTO;

import java.io.IOException;

public interface WasteManagerService {

        GenericResponseDTO<WasteManagerDTO> findById(Long id) throws IOException;

        GenericResponseDTO<WasteManagerDTO> create(WasteManagerDTO wasteManagerDTO) throws IOException;

        GenericResponseDTO<WasteManagerDTO> update(Long id, WasteManagerDTO wasteManagerDTO) throws IOException;
}
