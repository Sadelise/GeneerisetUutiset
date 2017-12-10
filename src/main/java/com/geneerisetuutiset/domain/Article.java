package com.geneerisetuutiset.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Article extends AbstractPersistable<Long> {

    @Id
    private Long id;
    @Size(min = 5, max = 50)
    private String title;
    @Size(min = 10, max = 100)
    private String ingress;
    @Lob
    private byte[] picture;
    @Size(min = 10, max = 1000)
    private String content;
    private LocalDateTime published;
    @Size(min = 1, max = 10)
    @JsonIgnore
    @Basic(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Author> authors;
    @Size(min = 1, max = 10)
    @JsonIgnore
    @Basic
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categories;
    private int timesRead;

    public void addAuthor(Author author) {
        if (authors == null) {
            authors = new ArrayList<>();
        }
        if (!authors.contains(author)) {
            authors.add(author);
        }
    }

    public void addCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public Object getAuthorsAsString() {
        String names = "";
        if (this.authors != null) {
            for (Author author : authors) {
                if (names.length() > 0) {
                    names += ", ";
                }
                names += author.getName();
            }
        }
        return names;
    }
}
