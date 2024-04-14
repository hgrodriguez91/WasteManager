package com.technical.test.wastemanageraddress.controller;


import com.technical.test.wastemanageraddress.dto.GenericResponseDto;
import com.technical.test.wastemanageraddress.dto.WasteManagerAddressDto;
import com.technical.test.wastemanageraddress.model.WasteManagerAddress;
import com.technical.test.wastemanageraddress.service.WasteManagerAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager-address")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WasteManagerAddressController {

    private final WasteManagerAddressService managerAddressService;

    @GetMapping()
    public ResponseEntity<GenericResponseDto<List<WasteManagerAddress>>> getAllAddress() {
        return new ResponseEntity<>(managerAddressService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDto<WasteManagerAddress>> getAddressById(@PathVariable("id") Long id){
        return new ResponseEntity<>(managerAddressService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GenericResponseDto<WasteManagerAddress>> createAddress(@RequestBody WasteManagerAddressDto managerAddressDto){
        return new ResponseEntity<>(managerAddressService.create(managerAddressDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto<WasteManagerAddress>> updateAddress(@PathVariable("id") Long id,
                                                                                 @RequestBody WasteManagerAddressDto managerAddressDto) {
        return new ResponseEntity<>(managerAddressService.edit(id,managerAddressDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto<String>> deleteAddress(@PathVariable("id") Long id){
        return new ResponseEntity<>(managerAddressService.delete(id), HttpStatus.OK);
    }
}
