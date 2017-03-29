package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.activities.PreGameActivity;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

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
            if (ClientModel.SINGLETON.getCurrentUser().getInGame() != 0) {
                ClientModel.SINGLETON.setCurrentTTRGame(ClientModel.SINGLETON.getTTRGameWithID(ClientModel.SINGLETON.getCurrentUser().getInGame()));
                FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
                ((PreGameActivity) jeffery).onLogin(ClientModel.SINGLETON.getCurrentUser());
            }
        } catch (Exception e){
            Log.d("ListGamesCommand", e.getMessage());
        }
        return null;
    }

}
