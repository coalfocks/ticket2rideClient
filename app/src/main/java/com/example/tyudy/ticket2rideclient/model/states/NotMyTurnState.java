package com.example.tyudy.ticket2rideclient.model.states;

/**
 * Created by Trevor on 3/15/2017.
 */

/**
 * All of the methods in this state will return false,
 * since there are no valid actions you can do on another
 * players' turns.
 */
public class NotMyTurnState implements IState {

    public NotMyTurnState() {}
    public NotMyTurnState(int flag) {}

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
        return new NotMyTurnState();
    }

    @Override
    public IState pickedWild() {
        return this;
    }

    public IState startTurn() {
        return new MyTurnStateNoAction();
    }
}
