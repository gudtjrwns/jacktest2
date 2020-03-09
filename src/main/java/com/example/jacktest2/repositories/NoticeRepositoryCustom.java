package com.example.jacktest2.repositories;

import com.example.jacktest2.entities.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<Notice> findAllLike(String searchType, String keyword, Pageable pageable);

}
