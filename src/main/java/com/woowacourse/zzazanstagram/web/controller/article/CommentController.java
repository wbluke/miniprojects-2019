package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;
import com.woowacourse.zzazanstagram.model.comment.exception.CommentException;
import com.woowacourse.zzazanstagram.model.comment.service.CommentService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{articleId}/comments/new")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long articleId, @Valid @RequestBody CommentContents commentContents,
                                                         MemberSession memberSession, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CommentException("댓글은 1자 이상, 100자 이하여야 합니다.");
        }
        CommentResponse commentResponse = commentService.save(commentContents, articleId, memberSession.getEmail());
        return ResponseEntity.ok(commentResponse);
    }

    @ExceptionHandler(CommentException.class)
    private ResponseEntity<String> CommentExceptionHandler(CommentException commentException) {
        return ResponseEntity.badRequest().body(commentException.getMessage());
    }
}
