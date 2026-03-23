public class ChessPieces {

    // enum for Piece type
    enum Type { PAWN, BISHOP, KNIGHT, ROOK, QUEEN, KING };

    // Variables for Piece identification
    String name;
    Type type;
    //color - true = white , false = black
    Boolean goesFirst; //could be usefull for smth else too, currently mainly for color

}