import dsa.WeightedQuickUnionUF;
import stdlib.In;
import stdlib.StdOut;

// An implementation of the Percolation API using the UF data structure.
public class UFPercolation implements Percolation {
    private int n; // instance variable n
    private  boolean[][] open; // boolean array open
    private int openSites; // keep track of open sites
    private  WeightedQuickUnionUF uf; // with both sink and source
    private  WeightedQuickUnionUF noSink; // without sink, to handle backwash
    // noSink will help take care of the backwash because it will not be
    // connected to the bottom sink at all-- bottom sink is responsible for
    // backwash-- noSink will follow all the steps as uf, except the one
    // where I connect the bottom row to the sink
    private  int sink; // integer representation of the sink
    private int source; // integer representation of the source

    // Constructs an n x n percolation system, with all sites blocked.
    public UFPercolation(int n) {
        if (n <= 0) {

            throw new IllegalArgumentException("Illegal n");
        }
        this.n = n;
        // define sink
        sink = n * n + 1;
        // define source
        source = 0;
        open = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        noSink = new WeightedQuickUnionUF(n * n + 1);
    }

    // Opens site (i, j) if it is not already open.
    public void open(int i, int j) {
        if (i < 0 || i > open.length - 1 || j < 0 || j > open.length - 1) {
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        if (!open[i][j]) {
            open[i][j] = true;
            openSites += 1;
            if (i == 0) {
                // if i is in the first row, connect it to the source
                uf.union(source, encode(i, j));
                noSink.union(source, encode(i, j));
            }
            if (i == n - 1) {
                // if i is in the last row, connect to the sink
                // but only for uf
                uf.union(sink, encode(i, j));
            }
            // if i is not on the boundary and south is open, connect it
            if (i < n - 1 && isOpen(i + 1, j)) {
                uf.union(encode(i, j), encode(i + 1, j));
                noSink.union(encode(i, j), encode(i + 1, j));
            }
            // if i isn't on the boundary and north is open, connect it
            if (i > 0 && isOpen(i - 1, j)) {
                uf.union(encode(i, j), encode(i - 1, j));
                noSink.union(encode(i, j), encode(i - 1, j));
            }
            // if j isn't on the boundary and east is open, connect it
            if (j < n - 1 && isOpen(i, j + 1)) {
                uf.union(encode(i, j), encode(i, j + 1));
                noSink.union(encode(i, j), encode(i, j + 1));
            }

            // if j isn't on the boundary and west is open, connect it
            if (j > 0 && isOpen(i, j - 1)) {
                uf.union(encode(i, j), encode(i, j - 1));
                noSink.union(encode(i, j), encode(i, j - 1));
            }

        }
    }


    // Returns true if site (i, j) is open, and false otherwise.
    public boolean isOpen(int i, int j) {
        if (i < 0 || i > open.length-1 || j < 0 || j > open.length-1) {
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        return open[i][j];
    }

    // Returns true if site (i, j) is full, and false otherwise.
    public boolean isFull(int i, int j) {
        if (i < 0 || i > open.length - 1 || j < 0 || j > open.length - 1) {
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // handle back wash using only the noSink WeightedUF object

        return isOpen(i, j) && noSink.connected(source, encode(i, j));
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        return openSites;
    }

    // Returns true if this system percolates, and false otherwise.
    public boolean percolates() {
        // the system percolates if the source is connected to the sink
        return uf.connected(sink, 0);
    }

    // Returns an integer ID (1...n) for site (i, j).
    private int encode(int i, int j) {
        return (n * i) + j + 1;

    }
    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        UFPercolation perc = new UFPercolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.printf("%d x %d system:\n", n, n);
        StdOut.printf("  Open sites = %d\n", perc.numberOfOpenSites());
        StdOut.printf("  Percolates = %b\n", perc.percolates());
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.printf("  isFull(%d, %d) = %b\n", i, j, perc.isFull(i, j));
        }
    }
}