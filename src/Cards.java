public class Cards {

    // enums for card identification
    enum Suit { spades , clubs , hearts , diamonds };

    //variables for object construction
    String name;
    Suit suit;
    int rank; // rank must be >0 and <14
    int visibility; // visibility > 0 the card is visible

    //constructor for card object
    public Cards( Suit getSuit , int getRank ){
        suit = getSuit;
        rank = getRank;
        name = getName();

        //throwing exceptions for illegal Arguments
        if( getRank < 1 || getRank > 13 ) {throw new IllegalArgumentException( getRank + " is not a valid Rank" ); }
    };

    //method for determining the name of the card
    public String getName() {
        String nameRank;
            if( rank == 1 ){ nameRank = "Ace"; }
            if( rank == 11 ){ nameRank = "Jack"; }
            if( rank == 12 ){ nameRank = "Queen"; }
            if( rank == 13 ){ nameRank = "King"; }
            else{ nameRank = Integer.toString(rank); }
        String nameSuit;
            if( suit == Suit.spades ) { nameSuit = "Spades"; }
            if( suit == Suit.clubs ) { nameSuit = "Club"; }
            if( suit == Suit.hearts ) { nameSuit = "Hearts"; }
            if( suit == Suit.diamonds ) { nameSuit = "Diamonds"; }
            else{ nameSuit = "null"; }
        return( nameRank + " of " + nameSuit );
    }
}
