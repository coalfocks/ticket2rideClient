package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.iCommand;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2017.
 */
public class RegisterCommand extends Command implements iCommand, Serializable
{
    public RegisterCommand(){}

    @Override
    public DataTransferObject execute()
    {
        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
        if(data.getErrorMsg().length()!=0){
            Toast.makeText(jeffery, data.getErrorMsg(), Toast.LENGTH_SHORT).show();
            return null;
        }
        else {
            try {
                Toast.makeText(jeffery, data.getData(), Toast.LENGTH_SHORT).show();
                jeffery.getSupportFragmentManager().popBackStack();

            } catch (Exception e){
                e.printStackTrace();
                Log.d("RegisterCommand", e.getMessage());
            }
        }
        return null;
    }
}
