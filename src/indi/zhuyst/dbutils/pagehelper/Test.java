package indi.zhuyst.dbutils.pagehelper;

import indi.zhuyst.dbutils.pagehelper.pojo.Page;
import indi.zhuyst.dbutils.pagehelper.pojo.User;
import indi.zhuyst.dbutils.pagehelper.service.UserService;

public class Test {
    public static void main(String[] args){
        UserService userService = new UserService();

        Page<User> page = userService.listUser(1);
        print(page);
        System.out.println("-----------------");
        page = userService.searchUserByUsername("zhuyst",1);
        print(page);
   }

   private static void print(Page<User> page){
       System.out.println("total = " + page.getTotal());
       System.out.println("pages = " + page.getPages());
       System.out.println("---- list ----");
       for(User user : page.getList()){
           System.out.println("username = " + user.getUsername());
       }
   }
}
