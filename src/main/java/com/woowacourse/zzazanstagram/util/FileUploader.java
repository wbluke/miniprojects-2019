package com.woowacourse.zzazanstagram.util;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
    String upload(MultipartFile multipartFile, String dirName);
}
