package com.example.tyudy.ticket2rideclient.model.states;

/**
 * Created by Trevor on 3/15/2017.
 */

public class MyTurnStateNoAction implements IState {

    @Override
    public IState drawTrainCard() {
        return new MyTurnDrewOneTrainCardState();
    }

    @Override
    public IState drawDestinationCard() {
        return new MyTurnDrawDestinationCardsState();
    }

    @Override
    public IState pickTrainCard() {
        return new MyTurnDrewOneTrainCardState();
    }

    @Override
    public IState claimPath() {
        return new MyTurnClaimedRouteState();
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
        return new NotMyTurnState();
    }
}
