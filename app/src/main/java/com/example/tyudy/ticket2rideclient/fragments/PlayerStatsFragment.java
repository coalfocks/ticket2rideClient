package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.User;

/**
 * Created by Trevor on 4/3/2017.
 */

public class PlayerStatsFragment extends Fragment {
    private TextView mPlayerName;
    private TextView mTotalPoints;
    private TextView mRoutePoints;
    private TextView mCompletedDestPoints;
    private TextView mLongestRoutePoints;
    private TextView mNotCompletedDest;
    private User mPlayer;

    // TODO: this should ultimately be not a User object but the end-game object
    public void setAssociatedPlayer(User player){
        mPlayer = player;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_over_player_stats, container, false);

        mPlayerName = (TextView) v.findViewById(R.id.player_stats_username);
        mRoutePoints = (TextView) v.findViewById(R.id.player_stats_route_points);
        mCompletedDestPoints = (TextView) v.findViewById(R.id.player_stats_completed_dest);
        mNotCompletedDest = (TextView) v.findViewById(R.id.player_stats_not_completed_dest);
        mLongestRoutePoints = (TextView) v.findViewById(R.id.player_stats_longest_route);
        mTotalPoints = (TextView) v.findViewById(R.id.player_stats_total_points);

        //

        return v;
    }
}
