package com.example.tyudy.ticket2rideclient.model.states;

import com.example.tyudy.ticket2rideclient.interfaces.IState;

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
        return null;
    }
}
