/**
 * ����qq�����������ڼ����ȴ�ĳ��qq�ͻ���������
 */
package com.qq.server.model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import com.qq.common.*;
public class QqServer implements  Runnable{


private boolean start=true;
	

public void setStart(boolean start) {
	this.start = start;
}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public QqServer()
	{}
	@Override
	public void run() {

		try {
			//��999�Ŷ˿ڼ���
			System.out.println("���Ƿ�������999��������");
			ServerSocket ss=new ServerSocket (9999);
			
			while(start)
			{
				//����,�ȴ��ͻ��˵�����
				Socket s=ss.accept();
				
				//���տͻ��˷�������Ϣ
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				
				System.out.println("���������յ�id: "+u.getUserId()+"�����룺"+u.getPasswd());
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123"))
				{
					//����һ���ɹ��ĵ�¼��Ϣ	
					m.setMesType("1");
					oos.writeObject(m);
					//���ﵥ��һ���߳��ø��̣߳���ͻ��˱���ͨѶ
					SerToClientThread scct = new SerToClientThread(s);
					ManagerSTCThread.addClientThread(u.getUserId(), scct);
					//������ÿͻ���ͨѶ���߳�
					scct.start();
					
					//��֪ͨ�����û�
					scct.notifyOther(u.getUserId());
					
					
				}else{
					m.setMesType("2");
					oos.writeObject(m);
					//�ر�����
					s.close();
				}
			
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{		
			
		}
	
		
	}
}
