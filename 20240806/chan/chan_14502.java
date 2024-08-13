import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class chan_14502 {
	static final int dx[] = {0,0,1,-1};
    static final int dy[] = {1,-1,0,0};
	static int N,M;
	static int[][] map;
	static int maxSafeZone = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}//입력 완료
		dfs(0);//벽부터 세워야 하므로 벽 세우는걸 dfs로 조합을 이용하여 벽이 세워지는 모든 경우를 구함
		System.out.println(maxSafeZone);
	}
	
	static void dfs(int wallCnt) {
        //벽이 3개가 설치 된 경우 bfs 탐색 시작
        if(wallCnt == 3) {//벽이 3개가 되면 bfs실행
            bfs();//벽이 3개가 되면 바이러스를 퍼뜨리기위해 bfs실행
            return;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                	map[i][j] = 1;//벽으로 세움
                    dfs(wallCnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }
	
	static void bfs() {
		Queue<xy> q = new LinkedList<>();//LinkedList가 값을 추가, 삭제시 가장 빠르기에 사용

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 2) {//바이러스 발견시
                    q.add(new xy(i,j));//q에다가 바이러스 좌표값 저장
                }
            }
        }
        //원본 연구소를 바꾸지 않기 위한 카피맵 사용
        int[][] copyMap = new int[N][M];//맵 깊은 복사

        for (int i=0; i<N; i++) {
            copyMap[i] = map[i].clone();
        }

        //BFS 탐색 시작
        while(!q.isEmpty()) {
            xy now = q.poll();//poll값을 xy형으로 선언한 now에 저장
            int x = now.x; // 현재 x값
            int y = now.y; // 현재 y값

            for(int k=0; k<4; k++) {//상하좌우
                int nx = x + dx[k];
                int ny = y + dy[k];
                //연구소 범위 밖이 아니고 빈칸일 경우에만 바이러스를 퍼트린다.
                if(0<=nx && nx<N && 0<=ny && ny<M) {
                	if(copyMap[nx][ny] == 0) {
                        q.add(new xy(nx,ny));//감염시킨 바이러스 좌표를 큐에 집어 넣음
                        copyMap[nx][ny] = 2;
                    }
                }
            }
        }
        funcSafeZone(copyMap);//바이러스 다 퍼뜨렸으므로 세이프존 계산
	}
	
	static class xy {//좌표
        int x;
        int y;
        xy(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
	
	static void funcSafeZone(int[][] copyMap) {//safeZone계산
		int safeZone=0;
        for(int i=0; i<N ; i++) {
        	for(int j=0; j<M; j++) {
        		if(copyMap[i][j] == 0) {//빈칸이라면
        			safeZone++;
                }
            }
        }
        maxSafeZone = maxSafeZone < safeZone ? safeZone : maxSafeZone;
    }
}