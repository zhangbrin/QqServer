/**
 * 这是服务器控制界面的，可以启动服务器，关闭服务器，可以管理和监控用户，可以强制用户下线
 * 目前这个server管理的后台页面比较粗糙
 * 仅一个启动和关闭按钮，关闭按钮还没有作用
 * 启动后new了一个MyqqServer的类，这个类还是一个内部无限循环的类，所以按钮点击下去一直不弹回，导致这个frame都无法关闭，比较大的bug----------需要优先修复下
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
		jb1=new JButton("启动服务器");
		
		jb1.addActionListener(this);
		jb2=new JButton("关闭服务器");
		
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
