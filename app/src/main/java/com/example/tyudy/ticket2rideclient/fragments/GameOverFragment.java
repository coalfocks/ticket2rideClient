package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.GameOverPresenter;

import java.util.ArrayList;

/**
 * Created by Trevor on 4/3/2017.
 */

public class GameOverFragment extends Fragment {
    private Button mDoneButton;
    private TextView mWinnerName;
    private RecyclerView mPlayerList;
    private GameOverPresenter mGameOverPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_over_fragment, container, false);

        mWinnerName = (TextView) v.findViewById(R.id.winner_name);
        mPlayerList = (RecyclerView) v.findViewById(R.id.game_over_recycler_view);
        mDoneButton = (Button) v.findViewById(R.id.done_button);
        mGameOverPresenter = new GameOverPresenter(this);

        ArrayList<User> players = new ArrayList<>(ClientModel.SINGLETON.getCurrentTTRGame().getUsers());
        PlayerInfoAdapter adapter = new PlayerInfoAdapter(players);
        mPlayerList.setAdapter(adapter);

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameOverPresenter.doneButtonClicked();
            }
        });

        return v;
    }

    private void setWinnerName(){

    }


    private class PlayerInfoHolder extends RecyclerView.ViewHolder {
        private TextView mPlayerName;
        private TextView mPlayerPoints;
        private TextView mPlayerTrainCards;
        private TextView mPlayerDestCards;

        public PlayerInfoHolder(View itemView) {
            super(itemView);

            mPlayerName = (TextView) itemView.findViewById(R.id.player_username);
            mPlayerPoints = (TextView) itemView.findViewById(R.id.player_points);
            mPlayerTrainCards = (TextView) itemView.findViewById(R.id.train_cards);
            mPlayerDestCards = (TextView) itemView.findViewById(R.id.dest_cards);

            mPlayerTrainCards.setVisibility(View.INVISIBLE);
            mPlayerDestCards.setVisibility(View.INVISIBLE);
        }

        public void bindHolder(User player) {
            mPlayerName.setText(player.getUsername());
            mPlayerPoints.setText(player.getPoints());
        }
    }

    private class PlayerInfoAdapter extends RecyclerView.Adapter<PlayerInfoHolder> {
        private ArrayList<User> mPlayers;

        public PlayerInfoAdapter(ArrayList<User> players) {
            mPlayers = players;
        }

        @Override
        public void onBindViewHolder(PlayerInfoHolder holder, int position) {
            User player = mPlayers.get(position);
            holder.bindHolder(player);
        }

        @Override
        public PlayerInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.points_fragment, parent, false);
            return new PlayerInfoHolder(view);
        }

        @Override
        public int getItemCount() {
            return mPlayers.size();
        }
    }
}
