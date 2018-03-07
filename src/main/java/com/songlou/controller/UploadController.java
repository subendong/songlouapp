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
	 * �ϴ�
	 * Ҫ�ǵ���spring-mvc.xml�������ϴ������bean:multipartResolver
	 * ����������ܵ�ʱ��ͼƬ�ϴ��ɹ��ˣ�����ʼ����ʾ404������ԭ���Ƿ��ص�ʱ��Ҫ����ע�⣺@ResponseBody
	 * �ϴ�Ч���õ�������ǣ�zyupload������dropzoneҲ��������Ҫ��Ӧ�ó����������ʺϾ�������
	 * @param file
	 * @return
	 */
	@NeedLogin
	@RequestMapping(value = "/index", method=RequestMethod.POST)
	@ResponseBody
	public ResultHelper upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception{			
		//����ļ���Ϊ�գ�д���ϴ�·��
        if(!file.isEmpty()) {
            //�ϴ��ļ�·��
        	String basePath = request.getSession().getServletContext().getRealPath("/");//"D:\\workspace\\songlouapp\\src\\main\\webapp\\WEB-INF\\images\\upload";//"src/main/webapp/WEB-INF/images/upload/";
        	//����ļ���Ŀ¼
        	String saveFolder = "upload";
            //�ϴ��ļ���
            String fileName = file.getOriginalFilename();
            File filePath = new File(basePath + File.separator + saveFolder, fileName);
            //�ж�·���Ƿ���ڣ���������ھʹ���һ��
            if (!filePath.getParentFile().exists()) { 
            	filePath.getParentFile().mkdirs();
            }
            //���ϴ��ļ����浽һ��Ŀ���ļ�����
            String relativePath = File.separator + saveFolder + File.separator + fileName;
            file.transferTo(new File(basePath, relativePath));
            return new ResultHelper(0, true, "success", relativePath);
        } else {
            return new ResultHelper(1, false, "error", null);
        }
	}
}
