package com.BK.Library.entity.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Bir Role İsmi Belirlemelisiniz.")
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private RoleName roleName;
}
