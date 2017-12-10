/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geneerisetuutiset.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Category extends AbstractPersistable<Long> {

    @JsonIgnore
    @Basic(fetch = FetchType.LAZY)
    @ManyToMany
    private List<Article> articles;
    @Id
    private Long id;
    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;

    public void addArticle(Article article) {
        if (articles == null) {
            articles = new ArrayList<>();
        }
        if (!articles.contains(article)) {
            articles.add(article);
        }
    }
}
