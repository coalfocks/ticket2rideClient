package com.example.tyudy.ticket2rideclient.model.states;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.exceptions.BadLogicException;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by Trevor on 3/15/2017.
 */

public class MyLastTurnBeganState implements IState {

    @Override
    public IState startGame() {
        return null;
    }

    @Override
    public IState claimPath() {
        return new EndGameState();
        //LastTurnNotMyTurnState();
    }

    @Override
    public IState drawDestinationCard() {
        return new EndGameState();
    }

    @Override
    public IState drawTrainCard() {

        return new MyLastTurnDrewOneTrainCardState();
    }

    @Override
    public IState changeTurn() {
        if (ClientModel.SINGLETON.getCurrentTTRGame().getInProgress() == 0){
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Game Over", Toast.LENGTH_SHORT).show();
            return new EndGameState();
        } else {
            return new LastTurnNotMyTurnState();
        }
    }

    @Override
    public IState lastTurn() {
        throw new BadLogicException("Last turn shouldn't be called if we are already in the last turn state");
    }
}