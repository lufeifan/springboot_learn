package com.myblog.demo.controller;
import com.myblog.demo.entity.UserEntity;
import com.myblog.demo.service.BannerService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/testuser")
    public UserEntity testuser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("123");
        userEntity.setUsername("123");
        return userEntity;
    }

    @RequestMapping("/del/{id}")
    public boolean delbannner1(@PathVariable String id){
        boolean b = bannerService.removeById(id);
        return b;
    }

    @RequestMapping("/string")
    public String testString(){
        return "stringtest";
    }

    /**
     * 文件上传
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/testfile")
    public String testFIle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        fileUpload.setHeaderEncoding("utf-8");
        List<FileItem> fileItems = fileUpload.parseRequest(new ServletRequestContext(request));

        System.out.println(fileItems);
        for (FileItem item:fileItems){
            System.out.println(item.getName());
            System.out.println(item.getSize());
            System.out.println(item.getFieldName());

            InputStream inputStream = item.getInputStream();
//            保存文件
            File file = new File("com.myblog.demo.file", item.getName());
            FileUtils.copyInputStreamToFile(inputStream,file);
        }
        fileItems.clear();
        return "上传成功";
    }

    @RequestMapping(value="/fileUploadController")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        return fileName;
    }

    /**
     * 文件下载
     * @return
     */
    @RequestMapping(value="/filedown")
    public void filedown(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("com.myblog.demo.file\\灯泡.png");
        InputStream in = new FileInputStream(file);
        response.setContentType("text/html; charset=utf-8");
        ServletOutputStream out = response.getOutputStream();
        IOUtils.copy(in,out);
        in.close();
        out.close();
    }
}
