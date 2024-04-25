package cn.bluedog2333.blueorginbackinit.utils.staticutils;

import cn.bluedog2333.blueorginbackinit.common.errors.CustomException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.setting.Setting;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OSSUtil {
    private static String endpoint = "";
    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    private static String bucketName = "";

    static {
        Setting setting = new Setting("config/alioss.setting");
        endpoint = setting.get("endpoint");
        accessKeyId = setting.get("accessKeyId");
        accessKeySecret = setting.get("accessKeySecret");
        bucketName = setting.get("bucketName");
    }

    public static String upload(byte[] data, String name) {
        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, name, new ByteArrayInputStream(data));
            PutObjectResult result = client.putObject(putObjectRequest);
            return bucketName+'.'+endpoint+'/'+name;
        }  catch (ClientException ce) {
            throw new CustomException("Error Message:" + ce.getMessage());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
    }
    public static String uploadImg(RenderedImage image, String name,boolean isLS) throws IOException {
        // ByteArrayOutputStream用于将图像数据写入字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        String s=upload(baos.toByteArray(), name+".jpg");
        if (isLS) {
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            scheduledExecutorService.schedule(() -> {
                try {
                    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                    ossClient.deleteObject(bucketName, name+".jpg");
                }catch (Exception e){
                    throw new CustomException("Error Message:" + e.getMessage());
                }
            },5, TimeUnit.MINUTES);
        }
        return s;
    }
    public static String uploadImg2(MultipartFile file) throws IOException {
        return upload(file.getBytes(), "photo/"+System.currentTimeMillis()+".jpg");
    }

    public static byte[] download(String name){
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId, accessKeySecret);

        try {
            // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元数据。
            OSSObject ossObject = ossClient.getObject(bucketName, name);
            // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
            InputStream content = ossObject.getObjectContent();
            if (content != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                }
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                content.close();
            }
            return IoUtil.readBytes(content);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
