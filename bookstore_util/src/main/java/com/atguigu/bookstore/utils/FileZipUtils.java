package com.atguigu.bookstore.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZipUtils {

	/**
     * 将一组文件打zip包
     *
     * @param srcFiles
     * @param targetFileName
     * @throws IOException
     */
    public static void filesToZip(List<File> srcFiles, String targetFileName)
            throws IOException {
        String fileOutName = targetFileName + ".zip";
        byte[] buf = new byte[1024];
        FileInputStream in = null;
        FileOutputStream fos = null;
        ZipOutputStream out = null;
        try {
            fos = new FileOutputStream(fileOutName);
            out = new ZipOutputStream(fos);
            for (File file : srcFiles) {
                in = new FileInputStream(file);
                out.putNextEntry(new ZipEntry(file.getName()));
                int len;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                if (in != null) {
                    in.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (fos != null) {
                out.closeEntry();
                out.close();
                fos.close();
            }
        }
    }
    }
