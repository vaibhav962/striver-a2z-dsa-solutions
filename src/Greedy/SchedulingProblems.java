package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    // Job Sequencing Problem
    // TC: O(N log N + (N * maxDeadline)) | SC: O(maxDeadline)
    // Can be optimized by DSU
    public static ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = deadline.length;

        // Sort job indices by profit descending
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> profit[b] - profit[a]);

        int maxD = 0;
        for (int d : deadline) maxD = Math.max(maxD, d);

        boolean[] hash = new boolean[maxD + 1];
        int count = 0, total = 0;

        for (int i : idx) {
            for (int t = deadline[i]; t > 0; t--) { // DSU
                if (!hash[t]) {
                    hash[t] = true;
                    count++;
                    total += profit[i];
                    break;
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        res.add(count);
        res.add(total);
        return res;
    }

    // Maximum Meetings in One Room
    // TC: O(2N + N log N) | SC: O(3N) + O(N) // for answer
    public static ArrayList<Integer> maxMeetings(int[] s, int[] f) {
        int n = s.length;

        // Each entry: {start, end, index}
        int[][] meetings = new int[n][3];
        for (int i = 0; i < n; i++) {
            meetings[i][0] = s[i];
            meetings[i][1] = f[i];
            meetings[i][2] = i + 1;   // 1-based index
        }

        // Sort by end time, then by index
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        ArrayList<Integer> result = new ArrayList<>();
        int lastEnd = -1;

        for (int[] m : meetings) {
            if (m[0] > lastEnd) {
                result.add(m[2]);
                lastEnd = m[1];
            }
        }

        Collections.sort(result);
        return result;
    }

    // 435. Non-overlapping Intervals
    // TC: O(N + N log N) | SC: O(1)
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int lastEnd = intervals[0][1];
        int count = 1;

        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= lastEnd){
                count++;
                lastEnd = intervals[i][1];
            }
        }

        return intervals.length - count;
    }

    // 57. Insert Interval
    // TC: O(N) | SC: O(N)
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        int start = newInterval[0];
        int end = newInterval[1];

        while (i < n && intervals[i][1] < start) {
            result.add(intervals[i]);
            i++;
        }

        while (i < n && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        result.add(new int[]{start, end});

        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    // 56. Merge Intervals
    // TC: O(N log N + N) | SC: O(N)
    public static int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> answer = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int[] interval : intervals) {
            if (answer.isEmpty() || interval[0] > answer.getLast()[1])
                answer.add(interval);
            else {
                answer.getLast()[1] = Math.max(answer.getLast()[1], interval[1]);
            }
        }
        return answer.toArray(new int[answer.size()][]);
    }

    // Minimum number of platforms required for a railway
    // TC: O(2(N log N + N)) | SC: O(1)
    public static int findPlatform(int[] arrival, int[] departure) {
        Arrays.sort(arrival);
        Arrays.sort(departure);
        int i = 0, j = 0, count = 0, maxCount = 0;
        while(i < arrival.length){
            if(arrival[i] <= departure[j]){
                count++;
                i++;
            }
            else{
                count--;
                j++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}
