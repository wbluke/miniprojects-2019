package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;

class CommentControllerTest extends RequestTemplate {
    @Test
    void name() {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentContents("내용");
        commentRequest.setArticleId(1L);

        postHeaderWithLogin("/comments/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(commentRequest), CommentRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommentRequest.class)
                .consumeWith(res ->
                        assertThat(res.getResponseBody().getCommentContents()).isEqualTo(commentRequest.getCommentContents()));

    }


    // TODO 아직~
    private void getNewArticleId() {
        postHeaderWithLogin("/articles")
                .body(BodyInserters.fromFormData("image", IMAGE_URL)
                        .with("contents", CONTENTS)
                        .with("hashTag", HASHTAG))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectHeader().valueMatches("Location", "http://[\\w\\d\\.]+:[0-9]+/");
    }
}