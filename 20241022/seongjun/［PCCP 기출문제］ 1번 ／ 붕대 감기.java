import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        for (int i=0;i<attacks.length-1;i++) {
            health -= attacks[i][1];
            if (health <= 0) {
                return -1;
            }
            int term = attacks[i+1][0] - attacks[i][0] - 1;
            int heal = (term / bandage[0] * bandage[2]) + ((term / bandage[0]) * bandage[0] * bandage[1]) + (term % bandage[0] * bandage[1]);
            health = Math.min(maxHealth, health+heal);
        }
        
        health -= attacks[attacks.length-1][1];
        if (health <= 0) {
            return -1;
        }
        return health;

    }
}