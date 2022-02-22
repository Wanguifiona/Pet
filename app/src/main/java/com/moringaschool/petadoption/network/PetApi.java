package com.moringaschool.petadoption.network;

import com.moringaschool.petadoption.models.PetResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PetApi {
    @GET("images/search?limit=20")
    Call<List<PetResponse>> getBreeds();
}
