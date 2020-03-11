package com.example.jacktest2.utility;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@Component
public class FileUtil {

    // 파일 업로드
    public String uploadFile(MultipartFile filedata, String uploadPath) throws IOException {

        // 랜덤 번호 생성
        Random random = new Random();
        int nextInt = random.nextInt(99);

        // 오리지널 파일명에 랜덤 번호를 붙여준다.
        String savedName = nextInt + "_" + filedata.getOriginalFilename();

        // common.io 사용
        InputStream fileStream = filedata.getInputStream();
        File target = new File(uploadPath, savedName);

        try {
            FileUtils.copyInputStreamToFile(fileStream, target);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedName;
    }


    // 파일 삭제
    public boolean deleteFileIfExists(String savedFileName, String filePath) throws IOException {

        File file = new File(filePath + '/' + savedFileName);

        if(file.exists()){
            file.delete();
            return true;

        } else {
            return false;
        }
    }
}
