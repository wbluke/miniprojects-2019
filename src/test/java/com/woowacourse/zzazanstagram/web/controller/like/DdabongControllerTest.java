package com.woowacourse.zzazanstagram.web.controller.like;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

class DdabongControllerTest extends RequestTemplate {
    private static final Logger log = getLogger(DdabongControllerTest.class);

    @BeforeEach
    void setUp2() {
        createArticle();
    }

    @Test
    void 좋아요_누르기() {
        String count = getDdabongCount();

        assertThat(count).isEqualTo("1");
    }

    @Test
    void 좋아요_취소() {
        String beforeCount = getDdabongCount();
        String afterCount = getDdabongCount();

        assertThat(beforeCount).isNotEqualTo(afterCount);
        assertThat(afterCount).isEqualTo("0");
    }

    private String getDdabongCount() {
        return postHeaderWithLogin("/articles/" + "1" + "/ddabongs")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

    private WebTestClient.ResponseSpec createArticle() {
        return postHeaderWithLogin("/articles")
                .body(BodyInserters.fromFormData("image", IMAGE_URL)
                        .with("contents", CONTENTS)
                        .with("hashTag", HASHTAG))
                .exchange();
    }
}