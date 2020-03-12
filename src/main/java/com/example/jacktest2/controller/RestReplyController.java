package com.example.jacktest2.controller;

import com.example.jacktest2.dao.RestResponse;
import com.example.jacktest2.entities.Reply;
import com.example.jacktest2.exception.EntityNotFoundException;
import com.example.jacktest2.exception.ReplyNotFoundException;
import com.example.jacktest2.services.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class RestReplyController {

    private final ReplyService replyService;

    public RestReplyController (ReplyService replyService) {
        this.replyService = replyService;
    }


    // 댓글 - 목록
    @GetMapping("/replies/{distName}/{distId}")
    public ResponseEntity<RestResponse> getReplies(@PathVariable("distName") String distName,
                                                   @PathVariable("distId") Long distId) {

        List<Reply> replyList = replyService.listAllReplyByDistIdEquals(distName, distId);
        RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", replyList);

        return new ResponseEntity(message, HttpStatus.OK);
    }


    // 댓글 - 조회
    @GetMapping("/replies/{id}")
    public ResponseEntity<RestResponse> getReply(@PathVariable("id") Long id) {

        Optional<Reply> byId = replyService.optionalReply(id);

        if (byId.isPresent()) {
            Reply replyOne = replyService.getReplyOne(id);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", replyOne);

            return new ResponseEntity(message, HttpStatus.OK);

        } else {
            throw new ReplyNotFoundException("댓글 정보를 확인할 수 없습니다.");
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
            Optional<Reply> byId = replyService.optionalReply(id);

            if (byId.isPresent()) {
                Reply saveReply = replyService.editReply(id, reply);
                RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success", saveReply);

                return new ResponseEntity(message, HttpStatus.OK);

            } else {
                throw new ReplyNotFoundException("댓글 정보를 확인할 수 없습니다.");
            }
        }
    }


    // 댓글 - 삭제
    @DeleteMapping("/replies/{id}")
    public ResponseEntity<RestResponse> deleteReply(@PathVariable("id") Long id) {

        Optional<Reply> byId = replyService.optionalReply(id);

        if (byId.isPresent()) {
            replyService.deleteReply(id);
            RestResponse message = new RestResponse(HttpStatus.OK.value(), "Success");

            return new ResponseEntity(message, HttpStatus.OK);

        } else {
            throw new ReplyNotFoundException("댓글 정보를 확인할 수 없습니다.");
        }
    }
}
