package com.example.tyudy.ticket2rideclient.common.decks;

import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cards.iCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zacheaton on 3/7/17.
 */
public class DestinationCardDeck implements iDeck, Serializable {
    List<iCard> cards = new ArrayList<iCard>();

    public DestinationCardDeck() {}

    public List<iCard> getDeck() { return cards; }

    public void shuffle(){
        Collections.shuffle(this.cards);
    };

    public  void addCard(iCard card){
        this.cards.add(0, card);
    }

    public  iCard getCard(){
        if(cards.size()>0) {
            iCard myCard = cards.get(cards.size() - 1);
            cards.remove(cards.size() - 1);
            return myCard;
        }
        else{
            return null;
        }
    }
}
