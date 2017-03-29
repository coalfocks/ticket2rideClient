package com.example.tyudy.ticket2rideclient.common.commands;


import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;

/**
 * Created by colefox on 3/10/17.
 */

public class ReturnDestCardsCommand extends Command implements iCommand, Serializable {
    public ReturnDestCardsCommand(){}
    private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
        if (data.getPlayerID() != ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
            for (User u : ClientModel.SINGLETON.getCurrentTTRGame().getUsers()) {
                if (u.getPlayerID() == data.getPlayerID()) {
                    for (int i = 0; i < Integer.parseInt(data.getData()); i++) {
                        u.addDestinationCard(new DestinationCard("","",0));
                    }
                    ClientModel.SINGLETON.notifyObservers();
                }
            }
        }
        return null;
    }


    public void setData(DataTransferObject d)
    {
        super.setData(d);
        this.data = d;
    }
}
