package com.example.tyudy.ticket2rideclient.model.states;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.exceptions.BadLogicException;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by colefox on 3/29/17.
 */

public class MyLastTurnDrewOneTrainCardState implements IState {

    @Override
    public IState startGame() {
        // We should do this type of check in more of these functions. If it should be unreachable throw this exception.
        throw new BadLogicException("You should not start the game from the MyLastTurnDrewOneTrainCard state");
    }

    @Override
    public IState claimPath() {
        throw new BadLogicException("Claiming a route cannot be done after drawing a card!");
    }

    @Override
    public IState drawDestinationCard() {
        return null;
    }

    @Override
    public IState drawTrainCard() {
        return new LastTurnNotMyTurnState();
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
