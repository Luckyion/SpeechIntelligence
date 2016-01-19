package me.videa.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
	/**
	 * 读取一个字节文件
	 * 
	 * @param path
	 *            文件路径
	 * @return 文件内容
	 */
	public static String readCharFile(String path) {
		// TODO Auto-generated method stub
		String fileContent = "";
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "UTF-8"), 1024 * 1024);
			while ((fileContent = br.readLine()) != null) {
				sb.append(fileContent);
				fileContent = "";
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
