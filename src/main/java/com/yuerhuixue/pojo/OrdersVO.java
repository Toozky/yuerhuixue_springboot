package com.yuerhuixue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersVO {

    private Integer orderId;
    private Integer userId;
    private String allGoods;
    private String receiverName;
    private String receiverTel;
    private String receiverAddr;
    private BigDecimal totalAmount;
    private BigDecimal actualAmount;
    private String payType;
    private String orderRemark;
    private String status;
    private Date createTime;
    private Date updateTime;
    private Date payTime;
    private Date deliverTime;
    private Date receiptTime;
    private Date finishTime;

    private List<OrderDetail> orderDetailList;

}
