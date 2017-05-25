import java.awt.*;

import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import org.jfree.chart.axis.CategoryAxis;
//import org.jfree.chart.plot.CategoryPlot;

public class MyCanvas_3 extends JPanel {


	// 数组赋值
	public MyCanvas_3() {
		// this.setPreferredSize(new Dimension(360,360));
		this.setBackground(Color.white);
	}

	public void paint(Graphics g)// 一秒取20数据，即1000组求平均取一个数，每组中取一个峰值，平均
	{

		super.paint(g);

		// 设置x轴及其箭头
		g.drawLine(50, 300, 550, 300);
		g.drawLine(550, 300, 540, 310);
		g.drawLine(550, 300, 540, 290);

		// 设置y轴及其箭头
		g.drawLine(50, 300, 50, 50);
		g.drawLine(50, 50, 40, 60);
		g.drawLine(50, 50, 60, 60);
		
		//设置y轴上的坐标
		
		for (int i=0,h=0,j=1;i<3;i++,h=h+100,j++) {
			BigDecimal b2 = new BigDecimal(j*Math.pow(10,-8));//直接用3的话会有精度问题，所以先转化为Bigdecimal对象
			g.drawLine(50,300-h,60,300-h);
			g.drawString(b2.floatValue()+"",10,300-h);		
		}
		
		//求结构常数
		MyCanvas_3 mc_3 = new MyCanvas_3();
		int b= mc_3.max(Screen.signal1, 12);
		double k1 =(2*Math.PI/(620*Math.pow(10, -9))),
				d=0.496*Math.pow(k1, (float)7/6)*Math.pow(25, (float)11/6),
				e=1+0.214*Math.pow((k1*0.008*0.008/100),(float)7/6),
				f=0.44*Math.pow(Math.E,(float)-25/1079.23)+0.5;
		double h=d*f/e;
		for(int i=0,j=0;i<5;i++,j=j+7999)
		{
			double a,c;	
			a = mc_3.average(Screen.signal1, b+j);
			c=  mc_3.average2(Screen.signal1, b+j);
			double hh=(1-a*a/c)/h*100000000*1000000;		
			Screen.signal3[i]=(int)hh-3000;
			//System.out.println(Screen.signal3[i]);			
		}

		// 设置湍流的信号
		for (int j = 50, i = 0; i < 4; i++, j = j + 100) {
			g.drawLine(j, 700-Screen.signal3[i], j + 100, 700-Screen.signal3[i + 1]);
		}

	}

	// 求一组数中的最大数
	int max(int[] x, int j) {
		int a = 0;
		for (int i = 0; i < j; i++) {
			if (x[a] < x[i])
				a = i;
		}

		return a;
	}

	// 求平均值
	double average(int[] x, int j) {
		double sum = 0, ave;
		for (int i = 0; i < 1000; i++, j = j + 8) {
			sum = sum + (double)x[j]/10000;
		}
		ave = sum / 1000;
		return ave;
	}
	
	double average2(int[] x, int j) {
		double sum = 0, ave;
		for (int i = 0; i < 1000; i++, j = j + 8) {
			sum = sum + Math.pow(((double)x[j]/10000),2);
		}
		ave = sum / 1000;
		return ave;
	}

}
