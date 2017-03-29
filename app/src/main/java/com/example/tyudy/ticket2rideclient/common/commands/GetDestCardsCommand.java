package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Poller;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by colefox on 3/10/17.
 */

public class GetDestCardsCommand extends Command implements iCommand, Serializable {
    public GetDestCardsCommand(){}
    private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
        try
        {
            ArrayList<DestinationCard> cards = (ArrayList<DestinationCard>) Serializer.deserialize(data.getData());
            PresenterHolder.SINGLETON.getDestCardsPresenter().setmDestCards(cards);
            PresenterHolder.SINGLETON.getDestCardsPresenter().showDialog(MethodsFacade.SINGLETON.getContext());
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
