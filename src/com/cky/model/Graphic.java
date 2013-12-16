package com.cky.model;

import java.util.ArrayList;

/**
 * 文件名：Graphic.java
 * 作   者： 陈恺垣 E-mail:chenkaiyuan1993@gmail.com
 * 创建时间：2013年12月14日 下午4:29:13
 * 类说明 ：用邻接矩阵存储的图
 */
public class Graphic {
	// 标题
	private String[] title;
	// 节点列表
	private String[] nodeList;
	// 节点数目
	private int nodeNum;
	// 权值
	private int[][] value;

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
	 * @param s
	 * @return s节点的下标
	 */
	public int getNodeIndex(String s) {
		for(int i = 0;i < nodeNum;i++) {
			if(nodeList[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 通过节点下表获得节点名
	 * @param n
	 * @return s节点名
	 */
	public String getNodeName(int n) {
		if(n >= 0 && n < nodeNum) {
			return nodeList[n];
		}
		return null;
	}

	public String[] getTitle() {
		return title;
	}

	public String[] getNodeList() {
		return nodeList;
	}

	public int getNodeNum() {
		return nodeNum;
	}

	public int[][] getValue() {
		return value;
	}

}
