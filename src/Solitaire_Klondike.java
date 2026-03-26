import java.util.Scanner;

public class Solitaire_Klondike {

    public Cards[] stockPile = new Cards[52];
    public Cards[][] tableau = new Cards[7][13];
    public Cards[][] foundation = new Cards[4][13];
    int pileCursor;

    // Method that resets the game state
    public void RESET() {
        stockPile = new Cards[52];
        tableau = new Cards[7][13];
        foundation = new Cards[4][13];
        pileCursor = 0;
    }

    // Method that sets the variable, determining which card is being seen when printing the pile
    int seeingThePile() {

        for(int i = stockPile.length - 1; i >= 0; i-- ){
            if (stockPile[i] != null) {
                return i;
            }
        }
        return -1; //pile is empty
    }

    // Method that checks the pile for duplicates // not to be called outside pileShuffle
    boolean pileDupeCheck(Cards checkCard){

        for (int i = 0; i < stockPile.length ; i++  ){
            if ( stockPile[i] != null && stockPile[i].suit == checkCard.suit && stockPile[i].rank == checkCard.rank ){
                return false;
            }
        }
        return true;
    }

    // Method that randomly fills the pile array
    public void pileShuffle() {

        for(int i = 0; i < stockPile.length ; i++ ){
            // chooses a random rank
            int randomRank = (int) ( Math.random() * 13 ) +1 ;
            // chooses a random suit
            Suit randomSuit;
                int suitNum = (int) ( Math.random() * 4 );
                if( suitNum == 0) { randomSuit = Suit.SPADES; }
                else if( suitNum == 1) { randomSuit = Suit.CLUBS; }
                else if( suitNum == 2) { randomSuit = Suit.HEARTS; }
                else if( suitNum == 3) { randomSuit = Suit.DIAMONDS; }
                else { throw new IllegalArgumentException( suitNum + " was not a valid Suit"); }

            //creates the card object
            Cards card = new Cards(randomSuit, randomRank);

            // filling the pile
            if( pileDupeCheck( card ) ) {
                stockPile[i] = card;
            }
            else{
                i--;
            }
        }

    }

    // Method for filling the tableau
    void tableauFill(){

        int tempFill = stockPile.length - 1;
        for( int i = 0; i < 7; i++ ){
            for( int n = 0; n < i+1; n++){

                tableau[i][n] = stockPile[tempFill];
                stockPile[tempFill] = null;
                tempFill--;

            }
        }

    }

    // Method to check the visibility of Cards in the tableau at Start of Game
    void tableauVisCheck() {

        for( int i = 0; i < 7; i++){
            for( int n = 0; n < 12; n++){
                if( tableau[i][n] != null && tableau[i][n+1] !=null){
                    tableau[i][n].visibility = false;
                }
            }
        }

    }

    // Method to check visibility of Cards in the stockPile at Start of Game
    void pileVisCheck() {

        for( int n = 0; n < 52; n++ ){
            if( stockPile[n] != null ){
                stockPile[n].visibility = false;
            }
        }

    }

    // Method for printing the tableau
    void printTableau() {
        for (int n = 0; n < 7; n++) {
            System.out.println(" ");
            System.out.println("Stack " + n);
            for (int i = 0; i < 13; i++) {
                if (tableau[n][i] != null && tableau[n][i].visibility ) {
                    System.out.println(tableau[n][i].name);
                } else if (tableau[n][i] != null &&  !tableau[n][i].visibility) {
                    System.out.println("[-]");
                }
            }
        }
    }

    // Method for printing the stockPile
    void printPile() {

        //currently prints nothing
        for(int n = stockPile.length - 1; n > -1; n-- ){
            if( stockPile[n] != null && stockPile[n].visibility && n == pileCursor + 1 ){
                System.out.println(stockPile[n].name + " <");
            }
            else if( stockPile[n] != null && stockPile[n].visibility ) {
                System.out.println(stockPile[n].name);
            }
        }

    }

    // Method for drawing a card from the stockPile ! NEEDS TO BE CALLED AFTER FILLING THE TABLEAU !
    void drawACard() {

        if( pileCursor == -1 ) {
            System.out.println("stockPile is empty");
            return;
        }


        for(int i = 0; i < stockPile.length; i++ ){
            if ( pileCursor == 0 && stockPile[i] == null ) {
                pileCursor = i - 1;
            }
        }
        if(stockPile[pileCursor] != null) stockPile[pileCursor].visibility = true;
        if( pileCursor + 3 < 52 && stockPile[pileCursor + 3] != null ) stockPile[pileCursor + 3].visibility = false;
        pileCursor --;

        printPile();

    }

    //Method that starts the game and manages the run
    public void gameStart(){
        RESET();
        pileShuffle();
        tableauFill();

        pileVisCheck();
        tableauVisCheck();
        pileCursor = seeingThePile();

        //Game loop
        boolean running = true;
        Scanner inputScanner = new Scanner(System.in);

        while( running ){

            System.out.print( "> " );
            String input = inputScanner.next();

            switch( input ){
                case "printTableau":
                    printTableau();
                    break;

                case "printStockPile":
                    printPile();
                    break;

                case "drawCard":
                    drawACard();

                    break;

                case "help":
                    System.out.println( "help - prints this screen" );
                    System.out.println( "quit - quits the program" );
                    System.out.println(  ); // for formating the output.
                    System.out.println( "printTableau - prints the current tableau, to be used when playing the text-based version" );
                    System.out.println( "printStockPile - prints the current stockPile, to be used when playing the text-based version" );
                    System.out.println( "drawCard - draws a card from the stockPile and prints the current stockPile, to be used when playing the text-based version");

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