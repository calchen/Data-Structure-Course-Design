package com.cky.model;

import java.util.ArrayList;

/**
 * 用邻接矩阵存储的图 ，存储该图节点与节点值对应的标题。
 * <br />
 * @version 1.0 <br />
 * @author  陈恺垣 chenkaiyuan1993@gmail.com
 */
public class Graphic {
	/**
	 * 标题。节点与节点值的名字，比如：城市 票价
	 */
	private String[] title;
	/**
	 * 节点列表
	 */
	private String[] nodeList;
	/**
	 * 节点数目
	 */
	private int nodeNum;
	/**
	 * 所有节点权值
	 */
	private int[][] value;

	/**
	 * 构造函数
	 * 
	 * @param list
	 *            一个ArrayList，一个数据为标题，第二个数据为城市列表，之后n个数据为n个城市间的权值
	 */
	public Graphic(ArrayList<String> list) {
		// 拆分出标题
		this.title = list.get(0).split(" ");
		// 拆分出城市列表
		this.nodeList = list.get(1).split(" ");
		// 拆分出每个数据
		int length = list.size() - 2;
		this.value = new int[length][length];
		for (int i = 2; i < list.size(); i++) {
			String[] str = list.get(i).split(" ");
			for (int j = 0; j < length; j++) {
				this.value[i - 2][j] = Integer.parseInt(str[j]);
			}
		}
		this.nodeNum = nodeList.length;
	}

	/**
	 * 通过节点名获得下标
	 * 
	 * @param s
	 *            节点名
	 * @return s节点的下标，若不存在返回-1
	 */
	public int getNodeIndex(String s) {
		for (int i = 0; i < nodeNum; i++) {
			if (nodeList[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 通过节点下表获得节点名
	 * 
	 * @param n
	 *            节点下标
	 * @return 节点名，若不存在返回null
	 */
	public String getNodeName(int n) {
		if (n >= 0 && n < nodeNum) {
			return nodeList[n];
		}
		return null;
	}

	/**
	 * 获取标题
	 * 
	 * @return 标题数组
	 */
	public String[] getTitle() {
		return title;
	}

	/**
	 * 获取节点列表
	 * 
	 * @return 节点数组
	 */
	public String[] getNodeList() {
		return nodeList;
	}

	/**
	 * 获取节点下标
	 * 
	 * @return 节点下标
	 */
	public int getNodeNum() {
		return nodeNum;
	}

	/**
	 * 获取所有节点权值
	 * 
	 * @return 所有节点权值组成的数组
	 */
	public int[][] getValue() {
		return value;
	}

}
