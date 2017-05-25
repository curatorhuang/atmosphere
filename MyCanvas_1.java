import java.awt.*;

import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.*;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import org.jfree.chart.axis.CategoryAxis;
//import org.jfree.chart.plot.CategoryPlot;

public class MyCanvas_1 extends JPanel {

	public MyCanvas_1() {
		
		this.setBackground(Color.white);
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		// 设置x轴及其箭头
		g.drawLine(50,300,550,300);
		g.drawLine(550,300,540,310);
		g.drawLine(550,300,540,290);

		// 设置y轴及其箭头
		g.drawLine(50,300,50,50-30);
		g.drawLine(50,50-30,40,60-30);
		g.drawLine(50,50-30,60,60-30);
		
		//设置y轴上的坐标
		for (int i=0,h=50;i<5;i++,h=h+50) {
			BigDecimal b2 = new BigDecimal((i+1)*0.1);
			g.drawLine(50,300-h,60,300-h);
			g.drawString(b2.floatValue()+"",10,300-h);
			
		}
		
		//设置消光系数的信号
		for (int j = 50,i=0,k=1; i < 100; i++,j=j+5) {
			g.drawLine(j, Screen.signal2[i],j+5,Screen.signal2[i+1]);	
		}
	}


}


	