package com.example.tyudy.ticket2rideclient.Utils;

import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by tyudy on 3/30/17.
 * This is a class that handles all the logic for the can do methods becuase the clientModel class is getting too big
 */

public final class ModelUtils {

    public static boolean canDrawTrainCard(){
        return false;
    }

    public static boolean canClaimPath(Path path){
        for (Path p : ClientModel.SINGLETON.getAllPaths())
        {
            if (p.getName().equals(path.getName()))
            {
                // Can't claim a path if the path already has
                // an owner (even if the owner is the current
                // player)
                if (p.hasOwner())
                    return false;

                return true;
            }
        }

        // Should only get to this point if for some reason the path doesn't exist
        return false;    }

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
        Class classToCompareTo = null;

        try {
            classToCompareTo = Class.forName("PreGameState");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // If the game is still in the pregame state this is valid
        if ( ClientModel.SINGLETON.getCurrentState().getClass() == classToCompareTo){
            return true;
        }

        return false;
    }
}
