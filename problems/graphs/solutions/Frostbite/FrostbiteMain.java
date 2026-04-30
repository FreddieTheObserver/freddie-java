package graphs.solutions.Frostbite;

import java.util.ArrayDeque;
import java.util.Scanner;

public class FrostbiteMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int R = scanner.nextInt();
        int C = scanner.nextInt();
        int D = scanner.nextInt();
        int K = scanner.nextInt();

        boolean[][] ice = new boolean[R][C];
        boolean[][] protectedCell = new boolean[R][C];

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < K; i++) {
            int hr = scanner.nextInt();
            int hc = scanner.nextInt();
            protectedCell[hr][hc] = true;

            for (int[] d : dirs) {
                int nr = hr + d[0];
                int nc = hc + d[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                protectedCell[nr][nc] = true;
            }
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayDeque<int[]> nextQ = new ArrayDeque<>();

        for (int day = 1; day <= D; day++) {
            while (!q.isEmpty()) {
                int[] cell = q.poll();
                int r = cell[0], c = cell[1];

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (ice[nr][nc]) continue;
                    if (protectedCell[nr][nc]) continue;

                    ice[nr][nc] = true;
                    nextQ.offer(new int[]{nr, nc});
                }
            }

            int fr = scanner.nextInt();
            int fc = scanner.nextInt();
            if (!ice[fr][fc] && !protectedCell[fr][fc]) {
                ice[fr][fc] = true;
                nextQ.offer(new int[]{fr, fc});
            }

            q = nextQ;
            nextQ = new ArrayDeque<>();
        }

        int count = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (ice[r][c]) count++;
            }
        }
        System.out.println(count);

        scanner.close();
    }
}
