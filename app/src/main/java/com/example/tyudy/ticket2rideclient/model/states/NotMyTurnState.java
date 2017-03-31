package com.example.tyudy.ticket2rideclient.model.states;

/**
 * Created by Trevor on 3/15/2017.
 * All of these functions will return 'this' because none of these actions can
 * be performed when it is not your turn.
 */

import com.example.tyudy.ticket2rideclient.interfaces.IState;


public class NotMyTurnState implements IState {


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
}
