package com.yuerhuixue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InsVO {

    //乐器
    private Integer insId;
    private String insName;
    private BigDecimal insPrice;
    private Integer insStock;
    private String insImg;
    private String insBrand;
    private String insDesc;
    private Date createTime;
    private Date updateTime;

    //乐器类型
    private InsType insType;

}
