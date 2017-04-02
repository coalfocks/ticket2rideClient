package com.example.tyudy.ticket2rideclient.presenters;

import android.app.Activity;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.cards.FaceUpCards;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCardCollection;
import com.example.tyudy.ticket2rideclient.fragments.DecksDialogFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import static com.example.tyudy.ticket2rideclient.common.ColorENUM.COLORLESS;
import static com.example.tyudy.ticket2rideclient.common.ColorENUM.RED;

/**
 * Created by tyudy on 3/24/17.
 */

public class DecksDialogPresenter  {
    DecksDialogFragment mDecksDialogFragment;
    FaceUpCards mFaceUpCards;

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
        if (card.getColor() == COLORLESS) {
            return;
        }
        ClientModel.SINGLETON.addTrainCard(card);
        MethodsFacade.SINGLETON.selectTrainCard(cardNumber);

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
}
