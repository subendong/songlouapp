package com.songlou.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.songlou.annotation.NeedLogin;
import com.songlou.instrument.ResultHelper;

@Controller
@RequestMapping("/upload")
public class UploadController {
	/**
	 * 上传
	 * 要记得在spring-mvc.xml中配置上传组件的bean:multipartResolver
	 * 在做这个功能的时候，图片上传成功了，但是始终提示404，最终原因是返回的时候要加上注解：@ResponseBody
	 * 上传效果用到的组件是：zyupload，但是dropzone也不错，具体要看应用场景，哪种适合就用哪种
	 * @param file
	 * @return
	 */
	@NeedLogin
	@RequestMapping(value = "/index", method=RequestMethod.POST)
	@ResponseBody
	public ResultHelper upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception{			
		//如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
        	String basePath = request.getSession().getServletContext().getRealPath("/");//"D:\\workspace\\songlouapp\\src\\main\\webapp\\WEB-INF\\images\\upload";//"src/main/webapp/WEB-INF/images/upload/";
        	//存放文件的目录
        	String saveFolder = "upload";
            //上传文件名
            String fileName = file.getOriginalFilename();
            File filePath = new File(basePath + File.separator + saveFolder, fileName);
            //判断路径是否存在，如果不存在就创建一个
            if (!filePath.getParentFile().exists()) { 
            	filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            String relativePath = File.separator + saveFolder + File.separator + fileName;
            file.transferTo(new File(basePath, relativePath));
            return new ResultHelper(0, true, "success", relativePath);
        } else {
            return new ResultHelper(1, false, "error", null);
        }
	}
}
