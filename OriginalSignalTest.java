import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class OriginalSignalTest {

	int[] originalSignal = new int[80000];
	public int[] originalSignalInput() {
		try {
			int h;
			File f = new File("F:/新建文件夹/"+Screen.a+".dat");
			RandomAccessFile fis = new RandomAccessFile(f, "r");
			byte[] bbuf = new byte[160000];
			fis.seek(Screen.pis);	//每次都把指针移动上上次刷新的结尾处
			h = fis.read(bbuf);
			Screen.pis = Screen.pis + h;
			for (int i = 0, j = 0,k=0; h > 1 && i < h -1; i = i + 2, j++) {
				originalSignal[j] = new OriginalSignalTest().getInt(bbuf[i + 1], bbuf[i]);	
				if(originalSignal[j]<0)
					originalSignal[j]=0;
	//			System.out.println((float)originalSignal[j]/1000000+"==========="+j);
			}


		} catch (Exception e) {
			System.out.println("wrong" + e.toString());
			System.exit(-1);
		}

		return originalSignal;
	}
	

	//将存档里的数据化为int型
	int getInt(byte buf1, byte buf2) {

		int r = 0;
		int w = -32768;
		int a, j;
		float h;
		r |= (buf1 & 0x00ff);
		r <<= 8;
		r |= (buf2 & 0x00ff);
		a = r & 0x8000;
		r &= (0x7fff & 0xffff);
		if (a == 32768)
			r = -r;
		h = (float) r / w;
		
		j = (int) (h * 1000000);
		return j;
	}

}
