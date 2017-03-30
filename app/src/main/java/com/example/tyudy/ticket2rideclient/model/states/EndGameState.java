package com.example.tyudy.ticket2rideclient.model.states;

/**
 * Created by Trevor on 3/15/2017.
 */

public class EndGameState implements IState {

    @Override
    public IState drawTrainCard() {
        return this;
    }

    @Override
    public IState drawDestinationCard() {
        return this;
    }

    @Override
    public IState pickTrainCard() {
        return this;
    }

    @Override
    public IState claimPath() {
        return this;
    }

    @Override
    public IState returnDestinationCard() {
        return this;
    }

    @Override
    public IState scorePoints() {
        return this;
    }

    @Override
    public IState endTurn() {
        return this;
    }
}
