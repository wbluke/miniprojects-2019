package com.woowacourse.zzazanstagram.web.controller.like;

import com.woowacourse.zzazanstagram.model.like.service.DdabongService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DdabongController {
    private final DdabongService ddabongService;

    public DdabongController(DdabongService ddabongService) {
        this.ddabongService = ddabongService;
    }

    // todo method naming
    @PostMapping("/articles/{articleId}/ddabongs")
    public ResponseEntity<String> clickDdabong(@PathVariable Long articleId, MemberSession memberSession) {
        String ddabongCount = ddabongService.saveOrRemove(articleId, memberSession.getEmail());
        return new ResponseEntity<>(ddabongCount, HttpStatus.OK);
    }
}
