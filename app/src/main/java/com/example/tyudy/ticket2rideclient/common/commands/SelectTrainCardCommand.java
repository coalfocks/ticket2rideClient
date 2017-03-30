package com.example.tyudy.ticket2rideclient.common.commands;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cards.FaceUpCards;
import com.example.tyudy.ticket2rideclient.common.iCommand;
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
            PresenterHolder.SINGLETON.getDecksDialogPresenter().exitClicked();
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "Selected a card!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
