package com.matches_simulator_app.domain

data class Match(
    val description: String,
    val place: Place,
    val homeTeam: Team,
    val awayTeam: Team
)
