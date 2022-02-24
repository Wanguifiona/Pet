package com.moringaschool.petadoption.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.petadoption.Constants;
import com.moringaschool.petadoption.R;
import com.moringaschool.petadoption.adapters.FirebasePetViewHolder;
import com.moringaschool.petadoption.models.PetResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedPetListActivity extends AppCompatActivity {

    private DatabaseReference mPetReference;
    private FirebaseRecyclerAdapter<PetResponse, FirebasePetViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

        mPetReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ADOPTION);
        setUpFirebaseAdapter();
        hideProgressBar();
        showPets();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<PetResponse> options =
                new FirebaseRecyclerOptions.Builder<PetResponse>()
                        .setQuery(mPetReference, PetResponse.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<PetResponse, FirebasePetViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebasePetViewHolder firebasePetViewHolder, int position, @NonNull PetResponse pets) {
                firebasePetViewHolder.bindPet(pets);
            }

            @NonNull
            @Override
            public FirebasePetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item, parent, false);
                return new FirebasePetViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showPets() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}