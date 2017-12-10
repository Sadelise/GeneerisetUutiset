package com.geneerisetuutiset.controllers;

import com.geneerisetuutiset.domain.Article;
import com.geneerisetuutiset.domain.Category;
import com.geneerisetuutiset.repositories.ArticleRepository;
import com.geneerisetuutiset.repositories.AuthorRepository;
import com.geneerisetuutiset.repositories.CategoryRepository;
import com.geneerisetuutiset.services.NewsEditingService;
import java.io.IOException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NewsController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NewsEditingService newsEditingService;

    @RequestMapping("/")
    public String home(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "published");
        model.addAttribute("news", this.articleRepository.findAll(pageable));
//        model.addAttribute("categories", this.categoryRepository.findAll());
        return "index";
    }

    @GetMapping("/news/{id}")
    public String getArticle(Model model, @PathVariable Long id) {
        Article article = articleRepository.getOne(id);
        article.setTimesRead(article.getTimesRead() + 1);
        model.addAttribute("article", article);
        model.addAttribute("authors", article.getAuthors());
        Pageable pageablePub = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "published");
        model.addAttribute("published", articleRepository.findAll(pageablePub));
        Pageable pageableRead = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "timesRead");
        model.addAttribute("timesRead", articleRepository.findAll(pageableRead));
        return "article";
    }

    @Transactional
    @Secured("ADMIN")
    @DeleteMapping("/news/{id}")
    public String deleteArticle(Model model, @PathVariable Long id) {
        newsEditingService.deleteArticle(id);
        return "redirect:/";
    }

    @Transactional
    @Secured("ADMIN")
    @PostMapping("/news/new")
    public String postArticle(Model model,
            String title, String ingress, String authorNames, String content, String[] categoryNames,
            @Param("file") MultipartFile picture, RedirectAttributes redirectAttributes) throws IOException {
        if (title == null || authorNames == null || content == null
                || title.isEmpty() || authorNames.isEmpty() || content.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Artikkelin lisäys epäonnistui. Artikkelilla täytyy olla ainakin otsikko, yksi kirjoittaja ja sisältöä.");
            return "redirect:/control";
        }
        if (articleRepository.findByTitle(title) != null) {
            redirectAttributes.addFlashAttribute("message", "Artikkelin lisäys epäonnistui. Artikkeli samalla otsikolla on jo olemassa!");
            return "redirect:/control";
        }
        newsEditingService.addNew(title, ingress, authorNames, content, categoryNames, picture);
        return "redirect:/";
    }

    @Secured("ADMIN")
    @GetMapping("/control")
    public String controlPanel(Model model) {
        model.addAttribute("news", this.articleRepository.findAll());
        model.addAttribute("categories", this.categoryRepository.findAll());
        return "control";
    }

    @GetMapping(path = "/pictures/{id}", produces = "image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return articleRepository.getOne(id).getPicture();
    }

    @GetMapping("/news/category/{name}")
    public String getArticlesByCategory(Model model, @PathVariable String name) {
        Category category = categoryRepository.getOneByName(name);
        model.addAttribute("news", category.getArticles());
        model.addAttribute("filteringTitle", name);
        return "filtered";
    }

    @GetMapping("/news/filtered/{filterBy}/{title}")
    public String getArticlesByPublishingTime(Model model, @PathVariable String filterBy, @PathVariable String title) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, filterBy);
        model.addAttribute("news", articleRepository.findAll(pageable));
        model.addAttribute("filteringTitle", title);
        return "filtered";
    }

    @Secured("ADMIN")
    @GetMapping("/edit/{id}")
    public String chooseToEditArticle(Model model, @PathVariable Long id) {
        Article article = articleRepository.getOne(id);
        model.addAttribute("article", article);
        model.addAttribute("authors", article.getAuthorsAsString());
        model.addAttribute("news", this.articleRepository.findAll());
        return "edit";
    }

    @Transactional
    @Secured("ADMIN")
    @PostMapping("/edit/{id}")
    public String editArticle(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes,
            String title, String ingress, String authorNames, String content, String[] categoryNames,
            @Param("file") MultipartFile picture) throws IOException {

        Article article = articleRepository.getOne(id);
        if (title == null || authorNames == null || content == null || article == null
                || title.isEmpty() || authorNames.isEmpty() || content.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Editointi epäonnistui! Artikkelilla täytyy olla ainakin otsikko, yksi kirjoittaja ja sisältöä.");
            return "redirect:/edit/" + id;
        }
        newsEditingService.editArticle(article, title, ingress, authorNames, content, categoryNames, picture);
        redirectAttributes.addFlashAttribute("message", "Editointi onnistui!");

        return "redirect:/control";
    }

}
