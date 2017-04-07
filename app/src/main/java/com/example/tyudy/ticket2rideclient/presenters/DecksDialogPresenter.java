package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.graphics.Color;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.cards.FaceUpCards;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCardCollection;
import com.example.tyudy.ticket2rideclient.fragments.DecksDialogFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.model.states.MyTurnBeganState;

import static com.example.tyudy.ticket2rideclient.common.ColorENUM.RED;

/**
 * Created by tyudy on 3/24/17.
 */

public class DecksDialogPresenter  {
    DecksDialogFragment mDecksDialogFragment;
    FaceUpCards mFaceUpCards;
    int firstDrawCardNumber;

    public FaceUpCards getmFaceUpCards()
    {
        return mFaceUpCards;
    }

    public void setmFaceUpCards(FaceUpCards mFaceUpCards)
    {
        this.mFaceUpCards = mFaceUpCards;
    }

    public DecksDialogPresenter(){
        mFaceUpCards = new FaceUpCards();
        firstDrawCardNumber = 0;

        TrainCardCollection one = new TrainCardCollection(RED);
        TrainCardCollection two = new TrainCardCollection(RED);
        TrainCardCollection three = new TrainCardCollection(RED);
        TrainCardCollection four = new TrainCardCollection(RED);
        TrainCardCollection five = new TrainCardCollection(RED);

        mFaceUpCards.setCard1(one);
        mFaceUpCards.setCard2(two);
        mFaceUpCards.setCard3(three);
        mFaceUpCards.setCard4(four);
        mFaceUpCards.setCard5(five);
    }

    public void setDecksDialogFragment(DecksDialogFragment decksDialogFragment){
        mDecksDialogFragment = decksDialogFragment;
    }

    public void showDialog(Activity gameBoardActivity){
        mDecksDialogFragment = new DecksDialogFragment();
        mDecksDialogFragment.show(gameBoardActivity.getFragmentManager(), "decks_dialog_fragment");
    }

    public void exitClicked(){
        mDecksDialogFragment.dismiss();
    }

    public void destDeckClicked() {
        MethodsFacade.SINGLETON.drawDestCard();
    }

    public void trainDeckClicked() {
        MethodsFacade.SINGLETON.drawTrainCard();
    }


    /**
     * triggered when a card is clicked in the DecksDialogFragment
     * @param cardNumber - IMPORTANT, this param is not an index, its just the order on the screen from left to right
     */
    public void cardClicked(int cardNumber){
        //card to client model
        //card to server model
        //replace faceups server
        //replace faceups model

        if (!ClientModel.SINGLETON.canDrawTrainCard()) {
            return;
        }

        TrainCardCollection card;
        switch (cardNumber) {
            case 1 :
                card = mFaceUpCards.getCard1();
                break;
            case 2 :
                card = mFaceUpCards.getCard2();
                break;
            case 3 :
                card = mFaceUpCards.getCard3();
                break;
            case 4 :
                card = mFaceUpCards.getCard4();
                break;
            case 5 :
                card = mFaceUpCards.getCard5();
                break;
            default :
                card = new TrainCardCollection();
        }

        // This should probably be in the ClientModel.SINGLETON.canDrawTrainCard function but im tired af rn and this will work
        // Don't allow player to pick the new wild card on their second picked if a wild was turned over from the deck.
        if (firstDrawCardNumber == cardNumber &&
                card.getColor() == ColorENUM.WILD) {
            Toast.makeText(mDecksDialogFragment.getActivity(), "You can't take the new wild card!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Only set this var on your first draw.
        if (ClientModel.SINGLETON.getCurrentState().getClass() == MyTurnBeganState.class) {
            firstDrawCardNumber = cardNumber;
        } else {
            firstDrawCardNumber = 0;
        }

        ClientModel.SINGLETON.addTrainCard(card);
        MethodsFacade.SINGLETON.selectTrainCard(cardNumber);

        // If a wild was chosen change the turn right away
        if (card.getColor() == ColorENUM.WILD) {
            MethodsFacade.SINGLETON.changeTurn();
        }



    }

    public int getCardImage(TrainCardCollection card) {
        ColorENUM color = card.getColor();
        switch(color) {
            case WHITE:
                return R.drawable.white_train_card;

            case BLACK:
                return R.drawable.black_train_card;

            case BLUE:
                return R.drawable.blue_train_card;

            case RED:
                return R.drawable.red_train_card;

            case ORANGE:
                return R.drawable.red_train_card;

            case YELLOW:
                return R.drawable.yellow_train_card;

            case PURPLE:
                return R.drawable.purple_train_card;

            case GREEN:
                return R.drawable.green_train_card;

            case WILD:
                return R.drawable.wild_train_card;

            default:
                break;
        }
        return 0;
    }

    public int getFirstDrawCardNumber(){
        return firstDrawCardNumber;
    }
}
