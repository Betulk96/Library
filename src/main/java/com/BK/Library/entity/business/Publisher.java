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
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Yayınevi ismi Girmelisiniz")
    @Size(min = 4, max = 70, message = "Yayınevi ismi :'${validatedValue} ,En az {min} en çok {max} karakter olmalıdır.")
    private String publisherName;

    @NotNull(message = "Builtin Durumunu belirtmelisiniz")
    private Boolean builtIn=false;
    //yayıncılarda birden fazla kitap vardır.aynı zamanda bir kitap farklı yayınevleri tarafından da basılmış oalbilir
    @ManyToMany(mappedBy ="publisher" ,cascade = CascadeType.DETACH)//yayıncı silindiğinde onunla ilişkisi olanlar ile ayrılır.
    private Set<Book> books;
}
