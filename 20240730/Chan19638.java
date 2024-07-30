package D2024_07_30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Chan19638 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input[] = br.readLine().split(" ");//거인의 수 | 센티키 | 망치횟수
		int N = Integer.valueOf(input[0]);//거인 수
		int senti = Integer.valueOf(input[1]);//센티 키
		int hammer = Integer.valueOf(input[2]);//망치 횟수
		int hammerUse = hammer;//망치를 몇번 사용했는지 위한 변수 추가
		
		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0; i<N; i++) {
			int giant = Integer.valueOf(br.readLine());
			if(giant >= senti) pq.add(giant);//거인의 키가 센티보다 크거나 같으면 우선순위큐에 추가
		}
		while(hammer > 0 && pq.size() != 0) {//거인의 키가 센티보다 크거나 같다면 반복
			if(pq.peek() != 1 && pq.peek()/2 < senti) pq.remove();//제일 큰 거인을 /2했을 때 센티보다 작다면 삭제
			else if (pq.peek() != 1) pq.add(pq.poll()/2);//센티보다 크거나 같다면 /2를 하고 다시 큐에 추가
			hammer--;//망치횟수가 0이 되면 반복문이 끝나야 하므로 1씩 감소
		}
		if(pq.isEmpty()) {//우선순위 큐에 값이 없다면 거인들의 키가 센티보다 작기 때문에 YES출력
			System.out.println("YES");
			System.out.println(hammerUse-hammer);
		}
		else {//우선순위 큐에 값이 있다면 망치횟수 만큼 다 써도 센티보다 키가 같거나 크기 때문에 NO출력
			System.out.println("NO");
			System.out.println(pq.peek());
		}
	}
}