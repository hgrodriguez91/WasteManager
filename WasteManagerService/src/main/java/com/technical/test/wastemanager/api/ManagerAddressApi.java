package com.technical.test.wastemanager.api;

import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.GenericResponseDto;
import com.technical.test.wastemanager.dto.WasteManagerAddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "waste-manager-address-service" , path ="/api/manager-address" )
public interface ManagerAddressApi {

    @GetMapping()
    ResponseEntity<GenericResponseDto<List<WasteManagerAddress>>> getAllAddress();

    @GetMapping("/{id}")
    ResponseEntity<GenericResponseDto<WasteManagerAddress>> getAddressById(@PathVariable("id") Long id);

    @PostMapping()
    ResponseEntity<GenericResponseDto<WasteManagerAddress>> createAddress(@RequestBody WasteManagerAddressDto managerAddressDto);

    @PutMapping("/{id}")
    ResponseEntity<GenericResponseDto<WasteManagerAddress>> updateAddress(@PathVariable("id") Long id,
                                                                          @RequestBody WasteManagerAddressDto managerAddressDto);

    @DeleteMapping("/{id}")
    ResponseEntity<GenericResponseDto<String>> deleteAddress(@PathVariable("id") Long id);
}
