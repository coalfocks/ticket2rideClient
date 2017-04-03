package com.example.tyudy.ticket2rideclient.presenters;

import android.support.v4.app.FragmentActivity;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.activities.GameLobbyActivity;
import com.example.tyudy.ticket2rideclient.fragments.GameOverFragment;

/**
 * Created by Trevor on 4/3/2017.
 */

public class GameOverPresenter {
    private GameOverFragment mGameOverFragment;

    public GameOverPresenter(GameOverFragment gameOverFragment) {
        mGameOverFragment = gameOverFragment;
    }

    public void doneButtonClicked() {
        // TODO: remove players from game in database

        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();

    }
}
