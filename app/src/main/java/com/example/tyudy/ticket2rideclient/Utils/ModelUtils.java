package com.example.tyudy.ticket2rideclient.Utils;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.model.states.MyTurnBeganState;
import com.example.tyudy.ticket2rideclient.model.states.PreGameState;

/**
 * Created by tyudy on 3/30/17.
 * This is a class that handles all the logic for the can do methods becuase the clientModel class is getting too big
 */

public final class ModelUtils {

    public static boolean canDrawTrainCard(){
        return false;
    }

    /**
     * Check to makes sure that
     * 1. It is the currentUsers turn
     * 2. The route has not yet been claimed
     * 3. The currentUser has enough train cards to claim the route
     * 4. The currentUser has enough train pieces to claim the route
     * @param path - the path that we are checking if the user can claim
     * @return - true if the path can be claimed. False otherwise.
     */
    public static boolean canClaimPath(Path path) {

        Class classToCompareTo = MyTurnBeganState.class;

        ColorENUM pathColor = path.getPathColor();
        boolean userHasEnoughColorsWithWilds = (ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(pathColor).getNum() +
                ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.WILD).getNum())
                >= path.getDistance();
        boolean userHasEnoughColorCards = ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(pathColor).getNum()
                >= path.getDistance();


        IState currentState = ClientModel.SINGLETON.getCurrentState();

        // Make sure it is the currentUsers turn and they haven't done anything yet
        if (currentState.getClass() != classToCompareTo) {
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

        // Make sure the user has enough train cards to claim the route
        if (!userHasEnoughColorCards) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "You don't have enough " + pathColor.toString() + " cards!", Toast.LENGTH_SHORT).show();
            return false;
        }

        //TODO check to see if they have enough train cards with wilds cards


        // Make sure the user has enough train pieces
        int numberTrainPieces = ClientModel.SINGLETON.getUsersTrains().getSize();
        if (numberTrainPieces < path.getDistance()) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "You only have " + numberTrainPieces + " train pieces!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public static boolean canDrawDestinationCard(){
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
}
