package com.matches_simulator_app.domain

import com.google.gson.annotations.SerializedName

data class Match(
    // Usando o SerializedName, passamos o nome que o campo tem na nossa API
    @SerializedName("descricao")
    val description: String,
    @SerializedName("local")
    val place: Place,
    @SerializedName("mandante")
    val homeTeam: Team,
    @SerializedName("visitante")
    val awayTeam: Team
)
