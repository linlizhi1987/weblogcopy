package com91.sdk.weblog;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;

public class downloadlog {
	public static void main(String[] args) throws Exception {
		
		if(args.length<6)
		{
			System.out.println("Notice your params:1.sourcehttpurldir  2.targetdir 3.httpusername 4.httppwd 5.host ip 6.filter ");
			System.exit(1);
			
		}
		
	    String sourceurldir=args[0]+"/";
	    String targetdir=args[1];
	    String httpusername=args[2];
	    String httppwd=args[3];
	    String filter=args[5];
		String hostip=args[4];
		Authenticator.setDefault(new BasicAuthenticator(httpusername, httppwd)); 	
        
        String str[] = hostip.split("\\.");  
        byte[] b =new byte[str.length];  
        for(int i=0,len=str.length;i<len;i++){  
            b[i] = (byte)(Integer.parseInt(str[i],10));  
        } 
        
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(InetAddress.getByAddress(b), 80)); 
        
    	String contentString=WebHelper.httpgetstring(sourceurldir,proxy);
    	
    	ArrayList<String> files=RegexHelper.Get(contentString, "HREF=\"", "\"");
    	for (String file : files) {
    		String tmpString=file.substring(file.lastIndexOf("/"));
    		if(tmpString.length()>1)
    		{   			
    			if(filter=="")
    				WebHelper.httpDownload(sourceurldir+tmpString, targetdir+tmpString,proxy);
    			else if(tmpString.startsWith("/"+filter))
    				WebHelper.httpDownload(sourceurldir+tmpString, targetdir+tmpString,proxy);
    		}
		}
    	

	    System.exit(0);
	}
	
	
}

