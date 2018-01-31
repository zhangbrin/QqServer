/**
 * ���ܣ���������ĳ���ͻ��˵�ͨѶ�߳�
 * 
 */
package com.qq.server.model;

import java.util.*;
import java.net.*;
import java.io.*;
import com.qq.common.*;

public class SerToClientThread extends Thread {

	Socket s;
	private boolean stop = false;

	public void setThreadStop(boolean b) {
		this.stop = b;
	}

	public SerToClientThread(Socket s) {
		// �ѷ������͸ÿͻ��˵����Ӹ���s
		this.s = s;
	}

	// �ø��߳�ȥ֪ͨ�����û�
	public void notifyOther(String iam) {
		// �õ����е��˵��߳�
		HashMap hm = ManagerSTCThread.hm;
		Iterator it = hm.keySet().iterator();

		while (it.hasNext()) {
			Message m = new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			// ȡ�������˵�id
			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManagerSTCThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}

	public void run() {

		while (!stop) {
			// ������Խ��տͻ��˵���Ϣ
			try {

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();

				// System.out.println(m.getSender()+"��"+m.getGetter()+"˵"+m.getCon());
				// �Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// һ�����ת������
					// ȡ�ý����˵�ͨѶ�߳�
					SerToClientThread sc = ManagerSTCThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMesType().equals(MessageType.message_get_onLineFriend)) {
					System.out.println(m.getSender() + "Ҫ���ĺ�����Ϣ");
					// �Ѹ÷������ĺ��ѷ��ظ��ͻ���
					String res = ManagerSTCThread.getAllOnLineUserid();
					Message m2 = new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}else if (m.getMesType().equals(MessageType.message_login_fail)) {
					//������յ��ͻ��˷���type=��message_login_fail����message���ر��߳�
					this.setThreadStop(true);
					s.close();
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}
}
