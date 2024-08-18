import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int n;
    static char[][] arr;
    static int answer;
    static boolean visit[];
    public static void main(String[] args) throws NumberFormatException, IOException {
        getIn();

        findMaxFriends();

        System.out.println(answer);
    }
    
    private static void findMaxFriends() {
        for (int i=0;i<n;i++) {
            int friends = 0;
            visit = new boolean[n];
            visit[i] = true;
            
            //1-친구 구하기
            for (int j=0;j<n;j++) {
                if (arr[i][j] == 'Y' && !visit[j]) {
                    visit[j] = true;
                    friends++;
                }
            }

            //2-친구 구하기
            for (int j=0;j<n;j++) {
                if (arr[i][j] == 'Y') {
                    for (int k=0;k<n;k++) {
                        if (!visit[k] && arr[j][k] == 'Y') {
                            visit[k] = true;
                            friends++;
                        }
                    }
                }
            }
            answer = Math.max(answer, friends);
        }
    }

    private static void getIn() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        arr = new char[n][n];
        answer = 0;
        for (int i=0;i<n;i++) {
            String line = in.readLine();
            for (int j=0;j<n;j++) {
                arr[i][j] = line.charAt(j);
            }
        }
    }
}