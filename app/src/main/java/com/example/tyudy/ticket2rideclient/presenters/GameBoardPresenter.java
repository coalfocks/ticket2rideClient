package com.example.tyudy.ticket2rideclient.presenters;

import android.view.MotionEvent;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cities.City;
import com.example.tyudy.ticket2rideclient.fragments.DisplayDestCardsDialogFragment;
import com.example.tyudy.ticket2rideclient.fragments.GameBoardFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.views.MapView;

/**
 * Created by tyudy on 2/24/17.
 */

public class GameBoardPresenter {

    private GameBoardFragment mGameBoardFragment;

    public GameBoardPresenter(){

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
        MethodsFacade.SINGLETON.getFaceUpCards();
    }

    public void displayRoutesIfCityClicked(MotionEvent event){
        MapView mapView = mGameBoardFragment.getMapView();
        float xUserClick = event.getX();
        float yUserClick = event.getY();
        float xClickScreenScale = xUserClick / mapView.getScreenWidth();
        float yClickScreenScale = yUserClick / mapView.getScreenHeight();

        City clickedCity = ClientModel.SINGLETON.getCityByScaleValues(xClickScreenScale, yClickScreenScale);

        if (clickedCity != null) {
//            Toast.makeText(MethodsFacade.SINGLETON.getContext(), clickedCity.getCityName(), Toast.LENGTH_SHORT).show();
            PresenterHolder.SINGLETON.getClaimRouteDialogPresenter().showDialog(mGameBoardFragment.getActivity(), clickedCity);
        } else {
            Toast.makeText(MethodsFacade.SINGLETON.getContext(), "That is not a city!", Toast.LENGTH_SHORT).show();
        }


    }

}

//TODO: start turn sends updated game model
//Todo: handle db exceptions gracefully
