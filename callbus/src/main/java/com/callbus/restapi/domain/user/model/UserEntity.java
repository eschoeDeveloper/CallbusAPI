package com.callbus.restapi.domain.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "CALLBUS_USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "account_type")
    private String account_type;

    @Column(name = "account_id")
    private String account_id;

    @Column(name = "quit")
    private String quit;

}
