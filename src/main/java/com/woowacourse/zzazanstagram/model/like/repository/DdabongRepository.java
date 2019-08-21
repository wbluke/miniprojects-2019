package com.woowacourse.zzazanstagram.model.like.repository;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.like.domain.Ddabong;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DdabongRepository extends JpaRepository<Ddabong, Long> {
    Optional<Ddabong> findByArticleAndMember(Article article, Member member);
}