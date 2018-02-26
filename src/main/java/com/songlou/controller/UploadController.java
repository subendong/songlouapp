package com.songlou.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.songlou.common.ResultHelper;

@Controller
@RequestMapping("/upload")
public class UploadController {
	/**
	 * �ϴ�
	 * Ҫ�ǵ���spring-mvc.xml�������ϴ������bean:multipartResolver
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/index", method=RequestMethod.POST)
	public ResultHelper upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception{			
		//����ļ���Ϊ�գ�д���ϴ�·��
        if(!file.isEmpty()) {
            //�ϴ��ļ�·��
        	String path = request.getSession().getServletContext().getRealPath("/upload");//"D:\\workspace\\songlouapp\\src\\main\\webapp\\WEB-INF\\images\\upload";//"src/main/webapp/WEB-INF/images/upload/";
            //�ϴ��ļ���
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //�ж�·���Ƿ���ڣ���������ھʹ���һ��
            if (!filepath.getParentFile().exists()) { 
                filepath.getParentFile().mkdirs();
            }
            //���ϴ��ļ����浽һ��Ŀ���ļ�����
            String fileasdfasdf = path + File.separator + filename;
            file.transferTo(new File(fileasdfasdf));
            System.out.println("�ϴ��ɹ�");
            return new ResultHelper(0, true, "success", null);
        } else {
            return new ResultHelper(1, false, "error", null);
        }
	}
}
