package com.sdonkey.score.util;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class OutputHelper {
	private static final Logger logger = LoggerFactory
			.getLogger(OutputHelper.class);

	/**
	 * 通过servletResponse输出数据
	 * 
	 * @param response
	 * @param content
	 */
	public static void outPut(HttpServletResponse response, String content) {
		if (response == null || StringUtil.isEmpty(content)) {
			return;
		}
		response.reset();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintStream out = null;
		try {
			out = new PrintStream(response.getOutputStream());
			out.print(content);
		} catch (IOException e) {
			logger.error("输出数据失败");
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}
}
