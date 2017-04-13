package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.activities.GameBoardActivity;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.UserStats;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Trevor on 2/11/2017.
 */
public class EndGameCommand extends Command implements iCommand, Serializable
{
    public EndGameCommand(){}

    @Override
    public DataTransferObject execute()
    {
        try {
            String winner = "NOBODY WON";
            for (User u : ClientModel.SINGLETON.getCurrentTTRGame().getUsers()) {
                if (u.getPlayerID() == data.getPlayerID()) {
                    winner = u.getUsername();
                }
            }

            ArrayList<UserStats> gameStats = (ArrayList<UserStats>) Serializer.deserialize(data.getData());
            PresenterHolder.SINGLETON.getmGameOverPresenter().setWinner(winner);
            PresenterHolder.SINGLETON.getmGameOverPresenter().setGameStats(gameStats);

            if (ClientModel.SINGLETON.canEndGame()) {
                ((GameBoardActivity) MethodsFacade.SINGLETON.getContext()).onEndGame();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
