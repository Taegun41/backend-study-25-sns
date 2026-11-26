package com.apptivedev.sns.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")                              // user는 DB 예약어라 테이블 이름을 users로 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 400)      //암호화를 대비해서 넉넉하게
    private String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void changeName(String name){
        this.name = name;
    }
}
