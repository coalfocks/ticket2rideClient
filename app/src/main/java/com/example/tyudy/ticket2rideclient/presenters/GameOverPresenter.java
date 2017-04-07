package com.example.tyudy.ticket2rideclient.presenters;

import android.support.v4.app.FragmentActivity;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.activities.PreGameActivity;
import com.example.tyudy.ticket2rideclient.fragments.GameOverFragment;

/**
 * Created by Trevor on 4/3/2017.
 */

public class GameOverPresenter {
    private GameOverFragment mGameOverFragment;

    public GameOverPresenter(GameOverFragment gameOverFragment) {
        mGameOverFragment = gameOverFragment;
    }

    // Returns user to game selection area
    public void doneButtonClicked() {
        // TODO: remove players from game in database (should be server side)
        int notInGame = 0;

        MethodsFacade.SINGLETON.setContext(new PreGameActivity());
        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
        ((PreGameActivity) jeffery).onLogin(notInGame);
    }
}