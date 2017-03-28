package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.Utils.GraphicsUtils;
import com.example.tyudy.ticket2rideclient.common.cities.City;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.fragments.ClaimRouteDialogFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.util.ArrayList;

/**
 * Created by tyudy on 3/27/17.
 */

public class ClaimRouteDialogPresenter {
    ClaimRouteDialogFragment mClaimRouteDialogFragment;
    City mSelectedCity;

    public ClaimRouteDialogPresenter(){

    }

    public void setClaimRouteDialogFragment(ClaimRouteDialogFragment claimRouteDialogFragment){
        mClaimRouteDialogFragment = claimRouteDialogFragment;
    }

    public void showDialog(Activity gameBoardActivity, City selectedCity){
        mSelectedCity = selectedCity;
        mClaimRouteDialogFragment = new ClaimRouteDialogFragment();
        mClaimRouteDialogFragment.show(gameBoardActivity.getFragmentManager(), "decks_dialog_fragment");
    }

    public void exitButtonClicked(){
        mClaimRouteDialogFragment.dismiss();
    }

    public void confirmButtonClicked(){
        //IMPLEMENT ME!
    }


    //---------------------------HELPER-FUNCTIONS------------------------------

    public void setTitleText(String cityName){
        String newText = "Path: " + cityName + " to...";
        mClaimRouteDialogFragment.getDialogTitle().setText(newText);
    }


    /**
     * Set all the contents for the dialog popup base off of the city that was clicked
     */
    public void setContentsText(){
        setTitleText(mSelectedCity.getCityName());
        eraseCurrentContents();
        ArrayList<City> connectedCities = mSelectedCity.getConnectedCityAsArray();
        int routesIndex; // Used to access different elements in the gui

        for (int i = 0; i < connectedCities.size(); i++){
            routesIndex = i+1; // The elements that this accesses start at 1 not 0.
            City currentConnectedCity = connectedCities.get(i);
            Path path = ClientModel.SINGLETON.getPathByCities(mSelectedCity, currentConnectedCity);

            // Set each part of the list element
            getRouteTitleByIndex(routesIndex).setText(currentConnectedCity.getCityName());
            getColorHolderByIndex(routesIndex).setBackgroundColor(GraphicsUtils.getRealColorFromEnum(path.getPathColor()));
            getRouteLengthByIndex(routesIndex).setText(Integer.toString(path.getPoints()));
        }
    }

    /**
     * Erase the placeholder variables in the popup.
     * If we have only 2 connections for a city we dont want to see 5 other place holder list elements.
     */
    private void eraseCurrentContents(){
        final int MAX_CITY_CONNECTIONS = 7;
        for (int i = 1; i <= MAX_CITY_CONNECTIONS; i++) {
            getRouteTitleByIndex(i).setText("");
            getColorHolderByIndex(i).setText("");
            getRouteLengthByIndex(i).setText("");
        }
    }


    /**
     * I know switch statements are ugly and non-scalable but
     * better than messing around with adapters for time sake
     */
    private TextView getColorHolderByIndex(int index){
        switch(index){
            case 1:
                return mClaimRouteDialogFragment.getColorHolder1();
            case 2:
                return mClaimRouteDialogFragment.getColorHolder2();
            case 3:
                return mClaimRouteDialogFragment.getColorHolder3();
            case 4:
                return mClaimRouteDialogFragment.getColorHolder4();
            case 5:
                return mClaimRouteDialogFragment.getColorHolder5();
            case 6:
                return mClaimRouteDialogFragment.getColorHolder6();
            case 7:
                return mClaimRouteDialogFragment.getColorHolder7();
            default:
                return null;
        }
    }

    /**
     * I know switch statements are ugly and non-scalable but
     * better than messing around with adapters
     */
    private TextView getRouteTitleByIndex(int index){
        switch (index) {
            case 1:
                return mClaimRouteDialogFragment.getDestinationRoute1();
            case 2:
                return mClaimRouteDialogFragment.getDestinationRoute2();
            case 3:
                return mClaimRouteDialogFragment.getDestinationRoute3();
            case 4:
                return mClaimRouteDialogFragment.getDestinationRoute4();
            case 5:
                return mClaimRouteDialogFragment.getDestinationRoute5();
            case 6:
                return mClaimRouteDialogFragment.getDestinationRoute6();
            case 7:
                return mClaimRouteDialogFragment.getDestinationRoute7();
            default:
                return null;
        }
    }

    /**
     * I know switch statements are ugly and non-scalable but
     * better than messing around with adapters
     */
    private TextView getRouteLengthByIndex(int index){
        switch (index) {
            case 1:
                return mClaimRouteDialogFragment.getRouteLength1();
            case 2:
                return mClaimRouteDialogFragment.getRouteLength2();
            case 3:
                return mClaimRouteDialogFragment.getRouteLength3();
            case 4:
                return mClaimRouteDialogFragment.getRouteLength4();
            case 5:
                return mClaimRouteDialogFragment.getRouteLength5();
            case 6:
                return mClaimRouteDialogFragment.getRouteLength6();
            case 7:
                return mClaimRouteDialogFragment.getRouteLength7();
            default:
                return null;

        }
    }
}
