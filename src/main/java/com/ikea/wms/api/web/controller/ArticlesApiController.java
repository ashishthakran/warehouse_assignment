package com.ikea.wms.api.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.service.IArticleService;
import com.ikea.wms.api.web.exceptions.ApiException;
import com.ikea.wms.api.web.mapper.ArticleMapper;
import com.ikea.wms.api.web.model.ApiResponse;
import com.ikea.wms.api.web.model.article.Article;
import com.ikea.wms.api.web.model.article.InventoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticlesApiController {

    private static final String MSG_INVENTORY_UPLOADED = "Articles uploaded successfully.";
    private static final String MSG_ARTICLE_CREATED = "Article created successfully.";

    private static ObjectMapper MAPPER = new ObjectMapper();

    private final IArticleService articleService;

    @PostMapping(value = "/upload", produces = APPLICATION_JSON_VALUE, consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> uploadInventory(@RequestParam("file") MultipartFile file) {
        try {
            InventoryRequest inventoryRequest = MAPPER.readValue(file.getBytes(), InventoryRequest.class);
            List<ArticleDto> articleDtoList =inventoryRequest.getArticles().stream()
                    .map(ArticleMapper::mapToDto)
                    .collect(Collectors.collectingAndThen(Collectors.toMap(ArticleDto::getId, Function.identity(), (left, right) -> {
                        return left.toBuilder().stock(left.getStock().add(right.getStock())).build();
                    }), result -> Lists.newArrayList(result.values())));
            articleService.saveAll(articleDtoList);
        } catch (IOException ex) {
            throw new ApiException(String.format("Unable to read data from ", file.getName()));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                        .status(true)
                        .message(MSG_INVENTORY_UPLOADED)
                        .build());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createArticle(@Valid @RequestBody Article article) {
        articleService.save(ArticleMapper.mapToDto(article));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                        .status(true)
                        .message(MSG_ARTICLE_CREATED)
                        .build());
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<Article>>> listArticles() {
        List<Article> articleList = articleService.getAll().stream()
                .map(ArticleMapper::mapToModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(ApiResponse.<List<Article>>builder()
                        .data(articleList)
                        .status(true)
                        .build());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Article>> getArticle(@PathVariable("id") BigInteger id) {
        Article article = ArticleMapper.mapToModel(articleService.get(id));
        return ResponseEntity.ok()
                .body(ApiResponse.<Article>builder()
                        .data(article)
                        .status(true)
                        .build());
    }
}
