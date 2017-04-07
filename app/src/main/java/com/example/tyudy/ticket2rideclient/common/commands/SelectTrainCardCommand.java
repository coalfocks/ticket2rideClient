package com.example.tyudy.ticket2rideclient.common.commands;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cards.FaceUpCards;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.interfaces.IState;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.model.states.DrewOneTrainCardState;
import com.example.tyudy.ticket2rideclient.model.states.NotMyTurnState;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

import java.io.Serializable;

/**
 * Created by colefox on 3/10/17.
 */

public class SelectTrainCardCommand extends Command implements iCommand, Serializable {
    public SelectTrainCardCommand(){}
    @Override
    public DataTransferObject execute()
    {
        try
        {

            FaceUpCards fu = (FaceUpCards) Serializer.deserialize(data.getData());
            PresenterHolder.SINGLETON.getDecksDialogPresenter().setmFaceUpCards(fu);


            // Change the state only for the currentPlayer that just drew the cards
            if(ClientModel.SINGLETON.canDrawTrainCard()) {
                PresenterHolder.SINGLETON.getDecksDialogPresenter().exitClicked();
                IState newState = ClientModel.SINGLETON.getCurrentState().drawTrainCard();
                ClientModel.SINGLETON.setCurrentState(newState);
                Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Selected a card!", Toast.LENGTH_SHORT).show();


                // Player just drew their second card
                if (ClientModel.SINGLETON.getCurrentState().getClass() == DrewOneTrainCardState.class){
                    MethodsFacade.SINGLETON.changeTurn();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
