import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    private PriorityQueue<Node> openList = new PriorityQueue<>();
    private List<Node> closeList = new ArrayList<>();
    private Node result;
    private static final int WHITE_CARD_NUM = 3;
    private static final int H_FACTOR = 1;

    // A*算法开始
    public void begin() {
        // 初始化

        var start = new Node(new ArrayList<Card>(Arrays.asList(Card.BLACK, Card.BLACK, Card.BLACK,
                Card.WHITE, Card.WHITE, Card.WHITE,

                Card.EMPTY)), 6,
                null, 0, 0);
        result = null;
        if (isEnd(start)) {
            result = start;
            result.print(false);
            return;
        }

        openList.add(start);

        //开始循环，类似广度优先遍历
        while (!openList.isEmpty()) {
            var curr = openList.poll();
            closeList.add(curr);
            // 将可能的节点加入openList
            for (var i = -3; i <= 3; i++) {
                if (i == 0) {
                    continue;
                }

                var addG = 0;
                addG = Math.abs(i) <= 2 ? 1 : Math.abs(i) - 1;
                addToOpenList(curr, i, addG);
            }

            // 找到目标，提前结束
            if (result != null) {
                break;
            }
        }

        // 打印结果
        if (result == null) {
            System.out.println("not found");
        } else {
            result.print(false);
        }
    }

    // A*算法核心逻辑
    public void addToOpenList(Node current, int bios, int addG) {
        // Empty的位置
        var emptyIndex = current.emptyIndex;
        // 被移动的牌的位置
        var moveIndex = emptyIndex + bios;
        // 越界直接跳出
        if (moveIndex < 0 || moveIndex >= current.coord.size()) {
            return;
        }

        // 移动牌
        var newCoord = new ArrayList<Card>(current.coord);
        newCoord.set(emptyIndex, newCoord.get(moveIndex));
        newCoord.set(moveIndex, Card.EMPTY);

        // 计算估计值：公式：总白牌数-排在前面的白牌数
        var H = 0;
        var whiteNum = 0;
        for (var i = newCoord.size() - 1; i >= 0; i--)  {
            if (newCoord.get(i) == Card.WHITE) {
                whiteNum++;
            } else if (newCoord.get(i) == Card.BLACK) {
                H += whiteNum;
            }
        }
//        for (var i = 0; i < newCoord.size(); i++) {
//            if (newCoord.get(i) == Card.WHITE) {
//                H++;
//            } else if (newCoord.get(i) == Card.BLACK) {
//                break;
//            }
//        }
//        H -= WHITE_CARD_NUM;
        H *= H_FACTOR;
        // 准确G值：即当前已消耗的步数
        var G = current.G + addG;
        var node = new Node(newCoord, moveIndex, current, G, H);
        if (canAdd(node)) {
            var sameNodeList = openList.stream().filter(n -> n.sameCoord(node)).toArray(Node[]::new);
            // openList已存在该节点并且条件优于之前时，修改该节点G值
            if (sameNodeList.length > 0) {
                var sameNode = sameNodeList[0];
                if (sameNode.G > G) {
                    sameNode.G = G;
                    sameNode.parent = current;
                    openList.add(sameNode);
                }
            } else {
                if (isEnd(node)) {
                    result = node;
                }
                openList.add(node);
            }

        }

    }

    // 判断是否加到openList
    public boolean canAdd(Node node) {
        // 判断是否closeList里
        for (var closeNode : closeList) {
            if (closeNode.sameCoord(node)) {
                return false;
            }
        }

        return true;
    }

    // 判断是否满足结束条件
    public boolean isEnd(Node node) {
        var coord = node.coord;
        var whiteAhead = 0;
        for (var i = 0; i < coord.size(); i++) {
            if (coord.get(i) == Card.WHITE) {
                whiteAhead++;
            } else if (coord.get(i) == Card.BLACK) {
                break;
            }
        }

        return whiteAhead == WHITE_CARD_NUM;
    }
}
