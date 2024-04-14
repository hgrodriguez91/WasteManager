package com.technical.test.wastemanager.service;

import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.WasteManagerAddressDto;

import java.io.IOException;
import java.util.List;

public interface WasteManagerService {

    WasteManagerAddress createAddress(WasteManagerAddressDto managerAddressDto) throws IOException;

    List<WasteManagerAddress> getAllAddress() throws IOException;

    WasteManagerAddress getAddressById(Long id) throws IOException;

    WasteManagerAddress updateAddress(Long id, WasteManagerAddressDto managerAddressDto) throws IOException;

    Boolean deleteAddress(Long id) throws IOException;
}
