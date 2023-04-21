package com.matches_simulator_app.domain;

import com.google.gson.annotations.SerializedName;

public class Place {
    // Usando o SerializedName, passamos o nome que o campo tem na nossa API
    @SerializedName("nome")
    private String name;
    @SerializedName("imagem")
    private String image;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
