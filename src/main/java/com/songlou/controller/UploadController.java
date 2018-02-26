package com.songlou.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class UploadController {
	/**
	 * 上传页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("upload/index");
        return mav;
	}

	/**
	 * 上传
	 * 要记得在spring-mvc.xml中配置上传组件的bean:multipartResolver
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception{
		String basePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		String adfasdf = basePath;
		
		
		//如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
        	String path = request.getSession().getServletContext().getRealPath("/upload");//"D:\\workspace\\songlouapp\\src\\main\\webapp\\WEB-INF\\images\\upload";//"src/main/webapp/WEB-INF/images/upload/";
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) { 
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            
            String fileasdfasdf = path + File.separator + filename;
            file.transferTo(new File(fileasdfasdf));
            
            return "success";
        } else {
            return "error";
        }
	}
}
