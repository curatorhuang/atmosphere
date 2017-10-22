import java.awt.*;

import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class MyCanvas_3 extends JPanel {
	int abc = 0;
	int timeSum=30,z=0,zz=0;
	SimpleDateFormat nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 数组赋值
	public MyCanvas_3() {

		this.setBackground(Color.white);
	}

	public void paint(Graphics g)// 一秒取20数据，即1000组求平均取一个数，每组中取一个峰值，平均
	{

		Screen.tmp++;
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(2.0f));
		g.setFont(new Font("",Font.BOLD, 15));

		// 设置x轴及其箭头
		g.drawLine(50+10, 350, 550+10, 350);
		g.drawLine( 550+10, 350,550+10,30);
		g.drawLine( 550+10,30,50+10, 30);
		
		g.drawString("时间",275,390);	
		g.drawString("大气折射率结构常数",8,18);
	
		// 设置y轴及其箭头
		g.drawLine(50+10, 350, 50+10, 30);
	

	
		for (int i = 0, h = 0, j = 15; i < 3; i++, h = h + 150, j--) {
		BigDecimal b2 = new BigDecimal(Math.pow(10, -j));// 直接用3的话会有精度问题，所以先转化为Bigdecimal对象
			g.drawString(b2.floatValue() + "", 5, 350 - h);
		}

		if(Screen.firstCycle>0)
		{
		g.drawString(nowtime.format(new Date()),230,370);
		// 求结构常数
		MyCanvas_3 mc_3 = new MyCanvas_3();
		double k1 = ((double) (2 * Math.PI) / (620 * Math.pow(10, -9))),
				d = 0.496 * Math.pow(k1, (float) 7 / 6) * Math.pow(25, (float) 11 / 6),
				e = 1 + 0.214 * Math.pow((k1 * 0.008 * 0.008 / 100), (float) 7 / 6),
				f = 0.44 * Math.pow(Math.E, (float) -25 / 1079.23) + 0.5;

		double h = (double) (d * f) / e;
		
			int jh=50;
			int[] temSignal4 = new int[20];			
			   int[] temSignal3 = new int[20];	
			for (int i = 0; i < 20; i++, Screen.h2 = Screen.h2 + 4000) {
				double a, c;
				a = mc_3.average(Screen.signal1, Screen.h2);
				c = mc_3.average2(Screen.signal1, Screen.h2);
				try{
					RandomAccessFile raf= new RandomAccessFile("t3.txt","rw");
				Screen.signal3[i] = (c / (a * a) - 1) / h;
				raf.seek(raf.length());
				String ss=Screen.signal3[i]+" ";
				raf.write(ss.getBytes());
				}
				catch(Exception e3)
				{
					System.out.println("error of canvas3");
				}
				double hh = Screen.signal3[i] * 1000000000000000l;
				int hh2 = (int) Math.log10(hh);
				temSignal3[i] = hh2;
				
			}
			for (int i = 0,j=0; i < 20; i++,j++) {
				switch (temSignal3[i]) {
				case 0:
					temSignal4[j] = 10 - (int) (Screen.signal3[i] * 100000 * 100000 * 10000000) ;
					break;
				case 1:
					temSignal4[j] = 10 - (int) (Screen.signal3[i] * 100000 * 100000 * 1000000) ;
					break;
				case 2:
					temSignal4[j] =(int) (Screen.signal3[i] * 100000 * 100000 * 100000);				
					break;
				case 3:
					temSignal4[j] = 10 - (int) (Screen.signal3[i] * 100000 * 100000 * 10000) ;
					break;
				case 4:
					temSignal4[j] = 10 - (int) (Screen.signal3[i] * 100000 * 100000 * 1000);
					break;
				default:
					System.out.println("no");
				}
			}
			for (int i = 0; i < 19; i++, jh = jh + 25) {
				
				g.drawLine(jh+10, 330-temSignal4[i], jh + 25+10,330-temSignal4[i + 1]);
				

			}
			Screen.h2=0;
			Screen.h=0;
		}
		
		Stroke dash = new BasicStroke(1f, BasicStroke.CAP_BUTT,
	              BasicStroke.JOIN_MITER, 3.5f, new float[] { 15, 10, },
	              0f);
			  g2.setStroke(dash);
				g.drawLine(50+10, 350 - 150, 550+10, 350 - 150);
	
		
			
			

		}
	
	String timeShow()
	{
		String[] s ={"11","00","01","02","03","04","05","06","07","08","09"};
		String a=s[z]+":"+timeSum;
		if(zz>2||zz==2)
		{
		timeSum++;
		zz=0;
		}
		if(timeSum==60)
		{
			timeSum=0;
			z++;
			zz=0;
		}
		zz=zz+new Random().nextInt(2)+1;
		return a;
	}
	


	// 求一组12个数中的最大数,从j位开始
	int max(int[] x, int j) {
		int max1 = j, max2 = j + 1;
		for (int i = 1; i < 8; i++) {
			if (x[j + i] > x[max1]) {
				max2 = max1;
				max1 = j + i;
			} else if (x[j + i] > x[max2] && x[j + i] < x[max1])
				max2 = j + i;
		}
		return max2;
	}

	double average(int[] x, int y) {
		double sum = 0, ave, zz = 0;
		int hh = 0, hhh = 1;
		int h = 0;
		for (int k = 0; k < 500; k++) {
			if (hhh == 1) {
				h = max(Screen.signal1, y);
			} else {
				h = waveHigh(Screen.signal1, hh);
			}
			sum = sum + (double) x[h] / 1000000;
			hh = h;
			hhh++;
		}
		ave = sum / 500;
		return ave;
	}

	double average2(int[] x, int y) {
		double sum = 0, ave, zz = 0;
		int hh = 0, hhh = 1;
		int h = 0;
		for (int k = 0; k < 500; k++) {
			if (hhh == 1) {
				h = max(Screen.signal1, y);
			} else {
				h = waveHigh(Screen.signal1, hh);
			}

			sum = sum + Math.pow(((double) x[h] / 1000000), 2);

			hh = h;
			hhh++;
		}
		ave = sum / 500;
		return ave;
	}


	int waveLow(int[] x, int y) {
		while (x[y] > 80000) {
			y++;
		}
		return y;
	}

	int waveHigh(int[] x, int y) {
		int a = waveLow(x, y);
		int b = max(Screen.signal1, a);
		return b;
	}
	

}

