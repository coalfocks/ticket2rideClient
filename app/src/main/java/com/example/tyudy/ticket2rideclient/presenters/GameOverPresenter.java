package com.example.tyudy.ticket2rideclient.presenters;

import android.support.v4.app.FragmentActivity;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.activities.PreGameActivity;
import com.example.tyudy.ticket2rideclient.common.UserStats;
import com.example.tyudy.ticket2rideclient.fragments.GameBoardFragment;
import com.example.tyudy.ticket2rideclient.fragments.GameOverFragment;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Trevor on 4/3/2017.
 */

public class GameOverPresenter {
    private GameOverFragment mGameOverFragment;
    private String gameWinner;
    private ArrayList<UserStats> gameStats;

    public GameOverPresenter () {}

    public GameOverPresenter(GameOverFragment gameOverFragment) {
        mGameOverFragment = gameOverFragment;
    }

    public void setGameOverFragment (GameOverFragment fragment) {
        this.mGameOverFragment = fragment;
    }

    // Returns user to game selection area
    public void doneButtonClicked() {
        // TODO: remove players from game in database (should be server side)
        int notInGame = 0;

        MethodsFacade.SINGLETON.setContext(new PreGameActivity());
        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
        ((PreGameActivity) jeffery).onLogin(notInGame);
    }

    public void setWinner(String name) {
        this.gameWinner = name;
    }

    public String getWinner() {
        return this.gameWinner;
    }

    public ArrayList<UserStats> getGameStats() {
        return gameStats;
    }

    public void setGameStats(ArrayList<UserStats> gameStats) {
        this.gameStats = gameStats;
    }
}