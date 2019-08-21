package com.woowacourse.zzazanstagram.model.article.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleException;
import com.woowacourse.zzazanstagram.model.article.repository.ArticleRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import com.woowacourse.zzazanstagram.util.S3Uploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);
    private static final String TAG = "[ArticleService]";

    @Value("${cloud.aws.s3.dirName.article}")
    private String dirName;

    private final ArticleRepository articleRepository;
    private final MemberService memberService;
    private final S3Uploader s3Uploader;

    public ArticleService(ArticleRepository articleRepository, MemberService memberService, S3Uploader s3Uploader) {
        this.articleRepository = articleRepository;
        this.memberService = memberService;
        this.s3Uploader = s3Uploader;
    }

    public List<ArticleResponse> getArticleResponses() {
        List<Article> articles = articleRepository.findAllByOrderByIdDesc();
        return articles.stream()
                .map(ArticleAssembler::toDto)
                .collect(Collectors.toList());
    }

    public ArticleResponse getArticleResponse(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleException("해당 게시글이 존재하지 않습니다."));
        return ArticleAssembler.toDto(article);
    }

    public void save(ArticleRequest dto, MultipartFile file, String email) {
        Member author = memberService.findMemberByEmail(email);
        String imageUrl = s3Uploader.upload(file, dirName);

        Article article = ArticleAssembler.toEntity(dto, imageUrl, author);
        articleRepository.save(article);

        log.info("{} imageUrl : {}", TAG, imageUrl);
        log.info("{} create() >> {}", TAG, article);
    }

    //TODO 메서드 중복 제거
    public Article getArticle(long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException("해당 게시글이 존재하지 않습니다."));

    public Article findArticleById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new ArithmeticException("해당 게시글을 찾을 수 없습니다."));
    }
}
