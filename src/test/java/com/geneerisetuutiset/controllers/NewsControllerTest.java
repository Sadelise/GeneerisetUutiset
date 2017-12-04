package com.geneerisetuutiset.controllers;

import com.geneerisetuutiset.domain.Article;
import com.geneerisetuutiset.repositories.ArticleRepository;
import com.geneerisetuutiset.repositories.AuthorRepository;
import com.geneerisetuutiset.repositories.CategoryRepository;
import com.geneerisetuutiset.services.NewsEditingService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.transaction.Transactional;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.servlet.FlashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NewsEditingService newsEditingService;
    private String testTitle;
    private String testContent;
    private Article testArticle;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        testTitle = "Uutinen";
        testContent = "Uutisen hienoa sisältöä.";
//        addNewArticle(testTitle, testContent);
//        testArticle = articleRepository.findByTitle(testTitle);
    }

    @Test
    public void statusAndAttributesOkGettingIndex() throws Exception {
//        mockMvc.perform(get("/"))
//                .andExpect(model().attributeExists("news"))
//                .andExpect(status().isOk());
    }
}
//    @Test
//    public void statusAndAttributesOkGettingControlPanel() throws Exception {
//        mockMvc.perform(get("/control"))
//                .andExpect(model().attributeExists("news"))
//                .andExpect(model().attributeExists("categories"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void statusAndAttributesOkGettingCategoryNews() throws Exception {
//        mockMvc.perform(get("/news/category/Vanhat"))
//                .andExpect(model().attributeExists("news"))
//                .andExpect(model().attributeExists("filteringTitle"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void statusAndAttributesOkGettingFilteredNews() throws Exception {
//        mockMvc.perform(get("/news/filtered/timesRead/Uusimmat"))
//                .andExpect(model().attributeExists("news"))
//                .andExpect(model().attributeExists("filteringTitle"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void statusAndAttributesOkGettingEditingPage() throws Exception {
//        mockMvc.perform(get("/edit/" + testArticle.getId()))
//                .andExpect(model().attributeExists("news"))
//                .andExpect(model().attributeExists("article"))
//                .andExpect(model().attributeExists("authors"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void articleIsAdded() throws Exception {
//        addNewArticle("Lisattava", testContent);
//        assertNotNull(articleRepository.findByTitle("Lisattava"));
//    }
//
//    @Test
//    @Transactional
//    public void articleIsNotAddedWithMissingParameters() throws Exception {
//        addNewArticle("UusiUutinen", null);
//        assertNull(articleRepository.findByTitle("UusiUutinen"));
//    }
//
//    @Test
//    @Transactional
//    public void sameArticleIsNotAddedTwice() throws Exception {
//        addNewArticle("DoubleArticle", "content");
//        MvcResult response = addNewArticle("DoubleArticle", "content");
//        String message = (String) response.getFlashMap().get("message");
//        assertEquals(message, "Artikkelin lisäys epäonnistui. Artikkeli samalla otsikolla on jo olemassa!");
//    }
//
//    @Test
//    @Transactional
//    public void statusAndAttributesOkGettingArticle() throws Exception {
//        mockMvc.perform(get("/news/" + testArticle.getId()))
//                .andExpect(model().attributeExists("article"))
//                .andExpect(model().attributeExists("authors"))
//                .andExpect(model().attributeExists("published"))
//                .andExpect(model().attributeExists("timesRead"))
//                .andExpect(status().isOk());
//    }
//
////    @Test
////    @Transactional
////    public void articleIsDeleted() throws Exception {
//////        addNewArticle("Poistettava", testContent);
//////        System.out.println("Tässä ollaan");
//////        Article article = articleRepository.findByTitle("Poistettava");
////        System.out.println("ARTICLE " + testArticle.getId());
////        mockMvc.perform(delete("/news/" + testArticle.getId()))
////                .andExpect(status().is3xxRedirection());
//////        assertEquals(null, article);
////        assertNull(testArticle);
////        addNewArticle(testTitle, testContent);
////    }
//
//    @Test
//    public void editingWorks() throws Exception {
//        FlashMap flashMap = mockMvc.perform(post("/edit/" + testArticle.getId())
//                .param("title", testTitle)
//                .param("authorNames", "Joku muu kirjoittaja")
//                .param("content", testContent))
//                .andExpect(status().is3xxRedirection())
//                .andReturn().getFlashMap();
//        String message = (String) flashMap.get("message");
//        assertEquals("Editointi onnistui!", message);
//    }
//
//    @Test
//    public void editingFailsIfBadInput() throws Exception {
//        FlashMap flashMap = editArticle(testTitle, null).getFlashMap();
//        String message = (String) flashMap.get("message");
//        assertEquals("Editointi epäonnistui! Artikkelilla täytyy olla ainakin otsikko, yksi kirjoittaja ja sisältöä.", message);
//    }
//
//    private MvcResult editArticle(String title, String content) throws Exception {
//        MvcResult result = mockMvc.perform(post("/edit/" + testArticle.getId())
//                .param("title", testTitle)
//                .param("authorNames", "Joku muu kirjoittaja")
//                .param("content", content))
//                .andExpect(status().is3xxRedirection())
//                .andReturn();
//        return result;
//    }
//
//    private MvcResult addNewArticle(String title, String content) throws Exception {
//        String[] categories = new String[1];
//        MvcResult response = mockMvc.perform(post("/news/new")
//                .param("title", title)
//                .param("authorNames", "kirjoittaja")
//                .param("content", content)
//                .param("categoryNames", categories))
//                .andExpect(status().is3xxRedirection())
//                .andReturn();
//        return response;
//    }
//}
