import java.awt.*;

import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyCanvas_2 extends JPanel {


	SimpleDateFormat nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	public MyCanvas_2() {
		this.setBackground(Color.white);	
	}

	public void paint(Graphics g) {
		super.paint(g);
		int dupWidth =1000;
		int dupHeight = 900;
		//线条颜色等
	    Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(2.0f));
		g.setFont(new Font("",Font.BOLD, 15));

		g.setColor(Color.black);
		// 设置x轴及其箭头
		g.drawLine(50+10,570,900+10,570);
		g.drawLine(900+10,570,900+10,50);
		g.drawLine(900+10,50,50+10,50);

		// 设置y轴及其箭头
		g.drawLine(50+10,570,50+10,50);
	
		
		g.drawString("电压(伏)",25,30);

		

		// 设置y轴上的坐标
		for (int i=0,h=250,j=1;i<2;i++,h=h+250,j++) {
			g.drawString(0.25*j+"",25,570-h);
		}
		g.drawString("0",25,570);

		// 设置原始数据的信号	
		if(Screen.firstCycle>0)
		{
			
		for(int i=0,j=50;i<23;i++)//200g个数，25组
		{
			for(int k=0;k<9;k++,j=j+4)
			{
				try{
					RandomAccessFile raf= new RandomAccessFile("t2.txt","rw");
				g.drawLine(j+10,570-Screen.signal1[k+i*3200]/1000,j+4+10,570-Screen.signal1[k+i*3200+1]/1000);
				raf.seek(raf.length());
				String ss=(double)Screen.signal1[k+i*3200]/1000000+" ";
				raf.write(ss.getBytes());
				}
				catch(Exception e2)
				{
					System.out.println("error of canvas2");
				}
		
				if(Screen.signal1[k+i*3200]>100000&&Screen.signal1[k+i*3200]<125354)//将符合条件的原始数据选出，求消光系数.142478 125354 122916
				{
					//double a=-Math.log((float)Screen.signal1[k+i*3200]/800000)/0.025;//此处需要商榷	
				//	double a=0.025*Math.log(0.05)/Math.log((float)Screen.signal1[k+i*3200]/800000);
					//float a=-(float)Math.log((float)Screen.signal1[k+i*3200]/1011600*8)/25*1000;
					double a=0.025*Math.log(0.05)/Math.log((float)Screen.signal1[k+i*3200]/1011600*8);
					if(Screen.hh<50)
					{
					//System.out.println(a);
					Screen.signal22.add(a);
					Screen.hh++;
					}
					else
					{
					Screen.signal22.remove(0);
					Screen.signal22.add(a);
					}
					
			}
			}
			if(i<22)
			Screen.signal1[(i+1)*3200]=Screen.signal1[9+i*3200];
		}
		}
		
		Stroke dash = new BasicStroke(1f, BasicStroke.CAP_BUTT,
	              BasicStroke.JOIN_MITER, 3.5f, new float[] { 15, 10, },
	              0f);
			  g2.setStroke(dash);
			  g.drawLine(50+10,570-250,900+10,570-250);
		
		
	}	
}


	