package com.example.testgetpost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class GetPostUtil {

	public static String sendGet(String url, String parmas) {
		String result = "";
		BufferedReader bufferedReader = null;
		String urlName = url + "?" + parmas;
		try {
			URL realUrl = new URL(urlName);
			try {
				URLConnection urlConnection = realUrl.openConnection();
				urlConnection.setRequestProperty("accept", "*/*");
				urlConnection.setRequestProperty("connection", "Keep-Alive");
				urlConnection
						.setRequestProperty("user-agent",
								"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.8.1.14)");
				urlConnection.connect();
				Log.i("sendGet","contentType " + urlConnection.getContentType());
				Log.i("sendGet","contentLength " + urlConnection.getContentLength());
				Log.i("sendGet","contentEncoding " + urlConnection.getContentEncoding());
				Log.i("sendGet", "contentDate " + urlConnection.getDate());

				Map<String, List<String>> map = urlConnection.getHeaderFields();
				
				for (String key:map.keySet()){  
					Log.i("GET��ʽ����", ""+map.get(key));  	                 
				} 
				bufferedReader = new BufferedReader(new InputStreamReader(
						urlConnection.getInputStream()));
				String line;
				for (; (line = bufferedReader.readLine()) != null;) {
					result += "\n" + line;
				} 


				
			} catch (IOException e) {
				Log.e("GET��ʽ����", "����GET�����쳣"+e);  
			    e.printStackTrace();  

			}
		} catch (Exception e) {
			 e.printStackTrace();  
		} finally {
			if (null != bufferedReader) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 
		return result;  

	}
	
	    /** 
	     * ��ָ��URL����POST���������� 
	     * @param url   ���������URL 
	     * @param parmas    ����������������Ӧ����name1=value1&name2=value2����ʽ 
	     * @return URL������Զ����Դ����Ӧ 
	     */  
	    public static String sendPost(String url, String parmas){  
	        String result = "";  
	        PrintWriter printWriter = null;  
	        BufferedReader bufferedReader = null;  
	        try {  
	                URL realUrl = new URL(url);  
	                //�򿪺�URL֮�������  
	                try {  
	                        URLConnection urlConnection = realUrl.openConnection();  
	                        //����ͨ����������  
	                       urlConnection.setRequestProperty("accept", "*/*");  
	                        urlConnection.setRequestProperty("connection", "Keep-Alive");  
	                       urlConnection.setRequestProperty("user-agent", "Mozilla/4.0(compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
	                        //����POST�������������������  
	                        urlConnection.setDoOutput(true);  
	                        urlConnection.setDoInput(true);  
	                       //��ȡ������Ӧͷ�ֶ�  
	                        Map<String, List<String>> map = urlConnection.getHeaderFields();  
	                        //����������Ӧͷ�ֶ�  
	                        for (String key:map.keySet()){  
	                            Log.i("POST��ʽ����", ""+map.get(key));  
	                        }  
	                        //��ȡURLConnection�����Ӧ�������  
	                        printWriter = new PrintWriter(urlConnection.getOutputStream());  
	                       //�����������  
	                        printWriter.print(parmas);  
	                        //flush���������  
	                        printWriter.flush();  
	                        //����BufferReader����������ȡURL����Ӧ  
	                        bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
	                        String line;  
	                        for (;(line = bufferedReader.readLine()) != null;){  
	                            result += "\n" + line;  
	                        }  
	                } catch (IOException e) {  
	                    // TODO Auto-generated catch block  
	                    Log.e("GET��ʽ����", "����GET�����쳣"+e);  
	                    e.printStackTrace();  
	                }  
	        } catch (MalformedURLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } finally {  
	           if (null != bufferedReader){  
	               try {  
	                   bufferedReader.close();  
	                } catch (IOException e) {  
	                   // TODO Auto-generated catch block  
                   e.printStackTrace();  
	                }  
	            }  
	           if (null != printWriter){  
	                printWriter.close();  
            }  
	       }  
	        return result;  
	    }  
	}  


