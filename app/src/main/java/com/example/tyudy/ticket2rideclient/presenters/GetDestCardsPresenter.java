package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;

import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.fragments.GetDestCardsFragment;

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
        mGetDestCardsFragment.dismiss();
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
