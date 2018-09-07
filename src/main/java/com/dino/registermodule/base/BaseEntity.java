package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable{
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String createId;
    @Getter
    @Setter
    private String updateId;
    @Getter
    @Setter
    private Date createTime;
    @Getter
    @Setter
    private Date updateTime;
    @Getter
    @Setter
    private String businessBelong;
    @Getter
    @Setter
    private String version;
}
