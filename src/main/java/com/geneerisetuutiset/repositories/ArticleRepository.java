
package com.geneerisetuutiset.repositories;

import com.geneerisetuutiset.domain.Article;
import com.geneerisetuutiset.domain.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Long> {

    public Article findByTitle(String name);

}
