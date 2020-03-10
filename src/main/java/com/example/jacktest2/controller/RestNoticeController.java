package com.example.jacktest2.controller;

import com.example.jacktest2.dao.NoticeValue;
import com.example.jacktest2.dao.RestResponse;
import com.example.jacktest2.entities.Notice;
import com.example.jacktest2.exception.NoticeNotFoundException;
import com.example.jacktest2.exception.NoticeTitleAlreadyExistException;
import com.example.jacktest2.services.NoticeService;
import com.example.jacktest2.utility.ToolsUtil;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
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
    public ResponseEntity<RestResponse> getNotices(@PageableDefault(size = 10, page = 0) Pageable pageable) {

        try {
            Page<Notice> noticePage = noticeService.pageAllNotice(pageable);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", noticePage);

            return new ResponseEntity(message, HttpStatus.OK);

        } catch (NoticeNotFoundException e) {
            throw new NoticeNotFoundException("게시글 정보를 확인할 수 없습니다.", e);
        }
    }


    // 게시판 - 검색 목록
    @GetMapping("/notices/search")
    public ResponseEntity<RestResponse> getNoticesSearch(@PageableDefault(size = 10, page = 0) Pageable pageable,
                                           @RequestParam("keyword") String keyword) {

        try {
            Page<Notice> noticePage = noticeService.pageAllNoticeDist(keyword, pageable);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", noticePage);

            return new ResponseEntity(message, HttpStatus.OK);

        } catch (NoticeNotFoundException e) {
            throw new NoticeNotFoundException("게시글 정보를 확인할 수 없습니다.", e);
        }
    }



    // 게시판 - 조회
    @GetMapping("/notice/{id}")
    public ResponseEntity<RestResponse> getNotice(@PathVariable("id") Long id) {

        try {
            NoticeValue noticeOne = noticeService.getNoticeValue(id);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", noticeOne);

            return new ResponseEntity(message, HttpStatus.OK);

        } catch (NoticeNotFoundException e) {
            throw new NoticeNotFoundException("게시글 정보를 확인할 수 없습니다.", e);
        }
    }


    // 게시판 - 제목 중복 조회
    @GetMapping("/notice/title")
    public ResponseEntity<RestResponse> getNoticeTitleEquals(@RequestParam("title") String title) {

        boolean isExistsTitle = noticeService.lookingForTitleEquals(title);

        if (isExistsTitle) {
            throw new NoticeTitleAlreadyExistException();

        } else {
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success");

            return new ResponseEntity(message, HttpStatus.OK);
        }
    }


    // 게시판 - 다운로드
    @GetMapping("/notice/file/{id}")
    public ResponseEntity<RestResponse> downloadNoticeFileData(@PathVariable("id") Long id) throws IOException {

        try {
            ResponseEntity<InputStreamResource> download = noticeService.downloadNoticeFile(id);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", download);

            return new ResponseEntity(message, HttpStatus.OK);

        } catch (NoticeNotFoundException e) {
            throw new NoticeNotFoundException("게시글 정보를 확인할 수 없습니다.", e);
        }
    }


    // 게시판 - 생성
    @PostMapping("/notice")
    public ResponseEntity<RestResponse> addNotice(@Valid Notice notice,
                                        BindingResult bindingResult,
                                        @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01) throws IOException, BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);

        } else {
            Notice saveNotice = noticeService.saveNotice(notice, file01);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", saveNotice);

            return new ResponseEntity(message, HttpStatus.OK);
        }
    }


    // 게시판 - 수정
    @PutMapping("/notice/{id}")
    public ResponseEntity<RestResponse> editNotice(@Valid Notice notice,
                                         BindingResult bindingResult,
                                         @PathVariable("id") Long id,
                                         @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01) throws IOException, BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);

        } else {
            try {
                Notice saveNotice = noticeService.editNotice(notice, id, file01);
                RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", saveNotice);

                return new ResponseEntity(message, HttpStatus.OK);

            } catch (NoticeNotFoundException e) {
                throw new NoticeNotFoundException("게시글 정보를 확인할 수 없습니다.", e);
            }
        }
    }


    // 게시판 - 삭제 / 단수
    @DeleteMapping("/notice/{id}")
    public ResponseEntity<RestResponse> deleteNotice(@PathVariable("id") Long id) {

        try {
            noticeService.deleteNoticeOne(id);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success");

            return new ResponseEntity(message, HttpStatus.OK);

        } catch (NoticeNotFoundException e) {
            throw new NoticeNotFoundException(e.getMessage(), e);
        }
    }


    // 게시판 - 삭제 / 복수
    @DeleteMapping("/notice/{idList}")
    public ResponseEntity<RestResponse> deleteNoticeAll(@PathVariable("idList") List<Long> idList) {

        try {
            noticeService.deleteAllNotice(idList);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success");

            return new ResponseEntity(message, HttpStatus.OK);

        } catch (NoticeNotFoundException e) {
            throw new NoticeNotFoundException("게시글 정보를 확인할 수 없습니다.", e);
        }
    }


}
