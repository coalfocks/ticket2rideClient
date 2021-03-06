package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
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
    Activity mActivity;
    ClaimRouteDialogFragment mClaimRouteDialogFragment;
    City mClickedCity;    // City that the user clicked on
    City mSelectedCity;   // City that was selected from the popup
    LinearLayout mSelectedListItem;

    public ClaimRouteDialogPresenter(){
        setToBaseValues();
    }

    /**
     * Sets the Presenter to its base state. Needs to be called when dialog goes away
     */
    private void setToBaseValues(){
        mSelectedListItem = null;
        mSelectedCity = null;
    }

    public void setClaimRouteDialogFragment(ClaimRouteDialogFragment claimRouteDialogFragment){
        mClaimRouteDialogFragment = claimRouteDialogFragment;
    }

    public void showDialog(Activity gameBoardActivity, City userClickedCity){
        mActivity = gameBoardActivity;
        mClickedCity = userClickedCity;
        mClaimRouteDialogFragment = new ClaimRouteDialogFragment();
        mClaimRouteDialogFragment.show(gameBoardActivity.getFragmentManager(), "decks_dialog_fragment");
    }

    public void exitButtonClicked(){
        setToBaseValues();
        mClaimRouteDialogFragment.dismiss();
    }

    public void claimButtonClicked(){
        // TODO: Check if user has enough train cards and trains
        if (mSelectedCity == null) {
            Toast.makeText(mActivity, "You must select a city before claiming", Toast.LENGTH_SHORT).show();
            return;
        }

        Path chosenPath = ClientModel.SINGLETON.getPathByCities(mClickedCity, mSelectedCity);
        MethodsFacade.SINGLETON.claimPath(chosenPath);

        setToBaseValues();
        mClaimRouteDialogFragment.dismiss();

    }

    public void routeClicked(int index) {

        // We shouldn't do anything if a space was clicked that isn't inhabited with info
        if(getRouteTitleByIndex(index).getText().toString().equals("")){
            return;
        }

        // Cached the city that was previously clicked to unhighlight it later
        LinearLayout oldSelection = mSelectedListItem;

        mSelectedListItem = getRouteHolderByIndex(index);
        mSelectedListItem.setBackgroundColor(Color.LTGRAY);

        // Cache the actually city that belongs to the selectedListItem
        String selectedCityName = getRouteTitleByIndex(index).getText().toString();
        mSelectedCity = ClientModel.SINGLETON.getCityByName(selectedCityName);

        if(oldSelection != null && oldSelection != mSelectedListItem) {   // If a route had previously been selected and the same selected route wasn't re clicked
            oldSelection.setBackgroundColor(0);   // Sets background to transparent
        }




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
        setTitleText(mClickedCity.getCityName());
        eraseCurrentContents();
        ArrayList<City> connectedCities = mClickedCity.getConnectedCityAsArray();
        int routesIndex; // Used to access different elements in the gui

        for (int i = 0; i < connectedCities.size(); i++){
            routesIndex = i+1; // The elements that this accesses start at 1 not 0.
            City currentConnectedCity = connectedCities.get(i);
            Path path = ClientModel.SINGLETON.getPathByCities(mClickedCity, currentConnectedCity);

            // Set each part of the list element
            getRouteTitleByIndex(routesIndex).setText(currentConnectedCity.getCityName());
            getColorHolderByIndex(routesIndex).setBackgroundColor(GraphicsUtils.getRealColorFromEnum(path.getPathColor()));
            getRouteLengthByIndex(routesIndex).setText(Integer.toString(path.getDistance()));
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

    private LinearLayout getRouteHolderByIndex(int index){
        switch (index) {
            case 1:
                return mClaimRouteDialogFragment.getRouteContainer1();
            case 2:
                return mClaimRouteDialogFragment.getRouteContainer2();
            case 3:
                return mClaimRouteDialogFragment.getRouteContainer3();
            case 4:
                return mClaimRouteDialogFragment.getRouteContainer4();
            case 5:
                return mClaimRouteDialogFragment.getRouteContainer5();
            case 6:
                return mClaimRouteDialogFragment.getRouteContainer6();
            case 7:
                return mClaimRouteDialogFragment.getRouteContainer7();
            default:
                return null;
        }
    }
}
