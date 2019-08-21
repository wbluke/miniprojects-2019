package com.woowacourse.zzazanstagram.model.follow.controller;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import com.woowacourse.zzazanstagram.model.support.WebTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FollowControllerTest extends RequestTemplate {

    @BeforeEach
    void setUpFollowController() {
        saveOtherMember("2ndMember", "test2@gmail.com");
        saveOtherMember("3rdMember", "test3@gmail.com");
        saveOtherMember("4thMember", "test4@gmail.com");
        saveOtherMember("5thMember", "test5@gmail.com");
        saveOtherMember("6thMember", "test6@gmail.com");
    }

    @Test
    void 팔로워_구하기() {
        postHeaderWithLogin("/follow")
                .body(WebTestHelper.followForm(2L, 1L))
                .exchange()
                .expectStatus().isOk();

        List responseBody = getHeaderWithLogin("/follow/follower/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody.size()).isEqualTo(1);
    }

    @Test
    void 팔로잉_구하기() {
        postHeaderWithLogin("/follow")
                .body(WebTestHelper.followForm(3L, 4L))
                .exchange()
                .expectStatus().isOk();

        postHeaderWithLogin("/follow")
                .body(WebTestHelper.followForm(3L, 2L))
                .exchange()
                .expectStatus().isOk();

        List responseBody = getHeaderWithLogin("/follow/following/3")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody.size()).isEqualTo(2);
    }

    @Test
    void 언팔로우() {
        postHeaderWithLogin("/follow")
                .body(WebTestHelper.followForm(5L, 6L))
                .exchange()
                .expectStatus().isOk();

        postHeaderWithLogin("/follow")
                .body(WebTestHelper.followForm(5L, 6L))
                .exchange()
                .expectStatus().isOk();

        List responseBody = getHeaderWithLogin("/follow/follower/6")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody).hasSize(0);
    }
}
