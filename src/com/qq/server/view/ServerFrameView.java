/**
 * ���Ƿ��������ƽ���ģ������������������رշ����������Թ���ͼ���û�������ǿ���û�����
 * Ŀǰ���server����ĺ�̨ҳ��Ƚϴֲ�
 * ��һ�������͹رհ�ť���رհ�ť��û������
 * ������new��һ��MyqqServer���࣬����໹��һ���ڲ�����ѭ�����࣬���԰�ť�����ȥһֱ�����أ��������frame���޷��رգ��Ƚϴ��bug----------��Ҫ�����޸���
 */
package com.qq.server.view;
import javax.swing.*;
import com.qq.server.model.QqServer;
import java.awt.*;
import java.awt.event.*;
public class ServerFrameView extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1, jb2;
	QqServer qq = null;
	Thread t =null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerFrameView ms=new ServerFrameView();
	}
	
	public ServerFrameView()
	{
		jp1=new JPanel();
		jb1=new JButton("����������");
		
		jb1.addActionListener(this);
		jb2=new JButton("�رշ�����");
		
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1)
		{ 
			System.out.println("The Server has been started");
			if(qq == null){
			qq=new QqServer();
		    t= new Thread(qq);
		    t.start();}else{ 
		    	qq.setStart(true);
		    
		    }
			
		}else if (arg0.getSource()==jb2){
			if (qq != null){qq.setStart(false);}
		}
	}
}
