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

                if(ClientModel.SINGLETON.getCurrentUser().getPlayerID() == gameAfterTurnChange.getWhoTurn()) {
                    ClientModel.SINGLETON.setCurrentState(new MyTurnBeganState());
                    Toast.makeText(jeffery, "Your turn began!", Toast.LENGTH_SHORT).show();
                } else {
                    ClientModel.SINGLETON.setCurrentState(new NotMyTurnState());
                    Toast.makeText(jeffery, "Turn Changed!", Toast.LENGTH_SHORT).show();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
