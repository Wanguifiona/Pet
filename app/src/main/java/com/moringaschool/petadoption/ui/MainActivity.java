package com.moringaschool.petadoption.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.petadoption.Constants;
import com.moringaschool.petadoption.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private ValueEventListener mSearchedPetsReferenceListener;
//    private DatabaseReference mSearchedPetsReference;

    @BindView(R.id.petButton) Button mPetButton;
//    @BindView(R.id.petsEditText) EditText mPetsEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        FirebaseApp.initializeApp(this);
//        mSearchedPetsReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_PET);
//        mSearchedPetsReferenceListener = mSearchedPetsReference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot petsSnapshot : dataSnapshot.getChildren()) {
//                    String pets = petsSnapshot.getValue().toString();
//                    Log.d("Pets updated", "pets: " + pets);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//



        mPetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mPetButton) {
//            String pets = mPetsEditText.getText().toString();
//            savePetsToFirebase(pets);
            Intent intent = new Intent(MainActivity.this, PetsListActivity.class);
//            intent.putExtra("pets", pets);
            startActivity(intent);


        }
    }
//    public void savePetsToFirebase(String pets) {
//        mSearchedPetsReference.setValue(pets);
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedPetsReference.removeEventListener(mSearchedPetsReferenceListener);
//    }


}

