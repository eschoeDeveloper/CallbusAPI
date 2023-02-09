package com.callbus.restapi.domain.postLike.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CALLBUS_POST_LIKE")
@DynamicInsert
public class PostLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "post_id")
    @JoinColumn(name = "post_id")
    private String post_id;

    @Column(name = "account_id")
    private String account_id;

    @Column(name = "like_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date like_date;

}
