package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lucio on 2017/12/4.
 */
@Controller
public class PageController {

    /**
     * 首页为index页面
     * @return
     */
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    /**
     * 配置输入哪个jsp路径就显示哪个jsp
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
