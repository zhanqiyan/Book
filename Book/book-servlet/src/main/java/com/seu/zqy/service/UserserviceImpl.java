package com.seu.zqy.service;

import com.seu.zqy.bean.User;
import com.seu.zqy.dao.UserDao;
import com.seu.zqy.utils.jdbcUtils;
import org.apache.ibatis.session.SqlSession;

public class UserserviceImpl implements UserService {

    /**
     * 注册用户信息：实质就是将用户信息保存到数据库中
     * @param user
     */
    @Override
    public void registUser(User user) {
        SqlSession session = null;
        try {
            session = jdbcUtils.getSession();
            UserDao userDao = session.getMapper(UserDao.class);
            userDao.Insert(user);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            jdbcUtils.closeConnection(session);
        }
    }

    @Override
    public User login(User user) {
        SqlSession session = jdbcUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user1 = userDao.getbyusernameandpassword(user.getUsername(), user.getPassword());
        jdbcUtils.closeConnection(session);
        return user1;
    }

    @Override
    public boolean existsUsername(String username) {
        SqlSession session = jdbcUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.getbyusername(username);
        jdbcUtils.closeConnection(session);
        if (user == null) {
            return false;
        }
        return true;
    }
}
