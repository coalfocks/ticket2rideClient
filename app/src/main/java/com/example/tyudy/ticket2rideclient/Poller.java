package com.example.tyudy.ticket2rideclient;

import com.example.tyudy.ticket2rideclient.activities.GameLobbyActivity;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.sql.ClientInfoStatus;
import java.util.concurrent.TimeUnit;

/**
 * Created by Trevor on 2/11/2017.
 */
public class Poller  implements Runnable
{


    private static Poller poller;
    private boolean stop;
    private int wait;
    private int queueIndex;


    private Poller(){
        poller = null;
        stop = false;
        wait = 2;
        queueIndex = 0;
    }

    public static Poller getInstance()
    {
        if (poller == null)
        {
            poller = new Poller();
        }

        return poller;
    }

    // default wait of 2 seconds

    public void run()
    {
        stop = false;

        while(!stop)
        {
            try
            {
                TimeUnit.SECONDS.sleep(wait);
                poller.poll();

            }
            catch (InterruptedException e){
              e.printStackTrace();
            }
        }
    }


    public void stop() { stop = true; }

    public void setWait(int seconds)
    {
        wait = seconds;
    }

    public void incIndex (int amt) {
        this.queueIndex += amt;
    }

    public int getQueueIndex() { return this.queueIndex; }

    /**
     * Poll method, called every [amount of time] by ClientCommunicator
     */
    public void poll() {
        if (ClientModel.SINGLETON.getIpAddress() == null) {
            return;
        }

        // Only pull new games if the client is not playing a game, if they are playing then stop polling for the game list
        if ((ClientModel.SINGLETON.getCurrentTTRGame() != null &&
                ClientModel.SINGLETON.getCurrentTTRGame().getInProgress() != 1) ||
                (ClientModel.SINGLETON.getCurrentUser() != null &&
                ClientModel.SINGLETON.getCurrentTTRGame() == null)) {
            MethodsFacade.SINGLETON.getGameList();
        }
        if (ClientModel.SINGLETON.getCurrentTTRGame() != null  &&
            MethodsFacade.SINGLETON.getContext().getClass() == GameLobbyActivity.class) {
            MethodsFacade.SINGLETON.getCommands(queueIndex);
        }
    }

}
