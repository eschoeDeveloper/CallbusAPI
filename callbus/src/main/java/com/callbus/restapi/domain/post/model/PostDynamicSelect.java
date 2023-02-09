package com.callbus.restapi.domain.post.model;

public interface PostDynamicSelect {
    Integer getId();
    String getPost_id();
    String getTitle();
    String getContents();
    String getDelete_yn();
    String getCreate_date();
    String getUpdate_date();
    String getDelete_date();
    String getLike_yn();
    Integer getLike_count();
}
