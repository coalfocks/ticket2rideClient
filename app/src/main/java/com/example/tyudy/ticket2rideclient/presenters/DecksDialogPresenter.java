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

import static com.example.tyudy.ticket2rideclient.common.ColorENUM.COLORLESS;
import static com.example.tyudy.ticket2rideclient.common.ColorENUM.RED;

/**
 * Created by tyudy on 3/24/17.
 */

public class DecksDialogPresenter  {
    private DecksDialogFragment mDecksDialogFragment;
    private FaceUpCards mFaceUpCards;
    private int firstDrawCardNumber;

    public FaceUpCards getmFaceUpCards()
    {
        return mFaceUpCards;
    }

    public void setmFaceUpCards(FaceUpCards mFaceUpCards)
    {
        this.mFaceUpCards = mFaceUpCards;
    }

    /**
     * @pre - none
     * @post - creates a new instance of DecksDialogPresenter with all the fields initialized.
     * Creates a
     */
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

    /**
     * Makes the card selection dialog appear where users can draw cards (destination and train)
     * @pre - The gameBoardActivity is on screen and is the activity where the dialog is to be presented
     * @post - The dialog will come on screen
     * @param gameBoardActivity - the activity that the dialog will appear in
     */
    public void showDialog(Activity gameBoardActivity){
        mDecksDialogFragment = new DecksDialogFragment();
        mDecksDialogFragment.show(gameBoardActivity.getFragmentManager(), "decks_dialog_fragment");
    }

    /**
     * @pre - The fragment must already be on screen
     * @post -  The fragemtn is removed from the screen.
     */
    public void exitClicked(){
        mDecksDialogFragment.dismiss();
    }

    /**
     * Simulate drawing a destination card
     * @pre - the dialog must have already been inflated onto the screen
     * @post - If it is the users turn and they haven't done anything to change their turn state,
     * then they draw a destination card and their turn will be changed.
     */
    public void destDeckClicked() {
        MethodsFacade.SINGLETON.drawDestCard();
    }

    /**
     * Simulate drawing a train card
     * @pre - the dialog must have already been inflated onto the screen
     * @post - If it is the users turn and they haven't done anything to change their turn state or drewTrainCardState,
     * then they draw a train card and their turn will be changed if necessary.
     */
    public void trainDeckClicked() {
        MethodsFacade.SINGLETON.drawTrainCard();
    }


    /**
     * Triggered when a card is clicked in the DecksDialogFragment and does the appropriate action.
     * If it is not the persons turn that clicked it will just notyify them that it is not their turn.
     * @pre - This fragment is on screen.
     * @post - If the user is allowed (depending on the canDrawCard canDo function), they will gain the card they clicked
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

        if (!ClientModel.SINGLETON.canDrawTrainCardOfColor(card.getColor())) {
            return;
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

    /**
     * Returns the R.drawable int that can be referenced to draw a card on screen.
     * @pre - none
     * @post - receive an int referencing a drawable card that can be used to draw a card on screen.
     * @param card - used for its color to match to the drawable reference
     * @return - an int that can be used to reference the drawable card image
     */
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

    public int trainPiecesRemaining() {
        return ClientModel.SINGLETON.getUsersTrains().getSize();
    }

}
