import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Camera {
	

	    private String imageFormat="jpg"; // ͼ���ļ��ĸ�ʽ
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //��ȡȫ��Ļ�Ŀ�߳ߴ������
	
	 public void snapShot(int x,int y,int width,int height) {
	        try {
	            // *** ���Ĵ��� *** ������Ļ��һ��BufferedImage����screenshot
	            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(x,y,width,height));
	            //// �����ļ�ǰ׺�������ļ���ʽ�������Զ������ļ���
	            //String name = "" + String.valueOf(serialNum) + "." + imageFormat;
	            String name ="d:/hello.jpg";
	            File f = new File(name);
	            // ��screenshot����д��ͼ���ļ�
	            ImageIO.write(screenshot, imageFormat, f);
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	    }

}