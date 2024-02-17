package com.BK.Library.entity;

import com.BK.Library.entity.business.Book;
import com.BK.Library.entity.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mongodb.lang.Nullable;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    //Bir Kullanıcı birden Fazla ödünç alma işlemi yapabilir
    //ama bir ödünç alma işlemi sadece bir kullanıcıya aittir
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    //Bir borş içinde birden fazla kitap olabilir
    @OneToMany
    private Set<Book> books;


    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "Turkey")//17/02/2024 14:30
    private LocalDateTime loanDate;//borç alma Tarihi
    @PrePersist
    protected void onCreate() {
        loanDate = LocalDateTime.now();
    }

    @Setter(AccessLevel.NONE)
    @NotNull(message="Kitabın Geri Getirme tarihini Belirtmelisiniz")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "Turkey")//17/02/2024 14:30
    private LocalDateTime expireDate;//Geri Getirmesi Gereken Tarih
    @PrePersist
    public void prePersist() {
        this.calculateExpireDate(); // Otomatik olarak son teslim tarihini hesapla
    }

    @Column(nullable = true)
    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "Turkey")//17/02/2024 14:30
    private LocalDateTime returnDate;//Geri Getirdiği Tarih

    private String note;


    public LocalDateTime calculateExpireDate() {
        int numberOfBooks = this.books.size(); // Loan sınıfındaki books alanının boyutu alınıyor
        LocalDateTime currentDate = this.loanDate;
        LocalDateTime calculatedExpireDate;

        // Ödünç alınan kitap sayısına göre son teslim tarihini belirle
        if (numberOfBooks >= 5) {
            calculatedExpireDate = currentDate.plusDays(20);
        } else if (numberOfBooks == 4) {
            calculatedExpireDate = currentDate.plusDays(15);
        } else if (numberOfBooks == 3) {
            calculatedExpireDate = currentDate.plusDays(10);
        } else if (numberOfBooks == 2) {
            calculatedExpireDate = currentDate.plusDays(6);
        } else if (numberOfBooks == 1) {
            calculatedExpireDate = currentDate.plusDays(3);
        } else {
            calculatedExpireDate = null; // Varsayılan olarak null atanıyor, bu durumu kontrol edebilirsiniz.
        }

        // Son teslim tarihini ayarla
        this.expireDate = calculatedExpireDate;

        return calculatedExpireDate;
    }
}

