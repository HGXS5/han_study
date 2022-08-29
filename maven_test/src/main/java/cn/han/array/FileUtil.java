package cn.han.array;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 * 
 * @author gyj
 * 
 */

public class FileUtil {
	private final static Logger log = Logger.getLogger(FileUtil.class);

	/**
	 * 将字节流转换成字符串返回
	 * 
	 * @param is
	 *            输入流
	 * @return 字符串
	 */
	public static String readFileByLines(InputStream is) {
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将文件一行一行的读成List返回
	 * 
	 * @param file
	 *            需要读取的文件
	 * @return 文件的一行就是一个List的Item的返回
	 */
	public static List<String> readFileToList(File file) {
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("======>readFileToList发生异常:" + e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					log.error("======>readFileToList发生异常:" + e1.getMessage());
				}
			}
		}
		return list;
	}

	/**
	 * 将文件按照一定的编码方式一行一行的读成List返回
	 * 
	 * @param file
	 *            需要读取的文件
	 * @param encodType
	 *            字符编码
	 * @return 文件的一行就是一个List的Item的返回
	 */
	public static List<String> readFileToList(File file, String encodType) {
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), encodType));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				if (!(tempString.charAt(0) >= 'a' && tempString.charAt(0) <= 'z')) {
					tempString = tempString.substring(1);
				}
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

	/**
	 * 将指定的字符串内容(List形式)以指定的方式写入到指定的文件中
	 * 
	 * @param file
	 *            需要写入的文件
	 * @param flag
	 *            是否追加写入
	 */
	public static boolean writeFile(File file, List<String> contentLines,
			Boolean flag) {
		BufferedWriter out = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(file, flag));
			for (String line : contentLines) {
				out.write(line);
				out.newLine();// 换行
			}
			out.flush();// 强制全部写入文件
			out.close();
			out = null;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("======>writeFile发生异常:" + e.getMessage());
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("======>writeFile发生异常:" + ex.getMessage());
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					// return false;
					e1.printStackTrace();
					log.error("======>writeFile发生异常:" + e1.getMessage());
				}
			}
		}
	}

	/**
	 * 将指定的字符串内容以指定的方式及编码写入到指定的文件中
	 * 
	 * @param file
	 *            需要写人的文件
	 * @param content
	 *            需要写入的内容
	 * @param flag
	 *            是否追加写入
	 * @param encodType
	 *            文件编码
	 */
	public static void writeFile(File file, String content, Boolean flag,
			String encodType) {
		try {
			FileOutputStream writerStream = new FileOutputStream(file, flag);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					writerStream, encodType));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 拷贝文件夹
	 * 
	 * @param oldPath
	 *            源目录
	 * @param newPath
	 *            目标目录
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs();
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将文件重命名
	 * 
	 * @param oldName
	 *            源文件名
	 * @param newName
	 *            新文件名
	 */
	public static void reName(String oldName, String newName) {
		File oldF = new File(oldName);
		File newF = new File(newName);
		oldF.renameTo(newF);
	}

	/**
	 * 将一个文件列表文件中所有文件拷贝到指定目录中
	 * 
	 * @param listFile
	 *            包含需要拷贝的文件的列表的文件，每个文件写在一行
	 * @param targetFloder
	 *            目标目录
	 */
	public static void copyFilesFromList(String listFile, String targetFloder) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(listFile));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				copyFile(tempString, targetFloder);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 拷贝文件
	 * 
	 * @param oldPath
	 *            源文件
	 * @param newPath
	 *            目标文件
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			File temp = new File(oldPath);
			FileInputStream input = new FileInputStream(temp);
			FileOutputStream output = new FileOutputStream(newPath + "/"
					+ (temp.getName()).toString());
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = input.read(b)) != -1) {
				output.write(b, 0, len);
			}
			output.flush();
			output.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件列表
	 * 
	 * @param files
	 *            需要删除的文件/文件夹列表
	 * @return 删除成功true，否则返回false
	 */
	public static boolean deleteFiles(List<String> files) {
		boolean flag = true;
		for (String file : files) {
			flag = delete(file);
			if (!flag) {
				break;
			}
		}
		return flag;
	}

	/**
	 * 删除文件或文件夹
	 * 
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 删除目录及目录下的文件
	 * 
	 * @param dir
	 *            要删除的目录路径
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			return false;
		}
		boolean flag = true;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			} else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			return false;
		}
		return dirFile.delete();
	}

	/**
	 * 创建多级目录
	 */
	public static void createDir(String dirPath) {
		log.info("====>创建目录 begin");
		if (!new File(dirPath).mkdirs()) {
			log.error("====>创建目录: " + dirPath + " fail");
		} else {
			log.info("====>创建目录: " + dirPath + " success");
		}
	}


	public static void main(String args[]) {
		// List <String>tempstring=new ArrayList<String>();
		// File file=new File("d://text");
		// System.out.println(FileUtil.writeFile(file, tempstring, false))
	}

	/**
	 * 写文件.
	 * 
	 * @param imgUrl
	 * @param path
	 * @param fileName
	 * @author : zhangf
	 * @throws Exception
	 */
	public static void writeImg(String imgUrl, String path, String fileName) {
		byte[] btImg = getImageFromNetByUrl(imgUrl);
		if (null != btImg && btImg.length > 0) {
			writeImageToDisk(btImg, path, fileName);
		} else {
			log.error("没有从该连接获得内容");
		}
	}

	/**
	 * 写文件.
	 *
	 * @param fileName
	 * @author : zhaomy
	 */
	public static void DownloadImage(String fileName, String downloadUrl,String localPath) {		 		
		try {
			FileUtils.copyURLToFile(new URL(downloadUrl),
					new File(localPath + fileName));
		}catch (Exception e) {	
			e.printStackTrace();
		}		
	}
	
	/**
	 * 写文件.
	 * 
	 * @param img
	 * @param pathName
	 * @param fileName
	 * @author : zhangf
	 */
	private static void writeImageToDisk(byte[] img, String pathName,
			String fileName) {
		FileOutputStream fops = null;
		try {
			File path = new File(pathName);
			if (!path.exists()) {
				path.mkdirs();
			}
			File file = new File(pathName + fileName);
			fops = new FileOutputStream(file);
			fops.write(img);
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				if (fops != null) {
					fops.flush();
					fops.close();
				}
			} catch (Exception e) {
				log.error("close file error", e);
			}

		}
	}


	/**
	 * 根据地址获得数据的字节流
	 * 
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 * @throws Exception
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) {
		HttpURLConnection conn = null;
		InputStream inStream = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			inStream = conn.getInputStream();
			// 得到图片的二进制数据
			byte[] btImg = readInputStream(inStream);
			return btImg;
		} catch (Exception e) {
			log.error("getImageFromNetByUrl error url: " + strUrl);
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (conn != null) {
					conn = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}
