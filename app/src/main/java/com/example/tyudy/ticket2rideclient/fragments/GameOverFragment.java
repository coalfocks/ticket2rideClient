package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.UserStats;
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

        // TEST ----------
        ArrayList<UserStats> stats = new ArrayList<>();
        UserStats p1 = new UserStats("Bob", 50, 14, -12, 0);
        UserStats p2 = new UserStats("Charlie", 49, 13, 0, 0);
        UserStats p3 = new UserStats("Poop", 67, 15, -8, 10);
        stats.add(p1);
        stats.add(p2);
        stats.add(p3);
        // ---------------

        // TODO: get user stats from server here
        //ArrayList<User> players = new ArrayList<>(ClientModel.SINGLETON.getCurrentTTRGame().getUsers());
        PlayerInfoAdapter adapter = new PlayerInfoAdapter(stats);
        mPlayerList.setAdapter(adapter);
        mPlayerList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameOverPresenter.doneButtonClicked();
            }
        });

        return v;
    }


    private class PlayerInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mPlayerName;
        private TextView mPlayerPoints;
        private TextView mPlayerTrainCards;
        private TextView mPlayerDestCards;
        private UserStats mPlayer;
        private boolean mStatsVisible = false;

        public PlayerInfoHolder(View itemView) {
            super(itemView);

            mPlayerName = (TextView) itemView.findViewById(R.id.player_username);
            mPlayerPoints = (TextView) itemView.findViewById(R.id.player_points);
            mPlayerTrainCards = (TextView) itemView.findViewById(R.id.train_cards);
            mPlayerDestCards = (TextView) itemView.findViewById(R.id.dest_cards);

            mPlayerTrainCards.setVisibility(View.INVISIBLE);
            mPlayerDestCards.setVisibility(View.INVISIBLE);
        }

        public void bindHolder(UserStats player) {
            mPlayer = player;
            mPlayerName.setText(player.getName());
            mPlayerPoints.setText(String.valueOf(player.getTotalPoints()));
        }

        @Override
        public void onClick(View v) {
            if (!mStatsVisible)
            {
                mStatsVisible = true;
                PlayerStatsFragment stats = new PlayerStatsFragment();
                stats.setUserStats(mPlayer);

                getChildFragmentManager().beginTransaction().add(R.id.points_fragment_expand,
                    stats).commit();
            }
            else
            {
                mStatsVisible = false;
                getChildFragmentManager().beginTransaction().replace(R.id.points_fragment_expand,
                        null).commit();
            }
        }
    }

    private class PlayerInfoAdapter extends RecyclerView.Adapter<PlayerInfoHolder> {
        private ArrayList<UserStats> mPlayers;

        public PlayerInfoAdapter(ArrayList<UserStats> players) {
            mPlayers = players;
        }

        @Override
        public void onBindViewHolder(PlayerInfoHolder holder, int position) {
            UserStats player = mPlayers.get(position);
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
