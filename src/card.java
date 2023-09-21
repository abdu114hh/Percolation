import java.util.Comparator;
import stdlib.StdOut;
public class card implements Comparable<card> {
    private static String[] RANKS = {"2", "3", "4", "5", "6", "7", "8",
                                    "9", "10", "Jack", "Queen", "King", "Ace"};

    private static String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private int suit;
    private int rank;

    public void Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;

    }

    public boolean equals(Object other){


    }

    public String toString(){
        return RANKS[rank] + "of" + SUITS[suit];
    }

    public int compareTo(card that) {
        if (this.suit < that.suit || (this.suit == that.suit && this.rank < that.rank)) {
            return -1;
        } else if (this.suit == that.suit && this.rank == that.rank) {
            return 0;
        } else {
            return 1;
        }

    }

    public static Comparator<card> suitOrder(){
            return new SuitOrder();
        }

    public static Comparator<card> reverseRankOrder(){
        return new ReverseRankOrder();
    }






}
