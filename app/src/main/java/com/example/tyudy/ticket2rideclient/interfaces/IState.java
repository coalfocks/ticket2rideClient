package com.example.tyudy.ticket2rideclient.interfaces;

/**
 * Created by Trevor on 3/15/2017.
 */

public interface IState {

    //----------------------------------------------------------------------------------------

    /**
     * Function designed to get called on a state when the server notifies the client that the game began
     * Should only be called on the pregame state.
     * @return
     */
    IState startGame();

    /**
     * This is the function to be called when a player
     * wants to claim a path with the necessary amount
     * of train cards they currently have.
     * @return New state if valid, current state otherwise.
     * @pre Must be the current player's turn, and can't have
     *      taken any other actions already, meaning drawing
     *      train or destination cards. The current player
     *      must also have a sufficient number of color
     *      train cards of the necessary color.
     * @post The action will be valid.
     */
    IState claimPath();

    /**
     * This is the function to be called when a player
     * wants to draw new destination cards.
     * @return New state if valid, current state otherwise.
     * @pre Must be the current player's turn, and can't have
     *      taken any other actions, meaning drawing any train
     *      cards or claimed any paths.
     * @post The action will be valid.
     */
    IState drawDestinationCard();

    /**
     * This is the function to be called when a player
     * wants to draw a train card from the top of the
     * deck.
     * @return New state if valid, current state otherwise.
     * @pre Must be the current player's turn, and can't have
     *      chosen a Locomotive (wild) card face up previously,
     *      or already have drawn two cards.
     *      Must also not have drawn any destination cards, or
     *      claimed a path on this turn.
     * @post The action will be valid.
     */
    IState drawTrainCard();


 }
