public class Solitaire {

    public String[] Pile = new String[52];

    public void shuffleDeck() {

        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {

            Pile[i] = deck.Deck[deck.getRandomColor][deck.getRandomRow][deck.getRandomRank];
        };

    };
}