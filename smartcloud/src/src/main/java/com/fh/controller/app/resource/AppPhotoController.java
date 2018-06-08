package com.fh.controller.app.resource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fh.controller.base.BaseController;
import com.fh.core.ErrorCode;
import com.fh.core.HttpJsonResult;
import com.fh.core.StringUtil;
import com.fh.service.system.appuser.AppuserManager;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.FileUpload;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
@Controller
@RequestMapping(value="/appPhoto")
public class AppPhotoController extends BaseController{
	
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	
	/**新增
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public @ResponseBody HttpJsonResult<String> upload(HttpServletRequest req ,@RequestParam(required=false) MultipartFile file){

		HttpJsonResult<String> jsonResult = new HttpJsonResult<String>();
		String uid = req.getParameter("uid");
		if(StringUtil.isEmpty(uid, true)){
			jsonResult.setMessage("您还没有登录！");
			jsonResult.setCode(ErrorCode.USER_NOT_LOGIN);
			return jsonResult;
		}
		String  ffile = DateUtil.getDays(), fileName = "";
		try {
			PageData user = new PageData();
			 user.put("USER_ID", uid);
			 user = appuserService.findById(user);
			 if(user == null){
				 jsonResult.setMessage("该用户不存在！");
				 jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
				 return jsonResult;
			 }
			if (null == file) {
				jsonResult.setMessage("请文件上传文件！");
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
			}
			MultipartFile multipartFile = (CommonsMultipartFile)file;
			fileName = multipartFile.getOriginalFilename();
		    String prefix = fileName.substring(fileName.lastIndexOf(".")+1);
		    if(StringUtil.isEmpty(prefix)){
		    	jsonResult.setMessage("不是文件！");
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
		    }
		    String prefixLower = prefix.toLowerCase().trim();
		    if(!prefixLower.equals("gif") && !prefixLower.equals("jpeg") && !prefixLower.equals("png")
		    		&& !prefixLower.equals("jpg") ){
		    	jsonResult.setMessage("上传的文件类型不正确！");
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
		    }
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.FILE_UPLOAD_ERROR);
			jsonResult.setMessage(e.getMessage());
			return jsonResult;
		}
		String path = Const.FILEPATHIMG + ffile + "/" + fileName;
		jsonResult.setData(path);
		return jsonResult;
	}
}
