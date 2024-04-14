package com.technical.test.wastemanager.service;

import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.WasteManagerAddressDTO;

import java.io.IOException;
import java.util.List;

public interface CallApiService {

    WasteManagerAddress createAddress(WasteManagerAddressDTO managerAddressDto) throws IOException;

    List<WasteManagerAddress> getAllAddress() throws IOException;

    WasteManagerAddress getAddressById(Long id) throws IOException;

    WasteManagerAddress updateAddress(Long id, WasteManagerAddressDTO managerAddressDto) throws IOException;

    Boolean deleteAddress(Long id) throws IOException;
}
