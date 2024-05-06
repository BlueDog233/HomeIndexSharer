package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.annotation.NeedPerm;
import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.common.enums.UserPermEnum;
import cn.bluedog2333.blueorginbackinit.properties.FileStoreProperties;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.bluedog2333.blueorginbackinit.utils.staticutils.OSSUtil;
import cn.hutool.core.convert.Convert;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Resource
    ContextUtil contextUtil;

    @PostMapping("/img")
    public Result<String> uploadImg(@RequestPart("img") MultipartFile img) throws IOException {
        String url=OSSUtil.uploadImg2(img);
        return Result.success(url);
    }

    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(400).body("请上传一个文件");
        }
        try {
            // 获取文件并转存
            final String originalFilename = file.getOriginalFilename();
            var user=contextUtil.getStoreUser();
            Path targetLocation = Paths.get(FileStoreProperties.storePath+"/cache", user.getUsername()+originalFilename.substring(originalFilename.lastIndexOf('.',originalFilename.length())));
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("文件上传成功，保存到: " + targetLocation.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("文件上传失败: " + e.getMessage());
        }
    }
}