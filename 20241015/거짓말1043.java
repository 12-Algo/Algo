import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static long know;
    static long[] party;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        party = new long[M];

        //진실 아는 사람
        st = new StringTokenizer(br.readLine());
        if(Integer.parseInt(st.nextToken()) != 0){
            while(st.hasMoreTokens()) {
                int i = Integer.parseInt(st.nextToken()) -1;
                know |= (1L << i);
            }
        }else {
            System.out.println(M);
            return;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            //파티 온 사람 체크
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                party[i] |= (1L << Integer.parseInt(st.nextToken()) - 1);
            }
        }

        for(int i=0; i<M; i++) {
            //진실을 아는 사람이 파티에 참석했다면 -> 파티 참가 인원 모두 진실을 알게 된다.
            //다시 탐색
            if((party[i] & know) > 0) {
                know |= party[i];

                //무한루프 방지 (party[i]에 참가한 인원은 이미 know에 포함되었음.
                party[i] = 0;
                i = -1;
            }
        }

        for(int i=0; i<M; i++) {
            //아는 사람이 없었던 파티의 개수
            if(party[i] != 0) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
