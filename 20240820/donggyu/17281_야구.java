import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] info;
    static int hitter4;
    static int[] order;
    static boolean[] isSelect;
    static int ans;

    static void perm(int depth) {

        if(depth==9) {
            ans = Math.max(play2(), ans);
            return;
        }
//      한 자리는 이미 확정        
        if(depth==3) {
        	perm(depth+1);
        	return;
        }
        
        for(int i=1;i<9;i++) {
            if(!isSelect[i]) {
                order[depth] = i;
                isSelect[i] = true;
                perm(depth+1);
                isSelect[i] = false;
            }
        }
    }
        
    static int play2() {
    	int score = 0;
    	int now = 0;
    	
    	for(int i=0;i<N;i++) {
    		int outCount = 0;
    		int[] base = {0, 0, 0};
    		
    		while(outCount < 3) {
    			int hitNow = info[i][order[now]];
    			
    			if(hitNow == 0) {
    				outCount++;
    			}
    			else {
    				for(int j=2;j>=0;j--) {
    					if(base[j]==1) {
    						if(j+hitNow >=3) {
    							score++;
    						}
    						else {
    							base[j+hitNow] = 1;
    						}
    						base[j] = 0;
    					}
    				}
//    				홈런
    				if(hitNow == 4) {
    					score++;
    				}
    				else {
    					base[hitNow-1] = 1;
    				}
    			}
    			now = (now+1)%9;
    		}
    	}
    	return score;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        
        info = new int[N][9];
        
        for(int i=0;i<N;i++) {
            StringTokenizer line = new StringTokenizer(bf.readLine());
            for(int j=0;j<9;j++) {
                info[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        
        isSelect = new boolean[9];
        order = new int[9];
        
        isSelect[0] = true;
        order[3] = 0;
        
        perm(0);
        System.out.println(ans);
    }
}