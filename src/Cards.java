public class Cards {

    //variables for object construction
    String name;
    Suit suit;
    int rank; // rank must be >0 and <14
    boolean isRed;
    boolean visibility; // visibility > 0 the card is visible

    //constructor for card object
    public Cards( Suit getSuit , int getRank ){
        suit = getSuit;
        rank = getRank;
        name = getName();
        visibility = true;
        isRed = getIsRed();

        //throwing exceptions for illegal Arguments
        if( getRank < 1 || getRank > 13 ) { throw new IllegalArgumentException( getRank + " is not a valid Rank" ); }
    };

    //method for determining the name of the card
    private String getName() {
        String nameRank;
            if( rank == 1 ){ nameRank = "Ace"; }
            else if( rank == 11 ){ nameRank = "Jack"; }
            else if( rank == 12 ){ nameRank = "Queen"; }
            else if( rank == 13 ){ nameRank = "King"; }
            else{ nameRank = Integer.toString(rank); }
        String nameSuit;
            if( suit == Suit.SPADES) { nameSuit = "Spades"; }
            else if( suit == Suit.CLUBS) { nameSuit = "Clubs"; }
            else if( suit == Suit.HEARTS) { nameSuit = "Hearts"; }
            else if( suit == Suit.DIAMONDS) { nameSuit = "Diamonds"; }
            else{ nameSuit = "null"; }
        return( nameRank + " of " + nameSuit );
    }

    //methods for determening color
    private Boolean getIsRed() {
        return (suit.equals(Suit.HEARTS) || suit.equals(Suit.DIAMONDS));
    }
}
