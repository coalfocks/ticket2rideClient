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
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.model.PlasticTrainCollection;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

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
                ClientModel.SINGLETON.setCurrentTTRGame(game);
                for (User u : game.getUsers()) {
                    if (u.getPlayerID() == ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
                        ClientModel.SINGLETON.setCurrentUser(u);
                        int usersColor = GraphicsUtils.getRealColorFromEnum(ClientModel.SINGLETON.getCurrentUser().getColor());
                        ClientModel.SINGLETON.setUsersTrains(new PlasticTrainCollection(usersColor));
                    }
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
