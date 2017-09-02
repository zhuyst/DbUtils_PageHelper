package indi.zhuyst.dbutils.pagehelper.util;

import com.sun.istack.internal.NotNull;
import indi.zhuyst.dbutils.pagehelper.enums.PageEnum;
import indi.zhuyst.dbutils.pagehelper.pojo.RowBounds;

public class PageUtil {
    //对SQL语句进行COUNT查询处理
    public static String count(@NotNull String sql){
        int fromIndex = sql.toUpperCase().indexOf("FROM");

        String replaceStr = sql.substring(7,fromIndex - 1);
        return sql.replace(replaceStr,"COUNT(0)");
    }

    //对SQL语句进行分页查询处理
    public static String page(@NotNull String sql, @NotNull RowBounds rowBounds){
        int pageNum = rowBounds.getPageNum();
        int pageSize = rowBounds.getPageSize();

        int offset = (pageNum - 1) * pageSize;
        return sql + " LIMIT " + offset + "," + pageSize;
    }

    //通过总数和页面大小获取总页数
    public static int getPages(long total,int pageSize){
        return (int) Math.ceil((double) total / pageSize);
    }

    public static RowBounds getRowBounds(Integer pageNum, Integer pageSize){
        RowBounds rowBounds = new RowBounds();

        if(pageNum == null || pageSize == null || pageSize <= 0){
            rowBounds.setPageEnum(PageEnum.ALL);
        }
        else {
            rowBounds.setPageEnum(PageEnum.PAGE);
            rowBounds.setPageNum(pageNum);
            rowBounds.setPageSize(pageSize);
        }

        return rowBounds;
    }
}
