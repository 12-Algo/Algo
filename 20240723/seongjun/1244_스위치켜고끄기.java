package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static void change(int[] switches, int index) {
		if (switches[index] == 0) {
			switches[index] = 1;
		} else {
			switches[index] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] switches = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1;i<=n;i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int students = Integer.parseInt(br.readLine());
		int[][] studentsInfo = new int[students][2];
		for (int i=0;i<students;i++) {
			st = new StringTokenizer(br.readLine());
			studentsInfo[i][0] = Integer.parseInt(st.nextToken());
			studentsInfo[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0;i<studentsInfo.length;i++) {
			// 남학생
			if (studentsInfo[i][0] == 1) {
				int index = studentsInfo[i][1];
				for (int j=1;j*index<=n;j++) {
					change(switches, j*index);
				}
			} else if (studentsInfo[i][0] == 2) { // 여학생
				int index = studentsInfo[i][1];
				change(switches, index);
				int symmetryCheck = 1;
				while (true) {
					if ((index - symmetryCheck) < 1 || (index + symmetryCheck) > n) {
						break;
					}
					if (switches[index-symmetryCheck] != switches[index+symmetryCheck]) {
						break;
					}
					change(switches, index-symmetryCheck);
					change(switches, index+symmetryCheck);
				
					symmetryCheck++;
				}
			}
		}
		
		for (int i=1;i<switches.length;i++) {
			System.out.print(switches[i] + " ");
			if (i % 20 == 0) {
				System.out.println();
			}
		}
	}

}
