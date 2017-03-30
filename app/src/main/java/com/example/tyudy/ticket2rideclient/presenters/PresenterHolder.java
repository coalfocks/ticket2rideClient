package com.example.tyudy.ticket2rideclient.presenters;

/**
 * Created by tyudy on 2/24/17.
 * This class is meant as just a holder for each presenter instance that the app will hold. (One per view).
 * From outside this class the PresenterHolder.SINGLETON.getExamplePresenter functions can be called to access
 * each presenter and then use that presenter to update the view.
 */

public class PresenterHolder {

    public static final PresenterHolder SINGLETON = new PresenterHolder();

    private LoginPresenter mLoginPresenter;
    private RegisterPresenter mRegisterPresenter;
    private GameSelectionPresenter mGameSelectionPresenter;
    private GameLobbyPresenter mGameLobbyPresenter;
    private GameBoardPresenter mGameBoardPresenter;
    private ChatPresenter mChatPresenter;
    private DisplayDestinationCardsPresenter mDisplayDestinationCardsPresenter;
    private DecksDialogPresenter mDecksDialogPresenter;
    private GetDestCardsPresenter mGetDestCardsPresenter;
    private ClaimRouteDialogPresenter mClaimRouteDialogPresenter;
    private InitDestCardsPresenter mInitDestCardsPresenter;

    private PresenterHolder(){
        mLoginPresenter = new LoginPresenter();
        mRegisterPresenter = new RegisterPresenter();
        mGameSelectionPresenter = new GameSelectionPresenter();
        mGameLobbyPresenter = new GameLobbyPresenter();
        mGameBoardPresenter = new GameBoardPresenter();
        mChatPresenter = new ChatPresenter();
        mDisplayDestinationCardsPresenter = new DisplayDestinationCardsPresenter();
        mDecksDialogPresenter = new DecksDialogPresenter();
        mGetDestCardsPresenter = new GetDestCardsPresenter();
        mClaimRouteDialogPresenter = new ClaimRouteDialogPresenter();
        mInitDestCardsPresenter = new InitDestCardsPresenter();
    }

    /*
     * GETTERS
     */

    public LoginPresenter getLoginPresenter() {
        return mLoginPresenter;
    }

    public RegisterPresenter getRegisterPresenter() {
        return mRegisterPresenter;
    }

    public GameSelectionPresenter getGameSelectionPresenter() {
        return mGameSelectionPresenter;
    }

    public GameLobbyPresenter getGameLobbyPresenter() {
        return mGameLobbyPresenter;
    }

    public GameBoardPresenter getGameBoardPresenter() {
        return mGameBoardPresenter;
    }

    public ChatPresenter getChatPresenter(){
        return mChatPresenter;
    }

    public DisplayDestinationCardsPresenter getDisplayDestinationCardsPresenter(){
        return mDisplayDestinationCardsPresenter;
    }

    public DecksDialogPresenter getDecksDialogPresenter(){
        return mDecksDialogPresenter;
    }

    public GetDestCardsPresenter getDestCardsPresenter() { return  mGetDestCardsPresenter; }

    public ClaimRouteDialogPresenter getClaimRouteDialogPresenter(){
        return mClaimRouteDialogPresenter;
    }

    public InitDestCardsPresenter getInitDestCardsPresenter() {
        return mInitDestCardsPresenter;
    }

}