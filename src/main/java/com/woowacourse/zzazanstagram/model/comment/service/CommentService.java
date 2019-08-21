package com.woowacourse.zzazanstagram.model.comment.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.comment.domain.Comment;
import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;
import com.woowacourse.zzazanstagram.model.comment.repository.CommentRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    MemberService memberService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentRepository commentRepository;

    public CommentResponse save(CommentContents commentContents, long articleId, String email) {
        Member member = memberService.findMemberByEmail(email);
        Article article = articleService.getArticle(articleId); // TODO 맘에 안 듦

        Comment comment = new Comment(commentContents, article, member);
        Comment saveComment = commentRepository.save(comment);

        return CommentAssembler.toDto(saveComment);
    }
}
