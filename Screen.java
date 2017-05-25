import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static java.awt.BorderLayout.*;
import javax.swing.Timer;
import java.util.*;
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

	// �ڲ�����
	private JDesktopPane jdp = new JDesktopPane();
	private JInternalFrame jf = new JInternalFrame("����ϵ��������ܼ���", true, false, true, true);
	private JInternalFrame jf2 = new JInternalFrame("ԭʼ�ź�", true, false, true, true);
	private JInternalFrame jf3 = new JInternalFrame("����ǿ��", true, false, true, true);


	// ��ȡ��Ļ�ߴ�
	int width = 1600;
	int height = 900;

	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	//��ʱ��
	Timer timer;

	static int h=0;//signal2����λ��
	static int pis = 60,pos=0;//��ȡ�浵��洢�浵��ָ��λ��
	static int[] signal1=new int[40000];//ԭʼ�ź�����
	static int[] signal2=new int[200];//��Ϊ100���ദ100�����࣬��������ϵ��������
	static int[] signal3=new int[5];
	
	JPanel jp = new JPanel();
	Button bt = new Button("��ʼ");
	TextField tf = new TextField(40);
	static String a ;
	
	
	public void init() throws IOException {

		// ��ӻ���
		jf.add(Canvas_1);
		Canvas_1.setPreferredSize(new Dimension(600,400));
		jf2.add(Canvas_2);
		Canvas_2.setPreferredSize(new Dimension(950,800));
		jf3.add(Canvas_3);
		Canvas_3.setPreferredSize(new Dimension(600,400));

		// ���ô���λ��
		jf.setLocation(0,0);
		jf3.setLocation(0,400);
		jf2.setLocation(600,0);
		jdp.add(jf);
		jdp.add(jf2);
		jdp.add(jf3);
		f.add(jdp);

		// ��Ӳ˵���
		file.add(openitem);
		file.add(saveitem);
		mb.add(file);
		mb.add(setting);
		f.setJMenuBar(mb);
		
		
		//����ı���
				jp.add(tf);
				jp.add(bt);
				jf2.add(jp,SOUTH);
	
		// 250����ˢ��һ��ͼ��
		timer = new Timer(250, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signal1=new OriginalSignaTest().originalSignalInput();
				Canvas_2.repaint();
				Canvas_1.repaint();
				Canvas_3.repaint();
				h=0;
			}
		});
		
		bt.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent e)
	{
		a=tf.getText();
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
		
		f.setSize(width,height);
		f.setLocationRelativeTo(null);// ������������Ļ������
		f.setVisible(true);
	}

	public static void main(String[] args) throws IOException {

		new Screen().init();

	}


}
