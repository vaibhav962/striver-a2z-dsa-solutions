package Greedy;

import java.util.Arrays;

public class SchedulingProblems {
    // Shortest Job first
    // TC: O(N + N log N) | SC: O(1)
    public static int shortestJobFirst(int bt[]) {
        Arrays.sort(bt);
        int t = 0;
        int waitTime = 0;
        for(int i = 0; i < bt.length; i++){
            waitTime += t;
            t += bt[i];
        }
        return waitTime/(bt.length);
    }
}
