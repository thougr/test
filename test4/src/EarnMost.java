import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class EarnMost {
    public String[] chinese = new String[] {"水稻", "大豆", "燕麦", "牧草"};
    private int [][] earnMap = new int[][] {
            {3, 2, 4, 1},
            {15, 1, 17, 7},
            {9, 4, 6, 5},
            {11, 1, 4, 3}
    };

    public void begin() {
        List<Integer> dp = Arrays.stream(earnMap[3]).boxed().collect(Collectors.toList());
        List<List<Integer>> route = new ArrayList<>();
        for (var i = 0; i < earnMap.length; i++) {
            route.add(new ArrayList<>(Arrays.asList(i)));
        }

        var printStr = "";
        for (var cnt = 0; cnt < 4; cnt++) {
            var newDp = new ArrayList<Integer>(dp);
            var newRoute = new ArrayList<>(route);
            for (var i = 0; i < earnMap.length; i++) {
                for (var j = 0; j < earnMap.length; j++) {
                    if (newDp.get(i) < dp.get(j) + earnMap[j][i]) {
                        var newList = new ArrayList<>(route.get(j));
                        newList.add(i);
                        newRoute.set(i, newList);
                    }
                    newDp.set(i, Math.max(newDp.get(i), dp.get(j) + earnMap[j][i]));
                }
            }
            route = newRoute;
            dp = newDp;

            var maxEarn = 0;
            var maxIndex = -1;
            for (var i = 0; i < earnMap.length; i++) {
                if (maxEarn < dp.get(i)) {
                    maxEarn = dp.get(i);
                    maxIndex = i;
                }
            }
            if (cnt == 3) {
                var maxRoute = route.get(maxIndex);
                for (var i = 0; i < maxRoute.size(); i++) {
                    printStr += chinese[maxRoute.get(i)];
                    if (i != maxRoute.size() - 1) {
                        printStr += "=>";
                    }
                }
                System.out.println(printStr);
                System.out.println("盈利：" + maxEarn);
            }
        }
    }
}
