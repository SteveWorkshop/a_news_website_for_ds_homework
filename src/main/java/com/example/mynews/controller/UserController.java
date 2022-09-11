package com.example.mynews.controller;

import com.example.mynews.entity.User;
import com.example.mynews.service.UserService;
import com.example.mynews.service.ex.*;
import com.example.mynews.util.JsonResult;
import com.example.mynews.util.UUIDUtil;
import com.example.mynews.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    public static final int AVATAR_MAX_SIZE=1024*1024*10;
    public static final List<String> ALLOWED_FILE_TYPES=new ArrayList<>();

    @Autowired
    private UserService userService;

    static
    {
        ALLOWED_FILE_TYPES.add("image/jpeg");
        ALLOWED_FILE_TYPES.add("image/png");
        ALLOWED_FILE_TYPES.add("image/bmp");
    }

    @PostMapping("login")
    public JsonResult<UserVO> login(String username, String password, HttpSession session)
    {
        //log.info("调用成功！");
        UserVO data=userService.login(username,password);
        log.info("用户ID："+data.getUid());
        session.setAttribute("uid",data.getUid());//省到极致啊
        session.setAttribute("username",data.getUsername());
        log.info("会话信息："+session.getAttribute("uid").toString());
        return new JsonResult<>(OK,data);
    }

    @PostMapping("reg")
    public JsonResult<Void> reg(User user)
    {
        log.info("input: "+user);
        JsonResult<Void> result=new JsonResult<>();

        userService.register(user);
        result.setState(OK);

        return result;
    }

    @PostMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session)
    {
        //用到了这里
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);

        userService.changePassword(uid,username,oldPassword,newPassword);

        return new JsonResult<>(OK);
    }

    @GetMapping("get_by_uid")
    public JsonResult<UserVO> getByUid(HttpSession session)
    {
        //DTO，当结构访问数据就可以了
        Integer uid=getUidFromSession(session);
        log.info("会话UID："+uid);
        if(uid==null)
        {
            throw new NotLoggedInException("拉取用户信息失败，原因是未登录");
        }

        UserVO user=userService.getByUid(uid);
        return new JsonResult<>(OK,user);
    }

    @PostMapping("change_info")
    public JsonResult<Void> changeInfo(UserVO uservo,HttpSession session)
    {
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changeInfo(uid,username,uservo);
        return new JsonResult<Void>(OK);
    }

    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session)
    {
        //前端表单类型也要改，不是x-www-form-urlencoded了
        //三种，一种是&拼字符串，一种是文件，一种是JSON
        if(file.isEmpty())
        {
            throw new FileEmptyException("文件不存在！");
        }
        if(file.getSize()>AVATAR_MAX_SIZE)
        {
            throw new FileSizeException("图片大小不得超过"+AVATAR_MAX_SIZE/1024+"KB！谢谢合作");
        }
        String contentType=file.getContentType();//获取这个文件的MIME类型（MIME类型是文件固有属性）
        if(!ALLOWED_FILE_TYPES.contains(contentType))
        {
            throw new FileTypeException("只能上传"+ALLOWED_FILE_TYPES+"的图片");
        }
        String parent=session.getServletContext().getRealPath("upload");
        File dir=new File(parent);//FILE类可以代表一个文件或文件夹，就是个占位符
        if(!dir.exists())
        {
            dir.mkdirs();//新建文件夹
        }
        String suffix="";

        //这是针对Windows的特殊操作
        String originalFilename=file.getOriginalFilename();
        int beginIndex=originalFilename.lastIndexOf(".");
        //获取后缀名，文件名重新生成

        //没有后缀名的也能上传成功么？
        if(beginIndex>0)
        {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename= UUIDUtil.getUUID()+suffix;
        File dest = new File(dir, filename);
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("一个意外的错误使你无法完成操作，若要继续进行，请重新启动应用程序");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }
        String avatar="/images/"+filename;

        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);

        userService.changeAvatar(uid,username,avatar);
        return new JsonResult<>(OK,avatar);
    }
}
