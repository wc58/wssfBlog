package com.chao.wssf.web.admin;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.chao.wssf.properties.OssProperties;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("admin")
public class FileUploadController {

    @Autowired
    private OssProperties ossProperties;


    /**
     * 文章图片
     *
     * @param files
     * @return
     */
    @PostMapping("uploadDiary")
    @ResponseBody
    public Map<String, Object> uploadDiary(@RequestParam("diary") MultipartFile[] files) {
        String bucketName = ossProperties.getBucketName();
        String endpoint = ossProperties.getEndpoint();
        OSS oss = getOss();
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> paths = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                //文件路径加名称
                String filename = "diary" + "/" + new DateTime().toString("yyyy/MM/dd") + "/" + UUID.randomUUID().toString() + file.getOriginalFilename();//设置日期格式
                InputStream inputStream = file.getInputStream();
                oss.putObject(bucketName, filename, inputStream);
                String filePath = "https://" + bucketName + "." + endpoint + "/" + filename;
                paths.add(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put("errno", 1);
        }
        map.put("errno", 0);
        map.put("data", paths);
        return map;
    }

    /**
     * 封面上传
     *
     * @param file
     * @return
     */
    @PostMapping("uploadCover")
    @ResponseBody
    public R uploadCover(@RequestParam("cover") MultipartFile file) {
        String bucketName = ossProperties.getBucketName();
        String endpoint = ossProperties.getEndpoint();
        OSS oss = getOss();
        //文件路径加名称
        String filename = "cover" + "/" + new DateTime().toString("yyyy/MM/dd") + "/" + UUID.randomUUID().toString() + file.getOriginalFilename();//设置日期格式
        try {
            InputStream inputStream = file.getInputStream();
            oss.putObject(bucketName, filename, inputStream);
            return R.OK().data("imageUrl", "https://" + bucketName + "." + endpoint + "/" + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }


    /**
     * 文章图片
     *
     * @param files
     * @return
     */
    @PostMapping("uploadPictures")
    @ResponseBody
    public Map<String, Object> uploadPictures(@RequestParam("article") MultipartFile[] files) {
        String bucketName = ossProperties.getBucketName();
        String endpoint = ossProperties.getEndpoint();
        OSS oss = getOss();
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> paths = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                //文件路径加名称
                String filename = "article" + "/" + new DateTime().toString("yyyy/MM/dd") + "/" + UUID.randomUUID().toString() + file.getOriginalFilename();//设置日期格式
                InputStream inputStream = file.getInputStream();
                oss.putObject(bucketName, filename, inputStream);
                String filePath = "https://" + bucketName + "." + endpoint + "/" + filename;
                paths.add(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put("errno", 1);
        }
        map.put("errno", 0);
        map.put("data", paths);
        return map;
    }

    /**
     * 删除对象
     *
     * @param filePath
     * @return
     */
    @RequestMapping("delCover")
    @ResponseBody
    public R delCover(@RequestParam String filePath) {
        try {
            String bucketName = ossProperties.getBucketName();
            filePath = filePath.replace("https://wssf.oss-cn-beijing.aliyuncs.com/", "");
            OSS oss = getOss();
            oss.deleteObject(bucketName, filePath);
        } catch (OSSException e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

    private OSS getOss() {
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getAccessKeySecret();
        //开始连接OSS
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
