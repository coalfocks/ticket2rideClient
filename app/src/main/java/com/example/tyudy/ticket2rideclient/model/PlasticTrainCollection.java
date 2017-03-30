package com.example.tyudy.ticket2rideclient.model;

import java.util.ArrayList;

/**
 * Created by tyudy on 3/28/17.
 */

public class PlasticTrainCollection {

    final int TRAIN_COLLECTION_MAX_SIZE = 45;
    ArrayList<PlasticTrain> mMyTrains;
    int mCollectionColor;

    /**
     * Don't pass an ENUM in here. They're kind pointless. We should use Color.RED for example from here on out
     * @param color - the color that can be used to represent the overall color of the collection of trains
     */
    public PlasticTrainCollection(int color){
        mCollectionColor = color;
        mMyTrains = new ArrayList<>();
        for(int i = 0; i < TRAIN_COLLECTION_MAX_SIZE; i++) { //Initialize with 45 train pieces
            mMyTrains.add(new PlasticTrain());
        }
    }

    public int getCollectionColor(){
        return mCollectionColor;
    }
    public int getSize(){
        return mMyTrains.size();
    }



    // Class for each individual train--------------------------------------------------------
    private class PlasticTrain {

        PlasticTrain(){

        }
    }
}
