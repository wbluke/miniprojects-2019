package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.comment.dto.CommentRequest;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;
import com.woowacourse.zzazanstagram.model.comment.service.CommentService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments/new")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest, MemberSession memberSession) {
        CommentResponse commentResponse = commentService.save(commentRequest, memberSession.getEmail());
        return ResponseEntity.ok(commentResponse);
    }
}
