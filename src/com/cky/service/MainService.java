package com.cky.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.cky.model.Graphic;

/**
 * 用于GUI交互的类，所有GUI需要的数据操作均由这个类实现 <br />
 * 
 * @version 1.0 <br />
 * @author 陈恺垣 chenkaiyuan1993@gmail.com
 */
public class MainService {
	/**
	 * 邻接矩阵存储的图
	 */
	private Graphic graphic = null;

	/**
	 * 构造方法
	 */
	public MainService() {

	}

	/**
	 * 使用Dijstea算法查询节点A和节点B之间的最短路径
	 * 
	 * @param A
	 *            开始节点
	 * @param B
	 *            结束节点
	 * @return A到B最短路径上所有经过的节点数组。若数据未加载则返回null。
	 */
	public String[] getPathByDijkstea(String A, String B) {
		if (graphic != null) {
			return new Dijkstea(graphic).getPath(A, B);
		}
		return null;
	}

	/**
	 * 使用Floyd算法查询节点A和节点B之间的最短路径
	 * 
	 * @param A
	 *            开始节点
	 * @param B
	 *            结束节点
	 * @return A到B最短路径上所有经过的节点数组。若数据未加载则返回null。
	 */
	public String[] getPathByFloyd(String A, String B) {
		if (graphic != null) {
			return new Floyd(graphic).getPath(A, B);
		}
		return null;
	}

	/**
	 * 加载数据
	 * 
	 * @param fileName
	 *            数据文件包含文件名的绝对地址
	 */
	public void loadData(String fileName) {
		File file = new File(fileName);
		if(file.exists()) {
			BufferedReader reader = null;
			ArrayList<String> list = null;

			try {
				reader = new BufferedReader(new FileReader(file));
				list = new ArrayList<String>();
				String str = reader.readLine();
				while (str != null) {
					list.add(str);
					str = reader.readLine();
				}
				this.graphic = new Graphic(list);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				reader = null;
			}
		}
	}

	/**
	 * 卸载数据
	 */
	public void unloadData() {
		graphic = null;
	}

	/**
	 * 获取与该节点直接连接的节点
	 * 
	 * @param NodeName
	 *            节点名称
	 * @return 与该节点直接连接的节点数组。若数据未加载则返回null。
	 */
	public String[] getNodeConnect(String NodeName) {
		if (graphic != null) {
			ArrayList<String> list = new ArrayList<String>();
			
			// 取出与NodeName直接相连的节点
			for(int i = 0;i < graphic.getNodeNum();i++) {
				if(graphic.getValue()[graphic.getNodeIndex(NodeName)][i] != 0) {
					list.add(graphic.getNodeName(i));
				}
			}
			
			if(list.size() == 0) {
				return null;
			}else {
				return list.toArray(new String[list.size()]);
			}
			
		}
		return null;
	}

	/**
	 * 获取从一个节点到另一个节点边的权值
	 * 
	 * @param nodeFrom
	 *            边开始的节点
	 * @param nodeTo
	 *            边结束的节点
	 * @return 该边的权值。如果数据未加载则返回-1。
	 */
	public int getNodeValue(String nodeFrom, String nodeTo) {
		if (graphic != null) {
			return graphic.getValue()[graphic.getNodeIndex(nodeFrom)][graphic
					.getNodeIndex(nodeTo)];
		}
		return -1;
	}

	/**
	 * 判断数据是否加载
	 * 
	 * @return 如果已经加载返回true否则返回false
	 */
	public boolean dataHasLoad() {
		if (graphic != null) {
			return true;
		}
		return false;
	}

	/**
	 * 获取节点列表
	 * 
	 * @return 返回所有节点数组。若数据未加载则返回null。
	 */
	public String[] getNodeList() {
		if (graphic != null) {
			return graphic.getNodeList();
		}
		return null;
	}

	/**
	 * 获取标题列表
	 * 
	 * @return 返回标题数组。若数据未加载则返回null。
	 */
	public String[] getNodeTitle() {
		if (graphic != null) {
			return graphic.getTitle();
		}
		return null;
	}

}
