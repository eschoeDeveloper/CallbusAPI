package com.callbus.restapi.domain.post.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private String delete_yn = "N";

    @Column(name = "create_date", updatable = false)
    private String create_date;

    @Column(name = "update_date")
    private String update_date;

    @Column(name = "delete_date")
    private String delete_date;

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", post_id='" + post_id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", delete_yn='" + delete_yn + '\'' +
                ", create_date='" + create_date + '\'' +
                ", update_date='" + update_date + '\'' +
                ", delete_date='" + delete_date + '\'' +
                '}';
    }
}
