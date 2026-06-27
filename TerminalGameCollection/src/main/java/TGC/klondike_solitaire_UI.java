package TGC;

import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.*;

import java.io.IOException;

public class klondike_solitaire_UI {

    public void klondike_start() throws IOException {

        Terminal terminal = new DefaultTerminalFactory().createTerminal();

        Screen screen = new TerminalScreen(terminal); // makes a new screen object
        screen.startScreen(); // starts said screen object

        TextGraphics tg = screen.newTextGraphics(); // creates a backround for the terminal screen
        tg.setBackgroundColor(TextColor.ANSI.BLACK); // sets backround color
        tg.fillRectangle(new TerminalPosition(0, 0), screen.getTerminalSize(), ' '); // fills the entire screen in that color

        tg.putString(5, 5, "┌────────┐");
        tg.putString(5, 6, "│        │");
        tg.putString(5, 7, "└────────┘");

        screen.refresh(); // shows what's supposed to be on screen
        screen.readInput(); // keeps the screen alive (and reads input duh)

    }

}
