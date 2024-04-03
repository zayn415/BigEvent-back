package com.zayn.bigevent.controller;

import com.zayn.bigevent.pojo.Result;
import com.zayn.bigevent.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author zayn
 * * @date 2024/4/3/22:28
 */
@RestController("/file")
public class FileUploadController {
    @Autowired
    private AliOssUtil ossUtil;
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;
        
        // 上传文件到OSS
        String url =  ossUtil.uploadFile(fileName, file.getInputStream());
        return Result.success(url);
    }
}
