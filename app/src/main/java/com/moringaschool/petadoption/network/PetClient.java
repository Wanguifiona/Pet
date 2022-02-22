package com.moringaschool.petadoption.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PetClient {
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://api.thedogapi.com/v1/";
    public static PetApi getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(PetApi.class);
    }
}
