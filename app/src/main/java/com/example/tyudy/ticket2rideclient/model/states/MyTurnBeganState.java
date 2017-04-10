package com.example.tyudy.ticket2rideclient.model.states;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by Trevor on 3/15/2017.
 * State of a game just after it becomes the currentUsers turn
 */

public class MyTurnBeganState implements IState {

    /**
     * This should not be called on this state. If it is just return 'this'
     * @return - 'this' instance of this class.
     */
    @Override
    public IState startGame() {
        return this;
    }

    @Override
    public IState claimPath() {

        return new NotMyTurnState();
    }

    @Override
    public IState drawDestinationCard() {
        return null;
    }

    @Override
    public IState drawTrainCard() {
        return new DrewOneTrainCardState();
    }

    /**
     * WARNING: The client model currentTTRGame must be updated with the new turn order before this will function properly
     * @return MyTurnBeganState if according to the currentTTRGame it is currentUsers turn. Else NotMyTurnState
     */
    @Override
    public IState changeTurn() {
        Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Turn Changed!", Toast.LENGTH_SHORT).show();
        return new NotMyTurnState();
    }

    @Override
    public IState lastTurn() {
        Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Turn Changed!", Toast.LENGTH_SHORT).show();
        return new LastTurnNotMyTurnState();
    }
}
