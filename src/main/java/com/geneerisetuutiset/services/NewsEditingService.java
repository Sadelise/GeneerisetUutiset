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
        editArticle(article, title, ingress, authorNames, content, categoryNames, picture);
    }

    public void addAuthorsToArticle(Article article, String authorNames) {
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

    public void addCategoriesToArticle(Article article, String[] categoryNames) {
        if (categoryNames != null) {
            for (String name : categoryNames) {
                Category category = categoryRepository.findByName(name);
                if (category != null && name != null && !name.isEmpty()) {
                    category.addArticle(article);
                    article.addCategory(category);
                    categoryRepository.save(category);
                }
            }
        }
        articleRepository.save(article);
    }

    public void editArticle(Article article, String title, String ingress, String authorNames, String content, String[] categoryNames, MultipartFile picture) throws IOException {
        if (picture != null) { // && picture.getContentType().equals("image/jpeg")) {
            article.setPicture(picture.getBytes());
        }
        addAuthorsToArticle(article, authorNames);
        addCategoriesToArticle(article, categoryNames);
        article.setContent(content);
        article.setIngress(ingress);
        article.setPublished(LocalDateTime.now());
        article.setTitle(title);
        articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        Article article = articleRepository.getOne(id);
        if (article != null) {
            for (Author author : article.getAuthors()) {
                author.getArticles().remove(article);
                authorRepository.save(author);
            }
            for (Category category : article.getCategories()) {
                category.getArticles().remove(article);
                categoryRepository.save(category);
            }
            articleRepository.delete(article);
        }
    }
}
