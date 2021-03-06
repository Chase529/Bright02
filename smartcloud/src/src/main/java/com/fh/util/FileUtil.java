package com.fh.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

/**	文件处理
*  创建人：FH Q313596790
 * 创建时间：2014年12月23日
 */
public class FileUtil {

	
	/**获取文件大小 返回 KB 保留3位小数  没有文件时返回0
	 * @param filepath 文件完整路径，包括文件名
	 * @return
	 */
	public static Double getFilesize(String filepath){
		File backupath = new File(filepath);
		return Double.valueOf(backupath.length())/1000.000;
	}
	
	/**
	 * 创建目录
	 * @param destDirName 目标目录名
	 * @return 
	 */
	public static Boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if(!dir.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
			return dir.getParentFile().mkdirs();		//不存在就全部创建
		}
		return false;
	}

	/**
	 * 删除文件
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 读取到字节数组0
	 * @param filePath //路径
	 * @throws IOException
	 */
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * 读取到字节数组1
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray3(String filePath) throws IOException {

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			//System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 上传文件
	 */
	public static List<List<String>> uploadFiles(Object obj,String pre) throws Exception {
		List<List<String>> res = new ArrayList<List<String>>();
		List<String> names = new ArrayList<String>(); 
		List<String> urls = new ArrayList<String>(); 
		if(obj instanceof DefaultMultipartHttpServletRequest) {
			MultipartHttpServletRequest request = (MultipartHttpServletRequest)obj;
			String serviceUrl = request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			
			MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
			Set<String> keySet = multiFileMap.keySet();
			int j=0;
			for(String key:keySet) {
				j++;
				List<MultipartFile> list = multiFileMap.get(key);
				if(list != null && list.size() != 0) {
					for(int i=0;i<list.size();i++) {
						MultipartFile file = list.get(i);
						String ffile = DateUtil.getDays(), fileName = ""; // ffile就是文件名称
			    			fileName = file.getOriginalFilename();
			    			if(!fileName.equals("")) {
			    				String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
			    				prefix = prefix.toLowerCase().trim(); // 文件的后缀名称 也就是文件名
				    			String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE + ffile; // 文件上传路径
				    			fileName = FileUpload.fileUp(file, filePath, pre+"_"+DateUtil.getSdfTimes()+j+""+i);// 执行上传
				    			String url = "http://"+serviceUrl+Const.FILEPATHFILE + ffile + "/" + fileName;
				    			names.add(fileName);
				    			urls.add(url);
			    			}
					}
				}
			}
		}
		res.add(names);
		res.add(urls);
		return res;
	}
//	/**
//	 * 保存文件，返回路径
//	 * @param urlPath
//	 * @param file
//	 * @return
//	 */
//	public static String saveFile(MultipartFile file) {
//		if (!file.isEmpty()) {
//			String ffile = DateUtil.getDays(), fileName = ""; // ffile就是文件名称
//			MultipartFile multipartFile = (CommonsMultipartFile) file;
//			fileName = multipartFile.getOriginalFilename();
//			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
//			prefix = prefix.toLowerCase().trim(); // 文件的后缀名称 也就是文件名
//			String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE + ffile; // 文件上传路径
//			fileName = FileUpload.fileUp(file, filePath, UuidUtil.get32UUID());// 执行上传
//			return Const.FILEPATHFILE + ffile + "/" + fileName;
//		}
//		return null;
//	}
}