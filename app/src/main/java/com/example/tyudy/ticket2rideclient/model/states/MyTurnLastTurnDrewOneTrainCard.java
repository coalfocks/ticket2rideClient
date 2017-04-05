package com.example.tyudy.ticket2rideclient.model.states;

import com.example.tyudy.ticket2rideclient.interfaces.IState;

/**
 * Created by colefox on 3/29/17.
 */

public class MyTurnLastTurnDrewOneTrainCard implements IState {
    @Override
    public IState drawTrainCard() {
        return new EndGameState();
    }

    @Override
    public IState changeTurn() {
        return new NotMyTurnLastTurnState();
    }

    @Override
    public IState lastTurn() {
        return null;
    }

    @Override
    public IState drawDestinationCard() {
        return this;
    }

    @Override
    public IState startGame() {
        return this;
    }

    @Override
    public IState claimPath() {
        return this;
    }

}
