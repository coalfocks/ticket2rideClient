package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Poller;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;

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
                Path path = (Path) Serializer.deserialize(data.getData());
                ClientModel.SINGLETON.updateClaimedPath(path);
                Toast.makeText(jeffery, "Route Claimed" + Poller.getInstance().getQueueIndex(), Toast.LENGTH_SHORT).show();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }

}
