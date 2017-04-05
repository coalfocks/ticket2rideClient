package com.example.tyudy.ticket2rideclient.model.states;

import com.example.tyudy.ticket2rideclient.interfaces.IState;

/**
 * Created by Trevor on 3/15/2017.
 */

public class MyTurnLastTurnState implements IState {

    @Override
    public IState drawTrainCard() {
        return new MyTurnLastTurnDrewOneTrainCard();
    }

    @Override
    public IState changeTurn() {
        return null;
    }

    @Override
    public IState lastTurn() {
        return null;
    }

    @Override
    public IState drawDestinationCard() {
        return new NotMyTurnLastTurnState();
    }

    @Override
    public IState startGame() {
        return null;
    }

    @Override
    public IState claimPath() {
        return new EndGameState();
    }
}
