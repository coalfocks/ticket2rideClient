package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.DisplayDestinationCardsPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

import java.util.ArrayList;

/**
 * Created by Trevor on 3/3/2017.
 */

public class DisplayDestCardsDialogFragment extends DialogFragment {
    private ArrayList<DestinationCard> destinationCards;
    private Activity gameBoardActivity;
    private ListView allCardsView;
    private DisplayCardsAdapter adapter;
    private DisplayDestinationCardsPresenter mDisplayDestinationCardsPresenter;

    public DisplayDestCardsDialogFragment(){
        destinationCards = new ArrayList<>();
    }

    public void setGameBoardActivity(Activity a) { gameBoardActivity = a; }

    /**
     * Set the list of destination cards for the adapter to use
     * @param list
     */
    public void setCardList(ArrayList<DestinationCard> list) {
        destinationCards = list;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Add this instance of the class the this classes presenter
        mDisplayDestinationCardsPresenter = PresenterHolder.SINGLETON.getDisplayDestinationCardsPresenter();
        mDisplayDestinationCardsPresenter.setDisplayDestCardsDialogFragment(this);


        AlertDialog.Builder builder = new AlertDialog.Builder(gameBoardActivity);
        View v = gameBoardActivity.getLayoutInflater().inflate(R.layout.scroll_view, null);

        adapter = new DisplayCardsAdapter(gameBoardActivity.getBaseContext(),
                R.layout.display_destination_cards, destinationCards);

        allCardsView = (ListView) v.findViewById(R.id.genericScrollViewContainer);
        allCardsView.setAdapter(adapter);

        builder.setTitle(R.string.dest_cards).
                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel(); // Close the dialog
                    }
                }).
                setCancelable(false).
                setIcon(R.drawable.dest_cards_icon).
                setView(v);

        return builder.create();
    }

    private class DisplayCardsAdapter extends ArrayAdapter<DestinationCard> {
        private Context mContext;

        public DisplayCardsAdapter(Context c, int resourceId, ArrayList<DestinationCard> cards){
            super(c, resourceId, cards);
            mContext = c;
        }

        /**
         * ViewHolder class for holding the data needed in each list view
         */
        private class ViewHolder {
            TextView source;
            TextView dest;
            TextView points;
            CheckBox checkBox;

            // We shouldn't have the return option when just viewing our current cards (not just drawn)
            //CheckBox returnBox;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            DestinationCard card = getItem(position);

            if (card != null) {
                String src = "From:   " + card.getDestination().getSource();
                String dst = "To:     " + card.getDestination().getDest();
                String pts = "Points: " + String.valueOf(card.getPointValue());

                LayoutInflater mInflater = (LayoutInflater) mContext
                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.display_destination_cards, null);

                    holder = new ViewHolder();
                    holder.source = (TextView) convertView.findViewById(R.id.destCard_source);
                    holder.dest = (TextView) convertView.findViewById(R.id.destCard_dest);
                    holder.points = (TextView) convertView.findViewById(R.id.destCard_points);
                    holder.checkBox = (CheckBox) convertView.findViewById(R.id.completed_dest_checkbox);

                    //holder.returnBox = (CheckBox) convertView.findViewById(R.id.return_checkbox);


                    convertView.setTag(holder);
                } else
                    holder = (ViewHolder) convertView.getTag();

                if (ClientModel.SINGLETON.getCurrentUser().haveCompletedRoute(card))
                    holder.checkBox.setChecked(true);
                else
                    holder.checkBox.setChecked(false);

                holder.source.setText(src);
                holder.dest.setText(dst);
                holder.points.setText(pts);

                if (ClientModel.SINGLETON.getCurrentUser().haveCompletedRoute(card))
                    holder.checkBox.setChecked(true);
                else
                    holder.checkBox.setChecked(false);

//                if(holder.returnBox.isChecked())
//                    ClientModel.SINGLETON.getCurrentUser().removeDestinationCard(card);
//                holder.returnBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                           holder.returnBox.setChecked(true);
//                    }
//                });
            }

            return convertView;
        }
    }
}
