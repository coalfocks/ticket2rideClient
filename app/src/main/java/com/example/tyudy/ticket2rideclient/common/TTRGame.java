package com.example.tyudy.ticket2rideclient.common;

import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCardCollection;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.common.decks.DestinationCardDeck;
import com.example.tyudy.ticket2rideclient.common.decks.TrainCardDeck;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by colefox on 2/6/17.
 */
public class TTRGame implements Serializable
{
    private int inProgress;
    private int gameID;
    private int ownerID;
    private String ownerUsername;
    private Set<User> players = new TreeSet<User>();
    private int mTurnIndex = 0;
    private TrainCardDeck myTrainDeck;
    private DestinationCardDeck myDestDeck;
    private TrainCardDeck mTrainDiscardDeck;
    private DestinationCardDeck mDestDiscardDeck;

    public TTRGame() {
        mTrainDiscardDeck = new TrainCardDeck();
        mDestDiscardDeck = new DestinationCardDeck();
    }

    public void setMyTrainDeck(TrainCardDeck myTrainDeck) {
        this.myTrainDeck = myTrainDeck;
    }

    public void setMyDestDeck(DestinationCardDeck myDestDeck) {
        this.myDestDeck = myDestDeck;
    }

    public void addToTrainDiscard(TrainCardCollection card)
    {
        mTrainDiscardDeck.addCard(card);
    }

    public void addToDestDiscard(DestinationCard card)
    {
        mDestDiscardDeck.addCard(card);
    }

    public TrainCardDeck getMyTrainDeck() {
        return myTrainDeck;
    }

    public DestinationCardDeck getMyDestDeck() {
        return myDestDeck;
    }

    public int getInProgress()
    {
        return inProgress;
    }

    public void setInProgress(int inProgress)
    {
        this.inProgress = inProgress;
    }

    public int getOwnerID()
    {
        return ownerID;
    }

    public void setOwnerID(int ownerID)
    {
        this.ownerID = ownerID;
    }

    public void addPlayer(User player)
    {
        players.add(player);
    }

    public int getNumPlayers()
    {
        return players.size();
    }

    public String getOwnerUsername()
    {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername)
    {
        this.ownerUsername = ownerUsername;
    }

    public Set<User> getUsers()
    {
        return players;
    }

    public void setPlayers(Set<User> players)
    {
        this.players = players;
    }

    public int getGameID()
    {
        return gameID;
    }

    public void setGameID(int gameID)
    {
        this.gameID = gameID;
    }

    public int getmTurnIndex()
    {
        return mTurnIndex;
    }

    public void setmTurnIndex(int mTurnIndex)
    {
        this.mTurnIndex = mTurnIndex;
    }

    /**
     * Updates the current user's owned paths with given path, and sets its owner in ClientModel
     * @param path - The path the current user claimed
     */
    public void updateClaimedPath(Path path) {
        for (Path p : ClientModel.SINGLETON.getAllPaths())
        {
            if (p.getName().equals(path.getName()))
            {
                p.setOwner(ClientModel.SINGLETON.getCurrentUser());
                ClientModel.SINGLETON.getCurrentUser().claimPath(path);
                ClientModel.SINGLETON.getCurrentUser().addPoints(path.getPoints());
                return;
            }
        }
    }

    // dealTrainCard used by the server
    public TrainCardCollection dealTrainCard(int playerID){
        return null;
    }

    public void dealTrainCard(User u){
        TrainCardCollection myCard = (TrainCardCollection)  getMyTrainDeck().getCard();
        u.addTrainCard(myCard);
    }


    public void dealDestCard(User u){
        DestinationCard myCard = (DestinationCard) getMyDestDeck().getCard();
        u.addDestinationCard(myCard);

    }

    public TrainCardDeck getTrainDiscardDeck() { return mTrainDiscardDeck; }

    public DestinationCardDeck getDestDiscardDeck() { return mDestDiscardDeck; }

    public void clearTrainDiscardDeck() { }//mTrainDiscardDeck.getDeck().clear(); }

    public void clearDestDiscardDeck() { }//mDestDiscardDeck.getDeck().clear(); }

    public void changeTurn() {
        this.mTurnIndex++;
        mTurnIndex %= this.players.size();
    }

    public int getWhoTurn() {
        ArrayList<User> arr = new ArrayList<>(players);
        return arr.get(mTurnIndex).getPlayerID();
    }

    public void setUsers(Set<User> users) {
        this.players = users;
    }
}
