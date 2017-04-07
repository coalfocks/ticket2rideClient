package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.fragments.GetDestCardsFragment;
import com.example.tyudy.ticket2rideclient.fragments.InitDestCardsFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.util.ArrayList;

/**
 * Created by colefox on 3/29/17.
 */

public class InitDestCardsPresenter {
    InitDestCardsFragment mInitDestCardsFragment;
    ArrayList<DestinationCard> mDestCards;

    Boolean card1Selected;
    Boolean card2Selected;
    Boolean card3Selected;

    public InitDestCardsPresenter(){
        mDestCards = new ArrayList<>();
//        mDestCards.add(new DestinationCard("Los Angeles", "New York", 21));
//        mDestCards.add(new DestinationCard("Duluth", "Houston", 8));
//        mDestCards.add(new DestinationCard("Sault St Marie", "Nashville", 8));

        card1Selected = false;
        card2Selected = false;
        card3Selected = false;
    }

    public void setInitDestCardsFragment(InitDestCardsFragment initDestCardsFragment){
        mInitDestCardsFragment = initDestCardsFragment;
    }

    public void showDialog(Activity gameBoardActivity){
        mInitDestCardsFragment = new InitDestCardsFragment();
        mInitDestCardsFragment.show(gameBoardActivity.getFragmentManager(), "get_dest_cards_fragment");
    }

    public void exitClicked(){
        ArrayList<DestinationCard> toReturn = new ArrayList<>();
        ArrayList<DestinationCard> toUpdate = new ArrayList<>();

        if (card1Selected) {
            toReturn.add(mDestCards.get(0));
        } else {
            toUpdate.add(mDestCards.get(0));
        }

        if (card2Selected) {
            toReturn.add(mDestCards.get(1));
        } else {
            toUpdate.add(mDestCards.get(1));        }

        if (card3Selected) {
            toReturn.add(mDestCards.get(2));
        } else {
            toUpdate.add(mDestCards.get(2));        }

        if (toReturn.size() > 1) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "You must keep at LEAST two cards!", Toast.LENGTH_SHORT).show();
            return;
        }

        ClientModel.SINGLETON.getCurrentUser().setDestCards(toUpdate);
        for (User u : ClientModel.SINGLETON.getCurrentTTRGame().getUsers()) {
            if (u.getPlayerID() == ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
                u.setDestCards(toUpdate);
            }
        }

        ArrayList<ArrayList<DestinationCard>> cards = new ArrayList<>();
        try
        {
            cards.add(toReturn);
            cards.add(toUpdate);
            MethodsFacade.SINGLETON.sendBackInitDestCards(cards);
            card1Selected = false;
            card2Selected = false;
            card3Selected = false;
            ClientModel.SINGLETON.getCurrentTTRGame().setInProgress(1);
            mInitDestCardsFragment.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * triggered when a card is clicked in the DecksDialogFragment
     * @param cardNumber - IMPORTANT, this param is not an index, its just the order on the screen from left to right
     */
    public void cardClicked(int cardNumber) {
        Boolean select = false;
        switch (cardNumber) {
            case (0):
                card1Selected ^= true;
                select = card1Selected;
                break;
            case (1):
                card2Selected ^= true;
                select = card2Selected;
                break;
            case (2):
                card3Selected ^= true;
                select = card3Selected;
                break;
        }
        mInitDestCardsFragment.selectCard(cardNumber, select);
    }

    public ArrayList<DestinationCard> getmDestCards()
    {
        return mDestCards;
    }

    public void setmDestCards(ArrayList<DestinationCard> mDestCards)
    {
        this.mDestCards = mDestCards;
    }

    public ArrayList<DestinationCard> fetchCards() {
        return ClientModel.SINGLETON.getCurrentUser().getDestCards();
    }
}
