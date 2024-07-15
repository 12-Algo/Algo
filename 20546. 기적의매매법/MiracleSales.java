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
		int yesterday = stock[0];
		int cnt = 0;

		for (int i = 1; i < 14; i++) {
			int today = stock[i];

			if (today < yesterday) {
				cnt -= 1;
			} else if (today > yesterday) {
				cnt += 1;
			} else {
				cnt = 0;
			}

			if (cnt == 3) {
				if (moneyS >= today) {
					stockS += (moneyS / today);
					moneyS %= today;
					cnt = 0;
				}
			} else if (cnt == -3) {
				if (stockS > 0) {
					moneyS += stockS * today;
					stockS = 0;
					cnt = 0;
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
	}
}
