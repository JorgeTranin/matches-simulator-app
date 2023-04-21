package com.matches_simulator_app.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.matches_simulator_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupMatchsList();
        setupMatchsRefresh();
        setupMatchsFloatingActionButtom();


    }

    private void setupMatchsFloatingActionButtom() {
        //TODO: Criar evento de click de simulação de partidas
    }

    private void setupMatchsRefresh() {
        //TODO: Fazer a atualização das partidas no arrastar do Swipe
    }

    private void setupMatchsList() {
        //TODO: listar as partidas consumindo nossa API REST
    }
}
