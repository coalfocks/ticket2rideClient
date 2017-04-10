package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.Utils.GraphicsUtils;
import com.example.tyudy.ticket2rideclient.activities.GameLobbyActivity;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.common.PlasticTrainCollection;

import java.io.Serializable;

/**
 * Created by Trevor on 2/11/2017.
 */
public class StartGameCommand extends Command implements iCommand, Serializable
{
    public StartGameCommand(){}

    @Override
    public DataTransferObject execute()
    {
        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
        if(data.getErrorMsg().length() != 0)
        {
            Toast.makeText(jeffery, data.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
        else{
            try {
                TTRGame game = (TTRGame) Serializer.deserialize(data.getData());
                if (game.getGameID() != ClientModel.SINGLETON.getCurrentTTRGame().getGameID()) {
                    return null;
                }
                ClientModel.SINGLETON.setCurrentTTRGame(game);
                //TODO: game coming back not in progress
                ClientModel.SINGLETON.setCurrentPlayerTurnID(game.getWhoTurn());
                for (User u : game.getUsers()) {
                    if (u.getPlayerID() == ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
                        ClientModel.SINGLETON.setCurrentUser(u);
                        int usersColor = GraphicsUtils.getRealColorFromEnum(ClientModel.SINGLETON.getCurrentUser().getColor());
                        ClientModel.SINGLETON.setUsersTrains(new PlasticTrainCollection(usersColor));
                    }
                }
                ClientModel.SINGLETON.getCurrentTTRGame().setInProgress(0);

                if (ClientModel.SINGLETON.canStartGame()) {
                    IState newState = ClientModel.SINGLETON.getCurrentState().startGame();
                    ClientModel.SINGLETON.setCurrentState(newState);
                } else {
                    IState newState = ClientModel.SINGLETON.getCurrentState().startGame();
                    ClientModel.SINGLETON.setCurrentState(newState);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.console().printf(e.getMessage());
            }
            Toast.makeText(jeffery, "Game Started!", Toast.LENGTH_SHORT).show();
            ((GameLobbyActivity) jeffery).onStartGame();
        }
        return null;
    }
}
