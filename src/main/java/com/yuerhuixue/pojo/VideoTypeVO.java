package com.yuerhuixue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoTypeVO {

    //视频类型
    private Integer typeId;
    private String typeName;
    private String typeImg;
    private String typeDesc;
    private String typeLevel;
    private Date createTime;
    private Date updateTime;

    //视频
    private List<Video> videoList;

}
