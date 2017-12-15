package cn.e3mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.mapper.TbItemParamMapper;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamExample;
import cn.e3mall.service.ItemParamsService;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 15/12/2017 3:48 PM
 * @Version
 */
@Service
public class ItemParamsServiceImpl implements ItemParamsService{

    @Autowired
    TbItemParamMapper tbItemParamMapper;

    @Override
    public EasyUIDataGridResult getItemParamsList(int page, int rows) {
        PageHelper.startPage(page,rows);
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExample(example);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(tbItemParams);
        result.setSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setRows(tbItemParams);
        return result;
    }
}
