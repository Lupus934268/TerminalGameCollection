public class Solitaire {

    public Cards[] Pile = new Cards[52];

    // Method that resets the Pile
    public void pileRESET() {
      Cards[] Pile = new Cards[52];
    }

    // Method that checks the pile for duplicates
    public boolean pileDupeCheck(Cards checkCard){

        for ( int i = 0 ; i < Pile.length ; i++  ){
            if ( Pile[i].suit == checkCard.suit && Pile[i].rank == checkCard.rank ){
                return false;
            }
        }
        return true;
    }

    // Method that randomly fills the pile array
    public void pileShuffle() {

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
        for( int i = 0 ; i < Pile.length ; i++ ){
            if( pileDupeCheck( card ) ) {
                Pile[i] = card;
            }
            else{
                i--;
            }
        }

    }


    //Method that starts the game and manages the run

}