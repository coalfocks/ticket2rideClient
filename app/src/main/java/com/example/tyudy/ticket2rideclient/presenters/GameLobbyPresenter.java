package com.example.tyudy.ticket2rideclient.presenters;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.activities.GameBoardActivity;
import com.example.tyudy.ticket2rideclient.activities.GameLobbyActivity;
import com.example.tyudy.ticket2rideclient.activities.PreGameActivity;
import com.example.tyudy.ticket2rideclient.fragments.GameSelectionFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by tyudy on 2/24/17.
 * Any changes you see on screen in the GameLobbyActivity should happen by going through this presenter.
 */

public class GameLobbyPresenter {
    private GameLobbyActivity mGameLobbyActivity;
    private int gameWasInProgress;

    public GameLobbyPresenter(){
        gameWasInProgress = 0;
    }

    // Called in the onCreate function in the GameLobbyActivity Class in the activities folder so that it can be updated.
    public void setGameLobbyActivity(GameLobbyActivity gameLobbyActivity) {
        mGameLobbyActivity = gameLobbyActivity;
    }

    public void startGameClicked() {
        if (ClientModel.SINGLETON.getCurrentTTRGame().getOwnerID() == ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
            if (ClientModel.SINGLETON.getCurrentTTRGame().getNumPlayers() >= 2) {
                if (ClientModel.SINGLETON.getCurrentTTRGame().getInProgress() == 0) {

                    MethodsFacade.SINGLETON.startGame();
                } else {
                    gameWasInProgress = 1;
                    FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
                    ((GameLobbyActivity) jeffery).onResumeGame();
                }
            } else {
                Toast.makeText(mGameLobbyActivity, "You can't play by yourself!", Toast.LENGTH_LONG).show();

            }
        } else if (ClientModel.SINGLETON.getCurrentTTRGame().getInProgress() == 1) {
            FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
            ((GameLobbyActivity) jeffery).onResumeGame();

        } else {
            Toast.makeText(mGameLobbyActivity, "Who do you think you are to start someone else's game?!", Toast.LENGTH_LONG).show();
        }
    }

    public int gameInProgress () {
        return this.gameWasInProgress;
    }
}