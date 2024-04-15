package com.technical.test.wastemanager.service;

import com.technical.test.wastemanager.dto.GenericResponseDto;
import com.technical.test.wastemanager.dto.WasteManagerDTO;

import java.io.IOException;

public interface WasteManagerService {

        GenericResponseDto<WasteManagerDTO> findById(Long id) throws IOException;

        GenericResponseDto<WasteManagerDTO> create(WasteManagerDTO wasteManagerDTO) throws IOException;

        GenericResponseDto<WasteManagerDTO> update(Long id, WasteManagerDTO wasteManagerDTO) throws IOException;
}
