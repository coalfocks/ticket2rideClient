package com.example.tyudy.ticket2rideclient.states;

import com.example.tyudy.ticket2rideclient.model.states.*;
import com.example.tyudy.ticket2rideclient.model.states.myturnstates.*;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by Trevor on 4/5/2017.
 */

public class StatesJunitTests {
    private IState state;

    /**
     * This test is the main focus of these tests, since
     * all state changing logic happens within MyTurnState.
     */
    @Test
    public void testMyTurnStates() throws Exception {
        state = new MyTurnState();

        // Should return itself for every action, since MyTurnState should
        // only be instantiated from its children states
        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn(), state);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);

        testNoActionState();

        testClaimRouteState();

        testDrawFirstState();
        testDrawSecondState();

        testPickFirstState();
        testPickSecondState();

        testDrawDestState();
        testReturnOneDestState();
        testReturnTwoDestState();
    }

    @Test
    public void testNotMyTurnState() throws Exception {
        state = new NotMyTurnState();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn(), state);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }

    @Test
    public void testEndGameState() throws Exception {
        state = new EndGameState();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn(), state);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }

    @Test
    public void testPreGameState() throws Exception {
        state = new PreGameState();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn(), state);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }


    private void testNoActionState() throws Exception {
        state = new NoAction();

        assertEquals(state.claimPath().getClass(), ClaimRoute.class);
        assertEquals(state.drawDest().getClass(), DrawDest.class);
        assertEquals(state.drawCard().getClass(), DrawFirst.class);
        assertEquals(state.pickCard().getClass(), PickFirst.class);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }

    private void testClaimRouteState() throws Exception {
        state = new ClaimRoute();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn().getClass(), NotMyTurnState.class);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }

    private void testDrawDestState() throws Exception {
        state = new DrawDest();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn().getClass(), NotMyTurnState.class);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard().getClass(), ReturnOneDest.class);
        assertEquals(state.scorePoints(), state);
    }

    private void testDrawFirstState() throws Exception {
        state = new DrawFirst();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard().getClass(), DrawSecond.class);
        assertEquals(state.endTurn(), state);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard().getClass(), PickSecond.class);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }

    private void testDrawSecondState() throws Exception {
        state = new DrawSecond();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn().getClass(), NotMyTurnState.class);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }

    private void testPickFirstState() throws Exception {
        state = new PickFirst();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard().getClass(), DrawSecond.class);
        assertEquals(state.endTurn(), state);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard().getClass(), PickSecond.class);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }

    private void testPickSecondState() throws Exception {
        state = new PickSecond();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn().getClass(), NotMyTurnState.class);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }

    private void testReturnOneDestState() throws Exception {
        state = new ReturnOneDest();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn().getClass(), NotMyTurnState.class);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard().getClass(), ReturnTwoDest.class);
        assertEquals(state.scorePoints(), state);
    }

    private void testReturnTwoDestState() throws Exception {
        state = new ReturnTwoDest();

        assertEquals(state.claimPath(), state);
        assertEquals(state.drawCard(), state);
        assertEquals(state.endTurn().getClass(), NotMyTurnState.class);
        assertEquals(state.drawDest(), state);
        assertEquals(state.pickCard(), state);
        assertEquals(state.returnCard(), state);
        assertEquals(state.scorePoints(), state);
    }
}
