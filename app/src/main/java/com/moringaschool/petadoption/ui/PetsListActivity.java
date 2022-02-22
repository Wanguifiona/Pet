package com.moringaschool.petadoption.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.petadoption.R;
import com.moringaschool.petadoption.adapters.PetListAdapter;
import com.moringaschool.petadoption.models.Breed;
import com.moringaschool.petadoption.models.PetResponse;
import com.moringaschool.petadoption.network.PetApi;
import com.moringaschool.petadoption.network.PetClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetsListActivity extends AppCompatActivity {
    private static final String TAG = PetsListActivity.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    private PetListAdapter mAdapter;
    public List<Breed> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

        pets = new ArrayList<>();

        Intent intent = getIntent();
        String pets = intent.getStringExtra("pets");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        PetApi client = PetClient.getClient();
        Call<List<PetResponse>> call = client.getBreeds();

        call.enqueue(new Callback<List<PetResponse>>() {
            @Override
            public void onResponse(Call<List<PetResponse>> call, Response<List<PetResponse>> response) {
                hideProgressBar();
                Log.e(TAG, response.raw().toString());
                if(response.isSuccessful()){
                    List<PetResponse> allPets = response.body();
                    Toast.makeText(PetsListActivity.this, "success " + response.body().get(0).getBreeds().get(0).getName(), Toast.LENGTH_SHORT).show() ;
                    ArrayList<PetResponse> allPetsWithBreeds = new ArrayList<>();
                    for(PetResponse mPets : allPets) {
                        if (mPets.getBreeds().size() > 0){
                            allPetsWithBreeds.add(mPets);
                        }
                    }
                    PetListAdapter adapter = new PetListAdapter(PetsListActivity.this, allPetsWithBreeds );
                    mRecyclerView.setAdapter(adapter);

                    showPets();
                }
                else {
                    showUnsuccessfulMessage();
                }

            }

            @Override
            public void onFailure(Call<List<PetResponse>> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }
        });
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showPets() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


}