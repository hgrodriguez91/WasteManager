package com.technical.test.wastemanager.config;

import com.technical.test.wastemanager.api.CallWasteManagerAddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class ApiCallConfig {

    @Value("${application.url.waste_manager_address_service}")
    private String baseUrlManagerAddressService;

    @Bean
    CallWasteManagerAddressService managerAddressApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlManagerAddressService)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(CallWasteManagerAddressService.class);
    }
}
