package com.matches_simulator_app.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.matches_simulator_app.R;
import com.matches_simulator_app.data.MatchesApi;
import com.matches_simulator_app.databinding.ActivityMainBinding;
import com.matches_simulator_app.domain.Match;
import com.matches_simulator_app.ui.adapter.MatchesAdapter;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    private MatchesApi matchesApi;
    private MatchesAdapter matchesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupMatchsList();
        setupMatchsRefresh();
        setupMatchsFloatingActionButtom();


    }


    private void setupHttpClient() {
        //TODO Implementar a chamada retrofit

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jorgetranin.github.io/matches-simulator-api/ ")
                .addConverterFactory(GsonConverterFactory.create()).build();

        matchesApi = retrofit.create(MatchesApi.class);
        //----------------------------------------------------Metodo para gerar placaras aleatorios para as partidas-------------------------------------------------------
    }
    private void setupMatchsFloatingActionButtom() {
        //TODO: Criar evento de click de simulação de partidas
        binding.fabRandom.setOnClickListener(view -> {
            view.animate().rotationBy(360).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //TODO implementar a geração de placares

                    Random random = new Random();
                    for (int i = 0; i < matchesAdapter.getItemCount(); i++) {
                        Match match = matchesAdapter.getMatches().get(i);


                        int timeA = random.nextInt(match.getHomeTeam().getForce() + 1);
                        int timeB = random.nextInt(match.getAwayTeam().getForce() + 1);

                        match.getHomeTeam().setScore(timeA);
                        match.getAwayTeam().setScore(timeB);
                        // Notifica o adapter que Houve mudança no item nesta posição
                        matchesAdapter.notifyItemChanged(i);
                    }


                }

            });
        });


    }

    //--------------------------------------------------------------------Metodo para recarregar a lista de partidas-------------------------------------------------------
    private void setupMatchsRefresh() {
        //TODO: Fazer a atualização das partidas no arrastar do Swipe
        binding.swiperefreshlayout.setOnRefreshListener(this::findMatchesFromApi);
    }

    private void setupMatchsList() {
        //TODO: listar as partidas consumindo nossa API REST
        binding.rvJogos.setHasFixedSize(true);
        binding.rvJogos.setLayoutManager(new LinearLayoutManager(this));

        findMatchesFromApi();

    }

    //--------------------------------------------------------------------Metodo para consumir a API do GitHubPages-------------------------------------------------------
    private void findMatchesFromApi() {
        binding.swiperefreshlayout.setRefreshing(true);
        matchesApi.getMatch().enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful()) {
                    List<Match> matches = response.body();
                    matchesAdapter = new MatchesAdapter(matches);
                    binding.rvJogos.setAdapter(matchesAdapter);
                } else {
                    showErroMessage();
                }
                binding.swiperefreshlayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                showErroMessage();
                binding.swiperefreshlayout.setRefreshing(false);
            }
        });
    }

    //--------------------------------------------------------------------Metodo para mensagem de erro renérica--------------------------------------------------------------
    private void showErroMessage() {
        Toast.makeText(this, R.string.ErroAPI, Toast.LENGTH_LONG).show();
    }

}
