import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //testing new card system

        //scanner
        Scanner myObj = new Scanner(System.in);

        //get rank for pulling a card from the deck
        System.out.println("Enter suit");
        Cards.Suit suitDeck = Cards.Suit.valueOf( myObj.next() );

        //get rank for pulling a card from the deck
        System.out.println("Enter rank");
        int rankDeck = myObj.nextInt();

        //creating card object
        Cards card = new Cards(suitDeck, rankDeck);

        System.out.println( card.name );

    }
}