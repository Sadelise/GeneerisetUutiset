package com.geneerisetuutiset.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
    private byte[] picture;
    private String content;
    private LocalDate published;
    @ManyToMany(mappedBy = "pieceOfNewss")
    private List<Author> authors;
    @ManyToMany(mappedBy = "pieceOfNewss")
    private List<Category> categories;

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
}
