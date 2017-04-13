package com.example.tyudy.ticket2rideclient.Utils;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCardCollection;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.model.states.DrewOneTrainCardState;
import com.example.tyudy.ticket2rideclient.model.states.EndGameState;
import com.example.tyudy.ticket2rideclient.model.states.MyLastTurnBeganState;
import com.example.tyudy.ticket2rideclient.model.states.MyLastTurnDrewOneTrainCardState;
import com.example.tyudy.ticket2rideclient.model.states.MyTurnBeganState;
import com.example.tyudy.ticket2rideclient.model.states.PreGameState;

/**
 * Created by tyudy on 3/30/17.
 * This is a class that handles all the logic for the can do methods because the clientModel class is getting too big
 */

public final class ModelUtils {

    //TODO: Make the canDoFunctions account for the last turn states as well
    /**
     * Checks that:
     * 1. It is the currentUsers turn
     * 2. The currentUser hasn't selected a wild card
     *
     * @return - true if card can be drawn
     */
    public static boolean canDrawTrainCardOfColor(ColorENUM cardColor){

        boolean userDrewTrainCard = (ClientModel.SINGLETON.getCurrentState().getClass() == DrewOneTrainCardState.class ||
                                     ClientModel.SINGLETON.getCurrentState().getClass() == MyLastTurnDrewOneTrainCardState.class);

        // Do the regular draw train card check
        canDrawTrainCard();

        // Make sure user doesn't pick a wild on their second draw
        if (userDrewTrainCard && cardColor == ColorENUM.WILD) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "You can't pick a wild NOW!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public static boolean canDrawTrainCard(){

        boolean isNotCurrentPlayersTurn = (ClientModel.SINGLETON.getCurrentState().getClass() != MyTurnBeganState.class &&
                                           ClientModel.SINGLETON.getCurrentState().getClass() != DrewOneTrainCardState.class);
        boolean isNotCurrentPlayersLastTurn = (ClientModel.SINGLETON.getCurrentState().getClass() != MyLastTurnBeganState.class &&
                                               ClientModel.SINGLETON.getCurrentState().getClass() != MyLastTurnDrewOneTrainCardState.class);

        if (isNotCurrentPlayersTurn && isNotCurrentPlayersLastTurn) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "It has to be your turn to get a card!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * Check to makes sure that
     * 1. It is the currentUsers turn
     * 2. The route has not yet been claimed
     * 3. The currentUser has enough train cards to claim the route
     * 4. The currentUser has enough train pieces to claim the route
     * @param path - the path that we are checking if the user can claim
     * @param pathColor - this is actualy the card color that was used to select the path from the GUI
     * @return - true if the path can be claimed. False otherwise.
     */
    public static boolean canClaimPath(Path path, ColorENUM pathColor) {


        int numberOfColoredTrainCards = ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(pathColor).getNum();
        int numberOfWildsCards = ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.WILD).getNum();
        boolean userHasEnoughColorsWithWilds = numberOfWildsCards + numberOfColoredTrainCards >= path.getDistance();
        boolean userHasEnoughColorCards = numberOfColoredTrainCards >= path.getDistance();


        IState currentState = ClientModel.SINGLETON.getCurrentState();

        // Player has already drew one train card
        if (currentState.getClass() == DrewOneTrainCardState.class ||
            currentState.getClass() == MyLastTurnDrewOneTrainCardState.class) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "You must pick/draw a train card again", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Make sure it is the currentUsers turn and they haven't done anything yet
        if (currentState.getClass() != MyTurnBeganState.class &&
            currentState.getClass() != MyLastTurnBeganState.class) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "It has to be your turn to claim a path!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Make sure the route has not yet been claimed
        for (Path p : ClientModel.SINGLETON.getAllPaths()) {
            if (p.getName().equals(path.getName())) {
                if (p.hasOwner()) {
                    Toast.makeText(MethodsFacade.SINGLETON.getContext(), "That path has already been claimed", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        // Make sure the user has enough train pieces
        int numberTrainPieces = ClientModel.SINGLETON.getUsersTrains().getSize();
        if (numberTrainPieces < path.getDistance()) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "You only have " + numberTrainPieces + " train pieces!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Make sure the user has enough train cards (or train cards with wild cards) to claim the route
        if (userHasEnoughColorCards) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Path claimed with all colored cards", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            if (userHasEnoughColorsWithWilds) {
                Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Path claimed with color and wild cards", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(MethodsFacade.SINGLETON.getContext(), "You don't have enough " + pathColor.toString() + "/WILD cards!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    public static boolean canDrawDestinationCard(){


        IState currentState = ClientModel.SINGLETON.getCurrentState();

        // Make sure it is the users turn
        if(currentState.getClass() == MyTurnBeganState.class ||
           currentState.getClass() == MyLastTurnBeganState.class) {
            return true;
        }

        // Player has already drew one train card
        if (currentState.getClass() == DrewOneTrainCardState.class ||
            currentState.getClass() == MyLastTurnDrewOneTrainCardState.class) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "You must draw a train card again", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(MethodsFacade.SINGLETON.getContext(), "It has to be your turn to draw cards!", Toast.LENGTH_SHORT).show();
        return false;

    }

    public static boolean canEndTurn(){
        return false;
    }

    /**
     * Check to see if the game is in a state where it can be started
     * @return - true if the game can be started. False if it can't
     */
    public static boolean canStartGame(){

        Class classToCompareTo = PreGameState.class;

        // If the game is still in the pregame state this is valid
        if ( ClientModel.SINGLETON.getCurrentState().getClass() == classToCompareTo){
            return true;
        }

        return false;
    }

    /**
     * Checks to see if the game is in a state where it can be changed.
     * @return - false if the currentState is preGameState or EndGameState, otherwise true
     */
    public static boolean canChangeTurn() {

        IState currentState = ClientModel.SINGLETON.getCurrentState();

        if (currentState.getClass() == PreGameState.class ||
            currentState.getClass() == EndGameState.class) {
            return false;
        }

        return true;  // Game is in progress, so the turn can be changed
    }

    public static boolean canEndGame() {
        IState currentState = ClientModel.SINGLETON.getCurrentState();

        if(currentState.getClass() == EndGameState.class) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if the user should send a lastTurnCommand.. basically checks to see...
     * 1. It is the currentUsers turn (not last turn)
     * 3. The current user has less than 3 train cars
     * @return - true if the last turn command should be send to the server, false otherwise
     */
    public static boolean canChangeToLastTurn(){
        IState currentState = ClientModel.SINGLETON.getCurrentState();

        boolean isCurrentUsersTurn = (currentState.getClass() == MyTurnBeganState.class ||
            currentState.getClass() == MyLastTurnDrewOneTrainCardState.class);

        // Make sure it is the current users turn (not last turn)
        // The User has less than 3 train cards so the last turn command should be sent to the server
        if ((ClientModel.SINGLETON.getUsersTrains().getSize() < 3) && isCurrentUsersTurn) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean canSubmitGameStats(){
        IState currentState = ClientModel.SINGLETON.getCurrentState();

        if (currentState.getClass() == MyLastTurnBeganState.class ||
            currentState.getClass() == MyLastTurnDrewOneTrainCardState.class) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove the trainCardCollection Object from the currentUsers collection of train cards if its number is 0
     * @param trainCardCollection - reference to the users train cards of the given color
     * @param color - color of cards to be deleted if they are empty
     */
    public static void cleanUpTrainCards(TrainCardCollection trainCardCollection, ColorENUM color){
        // Remove this TrainCardCollection from the
        if(trainCardCollection.isEmpty()) {
            ClientModel.SINGLETON.getCurrentUser().removeTrainCardsWithColor(color);
        }
    }




}
