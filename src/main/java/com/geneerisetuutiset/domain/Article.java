package com.geneerisetuutiset.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
    private String title;
    private String ingress;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;
    private String content;
    private LocalDateTime published;
    @ManyToMany(mappedBy = "articles")
    private List<Author> authors;
    @ManyToMany(mappedBy = "articles")
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
