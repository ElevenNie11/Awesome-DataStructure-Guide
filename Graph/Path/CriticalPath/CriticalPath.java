package Graph.Path.CriticalPath;

import java.util.ArrayList;
import java.util.List;

// @Algorithm: 关键路径-最早/最晚发生时间（AOE网）
// @Author: Shiyi Nie (ElevenNie11)
// @Date: 2026-11-13
// @Way: AdjacentMatrix（邻接矩阵） + TopologicalSort（Kahn拓扑排序）

public class CriticalPath {

    // 测试关键路径
    // public static void main(String[] args){
    //     // 图的表示方法之一：邻接矩阵，weight[i][j]就是活动<i,j>的工期
    //     String[] vertices = {"v1", "v2", "v3", "v4", "v5", "v6"};
    //     int[][] weight = {
    //         {0, 3, 2, inf, inf, inf},
    //         {inf, 0, inf, 2, 3, inf},
    //         {inf, inf, 0, 4, inf, inf},
    //         {inf, inf, inf, 0, inf, 3},
    //         {inf, inf, inf, inf, 0, 2},
    //         {inf, inf, inf, inf, inf, 0}
    //     };

    //     // 调用criticalPath方法
    //     criticalPath(vertices, weight);
    // }
}
