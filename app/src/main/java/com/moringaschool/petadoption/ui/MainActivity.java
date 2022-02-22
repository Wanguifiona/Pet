package com.moringaschool.petadoption.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moringaschool.petadoption.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.petButton)
    Button mPetButton;
    @BindView(R.id.petsEditText)
    EditText mPetsEditText;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mPetButton) {
            String pets = mPetsEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, PetsListActivity.class);
            intent.putExtra("pets", pets);
            startActivity(intent);


        }
    }
}

