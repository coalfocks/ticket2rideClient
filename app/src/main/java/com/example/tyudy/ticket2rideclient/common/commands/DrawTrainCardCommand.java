package com.example.tyudy.ticket2rideclient.common.commands;


import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCardCollection;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.exceptions.BadLogicException;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.model.states.NotMyTurnState;

import java.io.Serializable;
import java.lang.reflect.Method;

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
            TrainCardCollection card = (TrainCardCollection) Serializer.deserialize(data.getData());

            // This if should ALWAYS execute because we do the same check when the command is sent in the MethodsFacade
            if (ClientModel.SINGLETON.canDrawTrainCard()) {
                ClientModel.SINGLETON.addTrainCard(card);
                Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Drew a " + card.getColor().name() + " card!", Toast.LENGTH_SHORT).show();
                IState newState = ClientModel.SINGLETON.getCurrentState().drawTrainCard();
                ClientModel.SINGLETON.setCurrentState(newState);

                // This executes when the user has already drawn two cards (not exactly sticking to the pattern here but im tired af)
                if (ClientModel.SINGLETON.getCurrentState().getClass() == NotMyTurnState.class) {
                    MethodsFacade.SINGLETON.changeTurn();
                }

            } else {
                throw new BadLogicException("DrawTrainCard command returned from the server and user canDrawTrainCards returned false");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
