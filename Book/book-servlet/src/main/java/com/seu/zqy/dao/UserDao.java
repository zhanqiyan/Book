package com.seu.zqy.dao;

import com.seu.zqy.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 将新提交的用户信息保存到数据库中
     * @param user
     * @return
     */
    public int Insert(User user);

    /**
     * 根据传入的参数修改用户数据
     * @param user
     * @return
     */
    public int update(User user);

    /**
     * 根据用户id查出用户信息，并封装
     * @param id
     * @return
     */
    public User getbyid(Integer id);

    public User getbyidAndusername(@Param("id") Integer id, @Param("username") String username);

    /**
     * 根据用户名查出用户
     * @param username
     * @return
     */
    public User getbyusername(String username);

    /**
     * 通过用户民和密码获取用户
     * @param username
     * @param password
     * @return
     */
    public User getbyusernameandpassword(@Param("username") String username, @Param("password") String password);


    /**
     * 获取数据库中所有用户的资料，封装为列表
     * @return
     */
    public List<User> getall();
}
