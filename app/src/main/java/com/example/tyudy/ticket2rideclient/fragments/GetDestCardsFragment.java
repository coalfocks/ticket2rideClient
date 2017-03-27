package com.example.tyudy.ticket2rideclient.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.presenters.GetDestCardsPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

/**
 * Created by colefox on 3/26/17.
 */

public class GetDestCardsFragment extends DialogFragment
{
    private GetDestCardsPresenter mGetDestCardsPresenter;
    private Button mExitButton;

    private RelativeLayout mCard1;
    private RelativeLayout mCard2;
    private RelativeLayout mCard3;

    private TextView mCard1Source;
    private TextView mCard1Dest;
    private TextView mCard1Pts;
    private TextView mCard2Source;
    private TextView mCard2Dest;
    private TextView mCard2Pts;
    private TextView mCard3Source;
    private TextView mCard3Dest;
    private TextView mCard3Pts;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Add this instance of the class the this classes presenter
        mGetDestCardsPresenter = PresenterHolder.SINGLETON.getDestCardsPresenter();
        mGetDestCardsPresenter.setGetDestCardsFragment(this);

        return inflater.inflate(R.layout.get_dest_cards_dialog, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCard1 = (RelativeLayout) view.findViewById(R.id.card1);
        mCard2 = (RelativeLayout) view.findViewById(R.id.card2);
        mCard3 = (RelativeLayout) view.findViewById(R.id.card3);

        mCard1Source = (TextView) view.findViewById(R.id.card1_src);
        mCard1Dest = (TextView) view.findViewById(R.id.card1_dest);
        mCard1Pts = (TextView) view.findViewById(R.id.card1_pts);
        mCard2Source = (TextView) view.findViewById(R.id.card2_src);
        mCard2Dest = (TextView) view.findViewById(R.id.card2_dest);
        mCard2Pts = (TextView) view.findViewById(R.id.card2_pts);
        mCard3Source = (TextView) view.findViewById(R.id.card3_src);
        mCard3Dest = (TextView) view.findViewById(R.id.card3_dest);
        mCard3Pts = (TextView) view.findViewById(R.id.card3_pts);

        mExitButton = (Button) view.findViewById(R.id.exit_decks_dialog_button);

        getDialog().setTitle(R.string.decks_dialog_title);

        //Set-OnClick-Listeners-----------------------------------------------------
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetDestCardsPresenter.exitClicked();
            }
        });

        mCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetDestCardsPresenter.cardClicked(1);
            }
        });

        mCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetDestCardsPresenter.cardClicked(2);
            }
        });

        mCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetDestCardsPresenter.cardClicked(3);
            }
        });
    }

}
