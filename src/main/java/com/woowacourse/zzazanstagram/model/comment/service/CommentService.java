package com.woowacourse.zzazanstagram.model.comment.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.service.ArticleAssembler;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.comment.domain.Comment;
import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentRequest;
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

    public CommentResponse save(CommentRequest commentRequest, String email) {
        CommentContents commentContents = CommentContents.of(commentRequest.getCommentContents());
        ArticleResponse articleResponse = articleService.getArticleResponse(commentRequest.getArticleId());
        Member member = memberService.findMemberByEmail(email);
        Article article = ArticleAssembler.toEntity(articleResponse, member);

        Comment comment = new Comment(commentContents, article, member);
        Comment saveComment = commentRepository.save(comment);

        return CommentAssembler.toDto(saveComment);
    }
}
