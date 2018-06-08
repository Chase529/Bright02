//package com.fh.util.facerecog;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import com.baidu.aip.face.AipFace;
//
///**
// * 说明：百度人脸识别，比对接入
// *
// * @author 明亮
// *
// */
//public class FaceRecogBiduUtil {
//	// 设置APPID/AK/SK
//	// 此处使用个人的AppID等信息 免费额度QPS为2 如果注册企业免费额度QPS为5 需求更多需要购买
//	// 服务详情网址：https://cloud.baidu.com/doc/FACE/Face-Pricing.html
//	public static final String APP_ID = "10625380"; // App ID
//	public static final String API_KEY = "WOkdA1YsGFsNw6Fnu2Va26tk"; // Api Key
//	public static final String SECRET_KEY = "dIf22bvQkAkDICxxoGj0iZ09lfYDVkDF"; // SecretKey
//
//	/**
//	 * 进行两张图片的人脸比对 判断同一个人的相似度 说明：此处对比的阈值为79分，即相似度大于79判定为同一个人返回true 否则返回false
//	 *
//	 * @param pic1Path
//	 *            第一张图片的位置(如：'D:\\test.jpg')
//	 * @param pic2Path
//	 *            第二张图片的位置
//	 */
//	public static boolean faceCompare(String pic1Path, String pic2Path) {
//		// 初始化一个AipFace
//		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
//
//		// 可选：设置网络连接参数
//		client.setConnectionTimeoutInMillis(2000); // 建立连接的超时时间（单位：毫秒）
//		client.setSocketTimeoutInMillis(60000);// 通过打开的连接传输数据的超时时间（单位：毫秒）
//
//		// 传入可选参数调用接口
//		HashMap<String, String> options = new HashMap<String, String>();
//		options.put("ext_fields", "qualities");// 返回质量信息，取值固定:目前支持qualities(质量检测)。(对所有图片都会做改处理)
//		/**
//		 * faceliveness,faceliveness - 对比对的两张图片都做活体检测 ,faceliveness -
//		 * 对第一张图片不做活体检测、第二张图做活体检测 faceliveness, - 对第一张图片做活体检测、第二张图不做活体检测
//		 */
//		// options.put("image_liveness", ",faceliveness");
//		/**
//		 * 请求对比的两张图片的类型，示例：“7,13” 12表示带水印证件照：一般为带水印的小图，如公安网小图
//		 * 7表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等
//		 * 13表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片，注：需要确保人脸部分不可太小，通常为100px*100px
//		 */
//		options.put("types", "7,13");
//
//		// List<String> pics = new ArrayList<String>();
//		// pics.add(pic1Path);
//		// pics.add(pic2Path);
//		// JSONObject res = client.match(pics, options);
//		// System.out.println(res.toString());
//
//		FileInputStream fis1 = null;
//		FileInputStream fis2 = null;
//		BufferedInputStream bis1 = null;
//		BufferedInputStream bis2 = null;
//		try {
//			fis1 = new FileInputStream(new File(pic1Path));
//			bis1 = new BufferedInputStream(fis1);
//			byte[] pic1 = new byte[fis1.available()];
//			bis1.read(pic1);
//			fis2 = new FileInputStream(new File(pic2Path));
//			bis2 = new BufferedInputStream(fis2);
//			byte[] pic2 = new byte[fis2.available()];
//			bis2.read(pic2);
//			byte[][] req = { pic1, pic2 };
//			JSONObject res = client.match(req, options);
//			System.out.println(res.toString());
//			String resultScore = res.getJSONArray("result").getJSONObject(0).getString("score");
//			System.out.println("The comparation score is ---->>>" + resultScore);
//			double score = Double.valueOf(resultScore);
//			if (score < 79) {
//				return false;
//			} else {
//				return true;
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				bis2.close();
//				bis1.close();
//				fis2.close();
//				fis1.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
//
//
//	/**
//	 *
//	 * @param pic1Path
//	 * @param pic2Path
//	 * @param similar  根据需要设定比对的相似度 若结果大于其值返回true
//	 * @return
//	 */
//	public static boolean faceCompare(String pic1Path, String pic2Path, int similar) {
//		// 初始化一个AipFace
//		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
//
//		// 可选：设置网络连接参数
//		client.setConnectionTimeoutInMillis(2000); // 建立连接的超时时间（单位：毫秒）
//		client.setSocketTimeoutInMillis(60000);// 通过打开的连接传输数据的超时时间（单位：毫秒）
//
//		// 传入可选参数调用接口
//		HashMap<String, String> options = new HashMap<String, String>();
//		options.put("ext_fields", "qualities");// 返回质量信息，取值固定:目前支持qualities(质量检测)。(对所有图片都会做改处理)
//		// options.put("image_liveness", ",faceliveness");
//		options.put("types", "7,13");
//
//		// List<String> pics = new ArrayList<String>();
//		// pics.add(pic1Path);
//		// pics.add(pic2Path);
//		// JSONObject res = client.match(pics, options);
//		// System.out.println(res.toString());
//
//		FileInputStream fis1 = null;
//		FileInputStream fis2 = null;
//		BufferedInputStream bis1 = null;
//		BufferedInputStream bis2 = null;
//		try {
//			fis1 = new FileInputStream(new File(pic1Path));
//			bis1 = new BufferedInputStream(fis1);
//			byte[] pic1 = new byte[fis1.available()];
//			bis1.read(pic1);
//			fis2 = new FileInputStream(new File(pic2Path));
//			bis2 = new BufferedInputStream(fis2);
//			byte[] pic2 = new byte[fis2.available()];
//			bis2.read(pic2);
//			byte[][] req = { pic1, pic2 };
//			JSONObject res = client.match(req, options);
//			System.out.println(res.toString());
//			String resultScore = res.getJSONArray("result").getJSONObject(0).getString("score");
//			System.out.println("The comparation score is ---->>>" + resultScore);
//			double score = Double.valueOf(resultScore);
//			int resultSimilar  = (int) score;
//			if (similar < resultSimilar) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				bis2.close();
//				bis1.close();
//				fis2.close();
//				fis1.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
//
//	// 测试服务是否可用 ，如果控制台打印出识别信息 说明可用
//	public static void main(String[] args) {
//		// 初始化一个AipFace
//		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
//
//		// 可选：设置网络连接参数
//		client.setConnectionTimeoutInMillis(2000); // 建立连接的超时时间（单位：毫秒）
//		client.setSocketTimeoutInMillis(60000);// 通过打开的连接传输数据的超时时间（单位：毫秒）
//
//		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//		// client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
//		// client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理
//
//		// 调用接口
//		// String path = "d:" + File.separator + "4.jpg";
//		// JSONObject res = client.detect(path, new HashMap<String, String>());
//		// try {
//		// System.out.println(res.toString(2));
//		// } catch (JSONException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//
//		// 测试比对
//		String path1 = "d:" + File.separator + "11.jpg";
//		String path2 = "d:" + File.separator + "13.jpg";
//		//System.out.println(faceCompare(path1, path2));
//		System.out.println(faceCompare(path1, path2,80));
//	}
//}
