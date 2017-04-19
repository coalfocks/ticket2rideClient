package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.activities.PreGameActivity;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.PlasticTrainCollection;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.model.states.MyTurnBeganState;
import com.example.tyudy.ticket2rideclient.model.states.NotMyTurnState;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Trevor on 2/11/2017.
 */
public class ListGamesCommand extends Command implements iCommand, Serializable
{
    public ListGamesCommand(){}

    @Override
    public DataTransferObject execute()
    {

        try {
            ArrayList<TTRGame> gList = (ArrayList<TTRGame>) Serializer.deserialize(data.getData());
            MethodsFacade.SINGLETON.replaceModelsGames(gList);
            if (ClientModel.SINGLETON.getCurrentUser().getInGame() != 0 &&
                    gList.size() != 0) {

                ClientModel.SINGLETON.setCurrentTTRGame(ClientModel.SINGLETON.getTTRGameWithID(ClientModel.SINGLETON.getCurrentUser().getInGame()));
                if (ClientModel.SINGLETON.getCurrentTTRGame().getInProgress() == 1) {
                    if (ClientModel.SINGLETON.getCurrentTTRGame().getWhoTurn() == ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
                        ClientModel.SINGLETON.setCurrentState(new MyTurnBeganState());
                    } else {
                        ClientModel.SINGLETON.setCurrentState(new NotMyTurnState());
                    }

                    for (User u : ClientModel.SINGLETON.getCurrentTTRGame().getUsers()) {
                        int piecesPlaced = 0;
                        for (Path p : u.getClaimedPaths()) {
                            ClientModel.SINGLETON.updateClaimedPath(p);
                            piecesPlaced += p.getDistance();
                        }
                        if (u.getPlayerID() == ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
                            int num = 45 - piecesPlaced;
                            ClientModel.SINGLETON.setUsersTrains(new PlasticTrainCollection(num));
                        }
                    }
                }
                FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
                ((PreGameActivity) jeffery).onLogin(ClientModel.SINGLETON.getCurrentUser().getInGame());

            } else if (ClientModel.SINGLETON.getCurrentUser().getInGame() != 0) {
                FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
                ((PreGameActivity) jeffery).onLogin(0);
            }
        } catch (Exception e){
            Log.d("ListGamesCommand", e.getMessage());
        }
        return null;
    }
}
