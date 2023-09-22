package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private IDistrictService districtService;

    @Autowired
    private AddressMapper addressMapper;

//    @Override
//    public void addNewAddress(Integer uid, String username, Address address) {
//        // TODO
//    }
//    @Override
//    public void addNewAddress(Integer uid, String username, Address address) {
//        // 根据参数uid调用addressMapper的countByUid()方法，统计当前用户的收货地址数据的数量
//        // 判断数量是否达到上限值
//        // 是：抛出AddressCountLimitException
//
//        // 补全数据：将参数uid封装到参数address中
//        // 补全数据：根据以上统计的数量，得到正确的isDefault值(是否默认：0-不默认，1-默认)，并封装
//        // 补全数据：4项日志
//
//        // 调用addressMapper的insert()方法插入收货地址数据，并获取返回的受影响行数
//        // 判断受影响行数是否不为1
//        // 是：抛出InsertException
//    }

    @Value("${user.address.max-count}")
    private int maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        // 根据参数uid调用addressMapper的countByUid(Integer uid)方法，统计当前用户的收货地址数据的数量
        Integer count = addressMapper.countByUid(uid);
        // 判断数量是否达到上限值
        if (count > maxCount) {
            // 是：抛出AddressCountLimitException
            throw new AddressCountLimitException("收货地址数量已经达到上限(" + maxCount + ")！");
        }

        // 补全数据：将参数uid封装到参数address中
        address.setUid(uid);
        // 补全数据：根据以上统计的数量，得到正确的isDefault值(是否默认：0-不默认，1-默认)，并封装
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        // 补全数据：4项日志
        Date now = new Date();
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        address.setModifiedUser(username);
        address.setModifiedTime(now);

        // 补全数据：省、市、区的名称
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        // 调用addressMapper的insert(Address address)方法插入收货地址数据，并获取返回的受影响行数
        Integer rows = addressMapper.insert(address);
        // 判断受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("插入收货地址数据时出现未知错误，请联系系统管理员！");
        }
    }

}