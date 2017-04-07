package com.example.tyudy.ticket2rideclient.model.states;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by Trevor on 3/15/2017.
 */

public class DrewOneTrainCardState implements IState {

    @Override
    public IState startGame() {
        return null;
    }

    @Override
    public IState claimPath() {
        return null;
    }

    @Override
    public IState drawDestinationCard() {
        return null;
    }

    @Override
    public IState drawTrainCard() {

        return new NotMyTurnState();
    }

    /**
     * WARNING: The client model currentTTRGame must be updated with the new turn order before this will function properly
     * @return MyTurnBeganState if according to the currentTTRGame it is currentUsers turn. Else NotMyTurnState
     */
    @Override
    public IState changeTurn() {
        return new NotMyTurnState();
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
