package indi.zhuyst.dbutils.pagehelper.dao;

import indi.zhuyst.dbutils.pagehelper.pojo.Page;
import indi.zhuyst.dbutils.pagehelper.pojo.RowBounds;
import indi.zhuyst.dbutils.pagehelper.pojo.User;
import indi.zhuyst.dbutils.pagehelper.util.DatabaseUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class UserDao extends BaseDao{
    public UserDao() {
        super(DatabaseUtil.getQueryRunner());
    }

    //使用固定SQL语句进行查询
    public Page<User> list(RowBounds rowBounds) throws SQLException {
        String sql = "SELECT * FROM user";
        return super.queryWithPage(sql,rowBounds,User.class);
    }

    //使用动态SQL语句进行查询
    public Page<User> search(String username,RowBounds rowBounds) throws SQLException {
        String sql = "SELECT * FROM user WHERE username LIKE CONCAT('%',?,'%')";
        return super.queryWithPage(sql,rowBounds,User.class,username);
    }
}
