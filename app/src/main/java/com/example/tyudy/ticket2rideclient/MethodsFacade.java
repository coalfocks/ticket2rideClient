package com.example.tyudy.ticket2rideclient;

import android.support.v4.app.FragmentActivity;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.common.User;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by tyudy on 2/6/17.
 */

public class MethodsFacade {

    public static final MethodsFacade SINGLETON = new MethodsFacade();
    private FragmentActivity mContext;
    private Gson gson = new Gson();

    private MethodsFacade(){
        mContext = null;
    }

    /**
     * Function called by the LoginFragment when the Login button is clicked.
     * @param enteredName - name entered to perform login in the login fragment
     * @param enteredPassword - password entered to perform login in the login fragment
     * @return - a User object reflecting the logged in user
     */
    public void loginUser(String enteredName, String enteredPassword){
        User user = new User();
        user.setUsername(enteredName);
        user.setPassword(enteredPassword);
        String jsonUserString = gson.toJson(user);

        DataTransferObject dto = new DataTransferObject(); // Create DTO to pass to the proxy server
        dto.setData(jsonUserString);
        dto.setCommand("login");
        ServerProxy.SINGLETON.login(dto);
    }

    /**
     * Function called by the RegisterFragment when the register button is clicked.
     * Registers the user on the server if they have entered a valid user name and password
     * @param enteredName - name entered to perform login in the login fragment
     * @param enteredPassword - password entered to perform login in the login fragment
     * @return - a User object reflecting the registered user or null if the userName or password was invalid
     */
    public User registerUser(String enteredName, String enteredPassword){
        // IMPLEMENT ME!
        if(checkPassword(enteredName) && checkPassword(enteredPassword)){
            User user = new User();
            user.setUsername(enteredName);
            user.setPassword(enteredPassword);
            String s = gson.toJson(user);

            DataTransferObject dto = new DataTransferObject();
            dto.setData(s);
            dto.setCommand("register");
            ServerProxy.SINGLETON.register(dto);

        }
        return null;
    }

    /**
     * Replaces the current list of games in the model with the new one, called in the ListGamesCommand execute
     * @param gList - new list of games for the model
     */

    public void replaceModelsGames(ArrayList<TTRGame> gList){
        ClientModel.SINGLETON.replaceGames(gList);
    }

    /**
     * Called when the create game button is clicked in the GameSelectionFragment.
     * Creates a game based off of the current user and will add it to the games stored on the server.
     */
    public void createGame(){
        // User this curUser for any data that you may need (i.e. userName)
        DataTransferObject dto = new DataTransferObject();
        User user = ClientModel.SINGLETON.getCurrentUser();
        String s = gson.toJson(user);
        dto.setData(s);
        dto.setPlayerID(user.getPlayerID());
        dto.setCommand("create");
        ServerProxy.SINGLETON.createGame(dto);


    }

    public void startGame(){
        // User this curUser for any data that you may need (i.e. userName)
        TTRGame game = ClientModel.SINGLETON.getCurrentTTRGame();

        if(game != null){
            DataTransferObject dto = new DataTransferObject();
            String s = gson.toJson(game);
            dto.setData(s);
            dto.setPlayerID(ClientModel.SINGLETON.getCurrentTTRGame().getOwnerID());
            dto.setCommand("start");
            ServerProxy.SINGLETON.startGame(dto);
        }
    }


     public void getGameList() {

         DataTransferObject dto = new DataTransferObject();
         dto.setCommand("gameList");
         dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
         ServerProxy.SINGLETON.listGames(dto);
     }

    public void getCommands(int index) {
        DataTransferObject dto = new DataTransferObject();
        dto.setCommand(("getCommands"));
        dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
        dto.setData(String.valueOf(index) + "," + String.valueOf(ClientModel.SINGLETON.getCurrentTTRGame().getGameID()));
        ServerProxy.SINGLETON.getCommands(dto);
    }

    /**
     * Joins the currentUser into the game that was clicked on in the GameSelectionFragment.
     * At this point currentUser has already been set (Done at login) as well as the currentGame (done when clicked on)
     * @param gameToJoin - game that the currentUser is going to join
     */
    public void joinGame(TTRGame gameToJoin){

        DataTransferObject dto = new DataTransferObject();

        String s = String.valueOf(gameToJoin.getGameID());
        dto.setData(s);
        dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
        dto.setCommand("join");
        ServerProxy.SINGLETON.joinGame(dto);
    }

    public boolean checkPassword(String pass){
        if(pass == null || pass == ""){
            return false;
        }
        if(pass.length() > 10 || pass.length() < 2){
            return false;
        }

        else{
            return true;
        }
    }

    public void setContext(FragmentActivity jeffery){
        mContext = jeffery;
    }

    public FragmentActivity getContext(){
        return mContext;
    }

    public void addChat(String chat){
        ClientModel.SINGLETON.receiveNewChat(chat);
    }

    public void sendChatMessage(String chatMessage){
        // We don't want to send an empty string to the server
        if (!chatMessage.equals("")) {
            // Before sending to the server, patch on player's name before message
            String userName = ClientModel.SINGLETON.getCurrentUser().getUsername();
            StringBuilder message =
                    new StringBuilder(userName + ": " + chatMessage);

            DataTransferObject dto = new DataTransferObject();
            dto.setData(message.toString());
            dto.setCommand("sendChat");
            dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
            ServerProxy.SINGLETON.sendChatMessage(dto);
        }
    }

    /**
     * Send a path to the server and tell it its ready to go
     * @param path - path to be claimed
     */
    public void claimPath(Path path){
        DataTransferObject dto = new DataTransferObject();
        String pathData = gson.toJson(path);
        dto.setData(pathData);
        dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
        dto.setCommand("claimPath");
        ServerProxy.SINGLETON.claimPath(dto);
    }

    public ArrayList<Path> getModelPaths(){
        return ClientModel.SINGLETON.getPaths();
    }

    public void reset() {
        ClientModel.SINGLETON.setCurrentTTRGame(new TTRGame());
        ClientModel.SINGLETON.setCurrentUser(new User());
        ClientModel.SINGLETON.setObsList(new ArrayList<iObserver>());
    }

    public void drawDestCard() {
        DataTransferObject dto = new DataTransferObject();
        dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
        dto.setCommand("drawDestCard");
        dto.setData(String.valueOf(ClientModel.SINGLETON.getCurrentTTRGame().getGameID()));
        ServerProxy.SINGLETON.drawDestCard(dto);
    }

    public void sendBackDestCards(ArrayList<ArrayList<DestinationCard>> cards) {
        try
        {
            DataTransferObject dto = new DataTransferObject();
            dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
            dto.setCommand("sendBackDestCards");
            dto.setData(Serializer.serialize(cards));
            ServerProxy.SINGLETON.sendBackDestCards(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawTrainCard() {
        DataTransferObject dto = new DataTransferObject();
        dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
        dto.setCommand("drawTrainCard");
        dto.setData(String.valueOf(ClientModel.SINGLETON.getCurrentTTRGame().getGameID()));
        ServerProxy.SINGLETON.drawTrainCard(dto);
    }
}
