package com91.sdk.weblog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;


public class WebHelper {
	public static boolean httpDownload(String httpUrl,String saveFile,Proxy proxy){
		System.out.println("downloading "+httpUrl+".....");
	    // ���������ļ�
	    int byteread = 0;

	    URL url = null;
		try {
			url = new URL(httpUrl);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

	    try {

	        URLConnection conn = url.openConnection(proxy);
	        InputStream inStream = conn.getInputStream();
	        FileOutputStream fs = new FileOutputStream(saveFile);
	        OutputStreamWriter out = new OutputStreamWriter(fs, "UTF-8");
	        
	        InputStreamReader in = new InputStreamReader(inStream, "GBK");
	        
	        char[] buffer = new char[1024];
	        while ((byteread = in.read(buffer)) != -1) {
	        	out.write(buffer, 0, byteread);
	        }
	        out.close();
       
	        System.out.println("download success......");
	        return true;
	    } catch (FileNotFoundException e) {
	    	System.out.println("download failed......");
	        e.printStackTrace();
	        return false;
	    } catch (IOException e) {
	    	System.out.println("download failed......");
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static String httpgetstring(String httpUrl,Proxy proxy){
	    // ���������ļ�
	    URL url = null;
		try {
			url = new URL(httpUrl);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

	    try {
	    	URLConnection conn = url.openConnection(proxy);
	    	String result="";
	    	BufferedReader in = new BufferedReader(new InputStreamReader(
	    			conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
	        return result;
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
