package com.example.mynews.controller;

import com.example.mynews.service.ex.*;
import com.example.mynews.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

@Slf4j
//@CrossOrigin //重点：必须有这个注解，否则无限循环炸！
public class BaseController {
    public static final int OK=200;

    public static final int USERNAME_DUPLICATION=4000;
    public static final int USER_NOT_FOUND=4001;
    public static final int PASSWORD_WRONG=4002;
    public static final int NO_SUCH_USERTYPE=4003;

    public static final int NO_MORE_NEWS=4004;


    public static final int INSERT_EXCEPTION=5000;
    public static final int UPDATE_EXCEPTION=5001;
    public static final int DELETE_EXCEPTION=5002;

    public static final int FILE_EMPTY=6000;
    public static final int FILE_SIZE_TOO_LARGE=6001;
    public static final int FILE_TYPE_NOMATCH=6002;
    public static final int FILE_STATE_FALIURE=6003;
    public static final int FILE_IO_FALIURE=6004;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleEx(Throwable e)
    {
        JsonResult<Void> result=new JsonResult<>();

        //分类讨论开始
        if (e instanceof NoSuchUserTypeException)       {result.setState(NO_SUCH_USERTYPE);}
        if (e instanceof UserNotFoundException)         {result.setState(USER_NOT_FOUND);}
        if (e instanceof UsernameDuplicateException)    { result.setState(USERNAME_DUPLICATION); }
        if (e instanceof PasswordNotMatchException)     { result.setState(PASSWORD_WRONG); }

        if (e instanceof NoMoreNewsException)           { result.setState(NO_MORE_NEWS); }

        if (e instanceof InsertException)               { result.setState(INSERT_EXCEPTION); }
        if (e instanceof UpdateException)               { result.setState(UPDATE_EXCEPTION); }

        result.setMessage(e.getMessage());
        return result;
    }

    //女孩自用一手小工具九成新带体香，50包邮解君愁bushi
    protected final Integer getUidFromSession(HttpSession session)
    {
        //三千预算进卡吧，加钱加到九万八
        //三千预算进图吧，学校对面开网吧
        log.info("工具："+session.getAttribute("uid"));
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    //开玩笑了后面这俩小玩意经常用
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

    protected final String getAdminNameFromSession(HttpSession session)
    {
        return session.getAttribute("admin_name").toString();
    }
}
