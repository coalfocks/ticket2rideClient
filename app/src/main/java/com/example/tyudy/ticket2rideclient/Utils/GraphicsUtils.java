package com.example.tyudy.ticket2rideclient.Utils;

import android.graphics.Color;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;

/**
 * Created by tyudy on 3/28/17.
 */

public final class GraphicsUtils {
    private GraphicsUtils(){

    }

    public final static int YELLOW = android.graphics.Color.YELLOW;
    public final static int PURPLE = android.graphics.Color.MAGENTA;
    public final static int BLACK = android.graphics.Color.BLACK;
    public final static int WHITE = android.graphics.Color.WHITE;
    public final static int GREEN = android.graphics.Color.GREEN;
    public final static int ORANGE = android.graphics.Color.rgb(239, 163, 33); // Yes, this is Orange
    public final static int RED = android.graphics.Color.RED;
    public final static int BLUE = android.graphics.Color.BLUE;
    public final static int COLORLESS = android.graphics.Color.LTGRAY;
    public final static int WILD = android.graphics.Color.CYAN;

    public static int getRealColorFromEnum(ColorENUM color){
        switch(color) {
            case YELLOW:
                return Color.YELLOW;
            case PURPLE:
                return Color.MAGENTA;
            case BLACK:
                return Color.BLACK;
            case WHITE:
                return Color.WHITE;
            case GREEN:
                return Color.GREEN;
            case ORANGE:
                return ORANGE;
            case BLUE:
                return Color.BLUE;
            case RED:
                return Color.RED;
            default:
                return 0;
        }
    }
}
