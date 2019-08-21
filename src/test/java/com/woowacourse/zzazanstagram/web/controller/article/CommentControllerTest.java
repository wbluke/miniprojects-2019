package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.CONTENTS;
import static org.assertj.core.api.Assertions.assertThat;

class CommentControllerTest extends RequestTemplate {
    @Test
    void 댓글_작성_테스트() {
        CommentContents commentContents = CommentContents.of(CONTENTS);

        postHeaderWithLogin("/" + 1L + "/comments/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(commentContents), CommentContents.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommentResponse.class)
                .consumeWith(res -> assertThat(res.getResponseBody().getCommentContents()).isEqualTo(CONTENTS));
    }
}