package Algo20241015;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class 거짓말_1043 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int[] person;
    static List<Integer>[] party;
    static HashSet<Integer> truePerson;
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());	// 사람 수
        M = Integer.parseInt(st.nextToken());	// 파티 수
        person = new int[N+1];	// 0부터 N까지
        party = new List[M];
        truePerson = new HashSet<>();
        
        for(int i=0; i<M; i++) {
        	party[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());	// 진실을 아는 사람 수
        for(int i=0; i<a; i++) {
        	truePerson.add(Integer.parseInt(st.nextToken()));
        }
        
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int partyPersonNum = Integer.parseInt(st.nextToken());// 각 파티의 사람 수
        	for(int j=0; j<partyPersonNum; j++) {
        		party[i].add(Integer.parseInt(st.nextToken()));// 파티의 사람 수 만큼 반복해서 리스트에 값을 저장
        	}
        }// 입력 완료
        
        if(truePerson.size() == 0) {// 진실을 아는 사람이 0이라면 파티 수 출력
        	System.out.println(M);
        	return;
        }
        
        // 이거 반복 안해준거 때문에 3%에서 틀림!!! 개빡침
        // 반복을 파티의 수만큼 하는 이유는 87줄의 예외 테케를 볼 수 있음
        // 반복을 해주지 않으면 3번과 10번이 있는 파티는 3번 때문에 진실을 말해야함 따라서 10번은 진실을 들음 하지만
        // 7 8 10 번들이 있는 파티에 10번이 뒤 늦게 진실을 아는 사람이 되었기에 7번 8번도 진실을 아는 사람이 되어야함 따라서 여러번 반복을 해서
        // 이렇게 뒤늦게 진실을 아는 사람이 되는 사람이 있는지 확인해줘야함
        for(int i=0; i<M; i++) {
        	addTruePerson();
        }
        check();
    }        
    
    static void addTruePerson() {
    	Loop1:
    	for(int i=0; i<M; i++){		// 각 파티를 반복
    		Loop2:
    		for(int e : truePerson) {	// 진실을 아는 사람의 수만큼 반복
    			if(party[i].contains(e)) {// 파티에 진실을 아는 사람이 있다면
    				for(int j=0; j<party[i].size(); j++) {
    					truePerson.add(party[i].get(j));// 진실을 아는 사람이 있는 곳이라면 지민이는 진실을 말해야함
    				}	// 따라서 진실을 모르는 사람들도 진실을 듣게되므로 진실을 아는 사람 목록에 추가 해야함
    				continue Loop1;		// 추가를 했으니 다음 파티로 넘김
    			}
    		}
    	}
    }
    
    static void check() {
    	int cnt = 0;
    	
    	Loop1:
    	for(int i=0; i<M; i++){		// 각 파티를 반복
    		for(int e : truePerson) {	// 진실을 아는 사람의 수만큼 반복
    			if(party[i].contains(e)) {// 파티에 진실을 아는 사람이 있다면
    				continue Loop1;
    			}
    		}
    		cnt++;
    	}
    	System.out.println(cnt);
    }
}

/*
예외 테케

10 9
4 1 2 3 4
2 1 5
2 2 6
1 7
1 8
3 7 8 10
1 9
1 10
2 3 10
1 4

*/