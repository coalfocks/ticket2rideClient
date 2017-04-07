package com.example.tyudy.ticket2rideclient.fragments;

import android.app.DialogFragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.ChooseColorDialogPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

/**
 * Created by tyudy on 3/31/17.
 */

public class ChooseColorDialogFragment extends DialogFragment {

    ChooseColorDialogPresenter mChooseColorDialogPresenter;

    Button mBlueButton;
    Button mRedButton;
    Button mOrangeButton;
    Button mGreenButton;
    Button mPurpleButton;
    Button mBlackButton;
    Button mYellowButton;
    Button mWhiteButton;
    Button mWildbutton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Add this instance of the class the this classes presenter
        mChooseColorDialogPresenter = PresenterHolder.SINGLETON.getChooseColorDialogPresenter();
        mChooseColorDialogPresenter.setChooseColorDialogFragment(this);

        return inflater.inflate(R.layout.choose_color_dialog, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String blues = String.valueOf(ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.BLUE).getNum());
        String reds = String.valueOf(ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.RED).getNum());
        String oranges = String.valueOf(ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.ORANGE).getNum());
        String greens = String.valueOf(ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.GREEN).getNum());
        String purples = String.valueOf(ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.PURPLE).getNum());
        String blacks = String.valueOf(ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.BLACK).getNum());
        String yellows = String.valueOf(ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.YELLOW).getNum());
        String whites = String.valueOf(ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.WHITE).getNum());
        String wilds = String.valueOf(ClientModel.SINGLETON.getCurrentUser().getTrainCardsOfColor(ColorENUM.WILD).getNum());

        //TODO: use presenter instead of directly talking to model
        mBlueButton = (Button) view.findViewById(R.id.blue_color_select_button);
        mBlueButton.setText("BLUE - " + blues);
        mRedButton = (Button) view.findViewById(R.id.red_color_select_button);
        mRedButton.setText("RED - " + reds);
        mOrangeButton = (Button) view.findViewById(R.id.orange_color_select_button);
        mOrangeButton.setText("ORANGE - " + oranges);
        mGreenButton = (Button) view.findViewById(R.id.green_color_select_button);
        mGreenButton.setText("GREEN - " + greens);
        mPurpleButton = (Button) view.findViewById(R.id.purple_color_select_button);
        mPurpleButton.setText("PURPLE - " + purples);
        mBlackButton = (Button) view.findViewById(R.id.black_color_select_button);
        mBlackButton.setText("BLACK - " + blacks);
        mYellowButton = (Button) view.findViewById(R.id.yellow_color_select_button);
        mYellowButton.setText("YELLOW - " + yellows);
        mWhiteButton = (Button) view.findViewById(R.id.white_color_select_button);
        mWhiteButton.setText("WHITE - " + whites);
        mWildbutton = (Button) view.findViewById(R.id.wild_color_select_button);
        mWildbutton.setText("WILD - " + wilds);


        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseColorDialogPresenter.blueButtonClicked();
            }
        });

        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseColorDialogPresenter.redButtonClicked();
            }
        });

        mOrangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseColorDialogPresenter.orangeButtonClicked();

            }
        });

        mGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseColorDialogPresenter.greenButtonClicked();
            }
        });

        mPurpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseColorDialogPresenter.purpleButtonClicked();
            }
        });

        mBlackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseColorDialogPresenter.blackButtonClicked();
            }
        });

        mYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseColorDialogPresenter.yellowButtonClicked();
            }
        });

        mWhiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseColorDialogPresenter.whiteButtonClicked();
            }
        });

        mWildbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseColorDialogPresenter.wildButtonClicked();
            }
        });

    }
}
