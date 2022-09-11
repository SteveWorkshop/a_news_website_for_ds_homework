package com.example.mynews.service.impl;

import com.example.mynews.entity.User;
import com.example.mynews.mapper.UserMapper;
import com.example.mynews.mapper.UserTypeMapper;
import com.example.mynews.service.UserService;
import com.example.mynews.service.ex.*;
import com.example.mynews.util.UUIDUtil;
import com.example.mynews.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTypeMapper userTypeMapper;

    //RNM让你偷看密码？！！！！
    private String getMD5Password(String password, String salt)
    {
        for(int i=0;i<3;i++)
        {
            password= DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    @Override
    public void register(User user) {
        String username=user.getUsername();
        User loginUser=userMapper.findByUsername(username);
        if(loginUser!=null)
        {
            throw new UsernameDuplicateException("用户名已经占用或涉嫌违法被平台永久封禁");
        }
        Date now=new Date();
        String salt= UUIDUtil.getUUID();
        String processedPassword=getMD5Password(user.getPassword(),salt);
        user.setPassword(processedPassword);
        user.setSalt(salt);

        user.setIsDelete(User.ACTIVE);

        user.setCreatedUser(username);//日志记录在实体类当中
        user.setCreatedTime(now);
        user.setModifiedUser(username);
        user.setModifiedTime(now);

        log.debug("user: "+user);

        Integer rows=userMapper.insert(user);//用户信息被自动补全
        log.debug("rows: "+rows);

        if(rows!=1)
        {
            throw new InsertException("服务器端错误！请坐和放宽，滚回功率，我们正在调查此问题，将在未来的构建修复");
        }
    }

    @Override
    public UserVO login(String username, String password) {
        User loginUser= userMapper.findByUsername(username);
        if(loginUser==null)
        {throw new UserNotFoundException("用户不存在");

        }
        if(loginUser.getIsDelete()==User.DELETE)
        {
            throw new UserNotFoundException("你已注销此账户，或你曾涉嫌违法行为，根据《中华人民共和国网络安全法》，平台已经将你的账户永久封禁！");
        }
        String salt=loginUser.getSalt();
        String md5Password=getMD5Password(password,salt);
        if(!loginUser.getPassword().equals(md5Password))
        {
            throw new PasswordNotMatchException("密码不正确");
        }

        Integer utypeid=loginUser.getUtypeid();
        String typeName=null;
        if(utypeid!=null)
        {
            typeName=userTypeMapper.findByUtypeid(utypeid).getTypeName();
        }
        UserVO user=new UserVO();
        user.setUid(loginUser.getUid());


        user.setTypeName(typeName);
        user.setUsername(loginUser.getUsername());
        user.setAvatar(loginUser.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result=userMapper.findByUid(uid);
        if(result==null)
        {
            throw new UserNotFoundException("用户不存在");
        }
        if(result.getIsDelete()==1)
        {
            throw new UserNotFoundException("你已注销此账户，或你曾涉嫌违法行为，根据《中华人民共和国网络安全法》，平台已经将你的账户永久封禁！");
        }
        String salt=result.getSalt();

        String oldMD5Password=getMD5Password(oldPassword,salt);
        if(!result.getPassword().contentEquals(oldMD5Password))
        {
            throw new PasswordNotMatchException("密码不正确");
        }

        String newMD5Password=getMD5Password(newPassword,salt);
        Date now = new Date();

        int rows= userMapper.updatePasswordByUid(uid,newMD5Password,username,now);
        if(rows!=1)
        {
            throw new UpdateException("服务器端错误！请坐和放宽，滚回功率，我们正在调查此问题，将在未来的构建修复");
        }
    }

    @Override
    public UserVO getByUid(Integer uid) {
        UserVO result=userMapper.findVOByUid(uid);
        if(result==null)
        {
            throw new UserNotFoundException("用户不存在");
        }
        if(result.getIsDelete()==1)
        {
            throw new UserNotFoundException("你已注销此账户，或你曾涉嫌违法行为，根据《中华人民共和国网络安全法》，平台已经将你的账户永久封禁！");
        }
        UserVO user = new UserVO();

        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        user.setAvatar(result.getAvatar());
        user.setTypeName(result.getTypeName());

        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, UserVO uservo) {
        User result=userMapper.findByUid(uid);
        if(result==null)
        {
            throw new UserNotFoundException("用户不存在");
        }
        if(result.getIsDelete()==1)
        {
            throw new UserNotFoundException("你已注销此账户，或你曾涉嫌违法行为，根据《中华人民共和国网络安全法》，平台已经将你的账户永久封禁！");
        }

        Integer utypeid=null;
        if(uservo.getTypeName()!=null)
        {
            utypeid=userTypeMapper.findByTypeName(uservo.getTypeName()).getUtypeid();
        }

        User user=new User();
        user.setUtypeid(utypeid);
        user.setUsername(uservo.getUsername());
        user.setEmail(uservo.getEmail());
        user.setGender(uservo.getGender());

        user.setUid(uid);//看来设计的这个是可以改的了
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows=userMapper.updateInfoByUid(user);
        if(rows!=1)
        {
            throw new UpdateException("服务器端错误！请坐和放宽，滚回功率，我们正在调查此问题，将在未来的构建修复");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User result = userMapper.findByUid(uid);
        if (result == null) {
            throw new UserNotFoundException("用户不存在");
        }
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户已删除或封号");
        }
        Date date = new Date();

        //别忘了写日志
        Integer rows=userMapper.updateAvatarByUid(uid,avatar,username,date);
        if(rows!=1)
        {
            throw new UpdateException("我们这边发生错误！不用担心，请坐和放宽，滚回之前的版本，打开内部集线器提供反馈");
        }
    }
}
