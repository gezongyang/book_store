package com.atguigu.bookstore.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import com.atguigu.bookstore.beans.Resource;

public class DataprocessUtils {

	/**
	 * 生成日志表名
	 * @param offset
	 * 	偏移量：以当月为基准的偏移数值
	 * 		-1:上一个月
	 * 		0:这个月
	 * 		1:下一个月
	 * @return  manager_log_2017_11
	 */
	public static String generateTableName(int offset) {
		
		//2016_12→2017_01
		//2016_01→2015_12
		
		//1.获取当前系统时间的日历对象
		Calendar calendar = Calendar.getInstance();
		
		//2.附加偏移量
		calendar.add(Calendar.MONTH, offset);
		
		//3.获取Date对象
		Date time = calendar.getTime();
		
		//4.格式化日期为字符串
		
		return "manager_log_" + new SimpleDateFormat("yyyy_MM").format(time);
	}

		
		/**
		 * 深度复制
		 * @param sourceObj
		 * @return
		 */
//		public static Serializable deeplyCopy(Serializable sourceObj) {
//			
//			//1.声明四个流变量
//			ObjectInputStream ois = null;
//			ObjectOutputStream oos = null;
//			ByteArrayInputStream bais = null;
//			ByteArrayOutputStream baos = null;
//			
//			Serializable targetObject = null;
//			
//			try {
//				//2.创建字节数组输出流
//				baos = new ByteArrayOutputStream();
//				
//				//3.根据字节数组输出流创建对象输出流
//				oos = new ObjectOutputStream(baos);
//				
//				//4.执行序列化操作
//				oos.writeObject(sourceObj);
//				
//				//5.获取字节数组
//				byte[] byteArray = baos.toByteArray();
//				
//				//6.根据字节数组创建字节数组输入流
//				bais = new ByteArrayInputStream(byteArray);
//				
//				//7.根据字节数组输入流创建对象输入流
//				ois = new ObjectInputStream(bais);
//				
//				//8.执行反序列化操作
//				targetObject = (Serializable) ois.readObject();
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} finally {
//				
//				if(oos != null) {
//					try {
//						oos.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//				
//				if(ois != null) {
//					try {
//						ois.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//				
//			}
//			
//			return targetObject;
//		}
		
		/**
		 * 检查bagOrderList中是否存在重复的元素
		 * @param bagOrderList
		 * @return
		 * 存在重复元素时返回false
		 */
//		public static boolean checkBagOrderList(List<Integer> bagOrderList) {
//			
//			Set<Integer> bagOrderSet = new HashSet<>(bagOrderList);
//			
//			return bagOrderList.size() == bagOrderSet.size();
//		}
		
		/**
		 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
		 * @param inputStream
		 * @param realPath /surveyLogos目录的真实路径，后面没有斜杠
		 * @return 将生成的文件路径返回 surveyLogos/4198393905112.jpg
		 */
//		public static String resizeImages(InputStream inputStream, String realPath) {
//			
//			OutputStream out = null;
//			
//			try {
//				//1.构造原始图片对应的Image对象
//				BufferedImage sourceImage = ImageIO.read(inputStream);
//
//				//2.获取原始图片的宽高值
//				int sourceWidth = sourceImage.getWidth();
//				int sourceHeight = sourceImage.getHeight();
//				
//				//3.计算目标图片的宽高值
//				int targetWidth = sourceWidth;
//				int targetHeight = sourceHeight;
//				
//				if(sourceWidth > 100) {
//					//按比例压缩目标图片的尺寸
//					targetWidth = 100;
//					targetHeight = sourceHeight/(sourceWidth/100);
//					
//				}
//				
//				//4.创建压缩后的目标图片对应的Image对象
//				BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
//				
//				//5.绘制目标图片
//				targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);
//				
//				//6.构造目标图片文件名
//				String targetFileName = System.nanoTime() + ".jpg";
//				
//				//7.创建目标图片对应的输出流
//				out = new FileOutputStream(realPath+"/"+targetFileName);
//				
//				//8.获取JPEG图片编码器
//				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//				
//				//9.JPEG编码
//				encoder.encode(targetImage);
//				
//				//10.返回文件名
//				return "surveyLogos/"+targetFileName;
//				
//			} catch (Exception e) {
//				
//				e.printStackTrace();
//				
//				return null;
//			} finally {
//				//10.关闭流
//				if(out != null) {
//					try {
//						out.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//				
//			}
//		}
		
		/**
		 * 对源字符串进行加密，返回加密后的字符串
		 * @param source
		 * @return
		 */
//		public static String md5(String source) {
//			
//			//1.检查源字符串是否有效
//			if(source == null || source.length() == 0) {
//				return null;
//			}
//			
//			//2.获取源字符串的字节数组
//			byte[] bytes = source.getBytes();
//			
//			MessageDigest digest = null;
//			try {
//				//3.获取MessageDigest对象
//				digest = MessageDigest.getInstance("MD5");
//
//				//4.执行加密操作
//				byte[] targetBytes = digest.digest(bytes);
//				
//				//5.将目标字节数组转换成适合保存到数据库的字符串
//				return convertBytes(targetBytes);
//				
//			} catch (NoSuchAlgorithmException e) {
//				e.printStackTrace();
//			}
//			
//			return null;
//		}
//		
//		public static String convertBytes(byte[] targetBytes) {
//			
//			StringBuilder builder = new StringBuilder();
//			
//			//1.声明一个字符数组
//			char[] characters = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
//			
//			//2.遍历targetBytes数组
//			for(int i = 0; i < targetBytes.length; i++) {
//				
//				byte b = targetBytes[i];
//				
//				//3.取b的高四位和低四位的值
//				int low = b & 15;
//				int high = (b >> 4) & 15;
//				
//				//4.从字符数组中取对应的字符
//				char lowChar = characters[low];
//				char highChar = characters[high];
//				
//				//5.拼接
//				builder.append(highChar).append(lowChar);
//			}
//			
//			return builder.toString();
//		}

		/**
		 * 专业祛痘
		 * @param temp
		 * @return
		 */
		public static String removeComma(String temp, String operator) {
			
			if(temp == null || temp.length() == 0) {
				return temp;
			}
			
			//判断当前字符串是否已逗号开头
			if(temp.startsWith(operator)) {
				temp = temp.substring(1);
			}
			
			if(temp.endsWith(operator)) {
				temp = temp.substring(0, temp.length() - 1); 
			}
			
			return temp;
		}

		/**
		 * 将[1,2,3]或[Good]形式的数组转换为对应的字符串
		 * @param paramValues
		 * @return
		 */
//		public static String convertParamValues(String[] paramValues) {
//			
//			if(paramValues == null || paramValues.length == 0) {
//				return "";
//			}
//			
//			StringBuilder builder = new StringBuilder();
//			
//			for (String value : paramValues) {
//				
//				builder.append(value).append(",");
//				
//			}
//			
//			return removeComma(builder.toString(), ",");
//		}

		/**
		 * 将servletPath中后面附加的部分去掉
		 * 
		 *	 /xxx/yyy/zzz
		 *	 /xxx/yyy/zzz/1
		 *	 /xxx/yyy/zzz/1/2
		 *	 /xxx/yyy/zzz/1/a/2
		 *	 /www/xxx/yyy/zzz/标识/1/a/2
		 * 
		 * @param servletPath
		 * @return
		 */
		public static String cutUrl(String servletPath) {
			
			//1.以斜杠为分隔符将servletPath拆开
			String[] servletPathArr = servletPath.split("/");
			
			//2.将下标为1,2,3的元素拼接起来
			servletPath = "/" + servletPathArr[1] + "/" + servletPathArr[2] + "/" + servletPathArr[3];
			
			return servletPath;
		}

		/**
		 * 不论Admin还是User都是根据Role的集合计算权限码数组，所以封装工具方法
		 * @param roleSet
		 * @return
		 */
//		public static String calculateCodeArr(Set<Role> roleSet, Integer maxPos) {
//			
//			//声明一个整型数组用来保存最终的各个权限码
//			int [] codeArr = new int[maxPos+1]; //默认值是0
//			//Integer[] codeass = null; 默认值是null
//			
//			//逐层遍历roleSet
//			for (Role role : roleSet) {
//				
//				Set<Auth> authSet = role.getAuthSet();
//				
//				for (Auth auth : authSet) {
//				
//					Set<Res> resSet = auth.getResSet();
//					
//					for (Res res : resSet) {
//						
//						//经过逐层遍历之后，取得每一个资源对象的权限码和权限位
//						Integer resPos = res.getResPos();
//						Integer resCode = res.getResCode();
//						
//						//使用权限位作为数组下标，从用户的权限码数组中取出旧值
//						int oldValue = codeArr[resPos];
//						
//						//执行或运算，得到新值
//						int newValue = oldValue | resCode;
//						
//						//将新值放回用户的权限码数组
//						codeArr[resPos] = newValue;
//					}
//					
//				}
//			}
//			
//			StringBuilder builder = new StringBuilder();
//			
//			for (int i = 0; i < codeArr.length; i++) {
//				int code = codeArr[i];
//				builder.append(code).append(",");
//			}
//			
//			return removeComma(builder.toString(), ",");
//		}
		
		/**
		 * 验证权限
		 * @param codeArrStr
		 * @param res
		 * @return
		 */
		public static boolean checkAuthority(String codeArrStr, Resource res) {
			
			//1.将codeArrStr还原成数组
			String[] split = codeArrStr.split(",");
			
			int[] codeArr = new int[split.length];
			
			for (int i = 0; i < split.length; i++) {
				String codeStr = split[i];
				int code = Integer.parseInt(codeStr);
				codeArr[i] = code;
			}
			
			//2.以权限位为下标，从权限码数组中取值
			int userCode = codeArr[res.getResPos()];
			
			//3.将用户的权限码数组和目标资源的权限码数组执行与运算
			int result = userCode & res.getResCode();
			
			return result != 0;
		}
		
		/**
		 * 生成日志表名
		 * @param offset
		 * 	偏移量：以当月为基准的偏移数值
		 * 		-1:上一个月
		 * 		0:这个月
		 * 		1:下一个月
		 * @return
		 */
		public static String generateTableName1(int offset) {
			
			//2016_12→2017_01
			//2016_01→2015_12
			
			//1.获取当前系统时间的日历对象
			Calendar calendar = Calendar.getInstance();
			
			//2.附加偏移量
			calendar.add(Calendar.MONTH, offset);
			
			//3.获取Date对象
			Date time = calendar.getTime();
			
			//4.格式化日期为字符串
			
			return "manager_log_" + new SimpleDateFormat("yyyy_MM").format(time);
		}
		
		/**
		 *根据表名集合生成子查询字符串
		 * @param tableNames
		 * @return
		 */
//		public static String generateSubQuery(List<String> tableNames) {
//			
//			StringBuilder builder = new StringBuilder();
//			
//			for (String tableName : tableNames) {
//				builder.append("SELECT * FROM ").append(tableName).append(" UNION ");
//			}
//			
//			return builder.substring(0, builder.lastIndexOf(" UNION "));
//		}

	}

	
	
	
	

