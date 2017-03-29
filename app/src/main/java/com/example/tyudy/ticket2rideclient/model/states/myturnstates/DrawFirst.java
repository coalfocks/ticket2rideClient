package com.example.tyudy.ticket2rideclient.model.states.myturnstates;

import com.example.tyudy.ticket2rideclient.model.states.IState;
import com.example.tyudy.ticket2rideclient.model.states.MyTurnState;

/**
 * Created by Trevor on 3/21/2017.
 */

public class DrawFirst extends MyTurnState {
    @Override
    public IState drawCard() {
        return new DrawSecond();
    }

    @Override
    public IState drawDest() {
        return this;
    }

    @Override
    public IState pickCard() {
        return new PickSecond();
    }

    @Override
    public IState claimPath() {
        return this;
    }

    @Override
    public IState returnCard() {
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
