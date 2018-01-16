package cn.e3mall.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.RequestWrapper;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.service.ItemParamsService;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 15/12/2017 3:44 PM
 * @Version
 */
@Controller
public class ItemParamsController {

    @Autowired
    ItemParamsService itemParamsService;

    @RequestMapping("item/param/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamsList(@RequestParam int page, @RequestParam int rows) {
        return itemParamsService.getItemParamsList(page, rows);
    }
}
