package com.sdonkey.score.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.util.FileCopyUtils;

/**
 * 图片工具类：完成图片的放缩，并根据传入图片路径创建相应的文件夹。 把生成新的图片存入其中。
 * 
 * @author gary
 * 
 */
public class ImageUtil {

	/**
	 * 实现图像的等比缩放和缩放后的截取
	 * 
	 * @param inFilePath
	 *            要截取文件的路径
	 * @param outFilePath
	 *            截取后输出的路径
	 * @param width
	 *            要截取宽度
	 * @param hight
	 *            要截取的高度
	 * @param isCut
	 *            是否裁剪 0:等比 1:裁剪
	 * 
	 * @throws Exception
	 */
	public void saveZoomImage(String inFilePath, String outFilePath, int width, int height, int isCut) throws Exception {

		width = Math.max(width, 1);

		height = Math.max(height, 1);

		File file = new File(inFilePath);
		InputStream in = new FileInputStream(file);

		File saveFile = new File(outFilePath);

		BufferedImage srcImage = ImageIO.read(in);
		System.out.println("[width]"+width+" [height]"+height);
		//如果原图比截图小，则原尺寸复制
		int w = srcImage.getWidth();
		int h = srcImage.getHeight();
		if(w <= width && h <= height){
			width = w;
			height = h;
			System.out.println("no resize [width]"+width+" [height]"+height);
			FileCopyUtils.copy(file, saveFile);
		}else{
			System.out.println("resize [width]"+width+" [height]"+height);
			resize(srcImage, width, height, saveFile, isCut);
		}
		
		

		// if (isCut == 0) {// 等比
		// resize(srcImage, width, height, saveFile, isCut);
		// } else {// 裁剪
		// saveSubImage(srcImage, width, height, saveFile);
		// }

		in.close();
	}

	/**
	 * 实现图像的等比缩放
	 * 
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	private void resize(BufferedImage source, int targetW, int targetH, File saveFile, int isCut) throws Exception {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;

		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();

		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		if (isCut == 0) {// 等比
			if (sx < sy) {
				sx = sy;
				targetW = (int) (sx * source.getWidth());
			} else {// 非等比
				sy = sx;
				targetH = (int) (sy * source.getHeight());
			}
		}

		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			target = new BufferedImage(targetW, targetH, type);
		}
		Graphics2D g = target.createGraphics();

		// smoother than exlax:
		RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHints(qualityHints);

		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();

		String fileName = saveFile.getName();
		String typeName = fileName.substring(fileName.lastIndexOf('.') + 1);

		ImageIO.write(target, typeName, saveFile);

		return;
	}

	/**
	 * 截取实现缩放后的截图
	 * 
	 * @param image
	 *            缩放后的图像
	 * @param subImageBounds
	 *            要截取的子图的范围
	 * @param subImageFile
	 *            要保存的文件
	 * @throws IOException
	 */
	public void saveSubImage(BufferedImage source, int w, int h, File saveFile) throws IOException {

		int width = w;
		int height = h;

		double sx = (double) w / source.getWidth();
		double sy = (double) h / source.getHeight();

		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx < sy) {
			sx = sy;
			w = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			h = (int) (sy * source.getHeight());
		}

		int x = 0;
		int y = 0;
		// 如果缩放后的图像和要求的图像宽度一样，就对缩放的图像的高度进行截取
		if (w == width) {
			// 计算X轴坐标
			x = 0;
			y = h / 2 - height / 2;
		}
		// 否则如果是缩放后的图像的高度和要求的图像高度一样，就对缩放后的图像的宽度进行截取
		else if (h == height) {
			// 计算X轴坐标
			x = w / 2 - width / 2;
			y = 0;
		}

		BufferedImage subImage = source.getSubimage(x, y, width, height);

		String fileName = saveFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);

		// 转换成原图格式的图片
		ImageIO.write(subImage, formatName, saveFile);
		return;
	}

}