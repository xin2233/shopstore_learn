package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理收货地址数据的持久层接口
 */
@Repository
public interface AddressMapper {
    /**
     * 插入收货地址数据
     *
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 统计某用户的收货地址数据的数量
     *
     * @param uid 用户的id
     * @return 该用户的收货地址数据的数量
     */
    Integer countByUid(Integer uid);

    /**
     * 查询某用户的收货地址列表数据
     * @param uid 收货地址归属的用户id
     * @return 该用户的收货地址列表数据
     */
    List<Address> findByUid(Integer uid);
}
