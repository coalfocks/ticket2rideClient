package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.ClaimRouteDialogPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

import java.util.ArrayList;

/**
 * Created by tyudy on 3/27/17.
 */

public class ClaimRouteDialogFragment extends DialogFragment {

    ClaimRouteDialogPresenter mClaimRouteDialogPresenter;

    TextView mDialogTitle;
    LinearLayout mRouteContainer1;
    LinearLayout mRouteContainer2;
    LinearLayout mRouteContainer3;
    LinearLayout mRouteContainer4;
    LinearLayout mRouteContainer5;
    LinearLayout mRouteContainer6;
    LinearLayout mRouteContainer7;

    TextView mDestinationRoute1;
    TextView mDestinationRoute2;
    TextView mDestinationRoute3;
    TextView mDestinationRoute4;
    TextView mDestinationRoute5;
    TextView mDestinationRoute6;
    TextView mDestinationRoute7;

    TextView mColorHolder1;
    TextView mColorHolder2;
    TextView mColorHolder3;
    TextView mColorHolder4;
    TextView mColorHolder5;
    TextView mColorHolder6;
    TextView mColorHolder7;

    TextView mRouteLength1;
    TextView mRouteLength2;
    TextView mRouteLength3;
    TextView mRouteLength4;
    TextView mRouteLength5;
    TextView mRouteLength6;
    TextView mRouteLength7;

    Button mExitButton;
    Button mConfirmButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Add this instance of the class the this classes presenter
        mClaimRouteDialogPresenter = PresenterHolder.SINGLETON.getClaimRouteDialogPresenter();
        mClaimRouteDialogPresenter.setClaimRouteDialogFragment(this);

        return inflater.inflate(R.layout.claim_route_dialog, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDialogTitle = (TextView) view.findViewById(R.id.route_dialog_title);

        mRouteContainer1 = (LinearLayout) view.findViewById(R.id.route_item_1);
        mRouteContainer2 = (LinearLayout) view.findViewById(R.id.route_item_2);
        mRouteContainer3 = (LinearLayout) view.findViewById(R.id.route_item_3);
        mRouteContainer4 = (LinearLayout) view.findViewById(R.id.route_item_4);
        mRouteContainer5 = (LinearLayout) view.findViewById(R.id.route_item_5);
        mRouteContainer6 = (LinearLayout) view.findViewById(R.id.route_item_6);
        mRouteContainer7 = (LinearLayout) view.findViewById(R.id.route_item_7);

        mDestinationRoute1 = (TextView) view.findViewById(R.id.route_item_1_name);
        mDestinationRoute2 = (TextView) view.findViewById(R.id.route_item_2_name);
        mDestinationRoute3 = (TextView) view.findViewById(R.id.route_item_3_name);
        mDestinationRoute4 = (TextView) view.findViewById(R.id.route_item_4_name);
        mDestinationRoute5 = (TextView) view.findViewById(R.id.route_item_5_name);
        mDestinationRoute6 = (TextView) view.findViewById(R.id.route_item_6_name);
        mDestinationRoute7 = (TextView) view.findViewById(R.id.route_item_7_name);

        mColorHolder1 = (TextView) view.findViewById(R.id.route_color_1);
        mColorHolder2 = (TextView) view.findViewById(R.id.route_color_2);
        mColorHolder3 = (TextView) view.findViewById(R.id.route_color_3);
        mColorHolder4 = (TextView) view.findViewById(R.id.route_color_4);
        mColorHolder5 = (TextView) view.findViewById(R.id.route_color_5);
        mColorHolder6 = (TextView) view.findViewById(R.id.route_color_6);
        mColorHolder7 = (TextView) view.findViewById(R.id.route_color_7);

        mRouteLength1 = (TextView) view.findViewById(R.id.route_item_1_length);
        mRouteLength2 = (TextView) view.findViewById(R.id.route_item_2_length);
        mRouteLength3 = (TextView) view.findViewById(R.id.route_item_3_length);
        mRouteLength4 = (TextView) view.findViewById(R.id.route_item_4_length);
        mRouteLength5 = (TextView) view.findViewById(R.id.route_item_5_length);
        mRouteLength6 = (TextView) view.findViewById(R.id.route_item_6_length);
        mRouteLength7 = (TextView) view.findViewById(R.id.route_item_7_length);

        mExitButton = (Button) view.findViewById(R.id.exit_claim_route_dialog);
        mConfirmButton = (Button) view.findViewById(R.id.claim_route_button);

        // Set the contents of the whole popup
        mClaimRouteDialogPresenter.setContentsText();

        //LISTENERS_---------------------------------------------------------------

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClaimRouteDialogPresenter.exitButtonClicked();
            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClaimRouteDialogPresenter.confirmButtonClicked();
            }
        });
    }


    //GETTERS_--------------------------------------------------------------------

    public TextView getDialogTitle() {
        return mDialogTitle;
    }

    public TextView getDestinationRoute1() {
        return mDestinationRoute1;
    }

    public TextView getDestinationRoute2() {
        return mDestinationRoute2;
    }

    public TextView getDestinationRoute3() {
        return mDestinationRoute3;
    }

    public TextView getDestinationRoute4() {
        return mDestinationRoute4;
    }

    public TextView getDestinationRoute5() {
        return mDestinationRoute5;
    }

    public TextView getDestinationRoute6() {
        return mDestinationRoute6;
    }

    public TextView getDestinationRoute7() {
        return mDestinationRoute7;
    }

    public TextView getColorHolder1() {
        return mColorHolder1;
    }

    public TextView getColorHolder2() {
        return mColorHolder2;
    }

    public TextView getColorHolder3() {
        return mColorHolder3;
    }

    public TextView getColorHolder4() {
        return mColorHolder4;
    }

    public TextView getColorHolder5() {
        return mColorHolder5;
    }

    public TextView getColorHolder6() { return mColorHolder6; }

    public TextView getColorHolder7() {
        return mColorHolder7;
    }

    public TextView getRouteLength1() {
        return mRouteLength1;
    }

    public TextView getRouteLength2() {
        return mRouteLength2;
    }

    public TextView getRouteLength3() {
        return mRouteLength3;
    }

    public TextView getRouteLength4() {
        return mRouteLength4;
    }

    public TextView getRouteLength5() {
        return mRouteLength5;
    }

    public TextView getRouteLength6() {
        return mRouteLength6;
    }

    public TextView getRouteLength7() {
        return mRouteLength7;
    }


}


