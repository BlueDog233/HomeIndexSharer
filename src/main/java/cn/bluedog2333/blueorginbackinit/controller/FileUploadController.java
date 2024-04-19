package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.utils.staticutils.OSSUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @PostMapping("/img")
    public Result<String> uploadImg(@RequestPart("img") MultipartFile img) throws IOException {
        String res = OSSUtil.uploadImg((RenderedImage) img, String.valueOf(System.currentTimeMillis()), false);
        return Result.success(res);

    }
}