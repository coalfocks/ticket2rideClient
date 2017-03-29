package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.ClientCommunicator;
import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.commands.NextTurnCommand;
import com.example.tyudy.ticket2rideclient.fragments.DecksDialogFragment;
import com.example.tyudy.ticket2rideclient.fragments.DisplayDestCardsDialogFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tyudy on 3/24/17.
 */

public class DecksDialogPresenter  {
    DecksDialogFragment mDecksDialogFragment;
    ArrayList<TrainCard> mFaceUpCards;

    public DecksDialogPresenter(){
        mFaceUpCards = new ArrayList<>();
    }

    public void setDecksDialogFragment(DecksDialogFragment decksDialogFragment){
        mDecksDialogFragment = decksDialogFragment;
    }

    public void showDialog(Activity gameBoardActivity){
        mDecksDialogFragment = new DecksDialogFragment();
        mDecksDialogFragment.show(gameBoardActivity.getFragmentManager(), "decks_dialog_fragment");
    }

    public void exitClicked(){
        mDecksDialogFragment.dismiss();
    }

    public void destDeckClicked(){
        if (ClientModel.SINGLETON.canDrawDestCard())
        {
            //PresenterHolder.SINGLETON.getDestCardsPresenter().showDialog(mDecksDialogFragment.getActivity());
            MethodsFacade.SINGLETON.drawDestCard();
        }
    }

    public void trainDeckClicked(){
        //TREVOR'S TEST STUFF
        NextTurnCommand command = new NextTurnCommand();
        DataTransferObject dto = new DataTransferObject();
        int gameID = ClientModel.SINGLETON.getCurrentTTRGame().getGameID();


        dto.setData((Integer.toString(gameID)));
        command.setData(dto);

        try {
            ClientCommunicator.getInstance().sendCommand(Serializer.serialize(command));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * triggered when a card is clicked in the DecksDialogFragment
     * @param cardNumber - IMPORTANT, this param is not an index, its just the order on the screen from left to right
     */
    public void cardClicked(int cardNumber){
        //IMPLEMENT ME!!
    }


}
