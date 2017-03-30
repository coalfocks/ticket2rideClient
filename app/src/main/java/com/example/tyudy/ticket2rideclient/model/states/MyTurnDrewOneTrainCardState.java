package com.example.tyudy.ticket2rideclient.model.states;

/**
 * Created by Trevor on 3/15/2017.
 */

public class MyTurnDrewOneTrainCardState implements IState {

    @Override
    public IState drawTrainCard() {
        return new NotMyTurnState();
    }

    @Override
    public IState drawDestinationCard() {
        return this;
    }

    @Override
    public IState pickTrainCard() {
        return new NotMyTurnState();
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
        return new NotMyTurnState();
    }

    @Override
    public IState pickedWild() {
        return new MyTurnPickedWildState();
    }
}
