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
	
		//������ɫ��
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(Float.parseFloat("2.0f")));
		g.setColor(Color.black);
		// ����x�ἰ���ͷ
		g.drawLine(50,570,900,570);
		g.drawLine(900,570,890,580);
		g.drawLine(900,570,890,560);

		// ����y�ἰ���ͷ
		g.drawLine(50,570,50,50);
		g.drawLine(50,50,60,60);
		g.drawLine(50,50,40,60);


		// ����y���ϵ�����
		for (int i=0,h=250,j=1;i<2;i++,h=h+250,j++) {
			g.drawLine(50,570-h,60,570-h);
			g.drawString(0.25*j+"",25,570-h);
		}

		// ����ԭʼ���ݵ��ź�	
		for(int i=0,j=50;i<25;i++)//160000���ݣ���20000�飬ȡ25�飬��40����ȡ��һ��
			for(int k=0;k<8;k++,j=j+4)
			{
				g.drawLine(j,570-Screen.signal1[k+i*200]/10,j+4,570-Screen.signal1[k+i*200+1]/10);
					
				if(Screen.signal1[k+i*200]>1000)//������������ԭʼ����ѡ����������ϵ��.����U0λ0.8.���ﱾ��������С��0.8��
				{
					Screen.signal2[Screen.h]=300-(int)((-Math.log((float)Screen.signal1[k+i*200]/8000)/25)*500);
					Screen.h++;
				}
			}
		
	}	
	
	


}


	