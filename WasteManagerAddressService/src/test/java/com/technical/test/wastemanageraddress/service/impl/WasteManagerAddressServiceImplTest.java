package com.technical.test.wastemanageraddress.service.impl;

import com.technical.test.wastemanageraddress.dto.GenericResponseDto;
import com.technical.test.wastemanageraddress.dto.WasteManagerAddressDto;
import com.technical.test.wastemanageraddress.exception.NotFoundException;
import com.technical.test.wastemanageraddress.model.WasteManagerAddress;
import com.technical.test.wastemanageraddress.repository.WasteManagerAddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing WasteManagerAddress Service")
class WasteManagerAddressServiceImplTest {

    @Mock
    private WasteManagerAddressRepository addressRepository;

    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private WasteManagerAddressServiceImpl managerAddressService ;
    private WasteManagerAddress managerAddress;
    private WasteManagerAddressDto managerAddressDto;

    @BeforeEach
    void setUp() {

        managerAddress =  WasteManagerAddress.builder()
                .id(1L)
                .address("Street")
                .isEnabled(Boolean.TRUE)
                .createdDate(Date.from(Instant.now()))
                .lastModifiedDate(Date.from(Instant.now()))
                .version(1L)
                .build();

       managerAddressDto = WasteManagerAddressDto.builder()
                .address("Street")
                .version(1L)
                .isEnabled(Boolean.TRUE).build();
    }

    @Test
    @DisplayName("Test method getAll should Return a List of address")
    void getAll() {
        given(addressRepository.findAll()).willReturn(Collections.singletonList(managerAddress));
        GenericResponseDto<List<WasteManagerAddress>> managerAddresses = managerAddressService.getAll();
        assertFalse(managerAddresses.getData().isEmpty());
    }

    @Test
    @DisplayName("Test method getById should Return an address")
    void getById() {
        given(addressRepository.findWasteManagerAddressById(anyLong())).willReturn(Optional.ofNullable(managerAddress));
        GenericResponseDto<WasteManagerAddress> response = managerAddressService.getById(anyLong());
        assertEquals(response.getData().getId(),1L);
    }

    @Test
    @DisplayName("Test method getById should Return a NotFoundException")
    void getByIdException() {
        given(addressRepository.findWasteManagerAddressById(anyLong())).willReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->  managerAddressService.getById(anyLong()));
    }

    @Test
    @DisplayName("Test method create should Return a created address")
    void create() {
        given(modelMapper.map(managerAddressDto, WasteManagerAddress.class)).willReturn(managerAddress);
        given(addressRepository.save(managerAddress)).willReturn(managerAddress);
       GenericResponseDto<WasteManagerAddress> wasteManagerAddress = managerAddressService.create(managerAddressDto);
        assertEquals(wasteManagerAddress.getData().getAddress(), "Street");
        assertEquals(wasteManagerAddress.getData().getVersion(), 1L);
        assertEquals(wasteManagerAddress.getData().getIsEnabled(), Boolean.TRUE);
    }

    @Test
    @DisplayName("Test method edit should Return an edited address")
    void edit() {
        given(addressRepository.findWasteManagerAddressById(anyLong())).willReturn(Optional.ofNullable(managerAddress));
        given(addressRepository.save(managerAddress)).willReturn(managerAddress);
        GenericResponseDto<WasteManagerAddress> wasteManagerAddress = managerAddressService.edit(anyLong(),managerAddressDto);
        assertEquals(wasteManagerAddress.getData().getAddress(), "Street");
        assertEquals(wasteManagerAddress.getData().getVersion(), 1L);
        assertEquals(wasteManagerAddress.getData().getIsEnabled(), Boolean.TRUE);
    }

    @Test
    @DisplayName("Test method edit should throw a NotFoundException")
    void editThrowNotFoundException() {
        given(addressRepository.findWasteManagerAddressById(anyLong())).willReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> managerAddressService.edit(anyLong(),managerAddressDto));
    }

    @Test
    @DisplayName("Test method delete should throw a NotFoundException")
    void delete() {
        given(addressRepository.findWasteManagerAddressById(anyLong())).willReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> managerAddressService.delete(anyLong()));
    }
}