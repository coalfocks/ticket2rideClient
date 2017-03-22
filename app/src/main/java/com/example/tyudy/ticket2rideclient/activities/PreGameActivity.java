package com.example.tyudy.ticket2rideclient.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.ClientCommunicator;
import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.fragments.GameBoardFragment;
import com.example.tyudy.ticket2rideclient.fragments.GameSelectionFragment;
import com.example.tyudy.ticket2rideclient.fragments.LoginFragment;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

public class PreGameActivity extends AppCompatActivity implements iObserver {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = new LoginFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

    public void onLogin(){
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, new GameSelectionFragment()).commit(); // Go to the GameSelectionFragment
    }

    public void onLoginAndGame(int inProgress) {
        if (inProgress == 0) {
            Intent i = new Intent(this, GameLobbyActivity.class);
            startActivity(i);
        } else
        {
            Intent i = new Intent(this, GameBoardActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        MethodsFacade.SINGLETON.setContext(this);
    }

    @Override
    public void observe() {

    }
    @Override
    public void onStop() {
        super.onStop();
        ClientModel.SINGLETON.removeObserver((iObserver) this);
    }

}
