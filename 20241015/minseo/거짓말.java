import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Set<Integer> knowPeople = new HashSet<>(); // 진실을 아는 사람 => contains를 사용하기 위해 Set 사용

        st = new StringTokenizer(br.readLine());

        // 아는 사람의 수 
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            knowPeople.add(Integer.parseInt(st.nextToken()));
        }

        // 파티 정보를 담음
        List<List<Integer>> parties = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken()); // 파티에 오는 사람들의 수
            List<Integer> party = new ArrayList<>(); // 파티에 속한 사람들

            for (int j = 0; j < partySize; j++) {
                party.add(Integer.parseInt(st.nextToken()));
            }
            parties.add(party);
        }

        // 진실을 아는 사람 업데이트
        for (int i = 0; i < M; i++) {
            for (List<Integer> party : parties) {
                boolean flag = false; // 진실을 아는 사람들이 포함되었는지 여부
                for (int human : party) {
                    if (knowPeople.contains(human)) { // 진실을 아는 사람들의 집합에 포함되어 있다면
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    knowPeople.addAll(party); // 해당 파티에 있는 사람들은 진실을 아는 사람들로 치자.
                }
            }
        }

        // 거짓말 할 수 있는 파티 수 계산
        int cnt = 0;
        for (List<Integer> party : parties) {
            boolean flag = true;
            for (Integer human : party) {
                if (knowPeople.contains(human)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
