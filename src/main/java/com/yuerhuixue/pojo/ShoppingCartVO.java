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
public class ShoppingCartVO {

    //购物车
    private Integer cartId;
    private Integer cartNumber;
    private Integer userId;
    private Date createTime;

    //乐器
    private Ins ins;

}
