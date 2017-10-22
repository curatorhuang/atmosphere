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
	// ��������
	JFrame f = new JFrame("�Ͼ���ѧ�����ܼ���������ǿ�Ȳ����ǡ����ɼ���������ʾϵͳ");
	MyCanvas_1 Canvas_1 = new MyCanvas_1();
	MyCanvas_2 Canvas_2 = new MyCanvas_2();
	MyCanvas_3 Canvas_3 = new MyCanvas_3();

	// �˵���
	JMenuBar mb = new JMenuBar();
	private JMenu file = new JMenu("�ļ�");
	private JMenu setting = new JMenu("����");
	private JMenuItem openitem = new JMenuItem("��");
	private JMenuItem saveitem = new JMenuItem("����");
	private JMenuItem shotitem = new JMenuItem("��ͼ");
	private JMenuItem historyitem = new JMenuItem("��ʷ��ѯ");

	// �ڲ�����
	private JDesktopPane jdp = new JDesktopPane();
	private JInternalFrame jf = new JInternalFrame("�ܼ���", true, false, true, true);
	private JInternalFrame jf2 = new JInternalFrame("ԭʼ�ź�", true, false, true, true);
	private JInternalFrame jf3 = new JInternalFrame("����ǿ��", true, false, true, true);

	// ������Ļ�ߴ�
	int width = 1600;
	int height = 900;

	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	// ��ʱ��
	Timer timer;

	static int h = 0,h2=0;
	static int hh=0;// signal2����λ��
	static int pis = 60, pos = 0,tmp=0;// ��ȡ�浵��洢�浵��ָ��λ��
	static int[] signal1 = new int[80000];// ԭʼ�ź�����
	static int[] signal2 = new int[150];// ��Ϊ100���ദ50�����࣬��������ϵ��������
	static List<Double> signal22=new ArrayList();
	static double[] signal3 = new double[20];
	static String a;
	
	// ����ı���
	JPanel jp = new JPanel();
	Button bt = new Button("��ʼ");
	TextField tf = new TextField(40);
	
    static int firstCycle=0;//��ȥ��һ�μ���canvasʱĬ��ֵ

	public void init() throws IOException {

		// ��ӻ���
		jf.add(Canvas_1);
		Canvas_1.setPreferredSize(new Dimension(600, 400));
		jf2.add(Canvas_2);
		Canvas_2.setPreferredSize(new Dimension(950, 800));
		jf3.add(Canvas_3);
		Canvas_3.setPreferredSize(new Dimension(600, 400));

		// ���ô���λ��
		jf.setLocation(0, 0);
		jf3.setLocation(0, 400);
		jf2.setLocation(600, 0);
		jdp.add(jf);
		jdp.add(jf2);
		jdp.add(jf3);
		f.add(jdp);

		// ��Ӳ˵���
		file.add(openitem);
		file.add(saveitem);
		setting.add(shotitem);
		setting.add(historyitem);
		shotitem.setAccelerator(KeyStroke.getKeyStroke('a'));//���ÿ�ݷ�ʽ
		mb.add(file);
		mb.add(setting);
		f.setJMenuBar(mb);

		// ����ı���
		jp.add(tf);
		jp.add(bt);
		jf2.add(jp, SOUTH);
		// 250����ˢ��һ��ͼ��
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

		//������Ӧ����ȡ�ı�������ļ���
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a = tf.getText();
				timer.start();
			}
		});
		
		//��ͼ��Ӽ���
		shotitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dimension d =f.getSize();
				new Camera().snapShot(f.getX(),f.getY(),(int)d.getWidth(),(int)d.getHeight());
			}
		});
		
		//��ʷ��ѯ����
		FileDialog d1=new FileDialog(f,"ѡ��Ҫ�򿪵��ļ�",FileDialog.LOAD);
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


		// �����ڲ�������ʾ
		jf.setVisible(true);
		jf.pack();
		jf2.setVisible(true);
		jf2.pack();
		jf3.setVisible(true);
		jf3.pack();

		f.setSize(width, height);
		f.setLocationRelativeTo(null);// ������������Ļ������
		f.setVisible(true);
	}

	public static void main(String[] args) throws IOException {

		new Screen().init();

	}

}
