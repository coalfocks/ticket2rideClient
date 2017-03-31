package com.example.tyudy.ticket2rideclient.exceptions;

/**
 * Created by tyudy on 3/30/17.
 */

public class TrainCardDoesNotExist extends RuntimeException {

    public TrainCardDoesNotExist(String message){
        super(message);
    }

}
