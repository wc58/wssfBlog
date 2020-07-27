package com.chao.wssf.web.admin;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.chao.wssf.properties.OssProperties;
import com.chao.wssf.util.R;
import net.coobird.thumbnailator.Thumbnails;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
     * 日记图片
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
                String originalFilename = file.getOriginalFilename();
                String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                File f = cutImage(file, suffix);
                //文件路径加名称
                String filename = "article" + "/" + new DateTime().toString("yyyy/MM/dd") + "/" + UUID.randomUUID().toString() + suffix;//设置日期格式
                oss.putObject(bucketName, filename, f);
                String filePath = "https://" + bucketName + "." + endpoint + "/" + filename;
                paths.add(filePath);
                f.delete();
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
     * 压缩图片
     *
     * @param file
     * @param suffix
     * @return
     * @throws IOException
     */
    private File cutImage(MultipartFile file, String suffix) throws IOException {
        File f = new File("aaa" + suffix);
        file.transferTo(f);
        if (file.getSize() > 1023 * 1024 * 2) {
            if (file.getSize() > 1023 * 1024 * 10) {
                Thumbnails.of(f).scale(0.2).toFile(f);
            } else if (file.getSize() > 1023 * 1024 * 6) {
                Thumbnails.of(f).scale(0.4).toFile(f);
            } else {
                Thumbnails.of(f).scale(0.6).toFile(f);
            }
        }
        return f;
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
