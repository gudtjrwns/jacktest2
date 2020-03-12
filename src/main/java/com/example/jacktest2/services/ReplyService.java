package com.example.jacktest2.services;

import com.example.jacktest2.entities.Reply;
import com.example.jacktest2.repositories.ReplyRepository;
import com.example.jacktest2.utility.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final DateUtil dateUtil;

    public ReplyService (ReplyRepository replyRepository, DateUtil dateUtil) {
        this.replyRepository = replyRepository;
        this.dateUtil = dateUtil;
    }


    // 목록
    public Reply getReplyOneByDistIdEquals(String distName, Long distId) {
        
        if(distName.equals("notice")){
            Reply replyOne = replyRepository.findByNoticeidEquals(distId);
            return replyOne;
        }else{
            return null;
        }
    }


    // 조회
    public Reply getReplyOne(Long replyId){
        Reply replyOne = replyRepository.getOne(replyId);
        return replyOne;
    }


    // 조회 - boolean
    public Optional<Reply> optionalReply(Long replyId) {
        Optional<Reply> byId = replyRepository.findById(replyId);
        return byId;
    }



    // 목록
    public List<Reply> listAllReplyByDistIdEquals(String distName, Long distId) {

        if(distName.equals("notice")){
            List<Reply> replyList = replyRepository.findAllByNoticeidEqualsOrderByCredateAsc(distId);
            return replyList;

        } else {
            return null;
        }
    }


    // 저장
    public Reply saveReply(String distName, Long distId, Reply reply) {

        Reply replyDB = new Reply();

        replyDB.setWriter(reply.getWriter());
        replyDB.setContents(reply.getContents());
        replyDB.setCredate(dateUtil.currentDateInTimestamp());
        replyDB.setModdate(dateUtil.currentDateInTimestamp());

        if(distName.equals("notice")){
            replyDB.setNoticeid(distId);
        }
        
        Reply saveReply = replyRepository.save(replyDB);

        return saveReply;
    }


    // 수정
    public Reply editReply(Long replyId, Reply reply){

        Reply replyOne = replyRepository.getOne(replyId);

        replyOne.setContents(reply.getContents());

        Reply saveReply = replyRepository.save(replyOne);

        return saveReply;
    }


    // 삭제
    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    
    public void deleteAllByDistIdEquals(String distName, Long distId){

        if(distName.equals("notice")){
            List<Reply> replyList = replyRepository.findAllByNoticeidEquals(distId);

            replyList.forEach(i -> replyRepository.deleteById(i.getId()));
        }
    }

}