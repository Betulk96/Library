package com.BK.Library.entity.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable//Adres classını ayrı bir tablo yapmak yerine embeddable yapıp ,comminication classı içine yerleştirdik.
public class Address {//Kullanıcı adresi girmek istediğince ülke ,il,ilçe ,mahalle,sokak,bina no bilgilerini girmek zorunda

    @Column(nullable = false, length = 25)
    private String country;
    @Column(nullable = false, length = 25)
    private String province;//il
    @Column(nullable = false, length = 25)
    private String city;//ilçe
    @Column(nullable = false, length = 25)
    private String district;//mahalle
    @Column(nullable = false, length = 25)
    private String street;
    @Column(nullable = false, length = 25)
    private Integer houseNo;

    private Integer doorNo;
}

