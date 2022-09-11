package com.example.mynews.controller;

import com.example.mynews.entity.UserType;
import com.example.mynews.service.UserTypeService;
import com.example.mynews.util.JsonResult;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("user_types")
public class UserTypeController extends BaseController{

    @Autowired
    private UserTypeService userTypeService;

    @GetMapping("find_all")
    public JsonResult<List<UserType>> getAll()
    {
        List<UserType> userTypeList= userTypeService.findAll();
        for(var userType:userTypeList)
        {
            userType.setCreatedUser(null);
            userType.setCreatedTime(null);
            userType.setModifiedUser(null);
            userType.setModifiedTime(null);
        }
        return new JsonResult<>(OK,userTypeList);
    }

    @PostMapping("add_type")
    public JsonResult<Void> addType(String typeName, HttpSession session)
    {
        String admin=getAdminNameFromSession(session);
        userTypeService.addType(typeName,admin);
        return new JsonResult<>(OK);
    }

    @PostMapping("{id}/change_info")
    public JsonResult<Void> changeInfo(@PathVariable("id")Integer id, String typeName, HttpSession session)
    {
        String admin=getAdminNameFromSession(session);
        userTypeService.changeInfo(id,typeName,admin);
        return new JsonResult<>(OK);
    }
}
