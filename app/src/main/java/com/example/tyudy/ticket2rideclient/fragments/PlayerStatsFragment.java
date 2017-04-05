package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.UserStats;

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
    private UserStats mPlayer;

    public void setUserStats(UserStats player){
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

        mPlayerName.setText(mPlayer.getName());
        mRoutePoints.setText(mPlayer.getRoutePoints());
        mCompletedDestPoints.setText(mPlayer.getDestPoints());
        mNotCompletedDest.setText(mPlayer.getNegDestPoints());
        mLongestRoutePoints.setText(mPlayer.getLongestRoutePoints());
        mTotalPoints.setText(mPlayer.getTotalPoints());

        return v;
    }
}
