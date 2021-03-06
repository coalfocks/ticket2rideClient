package com.example.tyudy.ticket2rideclient.common.decks;

import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.cards.FaceUpCards;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.cards.iCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zacheaton on 3/7/17.
 */
public class TrainCardDeck implements iDeck, Serializable
{

    List<iCard> cards = new ArrayList<iCard>();
    FaceUpCards faceUpCards = new FaceUpCards();

    //initialize the deck
    public TrainCardDeck()
    {
        for (ColorENUM c : ColorENUM.values())
        {
            for (int i = 0; i < 12; i++)
            {
                TrainCard newCard = new TrainCard(c);
                this.addCard(newCard);
            }
        }
        TrainCard newWild = new TrainCard(ColorENUM.COLORLESS);
        this.addCard(newWild);
        this.addCard(newWild);
        this.shuffle();
        faceUpCards.setCard1((TrainCard) this.getCard());
        faceUpCards.setCard2((TrainCard) this.getCard());
        faceUpCards.setCard3((TrainCard) this.getCard());
        faceUpCards.setCard4((TrainCard) this.getCard());
        faceUpCards.setCard5((TrainCard) this.getCard());
    }
//=======
//    private List<iCard> cards;
//    private TTRGame currentGame;
//
//    //initialize the deck
//    public TrainCardDeck(){
//        cards = new ArrayList<>();
//    }
//
//    public void initCards(){
//        //implemented on server side
//>>>>>>> cbb6986a99714fce3e1db850c6ee04b66e7a733c
//    }
//
//    public void setCurrentGame(TTRGame game) { currentGame = game; }

    public List<iCard> getDeck() { return cards; }

    public void shuffle(){
        Collections.shuffle(this.cards);
    };

    public void addCard(iCard card){
        this.cards.add(card);
    }

    public iCard getCard(){
        if(cards.size() > 0) {
            iCard myCard = cards.get(cards.size() - 1);
            cards.remove(cards.size() - 1);
            return myCard;
        }
//        else if (currentGame != null)
//        {
//            TrainCardDeck newDeck = currentGame.getTrainDiscardDeck();
//            newDeck.shuffle();
//            cards = newDeck.getDeck();
//
//            currentGame.clearTrainDiscardDeck();
//
//            return getCard();
//        }
        else
        {
            return null;
        }
    }

    public FaceUpCards getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(FaceUpCards faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public void swapFaceUpCard(int index) {
        switch (index) {
            case 1:
                faceUpCards.setCard1((TrainCard) this.getCard());
                break;
            case 2:
                faceUpCards.setCard2((TrainCard) this.getCard());
                break;
            case 3:
                faceUpCards.setCard3((TrainCard) this.getCard());
                break;
            case 4:
                faceUpCards.setCard4((TrainCard) this.getCard());
                break;
            case 5:
                faceUpCards.setCard5((TrainCard) this.getCard());
                break;
        }
    }
}
