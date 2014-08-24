package com.changhong.bd.spider.core.domain;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
 * @author QiYao
 * @date 2014年8月24日
 * @mail yao.qi@changhong.com
 * @description 将要访问的URL队列
 */
public class TodoQueue implements Serializable{

	private static final long serialVersionUID = 7591091871747307629L;

	private LinkedList<String> queue = new LinkedList<String>();
	
	public void enQueue(String t){
		this.queue.addLast(t);
	}
	
	public String deQueue(){
		return this.queue.removeFirst();
	}
	
	public boolean isEmpty(){
		return this.queue.isEmpty();
	}
	
	public boolean contains(Object t){
		return this.queue.contains(t);
	}
}
