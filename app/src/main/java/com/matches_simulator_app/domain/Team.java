package com.matches_simulator_app.domain;

import com.google.gson.annotations.SerializedName;

public class Team {
    // Usando o SerializedName, passamos o nome que o campo tem na nossa API
    @SerializedName("nome")
    private String name;
    @SerializedName("forca")
    private int force;
    @SerializedName("imagem")
    private String image;

    private int Score;

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
