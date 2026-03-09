import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       //testing tableau array array
        Solitaire solitaireGame = new Solitaire();

        solitaireGame.gameStart();

        for( int n = 0; n < 7; n++){
            System.out.println( " " );
            System.out.println( "Stack " + n );
            for ( int i = 0; i < 13; i++ ){
                if( solitaireGame.tableau[n][i] != null ){
                    System.out.println( solitaireGame.tableau[n][i].name );
                }
            }
        }

    }
}