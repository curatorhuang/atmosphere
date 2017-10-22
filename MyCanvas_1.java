import java.awt.*;

import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyCanvas_1 extends JPanel {

	SimpleDateFormat nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public MyCanvas_1() {
		
		this.setBackground(Color.white);
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(2.0f));
		g.setFont(new Font("",Font.BOLD, 15));

		// 设置x轴及其箭头
		g.drawLine(50+10,300,550+10,300);
		g.drawLine(550+10,300,550+10,50-30);
		g.drawLine(550+10,50-30,50+10,50-30);	 
		
		// 设置y轴及其箭头
		g.drawLine(50+10,300,50+10,50-30);
		
		
		g.drawString("时间",275,340);
		g.drawString("能见度(千米)",20,15);
		
		
	
		
		//g.drawString("2017-07-28 22:12:11",230,340);
		//设置y轴上的坐标
		for (int i=0,h=25,j=5;i<10;i++,h=h+25,j=j+5) {
			BigDecimal b2 = new BigDecimal((i+1)*1);
			g.drawString(b2.floatValue()+"",10,300-h);							
		}
		
		g.drawString("0",10,300);
		
		if(Screen.firstCycle>0)
		{
			g.drawString(nowtime.format(new Date()),230,320);
		//设置消光系数的信号
		for (int j=50,i=1,k=1; i < Screen.hh; i++,j=j+10) {
			try{
				RandomAccessFile raf= new RandomAccessFile("t1.txt","rw");
			g.drawLine(j+10, 300-(int)(Screen.signal22.get(i-1)/0.04),j+10+10,300-(int)(Screen.signal22.get(i)/0.04));
			System.out.println((int)(Screen.signal22.get(i-1)/0.04));
			raf.seek(raf.length());
			String ss=(double)Screen.signal2[i]/1000000+" ";
			raf.write(ss.getBytes());
			}
			catch(Exception e1)
			{
				System.out.println("error of canvas1");
			}
			
		}
		}
		

		Stroke dash = new BasicStroke(1f, BasicStroke.CAP_BUTT,
              BasicStroke.JOIN_MITER, 3.5f, new float[] { 15, 10, },
              0f);
		  g2.setStroke(dash);
		  for (int i=0,h=25;i<10;i++,h=h+25) {
				g.drawLine(50+10,300-h,550+10,300-h);					
			}
		
		
	}


}


	