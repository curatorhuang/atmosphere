import java.awt.*;

import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyCanvas_2 extends JPanel {


	SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");

	public MyCanvas_2() {
		this.setBackground(Color.white);	
	}

	public void paint(Graphics g) {
		super.paint(g);
		int dupWidth =1000;
		int dupHeight = 900;
	
		//线条颜色等
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(Float.parseFloat("2.0f")));
		g.setColor(Color.black);
		// 设置x轴及其箭头
		g.drawLine(50,570,900,570);
		g.drawLine(900,570,890,580);
		g.drawLine(900,570,890,560);

		// 设置y轴及其箭头
		g.drawLine(50,570,50,50);
		g.drawLine(50,50,60,60);
		g.drawLine(50,50,40,60);


		// 设置y轴上的坐标
		for (int i=0,h=250,j=1;i<2;i++,h=h+250,j++) {
			g.drawLine(50,570-h,60,570-h);
			g.drawString(0.25*j+"",25,570-h);
		}

		// 设置原始数据的信号	
		for(int i=0,j=50;i<25;i++)//160000数据，共20000组，取25组，即40组中取第一组
			for(int k=0;k<8;k++,j=j+4)
			{
				g.drawLine(j,570-Screen.signal1[k+i*200]/10,j+4,570-Screen.signal1[k+i*200+1]/10);
					
				if(Screen.signal1[k+i*200]>1000)//将符合条件的原始数据选出，求消光系数.其中U0位0.8.这里本该在与上小于0.8的
				{
					Screen.signal2[Screen.h]=300-(int)((-Math.log((float)Screen.signal1[k+i*200]/8000)/25)*500);
					Screen.h++;
				}
			}
		
	}	
	
	


}


	