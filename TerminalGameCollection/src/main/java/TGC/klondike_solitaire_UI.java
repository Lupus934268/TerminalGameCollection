package TGC;

import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.*;

import java.io.IOException;

public class klondike_solitaire_UI {

    private void empty_foundation_object(TextGraphics graphic, int x, int y){

        // draws an 13x9 dotted border - used for foundations

        graphic.putString( x, y, "⡏⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⢹" );
        graphic.putString( x, y + 1, "⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸" );
        graphic.putString( x, y + 2, "⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸" );
        graphic.putString( x, y + 3, "⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸" );
        graphic.putString( x, y + 4, "⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸" );
        graphic.putString( x, y + 5, "⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸" );
        graphic.putString( x, y + 6, "⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸" );
        graphic.putString( x, y + 7, "⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸" );
        graphic.putString( x, y + 8, "⣇⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣸" );

    }

    public void klondike_start() throws IOException {

        Terminal terminal = new DefaultTerminalFactory().createTerminal();

        Screen screen = new TerminalScreen(terminal); // makes a new screen object
        screen.startScreen(); // starts said screen object

        TextGraphics tg = screen.newTextGraphics(); // creates a backround for the terminal screen
        tg.setBackgroundColor(TextColor.ANSI.BLACK); // sets backround color
        tg.fillRectangle(new TerminalPosition(0, 0), screen.getTerminalSize(), ' '); // fills the entire screen in that color

        empty_foundation_object(tg, 2, 1); // stockPile

        // foundations
        empty_foundation_object(tg, 55, 1);
        empty_foundation_object(tg, 70, 1);
        empty_foundation_object(tg, 85, 1);
        empty_foundation_object(tg, 100, 1);


        screen.refresh(); // shows what's supposed to be on screen
        screen.readInput(); // keeps the screen alive (and reads input duh)

    }

}
