package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.activities.PreGameActivity;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.iCommand;

import java.io.Serializable;

/**
 * Created by Trevor on 2/11/2017.
 */
public class EndGameCommand extends Command implements iCommand, Serializable
{
    public EndGameCommand(){}

    // When this command comes back from the server, the current user will be put
    // back into the game lobby to pick a new game
    @Override
    public DataTransferObject execute()
    {
//        int notInGame = 0;
//        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
//        if(data.getErrorMsg().length() != 0)
//        {
//            Toast.makeText(jeffery, data.getErrorMsg(), Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            MethodsFacade.SINGLETON.setContext(new PreGameActivity());
//            jeffery = MethodsFacade.SINGLETON.getContext();
//            ((PreGameActivity) jeffery).onLogin(notInGame);
//            Toast.makeText(jeffery, "Pick a Game!", Toast.LENGTH_SHORT).show();
//        }
        return null;
    }
}
