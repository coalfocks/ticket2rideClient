package com.example.tyudy.ticket2rideclient.model.states;

/**
 * Created by Trevor on 3/15/2017.
 */

public class MyTurnDrawDestinationCardsState implements IState {

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
        return null;
    }

    @Override
    public IState scorePoints() {
        return this;
    }

    @Override
    public IState endTurn() {
        return this;
    }

    @Override
    public IState pickedWild() {
        return null;
    }
}
