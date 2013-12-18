package com.cky.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import com.cky.model.Graphic;

/**
 * Floyd算法实现获取两节点间最短路径节点 <br />
 * 
 * @version 1.0 <br />
 * @author 陈恺垣 chenkaiyuan1993@gmail.com
 */
class Floyd {
	/**
	 * 邻接矩阵存储的图
	 */
	private Graphic graphic;

	/**
	 * 构造方法
	 * 
	 * @param graphic
	 *            邻接矩阵存储的图
	 */
	public Floyd(Graphic graphic) {
		this.graphic = graphic;
	}

	/**
	 * 获得从A到B的最短路径
	 * 
	 * @param A
	 *            开始节点
	 * @param B
	 *            结束节点
	 * @return A到B最短路径上所有经过的节点数组
	 */
	public String[] getPath(String A, String B) {
		int indexA = graphic.getNodeIndex(A);
		int indexB = graphic.getNodeIndex(B);

		// 记录从A到各个节点的最短路径
		int[][] footstep = new int[graphic.getNodeNum()][graphic.getNodeNum()];
		for (int i = 0; i < graphic.getNodeNum(); i++) {
			Arrays.fill(footstep[i], -1);
		}
		// 记录从A到各个节点的最短路径的花费
		int[][] cost = new int[graphic.getNodeNum()][graphic.getNodeNum()];

		// 将graphic中value的值复制到cost中去
		for (int i = 0; i < graphic.getNodeNum(); i++) {
			for (int j = 0; j < graphic.getNodeNum(); j++) {
				cost[i][j] = graphic.getValue()[i][j];
				if (graphic.getValue()[i][j] != 0) {
					// footstep[i][j] = j;
				}
			}
		}

		// 遍历图，获取每两个连通的节点间最低花费,以及最短路径节点
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost.length; j++) {
				if (cost[i][j] != 0) {
					int min = j;
					for (int k = 0; k < cost.length; k++) {
						if (cost[j][k] != 0) {
							if (cost[j][k] + cost[i][j] < cost[i][k]) {
								cost[i][k] = cost[j][k] + cost[i][j];
								footstep[i][k] = j;
								if (k < min) {
									min = k;
								}
							} else if (cost[i][k] == 0) {
								cost[i][k] = cost[j][k] + cost[i][j];
								footstep[i][k] = j;
								if (k < min) {
									min = k;
								}
							}
						}
					}
					j = j != min ? min - 1 : min;
				}
			}
		}

		// 获取从A到B的最短路径上的节点
		ArrayList<String> list = new ArrayList<String>();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(footstep[indexA][indexB]);
		list.add(A);
//		int a = indexA, b = indexB;
//		while (!stack.isEmpty()) {
//			int n = stack.pop();
//			if (n != -1) {
//				list.add(graphic.getNodeName(n));
//
//				if (footstep[a][n] != -1) {
//					stack.push(footstep[a][n]);
//					b = n;
//
//				} else if (footstep[n][b] != -1) {
//					stack.push(footstep[n][b]);
//					a = n;
//				}
//			}
//	 }

		findPath(indexA, indexB, list, footstep);
		if (list.size() == 1 && graphic.getValue()[indexA][indexB] != 0) {
			list.add(B);
		} else if (list.size() > 1) {
			list.add(B);
		} else {
			list.clear();
		}

		return list.toArray(new String[list.size()]);
	}

	/**
	 * 寻找两节点之间最短路径
	 * @param i
	 * @param j
	 * @param list
	 * @param path
	 */
	private void findPath(int i, int j, ArrayList<String> list, int[][] path) {
		int k = path[i][j];
		if (k == -1)
			return;
		findPath(i, k, list, path); // 递归
		list.add(graphic.getNodeName(k));
		findPath(k, j, list, path);
	}

}
