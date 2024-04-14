package com.technical.test.wastemanager.api;

import com.technical.test.wastemanager.api.model.WasteManagerAddress;
import com.technical.test.wastemanager.dto.GenericResponseDTO;
import com.technical.test.wastemanager.dto.WasteManagerAddressDTO;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CallWasteManagerAddressService {

    @GET("manager-address")
    Call<GenericResponseDTO<List<WasteManagerAddress>>> getAllAddress();

    @GET("manager-address/{id}")
    Call<GenericResponseDTO<WasteManagerAddress>> getAddressById(@Path("id") Long id);

    @POST("manager-address")
    Call<GenericResponseDTO<WasteManagerAddress>> createAddress(@Body WasteManagerAddressDTO addressDTO);

    @PUT("manager-address/{id}")
    Call<GenericResponseDTO<WasteManagerAddress>> updateAddress(@Path("id") Long id, @Body WasteManagerAddressDTO addressDTO);

    @DELETE("manager-address")
    Call<GenericResponseDTO<WasteManagerAddress>> deleteAddress(@Path("id") Long id);
}
