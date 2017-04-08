package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
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

public class PlayerStatsDialog extends DialogFragment {
    private TextView mPlayerName;
    private TextView mTotalPoints;
    private TextView mRoutePoints;
    private TextView mCompletedDestPoints;
    private TextView mLongestRoutePoints;
    private TextView mNotCompletedDest;
    private UserStats mPlayer;
    private Activity mActivity;

    public void setUserStats(UserStats player){
        mPlayer = player;
    }

    public void setActivity(Activity a) { mActivity = a; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        View v = mActivity.getLayoutInflater().inflate(R.layout.game_over_player_stats, null);

        builder.setTitle(R.string.stats).
                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel(); // Close the dialog
                    }
                }).
                setCancelable(false).
                setView(v);

        return builder.create();
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
        mRoutePoints.setText(String.valueOf(mPlayer.getRoutePoints()));
        mCompletedDestPoints.setText(String.valueOf(mPlayer.getDestPoints()));
        mNotCompletedDest.setText(String.valueOf(mPlayer.getNegDestPoints()));
        mLongestRoutePoints.setText(String.valueOf(mPlayer.getLongestRoutePoints()));
        mTotalPoints.setText(String.valueOf(mPlayer.getTotalPoints()));

        return v;
    }
}