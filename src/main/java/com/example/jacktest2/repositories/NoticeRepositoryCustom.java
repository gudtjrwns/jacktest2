package com.example.jacktest2.repositories;

import com.example.jacktest2.entities.Notice;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeRepositoryCustom {

    List<Notice> findAllLike(String keyword, Pageable pageable);

}
