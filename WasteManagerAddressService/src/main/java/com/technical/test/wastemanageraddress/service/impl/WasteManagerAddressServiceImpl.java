package com.technical.test.wastemanageraddress.service.impl;

import com.technical.test.wastemanageraddress.dto.GenericResponseDto;
import com.technical.test.wastemanageraddress.dto.WasteManagerAddressDto;
import com.technical.test.wastemanageraddress.exception.NotFoundException;
import com.technical.test.wastemanageraddress.model.WasteManagerAddress;
import com.technical.test.wastemanageraddress.repository.WasteManagerAddressRepository;
import com.technical.test.wastemanageraddress.service.WasteManagerAddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WasteManagerAddressServiceImpl implements WasteManagerAddressService {

    private final WasteManagerAddressRepository addressRepository;
    private final ModelMapper modelMapper;

    private static final String STATUS_SUCCES = "success";

    @Override
    public GenericResponseDto<List<WasteManagerAddress>> getAll() {
        return new GenericResponseDto<>("All WasteManagerAddress", addressRepository.findAll(), STATUS_SUCCES);
    }

    @Override
    public GenericResponseDto<WasteManagerAddress> getById(Long id) {
        WasteManagerAddress managerAddress = addressRepository
                .findWasteManagerAddressById(id).orElseThrow(() -> new NotFoundException("The requested address dont exist in the database"));
        return new GenericResponseDto<>("Listed Success", managerAddress, STATUS_SUCCES);
    }

    @Override
    public GenericResponseDto<WasteManagerAddress> create(WasteManagerAddressDto wasteManagerAddressDTO) {
        WasteManagerAddress managerAddress = modelMapper.map(wasteManagerAddressDTO, WasteManagerAddress.class);
        managerAddress.setCreatedDate(Date.from(Instant.now()));
        managerAddress.setLastModifiedDate(Date.from(Instant.now()));
        return new GenericResponseDto<>("WasteManagerAddress created", addressRepository.save(managerAddress), STATUS_SUCCES);
    }

    @Override
    public GenericResponseDto<WasteManagerAddress> edit(Long id, WasteManagerAddressDto wasteManagerAddressDto) {
        WasteManagerAddress managerAddress = addressRepository.findWasteManagerAddressById(id)
                .orElseThrow(() -> new NotFoundException("The requested address dont exist in the database"));
        managerAddress.setAddress(wasteManagerAddressDto.getAddress());
        managerAddress.setIsEnabled(wasteManagerAddressDto.getIsEnabled());
        managerAddress.setVersion(wasteManagerAddressDto.getVersion());
        managerAddress.setLastModifiedDate(Date.from(Instant.now()));
        return new GenericResponseDto<>("Address edited", addressRepository.save(managerAddress), STATUS_SUCCES);

    }

    @Override
    public GenericResponseDto<String> delete(Long id) {
        WasteManagerAddress managerAddress = addressRepository.findWasteManagerAddressById(id)
                .orElseThrow(() -> new NotFoundException("The requested address dont exist in the database"));
        addressRepository.delete(managerAddress);
        return new GenericResponseDto<>("Address deleted", null, STATUS_SUCCES);

    }
}
