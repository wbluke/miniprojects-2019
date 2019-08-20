package com.woowacourse.zzazanstagram.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Profile("!dev")
@Component
public class LocalFileUploader implements FileUploader {
    @Override
    public String upload(MultipartFile multipartFile, String dirName) {
        File uploadFile;
        try {
            uploadFile = convert(multipartFile)
                    .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        } catch (IOException e) {
            throw new IllegalArgumentException("업로드 파일 생성 실패");
        }

        return upload(uploadFile, dirName);
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }
}
