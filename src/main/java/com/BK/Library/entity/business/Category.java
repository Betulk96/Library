package com.BK.Library.entity.business;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Category İsmi Giriniz")
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @NotNull(message = "Builtin durumunu Giriniz.")
    private Boolean builtIn=false;

    //@NotNull kayıt yapıldığında otomatik arıcak
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "your_sequence_generator",
            sequenceName = "your_sequence",
            allocationSize = 10)//başlangıç değeri
    private Integer sequence;//sıra

    @ManyToMany(mappedBy = "category")
    private Set<Book> book;
}
