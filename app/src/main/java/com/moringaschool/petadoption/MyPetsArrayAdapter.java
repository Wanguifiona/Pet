package com.moringaschool.petadoption;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyPetsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mPets;
    private String[] mSpecies;

    public MyPetsArrayAdapter(Context mContext, int resource, String[] mPets, String[] mSpecies) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mPets = mPets;
        this.mSpecies = mSpecies;
    }
    @Override
    public Object getItem(int position) {
        String pets = mPets[position];
        String species = mSpecies[position];
        return String.format("%s \nAvailable are: %s", pets, species);
    }

    @Override
    public int getCount() {
        return mPets.length;
    }
}

