import java.util.Scanner;

public class Solitaire {

    //Deck Array - contains all Cards
    public String[][][] Deck = new String[][][] {

            // first Index: color ; 0 = black , 1 = red
            // second Index: row ; 0 = Spades / Hearts , 1 = Clubs / Diamonds
            // third Index: rank ; 0 - 12 =^= ace - King (Ace as card one, King as card 13)

            // cards are named like: "Rank"-"color""physical color"
            // e.g.: J-sb == Jack of Spades (physical color being black)
            {
                    {"A-sb", "2-sb", "3-sb", "4-sb", "5-sb", "6-sb", "7-sb", "8-sb", "9-sb", "10-sb", "J-sb", "Q-sb", "K-sb"},
                    {"A-cb", "2-cb", "3-cb", "4-cb", "5-cb", "6-cb", "7-cb", "8-cb", "9-cb", "10-cb", "J-cb", "Q-cb", "K-cb"},
            },
            {
                    {"A-hr", "2-hr", "3-hr", "4-hr", "5-hr", "6-hr", "7-hr", "8-hr", "9-hr", "10-hr", "J-hr", "Q-hr", "K-hr"},
                    {"A-dr", "2-dr", "3-dr", "4-dr", "5-dr", "6-dr", "7-dr", "8-dr", "9-dr", "10-dr", "J-dr", "Q-dr", "K-dr"},
            }
    };

    String[] Pile = new String[52];

    //search the pile
    public boolean searchPile( String str ) {
        for (String s : Pile) {
            if (s.equals(str)) {
                return false;
            };
        };
        return true;
    };

    // shuffling the deck
    public void shuffleDeck() {

        //fills the Pile with the shuffled cards
        for (int i = 0; i < 52; i++) {

            //gets random color
            int getRandomColor = (int) Math.floor(Math.random() * Deck.length);

            //gets random row
            int getRandomRow = (int) Math.floor(Math.random() * Deck[0].length);

            //gets random rank
            int getRandomRank = (int) Math.floor(Math.random() * Deck[0][0].length);

            boolean noDuplicateCards = searchPile( Deck[getRandomColor][getRandomRow][getRandomRank] );

            if( noDuplicateCards ) {
                Pile[i] = Deck[getRandomColor][getRandomRow][getRandomRank];
            }
            else{
                i--;
            };
        };
    };




    // Test Methods:

    //testMethod for testing the Deck
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