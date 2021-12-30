import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Search {
    private int [][] costMap = new int[][]{
            {0, 7, 3, 10, 15},
            {6, 0, 5, 13, 12},
            {4, 8, 0, 5, 10},
            {9, 11, 6, 0, 11},
            {17, 14, 9, 8, 0},
    };
    private PriorityQueue<Node> queue = new PriorityQueue<>();

    public void begin() {
        for (int i = 0; i < costMap.length; i++) {
            System.out.println("从" + i + "开始:");
            uniformSearch(i);
        }
    }

    public void uniformSearch(int from) {
//        int from = 0;
        Node start = new Node(new ArrayList<>(Arrays.asList(from)), new HashMap<>(){{put(from, true);}}, 0);
        queue.clear();
        queue.add(start);
        while (!queue.isEmpty()) {
            var curr = queue.poll();
            if (curr.route.size() == costMap.length) {
                curr.cost += costMap[curr.route.get(curr.route.size() - 1)][from];
                curr.route.add(from);
                queue.add(curr);
                continue;
            }

            if (curr.route.size() == costMap.length + 1) {
                curr.print();
                break;
            }

            for (var i = 0; i < costMap.length; i++) {
                var currLast = curr.route.get(curr.route.size() - 1);
                if (curr.visit.get(i) == null) {
                    var newRoute = new ArrayList<>(curr.route);
                    newRoute.add(i);
                    var newVisit = new HashMap<>(curr.visit);
                    newVisit.put(i, true);
                    Node node = new Node(newRoute, newVisit, curr.cost + costMap[currLast][i]);
                    queue.add(node);
                }
            }
        }
    }
}
