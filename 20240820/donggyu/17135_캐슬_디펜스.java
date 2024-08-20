import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, D;
	static int[][] board, tempBoard;
	static int[] selected;
	static int ans;
	
	
    static int attack(int[] selected) {
        int count = 0;

//      각 행을 반복하며 공격 시뮬레이션 수행
        for (int i = N; i > 0; i--) {
            boolean[][] attacked = new boolean[N][M];

//          각 궁수마다 반복
            for (int j : selected) {
                int closestDist = D + 1;
                int[] target = null;

//              각 적에 대해 거리 계산 및 가장 가까운 적 찾기
                for (int r = 0; r < i; r++) {
                    for (int c = 0; c < M; c++) {
                        if (tempBoard[r][c] == 1) {
                            int distance = dist(r, c, i, j);
                            if (distance <= D) {
                                if (distance < closestDist || (distance == closestDist && (target == null || c < target[1]))) {
                                    closestDist = distance;
                                    target = new int[]{r, c};
                                }
                            }
                        }
                    }
                }

                if (target != null) {
                    attacked[target[0]][target[1]] = true;
                }
            }

//          공격한 적 제거
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (attacked[r][c] && tempBoard[r][c] == 1) {
                        tempBoard[r][c] = 0;
                        count++;
                    }
                }
            }
        }

        return count;
    }

	static void setTempMap() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tempBoard[i][j] = board[i][j];
			}
		}
	}
	
	static int dist(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2)+Math.abs(c1-c2);
	}
	
	static void comb(int depth, int[] selected, int start) {
		if(depth==3) {
			setTempMap();
			ans = Math.max(ans, attack(selected));
			return;
		}
		for(int i=start;i<M;i++) {
			selected[depth] = i;
			comb(depth+1, selected, i+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(input.nextToken());
		M = Integer.parseInt(input.nextToken());
		D = Integer.parseInt(input.nextToken());
		
		board = new int[N+1][M];
		tempBoard = new int[N+1][M];
		
		for(int i=0;i<N;i++	) {
			StringTokenizer line = new StringTokenizer(bf.readLine());
			for(int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(line.nextToken());
			}
		}
//		궁수 조합 배열
		selected = new int[3];
		comb(0, selected, 0);
		
		System.out.println(ans);
	}

}