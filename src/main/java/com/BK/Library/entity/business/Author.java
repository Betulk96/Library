package com.BK.Library.entity.business;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Yazar ismi Girmelisiniz")
    @Size(min = 4, max = 70, message = "Yazar ismi :'${validatedValue} ,En az {min} en çok {max} karakter olmalıdır.")
    private String authorName;

    @NotNull(message = "Builtin Durumunu belirtmelisiniz")
    private Boolean builtIn=false;
    //Bir Yazarın Birden Fazla Kitabı Olabilir
    @OneToMany(mappedBy = "author")
    private Set<Book> books;
}
