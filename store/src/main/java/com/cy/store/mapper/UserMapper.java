package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Repository注解修饰哪个类表明这个类具有对数据库CRUD的功能，用在持久层的接口上。
 * @Repository用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
 * 具体只需将该注解标注在 DAO类上即可。
 * 同时，为了让 Spring 能够扫描类路径中的类并识别出 @Repository 注解，
 * 需要在 XML 配置文件中启用Bean 的自动扫描功能，这可以通过context:component-scan/实现。
 */
@Repository
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    User findByUsername(String username);

    /**
     * 根据uid更新用户的密码
     * @param uid 用户的id
     * @param password 新密码
     * @param modifiedUser 最后修改执行人
     * @param modifiedTime 最后修改时间
     * @return 受影响的行数
     */
    Integer updatePasswordByUid(
            @Param("uid") Integer uid, 
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser, 
            @Param("modifiedTime") Date modifiedTime);

            
    /**
     * 根据用户id查询用户数据
     * @param uid 用户id
     * @return 匹配的用户数据，如果没有匹配的用户数据，则返回null
     */
    User findByUid(Integer uid);

    /**
     * 根据uid更新用户资料
     * @param user 封装了用户id和新个人资料的对象
     * @return 受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据uid更新用户的头像
     * @param uid 用户的id
     * @param avatar 新头像的路径
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}
