package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;

/**
 * Created by Trevor on 3/24/2017.
 */

public class NextTurnCommand extends Command implements iCommand, Serializable {

    @Override
    public DataTransferObject execute()
    {
        int nextPlayerID = data.getPlayerID();

        // Checks if a turn end is valid in current state
        if (ClientModel.SINGLETON.canEndTurn())
            MethodsFacade.SINGLETON.changeTurn(nextPlayerID);

        return data;
    }
}
