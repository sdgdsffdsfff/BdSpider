/**
 * 
 */
package com.changhong.bd.spider.core.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * @author QiYao
 * @date 2014年8月24日
 * @mail yao.qi@changhong.com
 * @description URL地址集合队列
 */
public class LinkQueue implements Serializable{

	private static final long serialVersionUID = 7451770541766173105L;

	//已访问的url集合
	private static Set<String> visitedUrl = new HashSet<String>();
	//待访问的url集合
	private static TodoQueue unVisitedUrl = new TodoQueue();
	
	//获得url队列
	public static TodoQueue getUnvisitedUrl(){
		return unVisitedUrl;
	}
	
	//添加到访问过的url队列中
	public static void addVisitedUrl(String url){
		visitedUrl.add(url);
	}
	
	//移除访问过的url
	public static void removeVisitedUrl(String url){
		visitedUrl.remove(url);
	}
	
	//未访问的url出队列
	public static String unVisitedUrlDeQueue(){
		return unVisitedUrl.deQueue();
	}
	
	//保证每个url只访问一次
	public static void addUnvisitedUrl(String url){
		if(StringUtils.isNotEmpty(url)
				&& !visitedUrl.contains(url)
				&& !unVisitedUrl.contains(url)){
			unVisitedUrl.enQueue(url);
		}
	}
	
	//获取访问过的url的数目
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	
	//判断未访问的url队列是否为空
	public static boolean unVisitedUrlEmpty(){
		return unVisitedUrl.isEmpty();
	}
}
