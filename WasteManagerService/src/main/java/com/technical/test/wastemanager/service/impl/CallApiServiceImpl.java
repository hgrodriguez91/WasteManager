package com.technical.test.wastemanager.service.impl;

import com.technical.test.wastemanager.api.CallWasteManagerAddressService;
import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.WasteManagerAddressDto;
import com.technical.test.wastemanager.service.CallApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CallApiServiceImpl implements CallApiService {

    private final CallWasteManagerAddressService managerAddressService;

    @Override
    public WasteManagerAddress createAddress(WasteManagerAddressDto managerAddressDto) throws IOException {

        Call<WasteManagerAddress> addressCall = managerAddressService.createAddress(managerAddressDto);
        Response<WasteManagerAddress> response = addressCall.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        throw new IOException("Algo paso aqui");
    }

    @Override
    public List<WasteManagerAddress> getAllAddress() throws IOException {
        Call<List<WasteManagerAddress>> addressCall = managerAddressService.getAllAddress();
        Response<List<WasteManagerAddress>> response = addressCall.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        throw new IOException("Algo paso aqui");
    }

    @Override
    public WasteManagerAddress getAddressById(Long id) throws IOException {
        Call<WasteManagerAddress> addressCall = managerAddressService.getAddressById(id);
        Response<WasteManagerAddress> response = addressCall.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        throw new IOException("Algo paso aqui");
    }

    @Override
    public WasteManagerAddress updateAddress(Long id, WasteManagerAddressDto managerAddressDto) throws IOException {
        Call<WasteManagerAddress> addressCall = managerAddressService.updateAddress(id, managerAddressDto);
        Response<WasteManagerAddress> response = addressCall.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        throw new IOException("Algo paso aqui");
    }

    @Override
    public Boolean deleteAddress(Long id) throws IOException {
        Call<WasteManagerAddress> addressCall = managerAddressService.deleteAddress(id);
        Response<WasteManagerAddress> response = addressCall.execute();
        return response.isSuccessful();
    }
}
