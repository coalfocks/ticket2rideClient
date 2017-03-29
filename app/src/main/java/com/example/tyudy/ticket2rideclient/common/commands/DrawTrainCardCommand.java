package com.example.tyudy.ticket2rideclient.common.commands;


import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
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
        try
        {
            TrainCard card = (TrainCard) Serializer.deserialize(data.getData());
            ClientModel.SINGLETON.addTrainCard(card);
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Drew a " + card.getColor().name() + "card!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
