package com.BK.Library.entity.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Kitap İsmi Boş Bırakılamaz!!")
    @Size(min = 2, max = 50, message = "Kitap ismi :'${validatedValue} ,En az {min} en çok {max} karakter olmalıdır.")
    private String bookName;


    @NotNull
    @Column(length = 17, unique = true)
    @Pattern(regexp = "\\d{3} \\d{2} \\d{5} \\d{2} \\d",
            message = "ISBN :' 999 99 99999 99 9' formatında olmalıdır.")
    private String isbn;

    @Column(nullable = true)//default
    private Integer pageCount;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")//oluşacak FK adı
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)//default=lazy
    @JoinTable(name = "book_publisher",//ilişki book tarafında setlensin
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
    private Set<Publisher> publisher;

    @Min(value = 1950, message = "1950 tarihinden sonra bir tarih giriniz.")
    private int publishDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_category",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> category;

    private File image;

    @NotNull(message = "Ödünç Verilebilir Durumunu Belirtmeniz Gerekir.")
    private Boolean loanable = true;//ödünç verilebilir-default

    @NotNull(message = "Raf Kodu Girmelisiniz.")
    @Column(length = 6)
    @Pattern(regexp = "^[A-Z]{2} \\d{3}$", message = "Geçersiz format. Örnek: AA 999")
    private String shelfCode;//rafKodu

    @NotNull(message = "active kısmı boş bırakılamaz")
    private Boolean active = true;//tabloya eklendiği anda var kabul edilecek

    @NotNull(message = "Boş bırakılamaz")
    private Boolean featured = false;//öne çıkan


    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "Turkey")//17/02/2024 14:30
    private LocalDateTime createDate = LocalDateTime.now();

    @NotNull(message = "Builtin durumunu Giriniz.")
    private Boolean builtIn = false;


}
