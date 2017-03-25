package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.fragments.DisplayDestCardsDialogFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by tyudy on 3/24/17.
 */

public class DisplayDestinationCardsPresenter {
    private DisplayDestCardsDialogFragment mDisplayDestCardsDialogFragment;

    public DisplayDestinationCardsPresenter(){

    }

    public void setDisplayDestCardsDialogFragment(DisplayDestCardsDialogFragment displayDestCardsDialogFragment) {
        mDisplayDestCardsDialogFragment = displayDestCardsDialogFragment;
    }

    public void showDialog(Activity gameBoardActivity){
        User user = ClientModel.SINGLETON.getCurrentUser();
        mDisplayDestCardsDialogFragment = new DisplayDestCardsDialogFragment();
        mDisplayDestCardsDialogFragment.setCardList(user.getDestCards());
        mDisplayDestCardsDialogFragment.setGameBoardActivity(gameBoardActivity);
        mDisplayDestCardsDialogFragment.show(gameBoardActivity.getFragmentManager(), "Cards");
    }

}
