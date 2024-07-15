import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class MiracleSales {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int moneyJ = Integer.parseInt(br.readLine());
		int moneyS = moneyJ;

		String stocks = br.readLine();
		StringTokenizer st = new StringTokenizer(stocks, " ");

		int[] stock = new int[14];
		for (int i = 0; i < 14; i++) {
			stock[i] = Integer.parseInt(st.nextToken());
		}

		// 준현
		int stockJ = 0;
		int resultJ = 0;
		for (int i = 0; i < 14; i++) {
			if (moneyJ / stock[i] > 0) {
				stockJ += (moneyJ / stock[i]);
				moneyJ %= stock[i];
			}
		}
		resultJ = moneyJ + (stock[13] * stockJ);

		// 성민
		int stockS = 0;
		int resultS = 0;
		
		for (int i = 3; i < 14; i++) {
			//3일 연속 상승하면 판매
			if((stock[i-3] < stock[i-2])&&(stock[i-2]<stock[i-1]) && (stock[i-1]<stock[i])) {
				moneyS += (stockS*stock[i]);
				stockS = 0;
			}//3일 연속 하락하면 구매
			else if ((stock[i-3] > stock[i-2])&&(stock[i-2]>stock[i-1]) && stock[i-1]>stock[i]) {
				if(moneyS >= stock[i]) {
					stockS += (moneyS/stock[i]);
					moneyS %= stock[i];
				}
			}
		}
		resultS = moneyS + (stock[13] * stockS);
		if (resultJ > resultS) {
			System.out.println("BNP");
		} else if (resultJ < resultS) {
			System.out.println("TIMING");
		} else {
			System.out.println("SAMESAME");
		}
		System.out.println("%d   %d".formatted(resultS, resultJ));
	}
}
