import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       //testing new Pile array
        Solitaire solitaireGame = new Solitaire();

        solitaireGame.pileShuffle();

        for( int i = 0 ; i < solitaireGame.Pile.length ; i++ ) {
            System.out.println( solitaireGame.Pile[i].name );
        }

    }
}