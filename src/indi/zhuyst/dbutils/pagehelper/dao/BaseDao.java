package indi.zhuyst.dbutils.pagehelper.dao;

import com.sun.istack.internal.NotNull;
import indi.zhuyst.dbutils.pagehelper.enums.PageEnum;
import indi.zhuyst.dbutils.pagehelper.pojo.Page;
import indi.zhuyst.dbutils.pagehelper.pojo.RowBounds;
import indi.zhuyst.dbutils.pagehelper.util.PageUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    protected QueryRunner queryRunner;

    public BaseDao(QueryRunner queryRunner){
        this.queryRunner = queryRunner;
    }

    protected <T> Page<T> queryWithPage(@NotNull String sql, RowBounds rowBounds, @NotNull Class<T> type) throws SQLException {
        return this.queryWithPage(sql,rowBounds,type,(Object[]) null);
    }

    //判断PageEnum进行操作
    protected <T> Page<T> queryWithPage(@NotNull String sql, RowBounds rowBounds, @NotNull Class<T> type,Object... params) throws SQLException {
        Page<T> page = new Page<>();

        PageEnum pageEnum;
        if(rowBounds == null){
            pageEnum = null;
        }
        else {
            pageEnum = rowBounds.getPageEnum();
        }

        //如果PageEnum为null或者为ALL，则查询全部数据
        if(pageEnum == null || pageEnum == PageEnum.ALL){
            List<T> list = queryRunner.query(sql,new BeanListHandler<>(type),params);
            page.setList(list);
        }

        //如果PageEnum为PAGE，则进行分页查询
        else if(pageEnum == PageEnum.PAGE){
            //进行count查询获取total
            Long total = this.count(sql,params);
            page.setTotal(total);

            //通过total和pageSize获取总页数pages
            int pages = PageUtil.getPages(total,rowBounds.getPageSize());
            page.setPages(pages);

            //边界值合理化判断
            if(rowBounds.getPageNum() < 1){
                rowBounds.setPageNum(1);
            }
            else if(rowBounds.getPageNum() > pages){
                rowBounds.setPageNum(pages);
            }

            //进行分页查询获得list
            String querySQL = PageUtil.page(sql,rowBounds);
            List<T> list = queryRunner.query(querySQL,new BeanListHandler<>(type),params);
            page.setList(list);

            page.setPageNum(rowBounds.getPageNum());
            page.setPageSize(rowBounds.getPageSize());
        }
        else if(pageEnum == PageEnum.COUNT){
            Long total = this.count(sql,params);
            page.setTotal(total);
        }

        return page;
    }

    protected Long count(String sql) throws SQLException {
        return this.count(sql,(Object[])null);
    }

    protected Long count(String sql,Object... params) throws SQLException {
        sql = PageUtil.count(sql);
        return queryRunner.query(sql,new ScalarHandler<Long>(),params);
    }
}
