import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		
		char[][] friendInfo = new char[n][n]; 
		
		for(int i=0;i<n;i++) {
			String line = bf.readLine();
			for(int j=0;j<n;j++) {
				friendInfo[i][j] = line.charAt(j);
			}
		}
		
		int maxFriend = findMostFamous(friendInfo, n);
		System.out.println(maxFriend);
	}
	
//	2-친구를 구하는 함수 [1. 두 사람이 친구인 경우 2. 두 사람과 동시에 친구인 사람 X가 존재하는 경우 ]
	static int findMostFamous(char[][] friendInfo, int n) {
		int maxFriend = 0;
		int temp = 0;
		
		for(int i=0;i<n;i++) {
			temp = 0;
			for(int j=0;j<n;j++) {
				if(i==j) {
					continue;
				}
//				1. 두 사람이 친구인 경우 
				if(friendInfo[i][j] == 'Y') {
					temp +=1;
				}
//				2. 두 사람이 직접 친구는 아니지만 동시에 친구인 사람이 존재하는 경우
				else {
					for(int h=0;h<n;h++) {
						if(i==h || j==h) {
							continue;
						}
						if(friendInfo[i][h] == 'Y' && friendInfo[j][h] == 'Y') {
							temp += 1;
//               동시에 친구인 사람이 한 명이라도 존재하면 2-친구가 성립하므로 break
                            break;
						}
					}
				}
			}
			maxFriend = Math.max(temp, maxFriend);
		}
		return maxFriend;
	}
	
}
		