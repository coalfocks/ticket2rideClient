package com.example.tyudy.ticket2rideclient.common.commands;


import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCardCollection;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;

/**
 * Created by colefox on 3/10/17.
 */

public class DrawTrainCardCommand extends Command implements iCommand, Serializable {
    public DrawTrainCardCommand(){}

    @Override
    public DataTransferObject execute()
    {
        if (!data.getErrorMsg().equals("")) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(),data.getErrorMsg(), Toast.LENGTH_SHORT).show();
            return null;
        }
        try
        {
            TrainCardCollection card = (TrainCardCollection) Serializer.deserialize(data.getData());
            ClientModel.SINGLETON.addTrainCard(card);
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Drew a " + card.getColor().name() + " card!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
