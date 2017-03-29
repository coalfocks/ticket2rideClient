package com.example.tyudy.ticket2rideclient.common.commands;


import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cards.FaceUpCards;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

import java.io.Serializable;

/**
 * Created by colefox on 3/10/17.
 */

public class GetFaceUpCardsCommand extends Command implements iCommand, Serializable {
    public GetFaceUpCardsCommand(){}
    private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
        try
        {
            FaceUpCards faceUpCards = (FaceUpCards) Serializer.deserialize(data.getData());
            PresenterHolder.SINGLETON.getDecksDialogPresenter().setmFaceUpCards(faceUpCards);
            PresenterHolder.SINGLETON.getDecksDialogPresenter().showDialog(MethodsFacade.SINGLETON.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void setData(DataTransferObject d)
    {
        super.setData(d);
        this.data = d;
    }
}
