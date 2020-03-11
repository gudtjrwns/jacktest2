package com.example.jacktest2.repositories;

import com.example.jacktest2.entities.Notice;
import com.example.jacktest2.entities.QNotice;
import com.example.jacktest2.entities.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

public class NoticeRepositoryImpl extends QuerydslRepositorySupport implements NoticeCustomRepository {

    private final QNotice qNotice = QNotice.notice;
    private final QReply qReply = QReply.reply;

    public NoticeRepositoryImpl() {
        super(Notice.class);
    }

    @Override
    public Page<Notice> findAllLike(String keyword, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        JPQLQuery<Notice> query = from(qNotice);

        builder.and(eqTitle(keyword)
                .or(eqContents(keyword)
                        .or(eqWriter(keyword))));

        List<Notice> noticeList = query.where(builder).fetch();

        long totalCount = query.fetchCount();

        return new PageImpl<>(noticeList, pageable, totalCount);
    }


    private BooleanExpression eqTitle(String title) {
        if(title == null) return null;
        return qNotice.title.containsIgnoreCase(title);
    }


    private BooleanExpression eqContents(String contents) {
        if(contents == null) return null;
        return qNotice.contents.containsIgnoreCase(contents);
    }

    private BooleanExpression eqWriter(String writer) {
        if(writer == null) return null;
        return qNotice.writer.containsIgnoreCase(writer);
    }
}
