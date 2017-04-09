package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Poller;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tyudy on 3/9/17.
 */

public class ClaimPathCommand extends Command implements iCommand, Serializable {
    public ClaimPathCommand(){}

    @Override
    public DataTransferObject execute()
    {
        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
        if(data.getErrorMsg().length()!=0){
            Toast.makeText(jeffery, data.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else {
            try {
                ArrayList<String> pathData = (ArrayList<String>) Serializer.deserialize(data.getData());
                Path path = (Path) Serializer.deserialize(pathData.get(0));
                ClientModel.SINGLETON.updateClaimedPath(path); // Update the ClientModels collection of paths to refelct the new claimed path
                ClientModel.SINGLETON.notifyObservers();

                // Only do this if the command came back to the user who claimed the path
                if (path.getOwner().getPlayerID() == ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
                    IState newState = ClientModel.SINGLETON.getCurrentState().claimPath();
                    ClientModel.SINGLETON.setCurrentState(newState);
                }


            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }

}
