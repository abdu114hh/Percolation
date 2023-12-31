import stdlib.StdOut;

import java.util.Comparator;
import java.util.Iterator;

// This comparable, iterable data type that represents a sentence, which is a sequence of words.
public class Sentence implements Comparable<Sentence>, Iterable<String> {
    private String s; // the sentence
    private String[] words; // words in the sentence

    // Constructs a Sentence object from the sentence s.
    public Sentence(String s) {
        this.s = s;
        words = s.split("\\s+");
    }

    // Returns the number of words in this sentence.
    public int wordCount() {
        return words.length;
    }

    // Returns true if this sentence is the same as other, and false otherwise.
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Sentence sentence = (Sentence) other;
        return s != null && s.equals(sentence.s);
    }

    // Returns a string representation of this sentence.
    public String toString() {
        return s.length() + ":" + s;
    }

    // Returns a comparison of this and other sentence based on their lengths (ie, character
    // counts).
    public int compareTo(Sentence other) {
        return Integer.compare(s.length(), other.s.length());
    }

    // Returns a comparator for comparing sentences based on their word count.
    public static Comparator<Sentence> wordCountOrder() {

        return new WordCountOrder();
    }

    // Returns an iterator for iterating over this sentence in reverse order.
    public Iterator<String> iterator() {

        return new ReverseIterator();
    }

    // A comparator for comparing sentences based on their word counts.
    private static class WordCountOrder implements Comparator<Sentence> {
        // Returns a comparison of sentences s1 and s2 based on their word count.
        public int compare(Sentence s1, Sentence s2) {
            return Integer.compare(s1.wordCount(), s2.wordCount());
        }
    }

    // An iterator for iterating over a sentence in reverse order.
    private class ReverseIterator implements Iterator<String> {
        private int i; // index of current letter

        // Constructs an interator.
        public ReverseIterator() {
            this.i = words.length - 1;
        }

        // Returns true if there are more words in the sentence, and false otherwise.
        public boolean hasNext() {
            return (i >= 0);
        }

        // Returns the next word in the sentence.
        public String next() {
            return words[i--];
        }
    }

    // Unit tests the data type [DO NOT EDIT].
    public static void main(String[] args) {
        Sentence s1 = new Sentence("abc def ghi jkl mno");
        Sentence s2 = new Sentence("abcdefg hijklmn opqrst");
        Sentence s3 = new Sentence("abc def ghi jkl mno");
        StdOut.println("s1 = " + s1);
        StdOut.println("s2 = " + s2);
        StdOut.println("s1.equals(s2) = " + s1.equals(s2));
        StdOut.println("s2.equals(s3) = " + s2.equals(s3));
        StdOut.println("s1.compareTo(s2) = " + s1.compareTo(s2));
        StdOut.println("WordCountOrder::compare(s1, s2) = " +
                Sentence.wordCountOrder().compare(s1, s2));
        StdOut.print("reverse(s3): ");
        for (String word : s3) {
            StdOut.print(word + " ");
        }
        StdOut.println();
    }
}



    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Sentence sentence = (Sentence) other;
        return Objects.equals(s, sentence.s);
    }


    import java.util.Arrays;

            import stdlib.StdIn;
            import stdlib.StdOut;

public class UniqueCount {
    // Entry point [DO NOT EDIT].
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println(uniqueCount(a));
    }

    // Returns the number of unique strings in a.
    private static int uniqueCount(String[] a) {
        int cnt = 1;
        for(int i = 1; i < a.length; i++){
            int j = 0;
            for (j = 0; j < i; j++)
                if (a[i].equalsIgnoreCase(a[j]))
                    break;
            if (i == j)
                cnt++;
        }
        return cnt;
    }
}
