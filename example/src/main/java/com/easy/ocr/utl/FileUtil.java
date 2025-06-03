package com.easy.ocr.utl;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtil {
    /**
     * 将 MultipartFile 转换为临时 File 文件
     *
     * @param multipartFile Spring 的上传文件对象
     * @return 转换后的临时文件
     * @throws IOException 异常处理
     */
    public static File transfer(MultipartFile multipartFile) throws IOException {
        // 创建临时文件
        File tempFile = Files.createTempFile("upload-", ".tmp").toFile();
        // 确保在 JVM 关闭前删除临时文件
        tempFile.deleteOnExit();

        try (InputStream inputStream = multipartFile.getInputStream()) {
            // 使用 NIO 将 MultipartFile 写入临时文件
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        return tempFile;
    }
}
