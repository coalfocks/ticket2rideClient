package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;

import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.fragments.DecksDialogFragment;
import com.example.tyudy.ticket2rideclient.fragments.GetDestCardsFragment;

import java.util.ArrayList;

/**
 * Created by colefox on 3/26/17.
 */

public class GetDestCardsPresenter
{
    GetDestCardsFragment mGetDestCardsFragment;
    ArrayList<DestinationCard> mDestCards;

    public GetDestCardsPresenter(){
        mDestCards = new ArrayList<>();
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
    public void cardClicked(int cardNumber){
        //IMPLEMENT ME!!
    }

}
