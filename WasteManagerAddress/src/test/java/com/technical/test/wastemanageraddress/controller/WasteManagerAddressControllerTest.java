package com.technical.test.wastemanageraddress.controller;

import com.google.gson.Gson;
import com.technical.test.wastemanageraddress.dto.GenericResponseDto;
import com.technical.test.wastemanageraddress.dto.WasteManagerAddressDto;
import com.technical.test.wastemanageraddress.exception.NotFoundException;
import com.technical.test.wastemanageraddress.model.WasteManagerAddress;
import com.technical.test.wastemanageraddress.service.impl.WasteManagerAddressServiceImpl;
import com.technical.test.wastemanageraddress.util.ConstantsCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WasteManagerAddressController.class)
@DisplayName("Testing WasteManagerAddress Controller")
class WasteManagerAddressControllerTest {


    @MockBean
    private WasteManagerAddressServiceImpl addressService;

    @Autowired
    private MockMvc mockMvc;
    private WasteManagerAddress managerAddress;
    private WasteManagerAddressDto managerAddressDto;

    private Gson gson;
    private static final String STATUS_SUCCES = "success";

    @BeforeEach
    void setUp() {
        gson = new Gson();
        managerAddress = WasteManagerAddress.builder()
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
    @DisplayName("getAllAddress should return GenericResponseDto<List<WasteManagerAddress>>")
    void getAllAddress() throws Exception {
        when(addressService.getAll()).thenReturn(new GenericResponseDto<>("Listed Success", Collections.singletonList(managerAddress), STATUS_SUCCES));
        mockMvc.perform(get("/manager-address").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> new GenericResponseDto<>("Listed Success", Collections.singletonList(managerAddress), STATUS_SUCCES));

    }

    @Test
    @DisplayName("getById should return GenericResponseDto<WasteManagerAddress>")
    void getAddressById() throws Exception {
        when(addressService.getById(anyLong())).thenReturn(new GenericResponseDto<>("Listed Success", managerAddress, STATUS_SUCCES));
        mockMvc.perform(get("/manager-address/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(STATUS_SUCCES))
                .andExpect(jsonPath("$.data.address").value("Street"));
    }

    @Test
    @DisplayName("getAddressById should throw a NotFoundexception")
    void getAddressByIdThrow404Exception() throws Exception {
        when(addressService.getById(anyLong())).thenThrow(new NotFoundException("The requested address dont exist in the database"));
        mockMvc.perform(get("/manager-address/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.resultCode").value(ConstantsCode.BUSSINESS_ERROR));

    }

    @Test
    @DisplayName("createAddress should return the created GenericResponseDto<WasteManagerAddress>")
    void createAddress() throws Exception {
        when(addressService.create(managerAddressDto)).thenReturn(new GenericResponseDto<>("WasteManagerAddress created", managerAddress, STATUS_SUCCES));
        mockMvc.perform(post("/manager-address")
                        .content(gson.toJson(managerAddressDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(STATUS_SUCCES))
                .andExpect(jsonPath("$.data.address").value("Street"));
    }

    @Test
    @DisplayName("updateAddress should return the edited GenericResponseDto<WasteManagerAddress>")
    void updateAddress() throws Exception {
        when(addressService.edit(1L,managerAddressDto)).thenReturn(new GenericResponseDto<>("Address edited", managerAddress, STATUS_SUCCES));
        mockMvc.perform(put("/manager-address/{id}", 1L)
                        .content(gson.toJson(managerAddressDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(STATUS_SUCCES))
                .andExpect(jsonPath("$.data.address").value("Street"));
    }

    @Test
    @DisplayName("DeleteAddress should return the OK status")
    void deleteAddress() throws Exception {
        when(addressService.delete(1L)).thenReturn(new GenericResponseDto<>("Address deleted", null, STATUS_SUCCES));
        mockMvc.perform(delete("/manager-address/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(STATUS_SUCCES));
    }
}