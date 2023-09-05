package com.cy.store.service;

import com.cy.store.entity.Address;

/** 处理收货地址数据的业务层接口 */
public interface IAddressService {
    /**
     * 创建新的收货地址
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param address 用户提交的收货地址数据
     */
    void addNewAddress(Integer uid, String username, Address address);
}
