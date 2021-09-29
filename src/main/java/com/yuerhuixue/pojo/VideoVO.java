package com.yuerhuixue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoVO {

    //视频
    private Integer videoId;
    private String videoTitle;
    private String videoImg;
    private String videoDesc;
    private Integer userId;
    private String videoUrl;
    private Date createTime;
    private Date updateTime;

    //视频类型
    private VideoType videoType;

}
