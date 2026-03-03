public class Main {
    public static void main(String[] args) {

        //test-code if the shuffleing the Deck works
        Solitaire solitaireGame = new Solitaire();

        solitaireGame.shuffleDeck();
        for(int i = 0; i < 52; i++) {
            System.out.println( solitaireGame.Pile[i] );
        }
    }
}