package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.DecksDialogPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

/**
 * Created by tyudy on 3/23/17.
 */

public class DecksDialogFragment extends DialogFragment {
    private DecksDialogPresenter mDecksDialogPresenter;

    private ImageButton mTrainDeckButton;
    private ImageButton mDestDeckButton;

    private ImageButton mCardButton1;
    private ImageButton mCardButton2;
    private ImageButton mCardButton3;
    private ImageButton mCardButton4;
    private ImageButton mCardButton5;

    private Button mExitButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Add this instance of the class the this classes presenter
        mDecksDialogPresenter = PresenterHolder.SINGLETON.getDecksDialogPresenter();
        mDecksDialogPresenter.setDecksDialogFragment(this);

        return inflater.inflate(R.layout.decks_dialog_fragment, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDestDeckButton = (ImageButton) view.findViewById(R.id.dest_cards_deck);
        mTrainDeckButton = (ImageButton) view.findViewById(R.id.train_card_deck);

        mCardButton1 = (ImageButton) view.findViewById(R.id.face_up_card_1);
        mCardButton1.setImageResource(mDecksDialogPresenter.getCardImage(mDecksDialogPresenter.getmFaceUpCards().getCard1()));
        mCardButton2 = (ImageButton) view.findViewById(R.id.face_up_card_2);
        mCardButton2.setImageResource(mDecksDialogPresenter.getCardImage(mDecksDialogPresenter.getmFaceUpCards().getCard2()));
        mCardButton3 = (ImageButton) view.findViewById(R.id.face_up_card_3);
        mCardButton3.setImageResource(mDecksDialogPresenter.getCardImage(mDecksDialogPresenter.getmFaceUpCards().getCard3()));
        mCardButton4 = (ImageButton) view.findViewById(R.id.face_up_card_4);
        mCardButton4.setImageResource(mDecksDialogPresenter.getCardImage(mDecksDialogPresenter.getmFaceUpCards().getCard4()));
        mCardButton5 = (ImageButton) view.findViewById(R.id.face_up_card_5);
        mCardButton5.setImageResource(mDecksDialogPresenter.getCardImage(mDecksDialogPresenter.getmFaceUpCards().getCard5()));

        mExitButton = (Button) view.findViewById(R.id.exit_decks_dialog_button);

        getDialog().setTitle(R.string.decks_dialog_title);

        //Set-OnClick-Listeners-----------------------------------------------------
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDecksDialogPresenter.exitClicked();
            }
        });

        mDestDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDecksDialogPresenter.destDeckClicked();
                dismiss();
            }
        });

        mTrainDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDecksDialogPresenter.trainDeckClicked();
            }
        });

        mCardButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDecksDialogPresenter.cardClicked(1);
            }
        });

        mCardButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDecksDialogPresenter.cardClicked(2);
            }
        });

        mCardButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDecksDialogPresenter.cardClicked(3);
            }
        });

        mCardButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDecksDialogPresenter.cardClicked(4);
            }
        });

        mCardButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDecksDialogPresenter.cardClicked(5);
            }
        });
    }

}
