# DbUtils的物理分页插件

### 使用方法

将需要使用的Dao继承 `BaseDao` ，`BaseDao` 中有两个方法<br/>
<br/>
// 无参数方法<br/>
`<T> Page<T> queryWithPage(String sql, RowBounds rowBounds, Class<T> type)`<br/>

// 有参数方法<br/>
`<T> Page<T> queryWithPage(String sql, RowBounds rowBounds, Class<T> type,Object... params)`
<br/>

使用 `PageUtil.getRowBounds()` 方法获取RowBounds<br/>
```Java
RowBounds rowBounds = PageUtil.getRowBounds(pageNum,PAGE_SIZE);
```

### 演示Demo
#### UserDao

```Java
public class UserDao extends BaseDao{

    public UserDao() {
        //BaseDao需要QueryRunner
        //之后在这个Dao中也可以使用BaseDao的QueryRunner
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
```

#### 调用方法
```Java
RowBounds rowBounds = PageUtil.getRowBounds(pageNum,PAGE_SIZE);
page = userDao.list(rowBounds);
```
