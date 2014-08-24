/**
 * 
 */
package com.changhong.bd.spider.core.logic;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author QiYao
 * @date 2014年8月24日
 * @mail yao.qi@changhong.com
 * @description 网页下载处理过程
 */
public class DownloadFile {

	/**
	 * 根据URL生成需要保存的html页面的文件名
	 * @param url
	 * @param contentType
	 * @return
	 */
	public String getFileNameByUrl(String url, String contentType){
		url = url.substring(7);  //移除http://
		//text/html类型
		if(contentType.indexOf("html")!=-1){
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		}else{
			return url.replaceAll("", "_") + "." + contentType.substring(contentType.lastIndexOf("/")+1);
		}
	}
	/**
	 * 保存网页到文件
	 * @param data
	 * @param filePath
	 */
	private void saveToLocal(byte[] data, String filePath){
		try{
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
			for(int i=0;i<data.length;i++){
				out.write(data[i]);
			}
			out.flush();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * 下载指定的网页
	 * @param url
	 * @return
	 * @throws IOException 
	 */
	public String downloadFile(String url) throws IOException{
		String filePath = null;
		//
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//设置http连接超时 TODO
		
		HttpGet httpGet = new HttpGet(url);
		//设置GET超时 5S
		//设置请求重试处理
		CloseableHttpResponse resp = httpclient.execute(httpGet);
		try {
		    //System.out.println(resp.getStatusLine());
		    HttpEntity entity = resp.getEntity();
		    byte[] data = EntityUtils.toByteArray(entity);
		    filePath = "temp/" + getFileNameByUrl(url, resp.getFirstHeader("Content-Type").getValue());
		    saveToLocal(data, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    resp.close();
		}
		return filePath;
	}
}
