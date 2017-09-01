package indi.zhuyst.dbutils.pagehelper.service;

import indi.zhuyst.dbutils.pagehelper.dao.UserDao;
import indi.zhuyst.dbutils.pagehelper.pojo.Page;
import indi.zhuyst.dbutils.pagehelper.pojo.RowBounds;
import indi.zhuyst.dbutils.pagehelper.pojo.User;
import indi.zhuyst.dbutils.pagehelper.util.PageUtil;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao;

    public UserService(){
        userDao = new UserDao();
    }

    public Page<User> listUser(Integer pageNum){
        final int PAGE_SIZE = 10;

        Page<User> page = null;
        RowBounds rowBounds = PageUtil.getRowBounds(pageNum,PAGE_SIZE);

        try{
            page = userDao.list(rowBounds);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return page;
    }

    public Page<User> searchUserByUsername(String username,Integer pageNum){
        final int PAGE_SIZE = 10;

        Page<User> page = null;
        RowBounds rowBounds = PageUtil.getRowBounds(pageNum,PAGE_SIZE);

        try{
            page = userDao.search(username,rowBounds);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return page;
    }
}
