import java.util.Scanner;

public class Solitaire {

    public Cards[] Pile = new Cards[52];
    public Cards[][] tableau = new Cards[7][13];

    // Method that resets the Pile
    public void pileRESET() {
      Cards[] Pile = new Cards[52];
    }

    // Method that checks the pile for duplicates // not to be called outside of pileShuffle
    boolean pileDupeCheck(Cards checkCard){

        for ( int i = 0 ; i < Pile.length ; i++  ){
            if ( Pile[i] != null && Pile[i].suit == checkCard.suit && Pile[i].rank == checkCard.rank ){
                return false;
            }
        }
        return true;
    }

    // Method that randomly fills the pile array
    public void pileShuffle() {

        for( int i = 0 ; i < Pile.length ; i++ ){
            // chooses a random rank
            int randomRank = (int) ( Math.random() * 13 ) +1 ;
            // chooses a random suit
            Cards.Suit randomSuit;
                int suitNum = (int) ( Math.random() * 4 );
                if( suitNum == 0) { randomSuit = Cards.Suit.SPADES; }
                else if( suitNum == 1) { randomSuit = Cards.Suit.CLUBS; }
                else if( suitNum == 2) { randomSuit = Cards.Suit.HEARTS; }
                else if( suitNum == 3) { randomSuit = Cards.Suit.DIAMONDS; }
                else { throw new IllegalArgumentException( suitNum + " was not a valid Suit"); }

            //creates the card object
            Cards card = new Cards(randomSuit, randomRank);

            // filling the pile
            if( pileDupeCheck( card ) ) {
                Pile[i] = card;
            }
            else{
                i--;
            }
        }

    }

    // Method to check the visibility
    void tableauVisCheck() {
        for( int i = 0; i < 7; i++){
            for( int n = 0; n < 12; n++){
                if( tableau[i][n] != null && tableau[i][n+1] !=null){
                    tableau[i][n].visibility = 0;
                }
            }
        }
    }


    //Method that starts the game and manages the run
    public void gameStart(){
        pileRESET();
        pileShuffle();

        // filling tableau
        int tempFill = Pile.length - 1;
        for( int i = 0; i < 7; i++ ){
            for( int n = 0; n < i+1; n++){

                tableau[i][n] = Pile[tempFill];
                tempFill--;

            }
        }
        tableauVisCheck();

        //Game loop
        boolean running = true;
        Scanner inputScanner = new Scanner(System.in);

        while( running ){

            System.out.print( "> " );
            String input = inputScanner.next();

            switch( input ){
                case "printTableau":

                    for( int n = 0; n < 7; n++) {
                        System.out.println(" ");
                        System.out.println("Stack " + n);
                        for (int i = 0; i < 13; i++) {
                            if (tableau[n][i] != null && tableau[n][i].visibility != 0) {
                                System.out.println(tableau[n][i].name);
                            } else if (tableau[n][i] != null && tableau[n][i].visibility == 0) {
                                System.out.println("[-]");
                            }
                        }
                    }

                    break;
                case "help":
                    System.out.println( "help - prints this screen" );
                    System.out.println( "quit - quits the program" );
                    System.out.println( "printTableau - prints the current tableau, to be used when playing the text-based version" );
                    break;
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println( input + " is not a recognised command; type 'help' for help" );
            }
        }

    }
}