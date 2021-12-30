import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node> {
    public List<Integer> route = new ArrayList<>();
    // 已经在路径里，由于优化速度
    public Map<Integer, Boolean> visit = new HashMap<>();
    public int cost = 0;

    public Node(List<Integer> route, Map<Integer, Boolean> visit, int cost) {
        this.route = route;
        this.visit = visit;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        if (cost < o.cost) {
            return -1;
        } else if (cost > o.cost) {
            return 1;
        }

        return 0;
    }

    public void print() {
        var printStr = "";
        for (var i = 0; i < route.size(); i++) {
            printStr += route.get(i);
            if (i != route.size() - 1) {
                printStr += "=>";
            }
        }
        System.out.println(printStr);
        System.out.println("代价："+cost);
    }
}
