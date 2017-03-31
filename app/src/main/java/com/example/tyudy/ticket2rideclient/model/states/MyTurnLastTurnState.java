//package com.example.tyudy.ticket2rideclient.model.states;
//
//import com.example.tyudy.ticket2rideclient.interfaces.IState;
//
///**
// * Created by Trevor on 3/15/2017.
// */
//
//public class MyTurnLastTurnState implements IState {
//
//    @Override
//    public IState drawTrainCard() {
//        return new MyTurnLastTurnDrewOneTrainCard();
//    }
//
//    @Override
//    public IState drawDestinationCard() {
//        return new EndGameState();
//    }
//
//    @Override
//    public IState pickTrainCard() {
//        return new MyTurnLastTurnDrewOneTrainCard();
//    }
//
//    @Override
//    public IState claimPath() {
//        return new EndGameState();
//    }
//
//    @Override
//    public IState returnDestinationCard() {
//        return this;
//    }
//
//    @Override
//    public IState scorePoints() {
//        return this;
//    }
//
//    @Override
//    public IState endTurn() {
//        return new EndGameState();
//    }
//
//    @Override
//    public IState pickedWild() {
//        return new EndGameState();
//    }
//}
