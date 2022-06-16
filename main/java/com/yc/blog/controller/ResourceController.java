package com.yc.blog.controller;

import com.yc.blog.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.Calendar;

@RestController
@Api(tags="资源管理")
@RequestMapping("/api/resource")
public class ResourceController {

    private int indexOf(Object[] s, Object search){
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(search)) {
                return i;
            }
        }
        return -1;
    }

    @PostMapping("/upload")
    @ApiOperation(value="文件上传")
    public Response<String> upload(MultipartFile file){
        if (file.isEmpty()) {
            return Response.error("文件为空");
        }
        try {
            String[] suffix = file.getOriginalFilename().split("\\.");
            String[] arrow = {"jpg", "gif", "png", "bmp", "jpeg"};
            if(suffix.length == 2 && indexOf(arrow, suffix[1]) != -1){
                String filename = Calendar.getInstance().getTimeInMillis() + "." + suffix[1];
                file.transferTo(Paths.get("/www/wwwroot/YCBugBlog/"+ filename));
                return Response.ok(filename);
            }
            return Response.error("文件非法");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("上传失败");
        }
    }

    @GetMapping("/download")
    @ApiOperation(value="文件下载")
    @ApiImplicitParams({
        @ApiImplicitParam(name="file",value="文件名",dataType="string",required=true)
    })
    public Response<String> download(String file, HttpServletResponse response){
        String downloadFilePath = "/www/wwwroot/YCBugBlog/"+file;//被下载的文件在服务器中的路径,
        File f = new File(downloadFilePath);
        if (f.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + file);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(f);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return Response.ok("下载成功");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Response.error("文件不存在");
    }

}
