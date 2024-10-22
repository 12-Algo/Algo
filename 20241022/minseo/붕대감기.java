class Solution {
    // 걍 구현;
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int max = health; // 최대 체력
        int bcnt = 0;
        int attackTime = 0; // attack의 인덱스
        
        // 시간동안 계속~~맞음
        for (int t = 0; t <= attacks[attacks.length - 1][0]; t++) {
            
            // 맞을 때가 되었을 때
            if (attackTime < attacks.length && t == attacks[attackTime][0]) {
                health -= attacks[attackTime][1]; // 맞음

                attackTime++; // 다음 공격 인덱스
                bcnt = 0; // 연속 성공 카운트 초기화

                // 체력이 0 이하면 죽음
                if (health <= 0) {
                    return -1;
                }
            } else { // 붕대 감음
                bcnt++;

                if (bcnt == bandage[0]) { // 시간 다 채웠으면
                    // 현재 체력 + 초당 회복량 + 추가 회복량
                    health = Math.min(max, health + bandage[1] + bandage[2]);
                    bcnt = 0;
                } else {
                    health = Math.min(max, health + bandage[1]);
                }
            }
        }

        answer = health;
        return answer;
    }
}
