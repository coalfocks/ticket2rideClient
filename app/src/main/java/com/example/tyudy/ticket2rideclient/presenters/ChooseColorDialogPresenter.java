package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.fragments.ChooseColorDialogFragment;

/**
 * Created by tyudy on 3/31/17.
 */

public class ChooseColorDialogPresenter {
    Activity mActivity;
    ChooseColorDialogFragment mChooseColorDialogFragment;
    Path mAssociatedPath;

    public ChooseColorDialogPresenter(){

    }

    public void setChooseColorDialogFragment(ChooseColorDialogFragment chooseColorDialogFragment) {
        mChooseColorDialogFragment = chooseColorDialogFragment;
    }

    public void showDialog(Activity gameBoardActivity, Path associatedPath){
        mActivity = gameBoardActivity;
        mAssociatedPath = associatedPath;
        mChooseColorDialogFragment = new ChooseColorDialogFragment();
        mChooseColorDialogFragment.show(gameBoardActivity.getFragmentManager(), "decks_dialog_fragment");
    }

    public void blueButtonClicked(){
        Toast.makeText(mActivity, "Blue Cards Selected", Toast.LENGTH_SHORT).show();
        executeButtonClick(ColorENUM.BLUE);
    }

    public void redButtonClicked(){
        Toast.makeText(mActivity, "Red Cards Selected", Toast.LENGTH_SHORT).show();
        executeButtonClick(ColorENUM.RED);
    }

    public void orangeButtonClicked(){
        Toast.makeText(mActivity, "Orange Cards Selected", Toast.LENGTH_SHORT).show();
        executeButtonClick(ColorENUM.ORANGE);
    }

    public void greenButtonClicked(){
        Toast.makeText(mActivity, "Green Cards Selected", Toast.LENGTH_SHORT).show();
        executeButtonClick(ColorENUM.GREEN);
    }

    public void purpleButtonClicked(){
        Toast.makeText(mActivity, "Purple Cards Selected", Toast.LENGTH_SHORT).show();
        executeButtonClick(ColorENUM.PURPLE);
    }

    public void blackButtonClicked(){
        Toast.makeText(mActivity, "Black Cards Selected", Toast.LENGTH_SHORT).show();
        PresenterHolder.SINGLETON.getClaimRouteDialogPresenter().setSelectedListItemColor(ColorENUM.BLACK);
        executeButtonClick(ColorENUM.BLACK);
    }

    public void yellowButtonClicked(){
        Toast.makeText(mActivity, "Yellow Cards Selected", Toast.LENGTH_SHORT).show();
        executeButtonClick(ColorENUM.YELLOW);
    }

    public void whiteButtonClicked(){
        Toast.makeText(mActivity, "White Cards Selected", Toast.LENGTH_SHORT).show();
        executeButtonClick(ColorENUM.WHITE);
    }

    public void wildButtonClicked(){
        Toast.makeText(mActivity, "Wild Cards Selected", Toast.LENGTH_SHORT).show();
        executeButtonClick(ColorENUM.WILD);
    }

    /**
     * Sets the associated Paths color to the color that the user selected, This will make the rest of the functionality
     * use that color of cards from the user.
     * Continues in the claimingPath process and closes the popup.
     */
    private void executeButtonClick(ColorENUM selectedColor){
        mAssociatedPath.setPathColor(selectedColor);
        MethodsFacade.SINGLETON.claimPath(mAssociatedPath);
        mChooseColorDialogFragment.dismiss();
    }
}
