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

    @Override
    public DataTransferObject execute()
    {
        Boolean init = false;
        String [] cards = new String[2];
        if (data.getCommand().equals("sendBackInitDestCards")) {
            init = true;
            cards = data.getData().split(",");
        } else {
            cards [0] = data.getData();
        }

        if (data.getPlayerID() != ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
            for (User u : ClientModel.SINGLETON.getCurrentTTRGame().getUsers()) {
                if (u.getPlayerID() == data.getPlayerID()) {
                    for (int i = 0; i < Integer.parseInt(cards[0]); i++) {
                        u.addDestinationCard(new DestinationCard("","",0));
                    }
                    if (init) {
                        for (int i = 0; i < Integer.parseInt(cards[1]); i++) {
                            u.removeDestinationCard(u.getDestCards().get(u.getDestCards().size() - 1));
                        }
                    }
                    ClientModel.SINGLETON.notifyObservers();
                }
            }
        }
        return null;
    }
}
