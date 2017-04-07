package com.example.tyudy.ticket2rideclient.model.states;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
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
        return new LastTurnNotMyTurnState();
    }

    @Override
    public IState drawDestinationCard() {
        return null;
    }

    @Override
    public IState drawTrainCard() {

        return new MyLastTurnDrewOneTrainCardState();
    }

    @Override
    public IState changeTurn() {
       return new LastTurnNotMyTurnState();
    }

    @Override
    public IState lastTurn() {
        if(ClientModel.SINGLETON.getCurrentUser().getPlayerID() == ClientModel.SINGLETON.getCurrentTTRGame().getWhoTurn()) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "It is your last turn!", Toast.LENGTH_SHORT).show();
            return new MyLastTurnBeganState();
        } else {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Turn Changed!", Toast.LENGTH_SHORT).show();
            return new LastTurnNotMyTurnState();
        }
    }
}