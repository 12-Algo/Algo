class Solution {
    static int N = 5; // 대기실의 크기
    static int[][] directions = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}  // 대각선
        };

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int i = 0; i < N; i++) {
            
            answer[i] = isOk(places[i]) ? 1 : 0;
        }
        
        return answer;
    }

    static boolean isOk(String[] place) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                
                
                if (place[r].charAt(c) == 'P') { // 사람이 있다면
                    for (int i = 0; i < 4; i++) {
                        for(int j= 1; j <= 2; j++){
                            int nr = r + directions[i][0] * j;
                            int nc = c + directions[i][1] * j;
                            
                            if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) break;
                            
                            if(place[nr].charAt(nc) == 'X'){
                                break;
                            }
                            if(place[nr].charAt(nc) == 'P') {
                                return false;
                            }
                        }
                    }
                    
                    // 대각선 체크
                    for (int i = 4; i < 8; i++) {
                        int nr = r + directions[i][0];
                        int nc = c + directions[i][1];
                        
                        if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
                        if (place[nr].charAt(nc) == 'P') {
                            // 대각선에 사람이 있을 경우
                            if (i == 4 || i == 5) { // 아래쪽 대각선
                                if (place[nr].charAt(c) == 'O') return false; // 아래

                                if (c + 1 < 5 && place[r].charAt(nc) == 'O') return false; // 오른쪽

                            } else { // 위쪽 대각선

                                if (place[nr].charAt(c) == 'O') return false; // 위

                                if (place[r].charAt(nc) == 'O') return false; // 왼쪽
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
