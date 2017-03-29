package com.example.tyudy.ticket2rideclient.common.decks;

import com.example.tyudy.ticket2rideclient.common.ColorENUM;
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
    private List<iCard> cards;
    private TTRGame currentGame;

    //initialize the deck
    public TrainCardDeck(){
        cards = new ArrayList<>();
    }

    public void initCards(){
        //implemented on server side
    }

    public void setCurrentGame(TTRGame game) { currentGame = game; }

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
        else if (currentGame != null)
        {
            TrainCardDeck newDeck = currentGame.getTrainDiscardDeck();
            newDeck.shuffle();
            cards = newDeck.getDeck();

            currentGame.clearTrainDiscardDeck();

            return getCard();
        }
        else
        {
            return null;
        }
    }
}
