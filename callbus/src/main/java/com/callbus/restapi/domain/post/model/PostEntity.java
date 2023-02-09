package com.callbus.restapi.domain.post.model;

import com.callbus.restapi.domain.postLike.model.PostLikeEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "CALLBUS_POST")
@DynamicInsert
@DynamicUpdate
public class PostEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "post_id", updatable = false)
    private String post_id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "delete_yn")
    private String delete_yn;

    @Column(name = "create_date", updatable = false)
    private String create_date;

    @Column(name = "update_date")
    private String update_date;

    @Column(name = "delete_date")
    private String delete_date;

}
