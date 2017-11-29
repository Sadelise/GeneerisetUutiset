package com.geneerisetuutiset.services;

import com.geneerisetuutiset.domain.Article;
import com.geneerisetuutiset.domain.Author;
import com.geneerisetuutiset.domain.Category;
import com.geneerisetuutiset.repositories.ArticleRepository;
import com.geneerisetuutiset.repositories.AuthorRepository;
import com.geneerisetuutiset.repositories.CategoryRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
public class NewsEditingService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public void addNew(String title, String ingress, String authorNames, String content, String[] categoryNames, MultipartFile picture) throws IOException {
        Article article = new Article();

        if (picture != null) { // && picture.getContentType().equals("image/jpeg")) {
            article.setPicture(picture.getBytes());
        }
        articleRepository.save(article);

        addAuthorsToArticle(article, authorNames);
        addCategoriesToArticle(article, categoryNames);
        article.setContent(content);
        article.setIngress(ingress);
        article.setPublished(LocalDateTime.now());
        article.setTitle(title);
        articleRepository.save(article);
    }

    private void addAuthorsToArticle(Article article, String authorNames) {
        if (authorNames != null) {
            String replaceNames = authorNames.replace(" ", "");
            String[] names = replaceNames.split(",");
            for (String name : names) {
                Author author = authorRepository.findByName(name);
                if (author == null && name != null) {
                    author = new Author();
                    author.setName(name);
                    author.addArticle(article);
                    authorRepository.save(author);
                }
                article.addAuthor(author);
            }
        }
        articleRepository.save(article);

    }

    private void addCategoriesToArticle(Article article, String[] categoryNames) {
        if (categoryNames != null) {
            for (String name : categoryNames) {
                Category category = categoryRepository.findByName(name);
                if (category == null && name != null && !name.isEmpty()) {
                    System.out.println("CATEGORIA OLI NULL " + category);
                    category = new Category();
                    category.setName(name);
                    categoryRepository.save(category);
                }
                if (category != null) {
                    System.out.println("CATEGORIA OLI " + category);
                    category.addArticle(article);
                    article.addCategory(category);
                }
            }
        }
        articleRepository.save(article);
    }
}
