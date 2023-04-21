package com.matches_simulator_app.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matches_simulator_app.databinding.ItemJogosBinding;
import com.matches_simulator_app.domain.Match;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    private List<Match> matches;

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemJogosBinding binding = ItemJogosBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matches.get(position);

        //SetNomes dos Times
        holder.binding.nameTeamA.setText(match.getHomeTeam().getName());
        holder.binding.nameTeamB.setText(match.getAwayTeam().getName());

        //Set Imagem Times

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemJogosBinding binding;

        public ViewHolder(ItemJogosBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
