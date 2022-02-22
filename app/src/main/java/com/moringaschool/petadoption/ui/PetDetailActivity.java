package com.moringaschool.petadoption.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.moringaschool.petadoption.R;
import com.moringaschool.petadoption.models.Breed;
import com.moringaschool.petadoption.models.PetResponse;

import java.io.Serializable;

import butterknife.ButterKnife;

public class PetDetailActivity extends AppCompatActivity {
    PetResponse mPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mPets =(PetResponse) intent.getSerializableExtra("Pet");
        int startingPosition = getIntent().getIntExtra("position", 0);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Pet", mPets);
        Fragment fragment = new PetDetailFragment();
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment, "Pet")
                .commit();

    }
}