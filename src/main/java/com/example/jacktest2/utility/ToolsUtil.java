package com.example.jacktest2.utility;

import com.example.jacktest2.dao.PagingVariables;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class ToolsUtil {

    // 현재 날짜 - timestamp
    public Timestamp currentDateInTimestamp() {

        Date newDate = new Date();
        Timestamp sqlDate = new Timestamp(newDate.getTime());

        return sqlDate;
    }


    // 현재 날짜 - string
    public String timestampToString(Timestamp timeValue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String stringTime = sdf.format(timeValue);

        return stringTime;
    }


    // 페이징 값
    public PagingVariables returnPagingVariables(Page page, Pageable pageable) {
        PagingVariables pagingVariables = new PagingVariables();

        int pageNumber = 1 + pageable.getPageNumber();
        int endPage = (int) (Math.ceil(pageNumber / 10.0) * 10);
        int startPage = endPage -9;

        pagingVariables.setTotalPages(page.getTotalPages());
        pagingVariables.setHasNext(page.hasNext());
        pagingVariables.setHasPrevious(page.hasPrevious());
        pagingVariables.setPageNumber(pageNumber);
        pagingVariables.setPageableOffset(pageable.getOffset());
        pagingVariables.setEndPage(endPage);
        pagingVariables.setStartPage(startPage);

        return pagingVariables;
    }


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