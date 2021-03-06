import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static java.awt.BorderLayout.*;
import javax.swing.Timer;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class Screen {
	// 画布设置
	JFrame f = new JFrame("南京大学大气能见度与湍流强度测试仪——采集处理与显示系统");
	MyCanvas_1 Canvas_1 = new MyCanvas_1();
	MyCanvas_2 Canvas_2 = new MyCanvas_2();
	MyCanvas_3 Canvas_3 = new MyCanvas_3();

	// 菜单栏
	JMenuBar mb = new JMenuBar();
	private JMenu file = new JMenu("文件");
	private JMenu setting = new JMenu("设置");
	private JMenuItem openitem = new JMenuItem("打开");
	private JMenuItem saveitem = new JMenuItem("保存");
	private JMenuItem shotitem = new JMenuItem("截图");
	private JMenuItem historyitem = new JMenuItem("历史查询");

	// 内部窗口
	private JDesktopPane jdp = new JDesktopPane();
	private JInternalFrame jf = new JInternalFrame("能见度", true, false, true, true);
	private JInternalFrame jf2 = new JInternalFrame("原始信号", true, false, true, true);
	private JInternalFrame jf3 = new JInternalFrame("湍流强度", true, false, true, true);

	// 设置屏幕尺寸
	int width = 1600;
	int height = 900;

	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	// 定时器
	Timer timer;

	static int h = 0,h2=0;
	static int hh=0;// signal2数组位置
	static int pis = 60, pos = 0,tmp=0;// 读取存档与存储存档的指针位置
	static int[] signal1 = new int[80000];// 原始信号数组
	static int[] signal2 = new int[150];// 本为100，多处50是冗余，符合消光系数的数组
	static List<Double> signal22=new ArrayList();
	static double[] signal3 = new double[20];
	static String a;
	
	// 添加文本框
	JPanel jp = new JPanel();
	Button bt = new Button("开始");
	TextField tf = new TextField(40);
	
    static int firstCycle=0;//除去第一次加载canvas时默认值

	public void init() throws IOException {

		// 添加画布
		jf.add(Canvas_1);
		Canvas_1.setPreferredSize(new Dimension(600, 400));
		jf2.add(Canvas_2);
		Canvas_2.setPreferredSize(new Dimension(950, 800));
		jf3.add(Canvas_3);
		Canvas_3.setPreferredSize(new Dimension(600, 400));

		// 设置窗口位置
		jf.setLocation(0, 0);
		jf3.setLocation(0, 400);
		jf2.setLocation(600, 0);
		jdp.add(jf);
		jdp.add(jf2);
		jdp.add(jf3);
		f.add(jdp);

		// 添加菜单栏
		file.add(openitem);
		file.add(saveitem);
		setting.add(shotitem);
		setting.add(historyitem);
		shotitem.setAccelerator(KeyStroke.getKeyStroke('a'));//设置快捷方式
		mb.add(file);
		mb.add(setting);
		f.setJMenuBar(mb);

		// 添加文本框
		jp.add(tf);
		jp.add(bt);
		jf2.add(jp, SOUTH);
		// 250毫秒刷新一次图像
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstCycle=1;
				signal1 = new OriginalSignalTest().originalSignalInput();
		        Canvas_2.repaint();
				Canvas_1.repaint();
				Canvas_3.repaint();
				h = 0;
				h2=0;
			}
		});

		//设置响应，读取文本框里的文件名
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a = tf.getText();
				timer.start();
			}
		});
		
		//截图添加监听
		shotitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dimension d =f.getSize();
				new Camera().snapShot(f.getX(),f.getY(),(int)d.getWidth(),(int)d.getHeight());
			}
		});
		
		//历史查询监听
		FileDialog d1=new FileDialog(f,"选择要打开的文件",FileDialog.LOAD);
		historyitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d1.setVisible(true);			
				a=d1.getFile();
				String c=tf.getText();
				int  sum=(Integer.parseInt(c.substring(0, 2))-Integer.parseInt(a.substring(8, 10)))*3600+(Integer.parseInt(c.substring(3, 5))-Integer.parseInt(a.substring(10, 12)))*60;
				pis=sum*80000*2;	
				a=a.substring(0,a.length()-4);
				timer.start();
				
			}
		});


		// 三块内部屏的显示
		jf.setVisible(true);
		jf.pack();
		jf2.setVisible(true);
		jf2.pack();
		jf3.setVisible(true);
		jf3.pack();

		f.setSize(width, height);
		f.setLocationRelativeTo(null);// 将窗口置于屏幕的中央
		f.setVisible(true);
	}

	public static void main(String[] args) throws IOException {

		new Screen().init();

	}

}
