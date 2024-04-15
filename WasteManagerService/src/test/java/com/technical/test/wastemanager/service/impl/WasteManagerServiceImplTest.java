package com.technical.test.wastemanager.service.impl;

import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.GenericResponseDto;
import com.technical.test.wastemanager.dto.WasteManagerAddressDto;
import com.technical.test.wastemanager.dto.WasteManagerDTO;
import com.technical.test.wastemanager.model.WasteCenterAuthorization;
import com.technical.test.wastemanager.model.WasteManager;
import com.technical.test.wastemanager.repository.WasteManagerRepository;
import com.technical.test.wastemanager.service.CallApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing WasteManagerService Service")
class WasteManagerServiceImplTest {

    @Mock
    private CallApiService callApiService;
    @Mock
    private WasteManagerRepository wasteManagerRepository;

    @InjectMocks
    private WasteManagerServiceImpl managerService;
    private WasteManagerAddress wasteManagerAddress;
    private WasteManager wasteManager;

    private WasteManagerDTO wasteManagerDTO;

    private WasteManagerAddressDto managerAddressDto;


    @BeforeEach
    void setUp() {

        List<WasteCenterAuthorization> authorizations = Collections.singletonList(WasteCenterAuthorization.builder()
                .id(1L)
                .authorizationNumber("123456789")
                .build());

        wasteManagerAddress = WasteManagerAddress.builder()
                .id(1L)
                .address("Address #1")
                .isEnabled(Boolean.TRUE)
                .createdDate(Date.from(Instant.now()))
                .lastModifiedDate(Date.from(Instant.now()))
                .build();
        wasteManager = WasteManager.builder()
                .id(1L)
                .nif("nif")
                .name("Waste Manager Name")
                .managerAddressId(1L)
                .wasteCenterAuthorizations(authorizations)
                .isEnabled(Boolean.TRUE)
                .createdDate(Date.from(Instant.now()))
                .lastModifiedDate(Date.from(Instant.now()))
                .build();

        managerAddressDto = WasteManagerAddressDto.builder()
                .version(1L)
                .address("Address #1")
                .isEnabled(Boolean.TRUE)
                .build();

        wasteManagerDTO = WasteManagerDTO.builder()
                .nif("nif")
                .name("Waste Manager Name")
                .wasteManagerAddress(managerAddressDto)
                .wasteCenterAuthorizations(Arrays.asList("1321546","123121"))
                .isEnabled(Boolean.TRUE)
                .build();
    }

    @Test
    @DisplayName("Method findById should return an GenericResponseDto<WasteManagerDTO>")
    void findById() throws IOException {
        when(callApiService.getAddressById(1L)).thenReturn(wasteManagerAddress);
        when(wasteManagerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(wasteManager));
        GenericResponseDto<WasteManagerDTO> genericResponseDto = managerService.findById(1L);
        assertEquals(genericResponseDto.getData().getIsEnabled(), Boolean.TRUE);
    }

    @Test
    @DisplayName("Method create should save wasteManager ")
    void create() throws IOException {
        when(callApiService.createAddress(managerAddressDto)).thenReturn(wasteManagerAddress);
        lenient().when(wasteManagerRepository.save(wasteManager)).thenReturn(wasteManager);
        GenericResponseDto<WasteManagerDTO> genericResponseDto = managerService.create(wasteManagerDTO);
        assertEquals(genericResponseDto.getData().getIsEnabled(), Boolean.TRUE);
    }

    @Test
    @DisplayName("Method update should return an updated wasteManager")
    void update() throws IOException {
        when(callApiService.updateAddress(1L, managerAddressDto)).thenReturn(wasteManagerAddress);
        when(wasteManagerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(wasteManager));
        lenient().when(wasteManagerRepository.save(wasteManager)).thenReturn(wasteManager);
        GenericResponseDto<WasteManagerDTO> genericResponseDto = managerService.update(1L,wasteManagerDTO);
        assertEquals(genericResponseDto.getData().getIsEnabled(), Boolean.TRUE);
    }
}