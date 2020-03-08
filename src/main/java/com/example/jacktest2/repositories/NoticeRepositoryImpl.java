package com.example.jacktest2.repositories;

import com.example.jacktest2.entities.Notice;
import com.example.jacktest2.entities.QNotice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class NoticeRepositoryImpl extends QuerydslRepositorySupport implements NoticeRepositoryCustom {

    private final QNotice qNotice = QNotice.notice;

    public NoticeRepositoryImpl() {
        super(Notice.class);
    }

    @Override
    public Page<Notice> findAllLike(String keyword, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        JPQLQuery<Notice> query = from(qNotice);

        if (!StringUtils.isEmpty(keyword)) {
            builder.and(
                    qNotice.title.containsIgnoreCase(keyword)
                            .or(qNotice.contents.containsIgnoreCase(keyword))
                            .or(qNotice.writer.containsIgnoreCase(keyword)));
        }

        List<Notice> noticeList = getQuerydsl().applyPagination(pageable, query)
                .where(builder)
                .fetch();
        long totalCount = query.fetchCount();

        return new PageImpl<>(noticeList, pageable, totalCount);
    }
}
