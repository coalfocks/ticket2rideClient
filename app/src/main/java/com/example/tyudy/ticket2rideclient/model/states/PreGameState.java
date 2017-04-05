package com.example.tyudy.ticket2rideclient.model.states;


import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by tyudy on 3/30/17.
 */

public class PreGameState implements IState {

    /**
     * The game has started. States will be shifted depending on who's turn it is.
     * @return - an instance of MyTurnBeganState if it is the users turn, otherwise an instance of NotMyTurnState
     */
    @Override
    public IState startGame() {
        if (ClientModel.SINGLETON.isCurrentUsersTurn()){
            return new MyTurnBeganState();
        } else {
            return new NotMyTurnState();
        }
    }

    /**
     * Cannot claim a path from the preGame state (the game shouldn't even be on the screen yet)
     * @return - this instance of PreGameState
     */
    @Override
    public IState claimPath() {
        return this;
    }

    /**
     * Cannot draw a destination card from the preGame state (the game shouldn't even be on the screen yet)
     * @return - this instance of PreGameState
     */
    @Override
    public IState drawDestinationCard() {
        return this;
    }

    /**
     * Cannot draw a train card from the preGame state (the game shouldn't even be on the screen yet)
     * @return - this instance of PreGameState
     */
    @Override
    public IState drawTrainCard() {
        return this;
    }

    @Override
    public IState changeTurn() {
        return this;
    }

    /**
     * shouldn't ever happen, return this
     * @return this
     */
    @Override
    public IState lastTurn() {
        return this;
    }
}
