package com.moringaschool.petadoption.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.petadoption.Constants;
import com.moringaschool.petadoption.R;
import com.moringaschool.petadoption.models.Breed;
import com.moringaschool.petadoption.models.PetResponse;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PetDetailFragment extends Fragment implements View.OnClickListener{
    PetResponse mPets;
    @BindView(R.id.petNameTextView)
    TextView mPetNameTextView;
    @BindView(R.id.petImageView)
    ImageView mPetImageView;
    @BindView(R.id.weightTextView)
    TextView mWeightTextView;
    @BindView(R.id.heightTextView)
    TextView mHeightTextView;
    @BindView(R.id.idTextView)
    TextView mIdTextView;
    @BindView(R.id.breedTextView)
    TextView mBreedTextView;
    @BindView(R.id.lifespanTextView)
    TextView mLifespanTextView;
    @BindView(R.id.temperamentTextView)
    TextView mTemperamentTextView;
    @BindView(R.id.savePetButton)
    Button mSavePetButton;
    @BindView(R.id.savedPetsButton) Button mSavedPetsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        ButterKnife.bind(this, view);
        mPetNameTextView.setText(mPets.getBreeds().get(0).getName());
        Picasso.get().load(mPets.getUrl()).into(mPetImageView);
        mWeightTextView.setText(mPets.getBreeds().get(0).getWeight().getMetric());
        mHeightTextView.setText(mPets.getBreeds().get(0).getHeight().getMetric());
        mIdTextView.setText(mPets.getId());
        mBreedTextView.setText(mPets.getBreeds().get(0).getBreedGroup());
        mLifespanTextView.setText(mPets.getBreeds().get(0).getLifeSpan());
        mTemperamentTextView.setText(mPets.getBreeds().get(0).getTemperament());

        mSavePetButton.setOnClickListener(this);
        mSavedPetsButton.setOnClickListener(this);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mPets = (PetResponse) bundle.getSerializable("Pet");

    }

    @Override
    public void onClick(View v) {
        if (v == mSavePetButton) {
            DatabaseReference petRef = FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child(Constants.FIREBASE_CHILD_ADOPTION);
            petRef.push().setValue(mPets);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        if (v == mSavedPetsButton) {
            Intent intent = new Intent(getContext(), SavedPetListActivity.class);
            startActivity(intent);
        }
    }
}