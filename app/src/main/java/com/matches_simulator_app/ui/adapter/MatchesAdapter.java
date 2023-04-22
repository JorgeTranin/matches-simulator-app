package com.matches_simulator_app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.matches_simulator_app.databinding.ItemJogosBinding;
import com.matches_simulator_app.domain.Match;
import com.matches_simulator_app.ui.DetailActivity;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemJogosBinding binding = ItemJogosBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matches.get(position);
        Context context = holder.itemView.getContext();

        //SetNomes dos Times
        holder.binding.nameTeamA.setText(match.getHomeTeam().getName());
        holder.binding.nameTeamB.setText(match.getAwayTeam().getName());

        //Set Imagem Times
        ImageView imageTeamA = (ImageView) holder.binding.imageTeamA;
        Glide.with(context).load(match.getHomeTeam().getImage()).into(imageTeamA);

        ImageView imageTeamB = (ImageView) holder.binding.imageTeamB;
        Glide.with(context).load(match.getAwayTeam().getImage()).into(imageTeamB);

        //Set Score dos Times
        if (match.getHomeTeam().getScore() != -1) {
            holder.binding.scoreTeamA.setText(String.valueOf(match.getHomeTeam().getScore()));
        }
        if (match.getAwayTeam().getScore() != -1) {
            holder.binding.scoreTeamB.setText(String.valueOf(match.getAwayTeam().getScore()));
        }
        holder.itemView.setOnClickListener(View -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.Extras.MATCH, match);
            context.startActivity(intent);

        });

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
