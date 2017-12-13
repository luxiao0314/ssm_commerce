package cn.e3mall.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.service.ItemParamsService;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 12/12/2017 5:34 PM
 * @Version
 */
@Controller
class ItemParamsController {

    @Autowired
    private ItemParamsService itemParamsService;

    @RequestMapping("/item/param/query/itemcatid/{id}")
    @ResponseBody
    public E3Result queryItemParam(@PathVariable long id) {
        return itemParamsService.queryItemParams(id);
    }

    @RequestMapping(name = "/item/param/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result saveItemParam(TbItemParam tbItemParam,long id) {
        return itemParamsService.saveItemParams(tbItemParam,id);
    }

    @RequestMapping(name = "item/param/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(@RequestParam Integer page, @RequestParam Integer rows) {
        return itemParamsService.getItemParamList(page, rows);
    }
}
