package com.BK.Library.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(length = 30, nullable = false)
    @NotNull(message = "İsim Boş bırakılamaz")
    private String firstName;

    @Column(length = 30, nullable = false)
    @NotNull(message = "Soyisim Boş bırakılamaz")
    private String lastName;

    @NotNull(message = "Score Boş bırakılamaz")
    @Min(value = -2, message = "En az -2 olmalıdır")
    @Max(value = 2, message = "En fazla +2 olmalıdır")
    private Integer score =0;

    @Embedded
    @Column(nullable = true)
    private Address address;

    @NotNull(message = "Telefon Numarası Boş bırakılamaz")
    @Column(unique = true)
    @Size(min = 9, max = 9, message = "Telefon Numaranız 9 karakterli olmalıdır.")
    @Pattern(regexp = "\\d{3} \\d{3} \\d{4}",
            message = "Telefon numarası formatı: XXX XXX XXXX formatında olmalıdır")
    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past(message = "Doğum Günü Gelecek Bir Tarih olamaz")
    private LocalDate birthDate;

    @NotNull(message = "Email Boş bırakılamaz")
    @Column(unique = true)
    @Email(message = "Lütfen Geçerli bir mail giriniz.")
    private String email;

    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Turkey")
    private LocalDateTime createDate=LocalDateTime.now();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Json --> JAVA nesnesine
    @NotNull(message = "Please enter your password")
    @Size(min=6, max = 60, message = "Şifreniz En Az 6 Karakter olmalıdır.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$", message = "Şifreniz en az bir küçük harf,bir büyük harf,bir rakam içermelidir")
    private String password; // hassas veri oldugu icin client tarafina gitmesin-write-only


    private  String resetPasswordCode;//?


    private Boolean builtIn=false;//değiştirilmesini istemediğimiz değişkenler için kullanılır.silinemez silinmesi dahi teklif edilemez

    @ManyToMany(fetch = FetchType.EAGER)//default=lazy
    @JoinTable(name = "user_role",//ilişki book tarafında setlensin
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Role role;
}
