import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 8
 * 0 1 0 1 0 0 0 1
 * 2
 * 1 3
 * 2 3
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        List<Integer> list = new ArrayList<>();
        list.add(-1); // 리스트 인덱스를 1부터 사용하기 위해 -1을 추가

        for (int i = 0; i < str.length; i++) {
            list.add(Integer.parseInt(str[i]));
        }

        int try_cnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < try_cnt; i++) {
            String[] input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int index = Integer.parseInt(input[1]);

            if (s == 1) { // 남학생
                for (int j = index; j < list.size(); j += index) {
                    list.set(j, (list.get(j) + 1) % 2);
                }
            } else { // 여학생
                list.set(index, (list.get(index) + 1) % 2);
                for (int l = index - 1, r = index + 1; l >= 1 && r <= n; l--, r++) {
                    if (list.get(l).equals(list.get(r))) {
                        list.set(l, (list.get(l) + 1) % 2);
                        list.set(r, (list.get(r) + 1) % 2);
                    } else {
                        break;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(list.get(i));
            if (i % 20 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
    }
}
