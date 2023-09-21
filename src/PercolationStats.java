import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.StdStats;

public class PercolationStats {
    private int m; // number of independent experiments
    private double [] x; // percolation thresholds for the m experiments.
    private int i; // to keep track of random int i
    private int j; // to keep track of random int j
    private double grid; // double representation of grid(n x n)
    // Performs m independent experiments on an n x n percolation system.
    public PercolationStats(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Illegal n or m");
        }
        x = new double[m];
        this.m = m;
        grid = n * n;

        // carry out m experiments
        for (int k = 0; k < m; k++) {
            UFPercolation experiment = new UFPercolation(n);
            // until experiment percolates
            while (!experiment.percolates()) {
                // choose random i and j
                i = StdRandom.uniform(n);
                j = StdRandom.uniform(n);
                // if i, j is not open, open it
                if (!experiment.isOpen(i, j)) {
                    experiment.open(i, j);
                    // calculate and store the percolation threshold
                    // in the array x
                    x[k] = (experiment.numberOfOpenSites() / (grid));

                }
            }
        }
    }

    // Returns sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(x);

    }

    // Returns sample standard deviation of percolation threshold.
    public double stddev() {
        return  StdStats.stddev(x);
    }

    // Returns low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        // use the given formula to calculate the low endpoint
        return ((mean()) - 1.96 * stddev()/Math.sqrt(m));
    }

    // Returns high endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        // use the given formula to calculate the high endpoint
        return ((mean()) + 1.96 * stddev()/Math.sqrt(m));
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, m);
        StdOut.printf("Percolation threshold for a %d x %d system:\n", n, n);
        StdOut.printf("  Mean                = %.3f\n", stats.mean());
        StdOut.printf("  Standard deviation  = %.3f\n", stats.stddev());
        StdOut.printf("  Confidence interval = [%.3f, %.3f]\n", stats.confidenceLow(),
                stats.confidenceHigh());
    }
}