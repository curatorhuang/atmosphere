import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Camera {
	

	    private String imageFormat="jpg"; // 图像文件的格式
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //获取全屏幕的宽高尺寸等数据
	
	 public void snapShot(int x,int y,int width,int height) {
	        try {
	            // *** 核心代码 *** 拷贝屏幕到一个BufferedImage对象screenshot
	            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(x,y,width,height));
	            //// 根据文件前缀变量和文件格式变量，自动生成文件名
	            //String name = "" + String.valueOf(serialNum) + "." + imageFormat;
	            String name ="d:/hello.jpg";
	            File f = new File(name);
	            // 将screenshot对象写入图像文件
	            ImageIO.write(screenshot, imageFormat, f);
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	    }

}