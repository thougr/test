import java.util.List;

public class Node implements Comparable<Node> {
    public List<Card> coord;
    public int emptyIndex;
    public Node parent;
    public int G;
    public int H;

    public Node(List<Card> coord, int emptyIndex, Node parent, int g, int h) {
        this.coord = coord;
        this.emptyIndex = emptyIndex;
        this.parent = parent;
        G = g;
        H = h;
    }


    @Override
    public int compareTo(Node o) {
        if (G + H > o.G + o.H) {
            return 1;
        } else if (G + H < o.G + o.H) {
            return -1;
        }
        return 0;
    }

    public boolean sameCoord(Node node) {
        var coord2 = node.coord;
        for (var i = 0; i < coord.size(); i++) {
            if (coord.get(i) != coord2.get(i)) {
                return false;
            }
        }

        return true;
    }

    public void print(boolean addArrow) {
        if (parent != null) {
            parent.print(true);
        }

        String printStr = "";
        for (var i = 0; i < coord.size(); i++) {
            switch (coord.get(i)) {
                case BLACK:
                    printStr += "B ";
                    break;
                case WHITE:
                    printStr += "W ";
                    break;
                case EMPTY:
                    printStr += "E ";
                    break;
            }
        }

        if (addArrow) {
            printStr += "=>";
        }

        System.out.println(printStr);
    }
}
