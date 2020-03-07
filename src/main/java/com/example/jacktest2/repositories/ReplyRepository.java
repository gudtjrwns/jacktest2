package com.example.jacktest2.repositories;

import com.example.jacktest2.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 목록 - 게시판 / 오름차순 정렬
    List<Reply> findAllByNoticeidEqualsOrderByCredateAsc(Long distId);

    // 목록 - 게시판
    List<Reply> findAllByNoticeidEquals(Long distId);

    // 정보 찾기 - 게시판
    Reply findByNoticeidEquals(Long noticeId);
}