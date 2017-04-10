package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Poller;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.activities.GameBoardActivity;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.User;
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
                for (User u : ClientModel.SINGLETON.getCurrentTTRGame().getUsers()) {
                    if (u.getPlayerID() == ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
                        ClientModel.SINGLETON.setCurrentUser(u);
                    }
                }
                ClientModel.SINGLETON.notifyObservers();

                if (ClientModel.SINGLETON.canChangeTurn()) {
                    IState newState = ClientModel.SINGLETON.getCurrentState().changeTurn();
                    ClientModel.SINGLETON.setCurrentState(newState);
                } else {
                    throw new BadLogicException("It appears that a change turn command was sent before user was playing a game");
                }

                if (ClientModel.SINGLETON.canEndGame()) {
                    // Launch the end game activity
                    ((GameBoardActivity) jeffery).onEndGame();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
