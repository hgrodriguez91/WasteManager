package com.technical.test.wastemanager.service.impl;

import com.technical.test.wastemanager.api.ManagerAddressApi;
import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.GenericResponseDto;
import com.technical.test.wastemanager.dto.WasteManagerAddressDto;
import com.technical.test.wastemanager.service.CallApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CallApiServiceImpl implements CallApiService {

    private final ManagerAddressApi managerAddressApi;

    @Override
    public WasteManagerAddress createAddress(WasteManagerAddressDto managerAddressDto){
        ResponseEntity<GenericResponseDto<WasteManagerAddress>> responseAddress = managerAddressApi.createAddress(managerAddressDto);
            return Objects.requireNonNull(responseAddress.getBody()).getData();
    }

    @Override
    public List<WasteManagerAddress> getAllAddress(){
        ResponseEntity<GenericResponseDto<List<WasteManagerAddress>>> responseAddress = managerAddressApi.getAllAddress();
            return Objects.requireNonNull(responseAddress.getBody()).getData();
    }

    @Override
    public WasteManagerAddress getAddressById(Long id) {
        ResponseEntity<GenericResponseDto<WasteManagerAddress>> responseAddress = managerAddressApi.getAddressById(id);
            return Objects.requireNonNull(responseAddress.getBody()).getData();
    }

    @Override
    public WasteManagerAddress updateAddress(Long id, WasteManagerAddressDto managerAddressDto) {
        ResponseEntity<GenericResponseDto<WasteManagerAddress>> responseAddress = managerAddressApi.updateAddress(id, managerAddressDto);
            return Objects.requireNonNull(responseAddress.getBody()).getData();
        }


    @Override
    public Boolean deleteAddress(Long id) {
            ResponseEntity<GenericResponseDto<String>> responseAddress = managerAddressApi.deleteAddress(id);
            return responseAddress.hasBody();
    }
}
