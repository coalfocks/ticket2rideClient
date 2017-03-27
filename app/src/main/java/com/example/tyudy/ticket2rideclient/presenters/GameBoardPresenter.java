package com.example.tyudy.ticket2rideclient.presenters;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.fragments.DisplayDestCardsDialogFragment;
import com.example.tyudy.ticket2rideclient.fragments.GameBoardFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by tyudy on 2/24/17.
 */

public class GameBoardPresenter {
    private GameBoardFragment mGameBoardFragment;
    private DisplayDestCardsDialogFragment mDialogFragment;

    public GameBoardPresenter(){
        mDialogFragment = new DisplayDestCardsDialogFragment();
    }

    // Called in the onCreate function in the GameBoardFragment Class in the fragments folder so that it can be updated.
    public void setGameBoardFragment(GameBoardFragment gameBoardFragment) {
        mGameBoardFragment = gameBoardFragment;
    }

    /**
     * Method called when the button is pressed to show the current user
     * his/her destination cards
     */
    public void showDestCards(){
        PresenterHolder.SINGLETON.getDisplayDestinationCardsPresenter().showDialog(mGameBoardFragment.getActivity());
    }

    public void showDecks(){
        PresenterHolder.SINGLETON.getDecksDialogPresenter().showDialog(mGameBoardFragment.getActivity());
    }
}

//TODO: return card commands
//TODO: populate dialog with dest cards
//TODO: show dialog when button pushed
//Todo: tap dest card to remove