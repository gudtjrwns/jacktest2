package com.example.jacktest2.controller;

import com.example.jacktest2.dao.RestResponse;
import com.example.jacktest2.entities.Reply;
import com.example.jacktest2.exception.ReplyNotFoundException;
import com.example.jacktest2.services.ReplyService;
import com.example.jacktest2.utility.ToolsUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestReplyController {

    private final ReplyService replyService;
    private final ToolsUtil toolsUtil;

    public RestReplyController (ReplyService replyService, ToolsUtil toolsUtil) {
        this.replyService = replyService;
        this.toolsUtil = toolsUtil;
    }


    // 댓글 - 목록
    @GetMapping("/replies/{distName}/{distId}")
    public ResponseEntity<RestResponse> getReplies(@PathVariable("distName") String distName,
                                                   @PathVariable("distId") Long distId) {

        try {
            List<Reply> replyList = replyService.listAllReplyByDistIdEquals(distName, distId);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", replyList);

            return new ResponseEntity(message, HttpStatus.OK);

        } catch (Exception e) {
            throw new ReplyNotFoundException(e);
        }
    }


    // 댓글 - 조회
    @GetMapping("/replies/{id}")
    public ResponseEntity<RestResponse> getReply(@PathVariable("id") Long id) {

        try {
            Reply replyOne = replyService.getReplyOne(id);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", replyOne);

            return new ResponseEntity(message, HttpStatus.OK);

        } catch (Exception e) {
            throw new ReplyNotFoundException(e);
        }
    }


    // 댓글 - 생성
    @PostMapping("/replies/{distName}/{distId}")
    public ResponseEntity<RestResponse> addReply(@Valid Reply reply,
                                                 BindingResult bindingResult,
                                                 @PathVariable("distName") String distName,
                                                 @PathVariable("distId") Long distId) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);

        } else {
            Reply saveReply = replyService.saveReply(distName, distId, reply);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", saveReply);

            return new ResponseEntity(message, HttpStatus.OK);
        }
    }


    // 댓글 - 수정
    @PutMapping("/replies/{id}")
    public ResponseEntity<RestResponse> editReply(@Valid Reply reply,
                                                  BindingResult bindingResult,
                                                  @PathVariable("id") Long id) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);

        } else {
            Reply saveReply = replyService.editReply(id, reply);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", saveReply);

            return new ResponseEntity(message, HttpStatus.OK);
        }
    }


    // 댓글 - 삭제
    @DeleteMapping("/replies/{id}")
    public ResponseEntity<RestResponse> deleteReply(@PathVariable("id") Long id) {

        try {
            replyService.deleteReply(id);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success");

            return new ResponseEntity(message, HttpStatus.OK);

        } catch (Exception e) {
            throw new ReplyNotFoundException(e);
        }
    }
}
