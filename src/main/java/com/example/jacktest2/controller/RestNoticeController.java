package com.example.jacktest2.controller;

import com.example.jacktest2.dao.NoticeValue;
import com.example.jacktest2.entities.Notice;
import com.example.jacktest2.services.NoticeService;
import com.example.jacktest2.utility.ToolsUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class RestNoticeController {

    private final NoticeService noticeService;
    private final ToolsUtil toolsUtil;

    public RestNoticeController (NoticeService noticeService, ToolsUtil toolsUtil) {
        this.noticeService = noticeService;
        this.toolsUtil = toolsUtil;
    }


    // 게시판 - 목록
    @GetMapping("/notices")
    public Page<Notice> getNotices(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        // 노컨텐츠 - 예외
        return noticeService.pageAllNotice(pageable);
    }


    // 게시판 - 검색 목록
    @GetMapping("/notices/search")
    public Page<Notice> getNoticesSearch(@PageableDefault(size = 10, page = 0) Pageable pageable,
                                           @RequestParam("keyword") String keyword) {
        // 노컨텐츠 - 예외
        return noticeService.pageAllNoticeDist(keyword, pageable);
//        throw new RuntimeException();
    }



    // 게시판 - 조회
    @GetMapping("/notice/{id}")
    public NoticeValue getNotice(@PathVariable("id") Long id) {
        // 노컨텐츠 - 예외
        return noticeService.getNoticeValue(id);
    }


    // 게시판 - 제목 중복 조회
    @GetMapping("/notice/title={title}")
    public boolean getNoticeTitleEquals(@PathVariable("title") String title) {
        // 이미 존재하는 제목 - 예외
        return noticeService.lookingForTitleEquals(title);
    }


    // 게시판 - 다운로드
//    @GetMapping("/notice/file/{id}")
//    public ResponseEntity downloadNoticeFileData(@PathVariable("id") Long id) {
//
//        return ResponseEntity.ok("1111");
//    }


    // 게시판 - 생성
    @PostMapping("/notice")
    public Notice addNotice(@Valid Notice notice,
                                        BindingResult bindingResult,
                                        @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01) throws IOException {
        // 저장 실패 - 예외
        return noticeService.saveNotice(notice, file01);
    }


    // 게시판 - 수정
    @PutMapping("/notice/{id}")
    public Notice editNotice(@Valid Notice notice,
                                         BindingResult bindingResult,
                                         @PathVariable("id") Long id,
                                         @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01) throws IOException {
        // id 확인 어려움 - 예외
        return noticeService.editNotice(notice, id, file01);
    }


    // 게시판 - 삭제 / 단수
    @DeleteMapping("/notice/{id}")
    public void deleteNotice(@PathVariable("id") Long id) {
        // id 확인 어려움 - 예외
        noticeService.deleteNoticeOne(id);
    }


    // 게시판 - 삭제 / 복수
    @DeleteMapping("/notice/{idList}")
    public void deleteNoticeAll(@PathVariable("idList") List<Long> idList) {
        // id 확인 어려움 - 예외
        noticeService.deleteAllNotice(idList);
    }


}
