package com.technical.test.wastemanager.service.impl;

import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.GenericResponseDto;
import com.technical.test.wastemanager.dto.WasteManagerAddressDto;
import com.technical.test.wastemanager.dto.WasteManagerDTO;
import com.technical.test.wastemanager.exception.NotFoundException;
import com.technical.test.wastemanager.model.WasteCenterAuthorization;
import com.technical.test.wastemanager.model.WasteManager;
import com.technical.test.wastemanager.repository.WasteManagerRepository;
import com.technical.test.wastemanager.service.CallApiService;
import com.technical.test.wastemanager.service.WasteManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WasteManagerServiceImpl implements WasteManagerService {

    private final CallApiService callApiService;


    private final WasteManagerRepository wasteManagerRepository;

    @Override
    public GenericResponseDto<WasteManagerDTO> findById(Long id) throws IOException {
        WasteManager wasteManager = wasteManagerRepository.findById(id).orElseThrow(() -> new NotFoundException("WestManager not found"));
        WasteManagerAddress wasteManagerAddress = callApiService.getAddressById(wasteManager.getManagerAddressId());
        return new GenericResponseDto<>("WasteManager retrived", mapToWasteManagerDTO(wasteManagerAddress, wasteManager), "Status");
    }

    @Override
    @Transactional
    public GenericResponseDto<WasteManagerDTO> create(WasteManagerDTO wasteManagerDTO) throws IOException {
        WasteManagerAddress wasteManagerAddress = callApiService.createAddress(wasteManagerDTO.getWasteManagerAddress());
        WasteManager manager = WasteManager.builder()
                .name(wasteManagerDTO.getName())
                .nif(wasteManagerDTO.getNif())
                .version(wasteManagerDTO.getVersion())
                .isEnabled(wasteManagerDTO.getIsEnabled())
                .managerAddressId(wasteManagerAddress.getId())
                .build();
        List<WasteCenterAuthorization> wasteCenterAuthorizations = wasteManagerDTO.getWasteCenterAuthorizations()
                .stream()
                .map(s -> new WasteCenterAuthorization(s, manager))
                .toList();
        manager.setWasteCenterAuthorizations(wasteCenterAuthorizations);
        wasteManagerRepository.save(manager);
        return new GenericResponseDto<>("WasteManager created", mapToWasteManagerDTO(wasteManagerAddress, manager), "Status");
    }

    @Override
    public GenericResponseDto<WasteManagerDTO> update(Long id, WasteManagerDTO wasteManagerDTO) throws IOException {
        WasteManager manager = wasteManagerRepository.findById(id).orElseThrow(() -> new NotFoundException("WestManager not found"));
        WasteManagerAddress wasteManagerAddress = callApiService.updateAddress(manager.getManagerAddressId(), wasteManagerDTO.getWasteManagerAddress());
        manager.setName(wasteManagerDTO.getName());
        manager.setNif(wasteManagerDTO.getNif());
        manager.setVersion(wasteManagerDTO.getVersion());
        manager.setIsEnabled(wasteManagerDTO.getIsEnabled());
        wasteManagerRepository.save(manager);
        return new GenericResponseDto<>("WasteManager updated", mapToWasteManagerDTO(wasteManagerAddress, manager), "Status");
    }

    private WasteManagerDTO mapToWasteManagerDTO(WasteManagerAddress managerAddress, WasteManager manager) {
        return WasteManagerDTO.builder()
                .name(manager.getName())
                .nif(manager.getNif())
                .isEnabled(manager.getIsEnabled())
                .version(managerAddress.getVersion())
                .wasteCenterAuthorizations(manager
                        .getWasteCenterAuthorizations()
                        .stream()
                        .map(WasteCenterAuthorization::getAuthorizationNumber)
                        .collect(Collectors.toList()))
                .wasteManagerAddress(WasteManagerAddressDto.builder()
                        .address(managerAddress.getAddress())
                        .version(managerAddress.getVersion())
                        .isEnabled(managerAddress.getIsEnabled())
                        .build())
                .build();
    }

}
