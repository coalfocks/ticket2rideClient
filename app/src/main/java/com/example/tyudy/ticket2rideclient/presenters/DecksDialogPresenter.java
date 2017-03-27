package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.fragments.DecksDialogFragment;
import com.example.tyudy.ticket2rideclient.fragments.DisplayDestCardsDialogFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

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
        //IMPLEMENT ME!!
        PresenterHolder.SINGLETON.getDestCardsPresenter().showDialog(mDecksDialogFragment.getActivity());
    }

    public void trainDeckClicked(){
        //IMPLEMENT ME!!
    }

    /**
     * triggered when a card is clicked in the DecksDialogFragment
     * @param cardNumber - IMPORTANT, this param is not an index, its just the order on the screen from left to right
     */
    public void cardClicked(int cardNumber){
        //IMPLEMENT ME!!
    }


}
