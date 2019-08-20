package com.woowacourse.zzazanstagram.model.comment.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleException;
import com.woowacourse.zzazanstagram.model.article.repository.ArticleRepository;
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
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;

    public CommentResponse save(CommentRequest commentRequest, String email) {
        CommentContents commentContents = CommentContents.of(commentRequest.getCommentContents());
        Article article = articleRepository.findById(commentRequest.getArticleId()).orElseThrow(() -> new ArticleException("게시글을 찾을 수 없습니다."));
        Member member = memberService.findMemberByEmail(email);

        Comment comment = new Comment(commentContents, article, member);
        Comment saveComment = commentRepository.save(comment);

        return CommentAssembler.toDto(saveComment);
    }
//    @Autowired
//    MemberService memberService;
//
//    @Autowired
//    ArticleService articleService;
//
//    @Autowired
//    CommentRepository commentRepository;
//
//    public CommentResponse save(CommentRequest commentRequest, String email) {
//        CommentContents commentContents = CommentContents.of(commentRequest.getCommentContents());
//        ArticleResponse articleResponse = articleService.getArticleResponse(commentRequest.getArticleId());
//        Member member = memberService.findMemberByEmail(email);
//
//        Comment comment = new Comment(commentContents, article, member);
//        Comment saveComment = commentRepository.save(comment);
//
//        return CommentAssembler.toDto(saveComment);
//    }
}
