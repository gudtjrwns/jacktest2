package com.example.jacktest2.repositories;

import com.example.jacktest2.entities.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeCustomRepository {

    // 전체보기
    Page<Notice> findAllByOrderByCredateDesc(Pageable pageable);

    // 제목 중복 확인
    boolean existsByTitleEquals(String title);
}
