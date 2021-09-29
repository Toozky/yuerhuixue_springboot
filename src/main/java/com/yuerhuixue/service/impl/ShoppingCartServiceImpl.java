package com.yuerhuixue.service.impl;

import com.yuerhuixue.dao.ShoppingCartMapper;
import com.yuerhuixue.pojo.ShoppingCart;
import com.yuerhuixue.pojo.ShoppingCartVO;
import com.yuerhuixue.service.ShoppingCartService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 查询当前用户购物车
     * @param userId 用户id
     * @return 执行结果
     */
    @Override
    public ResultVO shoppingCartList(Integer userId) {

        //查询当前用户购物车
        List<ShoppingCartVO> shoppingCartList = shoppingCartMapper.shoppingCartByUser(userId);
        return new ResultVO(StatusCode.OK, "查询成功！", shoppingCartList);
    }

    /**
     * 添加购物车乐器
     * @param shoppingCart 购物车对象
     * @return 执行结果
     */
    @Override
    public ResultVO addShoppingCart(ShoppingCart shoppingCart) {

        Integer cartNumber = shoppingCart.getCartNumber();

        //判断数量是否异常
        if (cartNumber > 0){

            //判断添加的乐器是否为购物车存在商品
            Example example = new Example(ShoppingCart.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("insId", shoppingCart.getInsId());
            List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectByExample(example);

            //如果不是，则添加，否则修改当前存在的乐器数量
            if (shoppingCartList.size() == 0){

                //设置创建时间
                shoppingCart.setCreateTime(new Date());
                int i = shoppingCartMapper.insertUseGeneratedKeys(shoppingCart);
                if (i > 0){
                    return new ResultVO(StatusCode.OK, "添加成功！", shoppingCart);
                }else {
                    return new ResultVO(StatusCode.NO, "添加失败！", null);
                }
            }else {
                Integer cartId = shoppingCartList.get(0).getCartId();

                //乐器数量相加
                Integer cartNumberBefore = shoppingCartList.get(0).getCartNumber();
                Integer cartNumberAfter = cartNumberBefore + cartNumber;

                //数据库修改
                ShoppingCart shoppingCartTemp = new ShoppingCart();
                shoppingCartTemp.setCartId(cartId);
                shoppingCartTemp.setCartNumber(cartNumberAfter);
                int i = shoppingCartMapper.updateByPrimaryKeySelective(shoppingCartTemp);
                if (i > 0){
                    return new ResultVO(StatusCode.OK, "乐器存在，修改数量成功！", null);
                }else {
                    return new ResultVO(StatusCode.NO, "乐器存在，修改数量失败！", null);
                }
            }
        }else {
            return new ResultVO(StatusCode.NO, "数量异常！", null);
        }
    }

    /**
     * 修改购物车乐器
     * @param cartId 购物车id
     * @param cartNumber 乐器数量
     * @return 执行结果
     */
    @Override
    public ResultVO modifyShoppingCart(Integer cartId, Integer cartNumber) {

        //如果数量为0，则删除此条购物车记录
        if (cartNumber == 0){
            int i = shoppingCartMapper.deleteByPrimaryKey(cartId);
            if (i > 0){
                return new ResultVO(StatusCode.OK, "数量为0，删除成功！", null);
            }else {
                return new ResultVO(StatusCode.NO, "数量为0，删除失败！", null);
            }
        }else if (cartNumber > 0){

            //创建购物车对象，仅含id和乐器数量
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setCartId(cartId);
            shoppingCart.setCartNumber(cartNumber);

            //修改
            int i = shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
            if (i > 0){
                return new ResultVO(StatusCode.OK, "修改成功！", null);
            }else {
                return new ResultVO(StatusCode.NO, "修改失败！", null);
            }
        }else {
            return new ResultVO(StatusCode.NO, "数量异常！", null);
        }
    }

    /**
     * 删除购物车乐器
     * @param cartId 购物车id
     * @return 执行结果
     */
    @Override
    public ResultVO deleteShoppingCart(Integer cartId) {

        int i = shoppingCartMapper.deleteByPrimaryKey(cartId);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "删除成功！", null);
        }else {
            return new ResultVO(StatusCode.OK, "删除失败！", null);
        }

    }

}
