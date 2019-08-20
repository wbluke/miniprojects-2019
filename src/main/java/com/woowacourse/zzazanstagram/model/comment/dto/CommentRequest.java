package com.woowacourse.zzazanstagram.model.comment.dto;

public class CommentRequest {
    private String commentContents;
    private Long articleId;

    public String getCommentContents() {
        return commentContents;
    }

    public void setCommentContents(String commentContents) {
        this.commentContents = commentContents;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
