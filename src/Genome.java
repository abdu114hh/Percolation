import java.util.Comparator;
import java.util.Iterator;
import stdlib.StdOut;
public class Genome implements  Comparable<Genome>, Iterable<Character> {
    private String s;

    public Genome(String s) {
        this.s = s;
    }
    public double gcContent() {
        double G = 0;
        double C = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'G') {
                G++;
            } else if (s.charAt(i) == 'C') {
                C++;
            }
        }
        return (G + C) / s.length() * 100;
    }

    public String toString(){
        return s.length() + " : " + s;
    }

    public  int compareTo(Genome other){
        return Integer.compare(s.length(), other.s.length());
    }

    public static Comparator<Genome> gcOrder(){
        return new GcOrder();

    }

    private static class GcOrder implements Comparator<Genome>{
        public int compare(Genome g1, Genome g2) {
            return Double.compare(g1.gcContent(), g2.gcContent());
        }
    }

    public Iterator<Character> iterator(){
        return new GenomeIterator();
    }

    private class GenomeIterator implements Iterator<Character> {

        int x = s.length() - 1;

        public boolean hasNext(){
            return x >= 0;
        }

        public Character next(){

            return s.charAt(x--);

        }

    }

    public static  void main(String[] args) {
        Genome g1 = new Genome(args[0]);
        Genome g2 = new Genome(args[1]);
        StdOut.println("g1 = " + g1) ;
        StdOut.println("g2 = " + g2);
        StdOut.println(g1.gcContent());
        StdOut.println(g2.gcContent());
        StdOut.println(g1.compareTo(g2));
        StdOut.println(Genome.gcOrder().compare(g1, g2));
        for ( char c: g1) {
            StdOut.print(c);

        }
        StdOut.println();
        }

    }









