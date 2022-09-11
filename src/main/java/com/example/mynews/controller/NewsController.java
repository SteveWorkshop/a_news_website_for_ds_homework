package com.example.mynews.controller;

import com.example.mynews.service.NewsService;
import com.example.mynews.service.NewsTypeService;
import com.example.mynews.service.ex.NewsNotFoundException;
import com.example.mynews.service.ex.NoMoreNewsException;
import com.example.mynews.util.JsonResult;
import com.example.mynews.vo.NewsVO;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("news")
public class NewsController extends BaseController{

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsTypeService newsTypeService;

    @GetMapping("get_by_page/{pageStart}/{pageSize}")
    public JsonResult<List<NewsVO>> getByPage(@PathVariable("pageStart") int pageStart,@PathVariable("pageSize") int pageSize)
    {
        //TODO  有一定的性能缺陷，未来修复完善
        List<NewsVO> voList= newsService.getNewsByPage(pageStart,pageSize);
        if(voList==null||voList.size()==0)
        {
            throw new NoMoreNewsException("没有更多了，等会儿再刷吧！");
        }
        return new JsonResult<>(OK,voList);
    }

    @GetMapping("newsitem/{nid}")
    public JsonResult<NewsVO> getVOById(@PathVariable("nid") int nid)
    {
        NewsVO newsVO= newsService.findById(nid);
        if(newsVO==null)
        {
            throw new NewsNotFoundException("无此新闻");
        }
        return new JsonResult<>(OK,newsVO);
    }
}
