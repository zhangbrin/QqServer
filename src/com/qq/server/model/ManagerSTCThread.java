
package com.qq.server.model;
import java.util.*;
public class ManagerSTCThread  {

	public static HashMap<String, SerToClientThread> hm=new HashMap<String, SerToClientThread>();
	
	public static void addClientThread(String uid, SerToClientThread ct)
	//向hm中添加一个客户端通讯线程
	{
		hm.put(uid, ct);
	}
	public static SerToClientThread getClientThread(String uid)
	{
		return (SerToClientThread)hm.get(uid);
	}
	
	//返回当前在线人的情况
	public static String getAllOnLineUserid()
	{
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
