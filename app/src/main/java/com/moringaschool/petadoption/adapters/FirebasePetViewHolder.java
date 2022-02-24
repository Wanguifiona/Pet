package com.moringaschool.petadoption.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.petadoption.Constants;
import com.moringaschool.petadoption.R;
import com.moringaschool.petadoption.models.Breed;
import com.moringaschool.petadoption.models.PetResponse;
import com.moringaschool.petadoption.ui.PetDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebasePetViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebasePetViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPet(PetResponse pets) {
        ImageView petImageView = (ImageView) mView.findViewById(R.id.petImageView);
        TextView breedNameTextView = (TextView) mView.findViewById(R.id.breedNameTextView);
        TextView breedGroupTextView = (TextView) mView.findViewById(R.id.breedGroupTextView);


        Picasso.get().load(pets.getUrl()).into(petImageView);

        breedNameTextView.setText(pets.getBreeds().get(0).getName());
        breedGroupTextView.setText(pets.getBreeds().get(0).getBreedGroup());

    }

    @Override
    public void onClick(View view) {
        final ArrayList<PetResponse> pets = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ADOPTION);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    pets.add(snapshot.getValue(PetResponse.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, PetDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("restaurants", Parcels.wrap(pets));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}