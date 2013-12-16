package com.cky.service;

import sun.applet.Main;

import com.cky.model.Graphic;

/**
 * 文件名：MainService.java
 * 作   者： 陈恺垣 E-mail:chenkaiyuan1993@gmail.com
 * 创建时间：2013年12月14日 下午4:23:14
 * 类说明 ：实现MainService接口
 */
public class MainService {
	Graphic graphic;

	public MainService(Graphic graphic) {
		this.graphic = graphic;
	}

	public String[] getPathByDijkstea(String A, String B) {
		return new Dijkstea(graphic).getPath(A, B);
	}

	public String[] getPathByFloyd(String A, String B) {
		return new Dijkstea(graphic).getPath(A, B);
	}
}
