package binarySearch;

import java.util.*;

class GasStationMaxDistance {

    static class Pair {
        double gap;
        int parts;

        Pair(double gap, int parts) {
            this.gap = gap;
            this.parts = parts;
        }

        double getSectionLength() {
            return gap / parts;
        }
    }


    // Minimize Max Distance to Gas Station  - TC: O(N log N + k log N) | SC: O(w - 1), w gas stations, w-1 sections
    public double minmaxGasDist(int[] arr, int k) {

        // Max-heap based on current section length
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(
                        b.getSectionLength(),
                        a.getSectionLength()
                )
        );

        // Initially, every gap has 1 part
        for (int i = 0; i < arr.length - 1; i++) {
            double gap = arr[i + 1] - arr[i];
            pq.offer(new Pair(gap, 1));
        }

        // Add k stations
        for (int i = 0; i < k; i++) {

            // Get the gap currently having the largest section
            Pair largest = pq.poll();

            // Add one station -> number of parts increases
            largest.parts++;

            pq.offer(largest);
        }

        // Largest remaining section
        return pq.peek().getSectionLength();
    }
}
