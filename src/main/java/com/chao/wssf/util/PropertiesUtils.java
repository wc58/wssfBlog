package com.chao.wssf.util;

import lombok.Data;

/**
 * 读取阿里云oss配置信息
 */
@Data
public class PropertiesUtils {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}
