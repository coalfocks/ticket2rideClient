package com.example.tyudy.ticket2rideclient.model.states;

import com.example.tyudy.ticket2rideclient.interfaces.IState;

/**
 * Created by Trevor on 3/15/2017.
 * State of the game after the game has ended and the game over screen will come up
 */

public class EndGameState implements IState {

    @Override
    public IState startGame() {
        return null;
    }

    @Override
    public IState claimPath() {
        return null;
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
