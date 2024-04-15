package com.technical.test.wastemanager.service;

import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.WasteManagerAddressDto;

import java.io.IOException;
import java.util.List;

public interface CallApiService {

    WasteManagerAddress createAddress(WasteManagerAddressDto managerAddressDto);

    List<WasteManagerAddress> getAllAddress();

    WasteManagerAddress getAddressById(Long id);

    WasteManagerAddress updateAddress(Long id, WasteManagerAddressDto managerAddressDto);

    Boolean deleteAddress(Long id);
}
