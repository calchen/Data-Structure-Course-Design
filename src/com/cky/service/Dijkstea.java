package com.cky.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.cky.model.Graphic;

/**
 * Dijkstea算法实现获取两节点间最短路径节点 <br />
 * 
 * @version 1.0 <br />
 * @author 陈恺垣 chenkaiyuan1993@gmail.com
 */
class Dijkstea {
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
	public Dijkstea(Graphic graphic) {
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
		// TODO Auto-generated method stub
		int indexA = graphic.getNodeIndex(A);
		int indexB = graphic.getNodeIndex(B);

		// 记录从A到各个节点的最短路径,A所在节点默认我0
		int[] footstep = new int[graphic.getNodeNum()];
		// 记录从A到各个节点的最短路径的花费
		int[] cost = new int[graphic.getNodeNum()];

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(indexA);

		// 遍历图获取最短路径
		while (!queue.isEmpty()) {
			int n = queue.poll();
			for (int i = 0; i < graphic.getNodeNum(); i++) {
				if (graphic.getValue()[n][i] != 0) {
					if (graphic.getValue()[n][i] + cost[n] <= cost[i]) {
						cost[i] = graphic.getValue()[n][i] + cost[n];
						footstep[i] = n;
						queue.offer(i);
					} else if (cost[i] == 0) {
						cost[i] = graphic.getValue()[n][i] + cost[n];
						footstep[i] = n;
						queue.offer(i);
					}
				}
			}
		}

		// 获取从A到B的最短路径上的节点(逆序)
		ArrayList<String> list = new ArrayList<String>();
		for (int i = indexB; !graphic.getNodeName(i).equals(A);) {
			list.add(graphic.getNodeName(i));
			i = footstep[i];
		}
		if (list.size() == 1 && graphic.getValue()[indexA][indexB] != 0) {
			list.add(A);
		} else if (list.size() > 1) {
			list.add(A);
		}

		// 将A到B的最短路径上的节点顺序存储到 result
		String[] result = null;
		if (list.size() != 1) {
			result = new String[list.size()];
			for (int i = 0; i < result.length; i++) {
				result[i] = list.get(result.length - i - 1);
			}
		}

		return result;
	}

}
