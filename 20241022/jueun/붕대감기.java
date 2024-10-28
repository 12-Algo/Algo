class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {        
        int hp = health;
        int streak = 1;
        
        int t = 1, T = attacks[attacks.length-1][0];
        int atk = 0;
        for(; t<=T; t++) {
            if(t == attacks[atk][0]) {  
                hp -= attacks[atk][1];
                
                if(hp <= 0)
                    return -1;
                
                atk++;
                streak = 1;
                
            } else if(streak == bandage[0]) {
                hp += bandage[2] + bandage[1];
                streak = 1;
            } else {
                hp += bandage[1];
                streak ++;
            }
            
            if(hp > health)
                hp = health;
            
            //System.out.printf("%d: %d %d\n", t, hp, streak-1);
        }

        return hp;
    }
}
