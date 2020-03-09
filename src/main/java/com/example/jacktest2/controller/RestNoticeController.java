package com.example.jacktest2.controller;

import com.example.jacktest2.entities.Notice;
import com.example.jacktest2.services.NoticeService;
import com.example.jacktest2.utility.ToolsUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RestNoticeController {

    private final NoticeService noticeService;
    private final ToolsUtil toolsUtil;

    public RestNoticeController (NoticeService noticeService, ToolsUtil toolsUtil) {
        this.noticeService = noticeService;
        this.toolsUtil = toolsUtil;
    }



    // 게시판 - 목록
    @GetMapping("/notices")
    public ResponseEntity getNotices(@PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<Notice> noticePage = noticeService.pageAllNotice(pageable);

        return ResponseEntity.ok("test1111");
    }


    // 게시판 - 검색 목록
    @GetMapping("/notices/search")
    public ResponseEntity getNoticesSearch(@PageableDefault(size = 10, page = 0) Pageable pageable,
                                           @RequestParam("searchType") String searchType,
                                           @RequestParam("keyword") String keyword) {

        Page<Notice> noticeSearchPage = noticeService.pageAllNoticeDist(searchType, keyword, pageable);

        return ResponseEntity.ok(noticeSearchPage);
    }



    // 게시판 - 조회
    @GetMapping("/notice/{id}")
    public ResponseEntity getNotice(@PathVariable("id") Long id) {

        return ResponseEntity.ok("111");
    }


    // 게시판 - 제목 중복 조회
    @GetMapping("/notice/title={title}")
    public ResponseEntity getNoticeTitleEquals(@PathVariable("title") String title) {

        return ResponseEntity.ok("111");
    }


    // 게시판 - 다운로드
    @GetMapping("/notice/file/{id}")
    public ResponseEntity downloadNoticeFileData(@PathVariable("id") Long id) {

        return ResponseEntity.ok("1111");
    }


    // 게시판 - 생성
    @PostMapping("/notice")
    public ResponseEntity addNotice(@Valid Notice notice,
                                        BindingResult bindingResult,
                                        @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01) {

        return ResponseEntity.ok("1111");
    }


    // 게시판 - 수정
    @PutMapping("/notice/{id}")
    public ResponseEntity editNotice(@Valid Notice notice,
                                         BindingResult bindingResult,
                                         @PathVariable("id") Long id,
                                         @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01) {

        return ResponseEntity.ok("111");
    }


    // 게시판 - 삭제 / 단수
    @DeleteMapping("/notice/{id}")
    public ResponseEntity deleteNotice(@PathVariable("id") Long id) {

        return ResponseEntity.ok("111");
    }


    // 게시판 - 삭제 / 복수
    @DeleteMapping("/notice/{idList}")
    public ResponseEntity deleteNoticeAll(@PathVariable("idList") List<Long> idList) {

        return ResponseEntity.ok("111");
    }


}
