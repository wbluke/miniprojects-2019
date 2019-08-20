package com.woowacourse.zzazanstagram.model.comment.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommentContents {

    @Column(name = "contents", length = 100)
    private String contents;

    private CommentContents() {
    }

    private CommentContents(final String contents) {
        this.contents = contents;
    }

    public static CommentContents of(String contents) {
        return new CommentContents(contents);
    }

    public String getContents() {
        return contents;
    }
}