package TGC;

import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.*;

import java.io.IOException;

public class klondike_solitaire_UI {

    // draws a 13x9 dotted border - used for foundations
    // hardcoded - will likely remove
    private void empty_foundation_object(TextGraphics graphic, int x, int y){

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

    // poker card ratio 1:1.4
    private void card_placeholder_frame(TextGraphics graphic, int x, int y, int scale){

        // enforcing a minimum scale of 2
        scale = Math.max(scale, 2); // Math.max compares to ints and returns the bigger of the two

        // characters are 1:2, therefore we need double the width
        int width  = scale * 2;
        int height = (int) Math.round(scale * 1.4);

        String top = "⡏" + "⠉".repeat(width - 2) + "⢹";
        String middle = "⡇" + "⠀".repeat(width - 2) + "⢸";
        String bottom = "⣇" + "⣀".repeat(width - 2) + "⣸";

        graphic.putString(x, y, top);
        for (int row = 1; row < height - 1; row++) {
            graphic.putString(x, y + row, middle);
        }
        graphic.putString(x, y + height - 1, bottom);

    }

    public void klondike_start(String[] input) throws IOException {

        Terminal terminal = new DefaultTerminalFactory().createTerminal(); // creates a terminal screen
        Screen screen = new TerminalScreen(terminal); // makes a new screen object

        // for running the
        boolean running = true;
        boolean needsRedraw = true;


        screen.startScreen(); // starts said screen object
        TextGraphics tg = screen.newTextGraphics(); // creates a backround for the terminal screen

        while(running) {

            TerminalSize newSize = screen.doResizeIfNecessary();
            if (newSize != null) {
                needsRedraw = true;
            }

            if(needsRedraw) {
                // draws screen
                tg.setBackgroundColor(TextColor.ANSI.BLACK); // sets backround color
                tg.fillRectangle(new TerminalPosition(0, 0), screen.getTerminalSize(), ' '); // fills the entire screen in that color


                int cardScale = screen.getTerminalSize().getColumns() / (7 * 2);

                card_placeholder_frame(tg, 3, 1, cardScale); // stock pile

                int cardWidth = cardScale * 2;
                int rightEdge = screen.getTerminalSize().getColumns() - 3;
                // foundations
                for(int i = 1; i < 5; i++) {
                    card_placeholder_frame(tg, rightEdge - (i * cardWidth) - (2*i-2), 1, cardScale);
                }

                // tableau

                screen.refresh(); // shows what's supposed to be on screen
                needsRedraw = false;
            }

            if (screen.pollInput() != null) { running = false; }

        }

    }

}
