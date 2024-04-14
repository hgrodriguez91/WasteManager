package com.technical.test.wastemanager.controller;

import com.technical.test.wastemanager.dto.GenericResponseDTO;
import com.technical.test.wastemanager.dto.WasteManagerAddressDTO;
import com.technical.test.wastemanager.dto.WasteManagerDTO;
import com.technical.test.wastemanager.service.WasteManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/waste-manager")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WasteManagerController {

    private final WasteManagerService wasteManagerService;

    @GetMapping("/{id}")
    ResponseEntity<GenericResponseDTO<WasteManagerDTO>> findById(@PathVariable("id") Long id) throws IOException {
        return new ResponseEntity<>(wasteManagerService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<GenericResponseDTO<WasteManagerDTO>> create(@RequestBody WasteManagerDTO wasteManagerDTO) throws IOException {
        return new ResponseEntity<>(wasteManagerService.create(wasteManagerDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<GenericResponseDTO<WasteManagerDTO>> update(@PathVariable("id") Long id,
                                                               @RequestBody WasteManagerDTO wasteManagerDTO) throws IOException {
        return new ResponseEntity<>(wasteManagerService.update(id,wasteManagerDTO), HttpStatus.OK);
    }


}
