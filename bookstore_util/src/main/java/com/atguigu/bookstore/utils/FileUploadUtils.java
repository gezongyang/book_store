package com.atguigu.bookstore.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
     
	public  static String FILE_IMG_PATH = ConfigUtils.getString("file.img.path");
	
	
	
	/**
	 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
	 * @param inputStream
	 * @param realPath /surveyLogos目录的真实路径，后面没有斜杠
	 * @return 将生成的文件路径返回 surveyLogos/4198393905112.jpg
	 */
//	public static String resizeImages(InputStream inputStream, String realPath) {
//		
//		OutputStream out = null;
//		
//		try {
//			//1.构造原始图片对应的Image对象
//			BufferedImage sourceImage = ImageIO.read(inputStream);
//
//			//2.获取原始图片的宽高值
//			int sourceWidth = sourceImage.getWidth();
//			int sourceHeight = sourceImage.getHeight();
//			
//			//3.计算目标图片的宽高值
//			int targetWidth = sourceWidth;
//			int targetHeight = sourceHeight;
//			
//			if(sourceWidth > 100) {
//				//按比例压缩目标图片的尺寸
//				targetWidth = 100;
//				targetHeight = sourceHeight/(sourceWidth/100);
//				
//			}
//			
//			//4.创建压缩后的目标图片对应的Image对象
//			BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
//			
//			//5.绘制目标图片
//			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);
//			
//			//6.构造目标图片文件名
//			String targetFileName = System.nanoTime() + ".jpg";
//			
//			//7.创建目标图片对应的输出流
//			out = new FileOutputStream(realPath+"/"+targetFileName);
//			
//			//8.获取JPEG图片编码器
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			
//			//9.JPEG编码
//			encoder.encode(targetImage);
//			
//			//10.返回文件名
//			return "surveyLogos/"+targetFileName;
//			
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			
//			return null;
//		} finally {
//			//10.关闭流
//			if(out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
//	}
	
	/**
	 * 根据路径上传文件
	 * 
	 * @param request
	 * @param formName
	 * @return
	 * @throws Exception
	 */
	public static String upload(MultipartFile coverFile,String[] dirs)
			throws Exception {
		String path = "";
		String relativePath = "";
		//String visitPath = "";
		if (coverFile != null) {
			//获取相对路径
			relativePath = getPathRule(dirs) + getNameRule(coverFile.getOriginalFilename());
			System.out.println("相对路径为：======================" + relativePath);
			
			path = FILE_IMG_PATH + relativePath;
			System.out.println("真实路径为：======================" + path);

			File newFile = new File(path);
			if (!newFile.getParentFile().isDirectory()) {
				newFile.getParentFile().mkdirs();
			}
			//使用spring的文件复制工具完成复制
			FileCopyUtils.copy(coverFile.getBytes(), newFile);
			
		}
		return path;
	}
	
	/**
	 * 创建路径规则
	 * 
	 * @return
	 */
	private static String getPathRule(String[] dirs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(new Date());
		String splitTime[] = currentTime.split("-");
		StringBuffer path = new StringBuffer();
		path.append(splitTime[0]);
		path.append(File.separator);
		path.append(splitTime[1]);
		path.append(File.separator);
		path.append(splitTime[2]);
		path.append(File.separator);
		for (String dir : dirs) {
			path.append(dir);
			path.append(File.separator);
		}
		return path.toString();
	}
	
	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis());
	}
	
	/**
	 * 创建名称规则
	 * 
	 * @return
	 */
	private static String getNameRule(String sourceName) {
		String targetName = System.currentTimeMillis()
				+ sourceName.substring(sourceName.lastIndexOf("."));
		return targetName;
	}
}
