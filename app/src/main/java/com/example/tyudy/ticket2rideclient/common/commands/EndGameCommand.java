package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.iCommand;

import java.io.Serializable;

/**
 * Created by Trevor on 2/11/2017.
 */
public class EndGameCommand extends Command implements iCommand, Serializable
{
    public EndGameCommand(){}

    @Override
    public DataTransferObject execute()
    {
        // playerid is winner
        // data is serialized arralylist of userstats
        return null;
    }
}
