package com.example.mynews;

import com.example.mynews.entity.News;
import com.example.mynews.entity.NewsType;
import com.example.mynews.entity.User;
import com.example.mynews.entity.UserType;
import com.example.mynews.mapper.*;
import com.example.mynews.service.UserService;
import com.example.mynews.util.UUIDUtil;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.Date;

@SpringBootTest
class MynewsApplicationTests {

    @Autowired
    UserTypeMapper userTypeMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    NewsTypeMapper newsTypeMapper;

    @Autowired
    NewsMapper newsMapper;

    @Autowired
    FavoriteMapper favoriteMapper;

    @Autowired
    UserService userService;

    String getMD5Password(String password, String salt)
    {
        for(int i=0;i<3;i++)
        {
            password= DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }


    @Test
    void contextLoads() {
        UserType userType=new UserType();
        userType.setTypeName("电脑攒机");
        userType.setCreatedUser("su");
        userType.setCreatedTime(new Date());
        userType.setModifiedUser("su");
        userType.setModifiedTime(new Date());

        var res=userTypeMapper.insert(userType);
        System.out.println(res);
    }

    @Test
    void select()
    {
//        var user=userTypeMapper.findByUtypeid(1);
//        System.out.println(user.getCreatedUser());

        var vo=userMapper.findVOByUid(1);
        System.out.println(vo);
    }

    @Test
    void insert()
    {
        User user=new User();
        String password="111";
        String salt= UUIDUtil.getUUID();
        String md5=getMD5Password(password,salt);

        user.setUsername("沐思遥");
        user.setPassword(md5);
        user.setSalt(salt);
        user.setEmail("222@live.com");
        user.setGender(User.GIRL);
        user.setIsDelete(User.ACTIVE);
        user.setAvatar(null);
        user.setUtypeid(1);

        user.setCreatedUser("su");
        user.setCreatedTime(new Date());
        user.setModifiedUser("su");
        user.setModifiedTime(new Date());

        System.out.println(user);
        Integer res=userMapper.insert(user);
        System.out.println(res);
    }

    @Test
    void insert2()
    {
        NewsType newsType=new NewsType();
        newsType.setTypeName("DIY攒机");
        newsType.setCreatedUser("su");
        newsType.setCreatedTime(new Date());
        newsType.setModifiedUser("su");
        newsType.setModifiedTime(new Date());

        Integer res=newsTypeMapper.insert(newsType);
        System.out.println(res);
    }

    @Test
    void select2()
    {
        var type=newsTypeMapper.findByNtypeid(1);
        System.out.println(type);
    }

    @Test
    void insert3()
    {
        News news=new News();
        news.setNtypeid(1);
        news.setNewsTitle("【IT学院】想玩NAS？看图吧资深垃圾佬全方位讲解如何选购硬盘");
        news.setNewsContent("坐和放宽，滚回功率。。。");
        news.setIsDelete(0);
        news.setNewsImage(null);
        news.setCreatedUser("su");
        news.setCreatedTime(new Date());
        news.setModifiedUser("su");
        news.setModifiedTime(new Date());

        System.out.println(news);

        Integer res=newsMapper.insert(news);
        System.out.println(res);

    }

    @Test
    void xxx()
    {
//        var newses=newsMapper.getAllVO();
//        for(var news:newses)
//        {
//            System.out.println(news);
//        }
//        var newses=newsMapper.getPage(0,2);
//        for(var news:newses)
//        {
//            System.out.println(news);
//        }
        var user=userMapper.findVOByUserName("沐思遥");
        System.out.println(user);
    }

    @Test
    void login()
    {
        var vo=userService.login("黄欣灵","1");
        if(vo!=null)
        {
            System.out.println(vo);
        }
        else
        {
            System.out.println("芭比Q了");
        }
    }

    @Test
    void findByUid()
    {
        var vo=userService.getByUid(1);
        System.out.println(vo);
    }
}
