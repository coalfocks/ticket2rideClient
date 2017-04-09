package com.example.tyudy.ticket2rideclient.model.states;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.exceptions.BadLogicException;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by tyudy on 4/6/17.
 */

public class LastTurnNotMyTurnState implements IState {

    @Override
    public IState startGame() {
        return this;
    }

    @Override
    public IState claimPath() {
        return this;
    }

    @Override
    public IState drawDestinationCard() {
        return this;
    }

    @Override
    public IState drawTrainCard() {
        return this;
    }

    /**
     * Returns the new state of the player to the next turn
     * @return - If it is the players turn, MyLastTurnBeganState, else LastTurnNotMyTurnState
     */
    @Override
    public IState changeTurn() {
        if(ClientModel.SINGLETON.getCurrentUser().getPlayerID() == ClientModel.SINGLETON.getCurrentTTRGame().getWhoTurn()) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "It is your last turn!", Toast.LENGTH_SHORT).show();
            return new MyLastTurnBeganState();
        } else {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Turn Changed!", Toast.LENGTH_SHORT).show();
            return new LastTurnNotMyTurnState();
        }
    }

    @Override
    public IState lastTurn() {
        throw new BadLogicException("Last turn shouldn't be called if we are already in the last turn state");
    }
}
