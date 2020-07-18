package com.chao.wssf.web.admin;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.chao.wssf.util.PropertiesUtils;
import com.chao.wssf.util.R;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("admin")
public class WriteBlogController {

    @Autowired
    private PropertiesUtils propertiesUtils;

    /**
     * 封面上传
     *
     * @param file
     * @return
     */
    @PostMapping("uploadCover")
    @ResponseBody

    public R uploadCover(@RequestParam("cover") MultipartFile file) {
        return uploadToOss(file, "cover");
    }

    /**
     * 封面图片
     *
     * @param file
     * @return
     */
    @PostMapping("uploadPicture")
    @ResponseBody
    public R uploadPicture(@RequestParam("picture") MultipartFile file) {
        return uploadToOss(file, "picture");
    }

    /**
     * 上传核心方法
     *
     * @param file
     * @param type
     * @return
     */
    private R uploadToOss(MultipartFile file, String type) {
        String endpoint = propertiesUtils.getEndpoint();
        String accessKeyId = propertiesUtils.getAccessKeyId();
        String accessKeySecret = propertiesUtils.getAccessKeySecret();
        String bucketName = propertiesUtils.getBucketName();

        //开始连接OSS
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //文件路径加名称
        String filename = type + "/" + new DateTime().toString("yyyy/MM/dd") + "/" + UUID.randomUUID().toString() + file.getOriginalFilename();//设置日期格式
        try {
            InputStream inputStream = file.getInputStream();
            oss.putObject(bucketName, filename, inputStream);
            return R.OK().data("imageUrl", "https://" + bucketName + "." + endpoint + "/" + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

}
