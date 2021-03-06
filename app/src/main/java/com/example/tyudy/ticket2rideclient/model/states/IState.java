package com.example.tyudy.ticket2rideclient.model.states;

/**
 * Created by Trevor on 3/15/2017.
 */

public interface IState {

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
     * wants to draw one of the face-up train cards.
     * @return New state if valid, current state otherwise.
     * @pre Must be the current player's turn, and can't have
     *      already drawn two train cards, or, if this is the
     *      second card picked or drawn by the player, it cannot be
     *      a Locomotive (wild) card. Can't have taken any
     *      other actions on this turn.
     * @post The action will be valid.
     *
     */
    IState pickTrainCard();

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
     * wants to return one of the destination cards
     * they have just drawn earlier this turn.
     * @return New state if valid, current state otherwise.
     * @pre Must be the current player's turn, and must
     *      have previously (on this turn) drawn destination
     *      cards.
     * @post The action will be valid.
     */
    IState returnDestinationCard();

    /**
     * This is the function that is called at the end
     * of each player's turn. This function only adds
     * the new points gained this turn through claiming
     * paths, it doesn't calculate any completed destination
     * cards. (Those are calculated at the end of the game)
     * @return New state if valid, current state otherwise.
     * @pre Must be the end of the current player's turn,
     *      or in other words, the player must have already
     *      drawn train cards, destination cards, or have
     *      claimed a path.
     * @post The action will be valid.
     */
    IState scorePoints();

    /**
     * This is the function that is called when
     * a player's turn is over.
     * @return New state if valid, current state otherwise.
     */
    IState endTurn();

    IState pickedWild();

    //TODO: toast if can't do
    //TODO: send command in notmyturn constructor
 }
