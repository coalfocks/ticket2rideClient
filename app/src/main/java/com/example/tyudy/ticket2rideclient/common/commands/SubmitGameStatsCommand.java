package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.iCommand;

import java.io.Serializable;

/**
 * Created by tyudy on 4/10/17.
 */

public class SubmitGameStatsCommand extends Command implements iCommand, Serializable {

    // This doesn't need implmentation becuase it is never executed client side
    // This class is just use to submit data to the server
    @Override
    public DataTransferObject execute(){
        return null;
    }
}
