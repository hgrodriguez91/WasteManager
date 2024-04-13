package com.technical.test.wastemanageraddress.service;

import com.technical.test.wastemanageraddress.dto.GenericResponseDto;
import com.technical.test.wastemanageraddress.dto.WasteManagerAddressDto;
import com.technical.test.wastemanageraddress.model.WasteManagerAddress;

import java.util.List;

public interface WasteManagerAddressService {

    GenericResponseDto<List<WasteManagerAddress>> getAll();

    GenericResponseDto<WasteManagerAddress> getById(Long id);

    GenericResponseDto<WasteManagerAddress> create(WasteManagerAddressDto wasteManagerAddress);

    GenericResponseDto<WasteManagerAddress> edit(Long id,WasteManagerAddressDto wasteManagerAddress) ;

    GenericResponseDto<String> delete(Long id);
}
