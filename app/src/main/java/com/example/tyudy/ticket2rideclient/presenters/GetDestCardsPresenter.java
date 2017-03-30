package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.fragments.GetDestCardsFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by colefox on 3/26/17.
 */

public class GetDestCardsPresenter
{
    GetDestCardsFragment mGetDestCardsFragment;
    ArrayList<DestinationCard> mDestCards;

    Boolean card1Selected;
    Boolean card2Selected;
    Boolean card3Selected;

    public GetDestCardsPresenter(){
        mDestCards = new ArrayList<>();
//        mDestCards.add(new DestinationCard("Los Angeles", "New York", 21));
//        mDestCards.add(new DestinationCard("Duluth", "Houston", 8));
//        mDestCards.add(new DestinationCard("Sault St Marie", "Nashville", 8));

        card1Selected = false;
        card2Selected = false;
        card3Selected = false;
    }

    public void setGetDestCardsFragment(GetDestCardsFragment getDestCardsFragment){
        mGetDestCardsFragment = getDestCardsFragment;
    }

    public void showDialog(Activity gameBoardActivity){
        mGetDestCardsFragment = new GetDestCardsFragment();
        mGetDestCardsFragment.show(gameBoardActivity.getFragmentManager(), "get_dest_cards_fragment");
    }

    public void exitClicked(){
        ArrayList<DestinationCard> toReturn = new ArrayList<>();
        ArrayList<DestinationCard> toUpdate = new ArrayList<>();

        if (card1Selected) {
            toReturn.add(mDestCards.get(0));
        } else {
            ClientModel.SINGLETON.getCurrentUser().getDestCards().add(mDestCards.get(0));
            ClientModel.SINGLETON.addDestCard(mDestCards.get(0));
            toUpdate.add(mDestCards.get(0));
        }

        if (card2Selected) {
            toReturn.add(mDestCards.get(1));
        } else {
            ClientModel.SINGLETON.getCurrentUser().getDestCards().add(mDestCards.get(1));
            ClientModel.SINGLETON.addDestCard(mDestCards.get(1));
            toUpdate.add(mDestCards.get(1));        }

        if (card3Selected) {
            toReturn.add(mDestCards.get(2));
        } else {
            ClientModel.SINGLETON.getCurrentUser().getDestCards().add(mDestCards.get(2));
            ClientModel.SINGLETON.addDestCard(mDestCards.get(2));
            toUpdate.add(mDestCards.get(2));        }

        if (toReturn.size() > 2) {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "You must keep at LEAST one card!", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<ArrayList<DestinationCard>> cards = new ArrayList<>();
        try
        {
            cards.add(toReturn);
            cards.add(toUpdate);
            MethodsFacade.SINGLETON.sendBackDestCards(cards);
            card1Selected = false;
            card2Selected = false;
            card3Selected = false;
            mGetDestCardsFragment.dismiss();
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
        mGetDestCardsFragment.selectCard(cardNumber, select);
    }

    public ArrayList<DestinationCard> getmDestCards()
    {
        return mDestCards;
    }

    public void setmDestCards(ArrayList<DestinationCard> mDestCards)
    {
        this.mDestCards = mDestCards;
    }
}
