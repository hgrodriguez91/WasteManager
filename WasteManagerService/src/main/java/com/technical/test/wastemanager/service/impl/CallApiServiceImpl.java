package com.technical.test.wastemanager.service.impl;

import com.technical.test.wastemanager.api.CallWasteManagerAddressService;
import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.GenericResponseDTO;
import com.technical.test.wastemanager.dto.WasteManagerAddressDTO;
import com.technical.test.wastemanager.service.CallApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CallApiServiceImpl implements CallApiService {

    private final CallWasteManagerAddressService managerAddressService;

    @Override
    public WasteManagerAddress createAddress(WasteManagerAddressDTO managerAddressDto) throws IOException {

        Call<GenericResponseDTO<WasteManagerAddress>> addressCall = managerAddressService.createAddress(managerAddressDto);
        Response<GenericResponseDTO<WasteManagerAddress>> response = addressCall.execute();
        if (response.isSuccessful() && response.body() != null) {
            return response.body().getData();
        }
        throw new IOException("Algo paso aqui");
    }

    @Override
    public List<WasteManagerAddress> getAllAddress() throws IOException {
        Call<GenericResponseDTO<List<WasteManagerAddress>>> addressCall = managerAddressService.getAllAddress();
        Response<GenericResponseDTO<List<WasteManagerAddress>>> response = addressCall.execute();
        if (response.isSuccessful() && response.body() != null) {
            return response.body().getData();
        }
        throw new IOException("Algo paso aqui");
    }

    @Override
    public WasteManagerAddress getAddressById(Long id) throws IOException {
        Call<GenericResponseDTO<WasteManagerAddress>> addressCall = managerAddressService.getAddressById(id);
        Response<GenericResponseDTO<WasteManagerAddress>> response = addressCall.execute();
        if (response.isSuccessful() && response.body() != null) {
            return response.body().getData();
        }
        throw new IOException("Algo paso aqui");
    }

    @Override
    public WasteManagerAddress updateAddress(Long id, WasteManagerAddressDTO managerAddressDto) throws IOException {
        Call<GenericResponseDTO<WasteManagerAddress>> addressCall = managerAddressService.updateAddress(id, managerAddressDto);
        Response<GenericResponseDTO<WasteManagerAddress>> response = addressCall.execute();
        if (response.isSuccessful() && response.body() != null) {
            return response.body().getData();
        }
        throw new IOException("Algo paso aqui");
    }

    @Override
    public Boolean deleteAddress(Long id) throws IOException {
        Call<GenericResponseDTO<WasteManagerAddress>> addressCall = managerAddressService.deleteAddress(id);
        Response<GenericResponseDTO<WasteManagerAddress>> response = addressCall.execute();
        return response.isSuccessful();
    }
}
