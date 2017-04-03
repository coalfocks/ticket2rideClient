package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Poller;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.exceptions.BadLogicException;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.model.states.MyTurnBeganState;
import com.example.tyudy.ticket2rideclient.model.states.NotMyTurnState;

import java.io.Serializable;

/**
 * Created by Trevor on 3/24/2017.
 */

public class ChangeTurnCommand extends Command implements iCommand, Serializable {

    @Override
    public DataTransferObject execute()
    {

        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
        if(data.getErrorMsg().length()!=0){
            Toast.makeText(jeffery, data.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else {
            try {
                TTRGame gameAfterTurnChange = (TTRGame) Serializer.deserialize(data.getData());
                ClientModel.SINGLETON.setCurrentTTRGame(gameAfterTurnChange);
                ClientModel.SINGLETON.notifyObservers();

                // TODO: Test that this works when the server is impleented to send this command back off of the commandQueue
                if (ClientModel.SINGLETON.canChangeTurn()) {
                    IState newState = ClientModel.SINGLETON.getCurrentState().changeTurn();
                    ClientModel.SINGLETON.setCurrentState(newState);
                } else {
                    throw new BadLogicException("It appears that a change turn command was sent before user was playing a game");
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
