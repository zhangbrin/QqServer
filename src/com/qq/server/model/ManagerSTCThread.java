
package com.qq.server.model;
import java.util.*;
public class ManagerSTCThread  {

	public static HashMap<String, SerToClientThread> hm=new HashMap<String, SerToClientThread>();
	
	public static void addClientThread(String uid, SerToClientThread ct)
	//��hm�����һ���ͻ���ͨѶ�߳�
	{
		hm.put(uid, ct);
	}
	public static SerToClientThread getClientThread(String uid)
	{
		return (SerToClientThread)hm.get(uid);
	}
	
	//���ص�ǰ�����˵����
	public static String getAllOnLineUserid()
	{
		//ʹ�õ��������
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
