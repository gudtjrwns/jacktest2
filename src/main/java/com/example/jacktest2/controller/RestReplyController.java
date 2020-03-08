package com.example.jacktest2.controller;

import com.example.jacktest2.entities.Reply;
import com.example.jacktest2.services.ReplyService;
import com.example.jacktest2.utility.ToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RestReplyController {

    private final ReplyService replyService;
    private final ToolsUtil toolsUtil;

    public RestReplyController (ReplyService replyService, ToolsUtil toolsUtil) {
        this.replyService = replyService;
        this.toolsUtil = toolsUtil;
    }


    // 댓글 - 목록
    @GetMapping("/{distName}/{distId}/replies")
    public ResponseEntity getReplies(@PageableDefault(size = 10, page = 0) Pageable pageable,
                                     @PathVariable("distName") String distName,
                                     @PathVariable("distId") Long distId) {

        return ResponseEntity.ok("111");
    }


    // 댓글 - 조회
    @GetMapping("/replies/{id}")
    public ResponseEntity getReply(@PathVariable("id") Long id) {

        return ResponseEntity.ok("111");
    }


    // 댓글 - 생성
    @PostMapping("/{distName}/{distId}/replies")
    public ResponseEntity addReply(@Valid Reply reply,
                                   BindingResult bindingResult,
                                   @PathVariable("distName") String distName,
                                   @PathVariable("distId") Long distId) {

        return ResponseEntity.ok("2111");
    }


    // 댓글 - 수정
    @PutMapping("/replies/{id}")
    public ResponseEntity editReply(@Valid Reply reply,
                                    BindingResult bindingResult,
                                    @PathVariable("id") Long id) {

        return ResponseEntity.ok("111");
    }


    // 댓글 - 삭제
    @DeleteMapping("/replies/{id}")
    public ResponseEntity deleteReply(@PathVariable("id") Long id) {

        return ResponseEntity.ok("1111");
    }
}
