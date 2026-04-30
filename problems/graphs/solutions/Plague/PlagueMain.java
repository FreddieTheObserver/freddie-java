package graphs.solutions.Plague;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class PlagueMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();
        int K = sc.nextInt();

        int[][] infectedDay = new int[R][C];
        for (int r = 0; r < R; r++) {
            Arrays.fill(infectedDay[r], -1);
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayDeque<int[]> nextQ = new ArrayDeque<>();

        for (int i = 0; i < K; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            infectedDay[r][c] = 0;
            q.offer(new int[]{r, c});
        }

        for (int day = 1; day <= D; day++) {
            while (!q.isEmpty()) {
                int[] cell = q.poll();
                int r = cell[0], c = cell[1];
                int age = day - infectedDay[r][c];

                if (age >= 3) {
                    continue;
                }

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (infectedDay[nr][nc] != -1) continue;
                    infectedDay[nr][nc] = day;
                    nextQ.offer(new int[]{nr, nc});
                }

                nextQ.offer(new int[]{r, c});
            }

            q = nextQ;
            nextQ = new ArrayDeque<>();
        }

        int infected = 0, immune = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (infectedDay[r][c] == -1) continue;
                int age = D - infectedDay[r][c];
                if (age >= 3) immune++;
                else infected++;
            }
        }

        System.out.println(infected + " " + immune);

        sc.close();
    }
}
