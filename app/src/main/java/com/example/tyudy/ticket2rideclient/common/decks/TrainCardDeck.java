package com.example.tyudy.ticket2rideclient.common.decks;

import com.example.tyudy.ticket2rideclient.common.cards.FaceUpCards;
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

    private List<iCard> cards = new ArrayList<iCard>();
    private FaceUpCards faceUpCards = new FaceUpCards();

    //initialize the deck
    public TrainCardDeck()
    {

    }

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
        } else {
            return null;
        }
    }

    public FaceUpCards getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(FaceUpCards faceUpCards) {
        this.faceUpCards = faceUpCards;
    }
}
