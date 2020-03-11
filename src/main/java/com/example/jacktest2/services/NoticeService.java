package com.example.jacktest2.services;

import com.example.jacktest2.dao.NoticeValue;
import com.example.jacktest2.entities.Notice;
import com.example.jacktest2.exception.NoticeNotFoundException;
import com.example.jacktest2.repositories.NoticeRepository;
import com.example.jacktest2.utility.DateUtil;
import com.example.jacktest2.utility.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final DateUtil dateUtil;
    private final FileUtil fileUtil;

    public NoticeService(NoticeRepository noticeRepository, DateUtil dateUtil, FileUtil fileUtil) {
        this.noticeRepository = noticeRepository;
        this.dateUtil = dateUtil;
        this.fileUtil = fileUtil;
    }

    @Value("${notice.uploadPath}")
    private String uploadPath;

    
    // 목록
    public Page<Notice> pageAllNotice(Pageable Pageable) {
        Page<Notice> noticePage = noticeRepository.findAllByOrderByCredateDesc(Pageable);
        return noticePage;
    }


    // 검색 목록
    public Page<Notice> pageAllNoticeDist(String keyword, Pageable pageable){
        Page<Notice> allLike = noticeRepository.findAllLike(keyword, pageable);
        return allLike;
    }


    // 검색 목록 - 스트림 활용
    public Page<Notice> listAllNoticeDistStream(String keyword, Pageable pageable) {
        List<Notice> noticeList = noticeRepository.findAll();

        noticeList = noticeList.stream()
                .filter(x -> x.getWriter().contains(keyword))
                .collect(toList());

        return new PageImpl(noticeList, pageable, noticeList.size());
    }



    // 저장
    public Notice saveNotice(Notice notice, MultipartFile file01) throws IOException {
        
        Notice noticeDB = new Notice();

        noticeDB.setTitle(notice.getTitle());
        noticeDB.setContents(notice.getContents());
        noticeDB.setWriter(notice.getWriter());
        noticeDB.setViewcnt(0);
        noticeDB.setReplycnt(0);

        if (file01.isEmpty()) { // 파일 저장
            noticeDB.setFilename("NONE");
            noticeDB.setFilepath("NONE");
        } else {
            noticeDB.setFilename(file01.getOriginalFilename());
            noticeDB.setFilepath(fileUtil.uploadFile(file01, uploadPath));
        }

        noticeDB.setCredate(dateUtil.currentDateInTimestamp());
        noticeDB.setModdate(dateUtil.currentDateInTimestamp());

        Notice saveNoticeOne = noticeRepository.save(noticeDB);

        return saveNoticeOne;
    }


    // 수정
    public Notice editNotice(Notice notice, Long id, MultipartFile file01) throws IOException {

        Notice noticeOne = noticeRepository.getOne(id);

        noticeOne.setTitle(notice.getTitle());
        noticeOne.setContents(notice.getContents());
        noticeOne.setModdate(dateUtil.currentDateInTimestamp());

        if (!file01.isEmpty()) { // 파일 저장
            fileUtil.deleteFileIfExists(noticeOne.getFilepath(), uploadPath); // 기존 파일 삭제

            noticeOne.setFilename(file01.getOriginalFilename());
            noticeOne.setFilepath(fileUtil.uploadFile(file01, uploadPath));
        }

        Notice saveNoticeOne = noticeRepository.save(noticeOne);

        return saveNoticeOne;
    }


    // 조회
    public Notice getNoticeOne(Long id){
        Notice noticeOne = noticeRepository.getOne(id);
        return noticeOne;
    }


    // 값 조회
    public NoticeValue getNoticeValue(Long id) {
        
        Notice noticeOne = noticeRepository.getOne(id);

        NoticeValue noticeValueOne = new NoticeValue();

        noticeValueOne.setId(noticeOne.getId());
        noticeValueOne.setTitle(noticeOne.getTitle());
        noticeValueOne.setContents(noticeOne.getContents());
        noticeValueOne.setWriter(noticeOne.getWriter());
        noticeValueOne.setViewcnt(noticeOne.getViewcnt());
        noticeValueOne.setReplycnt(noticeOne.getReplycnt());
        noticeValueOne.setFilename(noticeOne.getFilename());
        noticeValueOne.setFilepath(noticeOne.getFilepath());
        noticeValueOne.setCredate(dateUtil.timestampToString(noticeOne.getCredate()));
        noticeValueOne.setModdate(dateUtil.timestampToString(noticeOne.getModdate()));

        return noticeValueOne;
    }


    // id 존재 확인
    public Optional<Notice> optionalNotice(Long id) {
        Optional<Notice> byId =  noticeRepository.findById(id);
        return byId;
    }


    // 삭제 - 단수
    public void deleteNoticeOne(Long id) {
        noticeRepository.deleteById(id);
    }


    // 삭제 - 복수
    public void deleteAllNotice(List<Long> idList) {

        Stream<Long> stream = idList.stream();
        stream.forEach(noticeRepository::deleteById);
    }


    // 조회수 추가
    public void addViewCount(Long id) {
        Notice noticeOne = noticeRepository.getOne(id);

        noticeOne.setViewcnt(noticeOne.getViewcnt()+1);

        noticeRepository.save(noticeOne);
    }


    // 댓글수 추가
    public void addReplyCount(Long id){
        Notice noticeOne = noticeRepository.getOne(id);

        noticeOne.setReplycnt(noticeOne.getReplycnt()+1);

        noticeRepository.save(noticeOne);
    }


    // 댓글수 삭제
    public void delReplyCount(Long id){
        Notice noticeOne = noticeRepository.getOne(id);

        noticeOne.setReplycnt(noticeOne.getReplycnt()-1);

        noticeRepository.save(noticeOne);
    }


    // 제목 중복 조회
    public boolean lookingForTitleEquals(String title) {
        boolean existsByTitleEquals = noticeRepository.existsByTitleEquals(title);
        return existsByTitleEquals;
    }


    // 파일 삭제 - 단수
    public boolean delFileData(Long id) throws IOException {
        
        Optional<Notice> byId = noticeRepository.findById(id);

        if(byId.isPresent()){
            Notice noticeOne = noticeRepository.getOne(id);
            fileUtil.deleteFileIfExists(noticeOne.getFilepath(), uploadPath);
            return true;

        } else {
            return false;
        }
    }


    // 파일 삭제 - 복수
    public void delAllFileData(List<Long> idList) throws IOException {

        for(int i=0; i<idList.size(); i++) {
            delFileData(idList.get(i));
        }
    }


    // 파일 다운로드
    public ResponseEntity<InputStreamResource> downloadNoticeFile(Long noticeId) throws FileNotFoundException, UnsupportedEncodingException {

        Optional<Notice> byId = noticeRepository.findById(noticeId);

        if(byId.isPresent()) {
            Notice fileOne = noticeRepository.getOne(noticeId);

            String fileFullPath = uploadPath + "/" + fileOne.getFilepath();

            File file = new File(fileFullPath);

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment;filename=" + URLEncoder.encode(fileOne.getFilename(), "UTF-8"))
                    .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                    .body(resource);

        } else {
            return null;
        }
    }


    // 파일 다운로드 - 2번째 방법
    public void downloadFileData(Long noticeId, HttpServletResponse response) throws IOException {

        Optional<Notice> byId = noticeRepository.findById(noticeId);

        if (byId.isPresent()) {
            Notice fileOne = noticeRepository.getOne(noticeId);

            String fullFilePath = uploadPath + "/" + fileOne.getFilepath();

            File file = new File(fullFilePath);

            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileOne.getFilename(), "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setContentType("application/download;charset=utf-8");
            response.setContentLengthLong(file.length());
            response.setHeader("Pragma", "no-cache;");
            response.setHeader("Expires", "-1;");
            response.setHeader("Connection", "close");

            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        } else {
            throw new NoticeNotFoundException();
        }
    }
}