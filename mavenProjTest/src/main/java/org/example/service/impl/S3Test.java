package org.example.service.impl;

import java.net.URL;
import java.util.Objects;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class S3Test {

    @Value("${minio_path_style:0}")
    public static String pathStyle;

    @Data
    public static class S3Info {
        private String host;
        private String bucket;
        private String path;
    }

    public static void S3InfoTest() {
        S3Info s3Info = parseFromUrl("1", "http://minio-hs.beacon-base:9000/minio-export/5dcfc24015a74b76bb27abc9418b88e9.csv.gz");
        S3Info s3Info1 = parseFromUrl("0", "http://minio-hs.beacon-base:9000/minio-export/5dcfc24015a74b76bb27abc9418b88e9.csv.gz");
        S3Info s3Info2 = parseFromUrl("1", "https://qdam-wm-1300986092.cos.ap-nanjing.myqcloud.com/5c02e1a94d1b453abbae19e630e4402c.csv.gz");
        S3Info s3Info3 = parseFromUrl("0", "https://qdam-wm-1300986092.cos.ap-nanjing.myqcloud.com/5c02e1a94d1b453abbae19e630e4402c.csv.gz");
        S3Info s3Info4 = parseFromUrl(pathStyle, "http://minio-hs.beacon-base:9000/minio-export/5dcfc24015a74b76bb27abc9418b88e9.csv.gz");
        log.info("S3InfoTest s3Info:{},s3Info1:{},s3Info2:{},s3Info3:{},,s3Info4:{}",
                s3Info, s3Info1, s3Info2, s3Info3, s3Info4);
    }

    public static S3Info parseFromUrl(String pathStyle, String urlString) {
        log.info("parseFromUrl urlString:{}", urlString);

        S3Info result = new S3Info();
        try {
            if (urlString == null || urlString.isEmpty()) {
                return null;
            }
            if (Objects.equals(pathStyle, "1")) {
                URL parser = new URL(urlString);
                String path = parser.getPath();
                // 获取第一个/的就是bucket
                String[] list = path.split("/");
                if (list.length < 3) {
                    return null;
                }
                result.setBucket(list[1]);

                String prefix = "/" + list[1] + "/";
                result.setPath(path.substring(prefix.length()));
                return result;
            } else {
                URL urlParse = new URL(urlString);
                String host = urlParse.getHost();
                String path = urlParse.getPath();

                String bucketName = host.substring(0, host.indexOf("."));
                String fileId = path.startsWith("/") ? path.substring(1) : path;
                log.debug("parseFromUrl bucketName:{}, fileId:{}, host:{}, path:{}, url:{}", bucketName, fileId, host,
                        path,
                        urlParse);

                result.setBucket(bucketName);
                result.setPath(fileId);
                return result;
            }
        } catch (Exception e) {
            log.error("error to parse bucket and path from s3 url", e);
            return null;
        }
    }
}
