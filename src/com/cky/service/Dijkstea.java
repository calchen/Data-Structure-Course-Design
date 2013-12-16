package com.cky.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.cky.model.Graphic;

/**
 * 文件名：Dijkstea.java
 * 作   者： 陈恺垣 E-mail:chenkaiyuan1993@gmail.com
 * 创建时间：2013年12月16日 下午7:28:52
 * 类说明 ：
 */
class Dijkstea {
	Graphic graphic ;

	public Dijkstea(Graphic graphic) {
		this.graphic = graphic;
	}

	/**
	 * 获得从A到B的最短路径
	 * @param A
	 * @param B
	 * @return A到B最短路径上所有经过的节点
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
					} else if(cost[i] == 0) {
						cost[i] = graphic.getValue()[n][i] + cost[n];
						footstep[i] = n;
						queue.offer(i);
					}
				}
			}
		}
		
		// 获取从A到B的最短路径上的节点(逆序)
		ArrayList<String> list = new ArrayList<String>();
		for(int i = indexB;i != 0;) {
			list.add(graphic.getNodeName(i));
			i = footstep[i];
		}
		
		// 将A到B的最短路径上的节点顺序存储到 result
		String[] result = new String[list.size()];
		for(int i = 0;i < result.length;i++) {
			result[i] = list.get(result.length-i-1);
		}
		
		return result;
	}

}
