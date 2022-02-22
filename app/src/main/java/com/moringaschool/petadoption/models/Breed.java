
package com.moringaschool.petadoption.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Breed implements Serializable {

    @SerializedName("weight")
    @Expose
    private Weight weight;
    @SerializedName("height")
    @Expose
    private Height height;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("breed_group")
    @Expose
    private String breedGroup;
    @SerializedName("life_span")
    @Expose
    private String lifeSpan;
    @SerializedName("temperament")
    @Expose
    private String temperament;
    @SerializedName("reference_image_id")
    @Expose
    private String referenceImageId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Breed() {
    }

    /**
     * 
     * @param lifeSpan
     * @param temperament
     * @param name
     * @param weight
     * @param id
     * @param referenceImageId
     * @param breedGroup
     * @param height
     */
    public Breed(Weight weight, Height height, Integer id, String name, String breedGroup, String lifeSpan, String temperament, String referenceImageId) {
        super();
        this.weight = weight;
        this.height = height;
        this.id = id;
        this.name = name;
        this.breedGroup = breedGroup;
        this.lifeSpan = lifeSpan;
        this.temperament = temperament;
        this.referenceImageId = referenceImageId;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getReferenceImageId() {
        return referenceImageId;
    }

    public void setReferenceImageId(String referenceImageId) {
        this.referenceImageId = referenceImageId;
    }

}
