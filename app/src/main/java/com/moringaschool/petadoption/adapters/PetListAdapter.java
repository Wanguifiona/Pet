package com.moringaschool.petadoption.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.petadoption.R;
import com.moringaschool.petadoption.models.Breed;
import com.moringaschool.petadoption.models.PetResponse;
import com.moringaschool.petadoption.ui.PetDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetViewHolder> {
    private List<PetResponse> mPets;
    private Context mContext;

    public PetListAdapter(Context context, List<PetResponse> pets) {
        mContext = context;
        mPets = pets;
    }

    @Override
    public PetListAdapter.PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item, parent, false);
        PetViewHolder viewHolder = new PetViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PetListAdapter.PetViewHolder holder, int position) {
        holder.bindPet(mPets.get(position));
    }

    @Override
    public int getItemCount() {
        return mPets.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.petImageView) ImageView mPetImageView;
        @BindView(R.id.breedNameTextView) TextView mBreedNameTextView;
        @BindView(R.id.breedGroupTextView) TextView mBreedGroupTextView;
        private Context mContext;

        public PetViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(mContext, PetDetailActivity.class);
                    intent.putExtra("Pet", mPets.get(itemPosition));
                    mContext.startActivity(intent);


                }
            });

        }
        public void bindPet(PetResponse pet) {
                mBreedNameTextView.setText(pet.getBreeds().get(0).getName());
                mBreedGroupTextView.setText(pet.getBreeds().get(0).getBreedGroup());
                Picasso.get().load(pet.getUrl()).into(mPetImageView);
            }




    }

    }