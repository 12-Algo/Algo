import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ahnseongjun20546 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int budgetBnp = Integer.parseInt(br.readLine());
        int budgetTiming = budgetBnp;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] prices = new int[14];
        for (int i=0;i<14;i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        int purchaseCountBnp = 0;
        int purchaseCountTiming = 0;
        int increase = 0;
        int decrease = 0;
        purchaseCountBnp += budgetBnp / prices[0];
        budgetBnp -= (budgetBnp / prices[0]) * prices[0];
        for (int i=1;i<14;i++) {
            int yesterdayPrice = prices[i-1];
            int price = prices[i];

            // bnp
            purchaseCountBnp += budgetBnp / price;
            budgetBnp -= (budgetBnp / price) * price;

            // timing
            if (price < yesterdayPrice) {
                decrease++;
                increase = 0;
            } else if (price > yesterdayPrice) {
                increase++;
                decrease = 0;
            } else {
                increase = 0;
                decrease = 0;
            }

            if (increase >= 3) {
                budgetTiming += purchaseCountTiming * price;
                purchaseCountTiming = 0;
            } else if (decrease >= 3) {
                purchaseCountTiming += budgetTiming / price;
                budgetTiming -= (budgetTiming / price) * price;
            }
        }

        int resultBnp = budgetBnp + (purchaseCountBnp * prices[13]);
        int resultTiming = budgetTiming + (purchaseCountTiming * prices[13]);

        if (resultBnp > resultTiming) {
            System.out.println("BNP");
        } else if (resultTiming > resultBnp) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }
    }
}


