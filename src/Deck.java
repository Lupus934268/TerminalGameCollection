import java.util.Scanner;

public class Deck {

    //Deck Array - contains all Cards
    public String[][][] Deck = new String[][][] {

            {
                    //Spades, black
                    {"A-sb", "2-sb", "3-sb", "4-sb", "5-sb", "6-sb", "7-sb", "8-sb", "9-sb", "10-sb", "J-sb", "Q-sb", "K-sb"},
                    //Clubs, black
                    {"A-cb", "2-cb", "3-cb", "4-cb", "5-cb", "6-cb", "7-cb", "8-cb", "9-cb", "10-cb", "J-cb", "Q-cb", "K-cb"},
            },
            {
                    //Hearts, red
                    {"A-hr", "2-hr", "3-hr", "4-hr", "5-hr", "6-hr", "7-hr", "8-hr", "9-hr", "10-hr", "J-hr", "Q-hr", "K-hr"},
                    //Diamonds, red
                    {"A-dr", "2-dr", "3-dr", "4-dr", "5-dr", "6-dr", "7-dr", "8-dr", "9-dr", "10-dr", "J-dr", "Q-dr", "K-dr"},
            }
    };

    //method for testing the Deck
    public void pullACard () {

        //scanner
        Scanner myObj = new Scanner(System.in);

        //get rank for pulling a card from the deck
        System.out.println("Enter color");
        int colorDeck = myObj.nextInt();

        //get row for pulling a card from the deck
        System.out.println("Enter row");
        int rowDeck = myObj.nextInt();

        //get rank for pulling a card from the deck
        System.out.println("Enter rank");
        int rankDeck = myObj.nextInt();

        System.out.println( "Your card is:" + Deck[colorDeck][rowDeck][rankDeck] );

    };

}
