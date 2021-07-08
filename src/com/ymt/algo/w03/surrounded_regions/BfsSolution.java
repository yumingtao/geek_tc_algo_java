package com.ymt.algo.w03.surrounded_regions;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 思路:
 * 1. 找到一个为'O'的点
 * 2. 判断其位置是否在边界，如果不在边界，bfs
 * 3. 四个方向移动，点为'O'，且未被访问过，标记为已访问，并放入结果集合中
 * 4. 返回主函数时，循环找到点的集合，判断是否有点在边界，有则放弃，没有则放入总的结果列表
 * 5. 处理结果列表，全部替换成'X'
 *
 * @author yumingtao
 * @date 7/6/21 10:41 PM
 */
public class BfsSolution {
    private final int[] dx = {-1, 0, 0, 1};
    private final int[] dy = {0, 1, -1, 0};
    private final int DIRECTIONS = 4;
    private boolean[][] visited;
    private List<Pair<Integer, Integer>> pairs = new ArrayList<>();
    private List<List<Pair<Integer, Integer>>> allPairs = new ArrayList<>();
    int rows;
    int cols;

    public void solve(char[][] board) {
        //循环二维数组
        rows = board.length;
        cols = board[0].length;

        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //如果点不在边界，且为'O'
                if (board[i][j] == 'O' && i > 0 && j > 0 && i < rows - 1 && j < cols - 1 && !visited[i][j]) {
                    bfs(i, j, board);
                    boolean isExpected = true;
                    for (Pair p : pairs) {
                        int px = (int) p.getKey();
                        int py = (int) p.getValue();
                        if (px == 0 || py == 0 || px == rows - 1 || py == cols - 1) {
                            isExpected = false;
                            break;
                        }
                    }

                    if (isExpected) {
                        allPairs.add(new ArrayList<>(pairs));
                    }

                    pairs.clear();
                }
            }
        }

        allPairs.stream().forEach(a -> {
            a.stream().forEach(p -> {
                board[p.getKey()][p.getValue()] = 'X';
            });
        });

        System.out.println();
    }

    private void bfs(int i, int j, char[][] board) {
        //模板，先加入队列，再标记为已访问
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Pair connectedOPair = new Pair(i, j);
        queue.add(connectedOPair);
        visited[i][j] = true;

        //将未访问过的，连通的'O'加入链表
        pairs.add(connectedOPair);

        Pair pair;
        while (!queue.isEmpty()) {
            pair = queue.poll();
            //模板，判断4个方向的出边
            for (int k = 0; k < DIRECTIONS; k++) {
                int ni = (int)pair.getKey() + dx[k];
                int nj = (int)pair.getValue() + dy[k];

                //判断如果越界，直接忽略
                if (ni < 0 || nj < 0 || ni >= rows || nj >= cols) {
                    continue;
                }

                //如果出边的点在边界，且为'O'，直接返回
                if (board[ni][nj] == 'O' && !visited[ni][nj]) {
                    connectedOPair = new Pair(ni, nj);
                    queue.add(connectedOPair);
                    visited[ni][nj] = true;
                    //将未访问过的，连通的'O'加入链表
                    pairs.add(connectedOPair);
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] input = new char[][]{
                {'O', 'x', 'x', 'O', 'x'},
                {'x', 'O', 'O', 'x', 'O'},
                {'x', 'O', 'x', 'O', 'x'},
                {'O', 'x', 'O', 'O', 'O'},
                {'x', 'x', 'O', 'x', 'O'}
        };

        BfsSolution solution = new BfsSolution();
        solution.solve(input);
    }
}

//Wrong Answer:
// input:[
// ["O","X","X","O","X"],
// ["X","O","O","X","O"],
// ["X","O","X","O","X"],
// ["O","X","O","O","O"],
// ["X","X","O","X","O"]
// ]
// Output:[
// ["O","X","X","O","X"],
// ["X","X","X","X","O"],
// ["X","X","X","X","X"],
// ["O","X","O","O","O"],
// ["X","X","O","X","O"]
// ]
// Expected:[
// ["O","X","X","O","X"],
// ["X","X","X","X","O"],
// ["X","X","X","O","X"],
// ["O","X","O","O","O"],
// ["X","X","O","X","O"]
// ]


//输入：board = [
// ["X","X","X","X"],
// ["X","O","O","X"],
// ["X","X","O","X"],
// ["X","O","X","X"]
// ]
//输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
//会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
//
//
// 示例 2：
//
//
//输入：board = [["X"]]
//输出：[["X"]]
//
//
//
//
// 提示：
//
//
// m == board.length
// n == board[i].length
// 1 <= m, n <= 200
// board[i][j] 为 'X' 或 'O'