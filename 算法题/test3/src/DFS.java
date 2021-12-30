public class DFS {
    private int [][] square = new int[5][5];

    private int [][] nextChange = new int[][]{{-3, 0}, {3, 0}, {0, 3}, {0, -3}, {2, 2}, {2, -2}, {-2, 2}, {-2, -2}};

    int  count = 0;

    public void begin() {
        DFS(1, 0, 0);
        System.out.println(count);
    }

    public void DFS(int beginNum, int beginX, int beginY) {
        square[beginX][beginY] = beginNum;
        if (beginNum == square.length * square[0].length) {
            print();
            count++;
            return;
        }

        for (var i = 0; i < nextChange.length; i++) {
            int newX = beginX + nextChange[i][0];
            int newY = beginY + nextChange[i][1];

            if (newX < 0 || newX >= square.length) {
                continue;
            }

            if (newY < 0 || newY >= square[0].length) {
                continue;
            }

            if (square[newX][newY] != 0) {
                continue;
            }

            DFS(beginNum+1, newX, newY);
            square[newX][newY] = 0;
        }

    }

    public void print() {
        for (var i = 0; i < square.length; i++) {
            var printStr = "";
            for (var j = 0; j < square[0].length; j++) {
                printStr += square[i][j] + " ";
            }
            System.out.println(printStr);
        }
        System.out.println("==============================");
    }
}
