package cn.e3mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbItemParamMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamExample;
import cn.e3mall.service.ItemParamsService;

/**
 * Created by lucio on 29/11/2017.
 */
@Service
public class ItemParamsServiceImpl implements ItemParamsService {

    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public E3Result queryItemParams(long id) {
        TbItemParamExample example = new TbItemParamExample();
        example.createCriteria().andIdEqualTo(id);
        itemParamMapper.selectByExample(example);
        return E3Result.ok();
    }

    @Override
    public E3Result saveItemParams(TbItemParam tbItemParam, long id) {
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        tbItemParam.setId(id);
        tbItemParam.setParamData("[{\"group\":\"f1\",\"params\":[\"1\"]},{\"group\":\"f2\",\"params\":[\"2\"]}]");
        itemParamMapper.insert(tbItemParam);
        return E3Result.ok();
    }

    @Override
    public EasyUIDataGridResult getItemParamList(int pages, int rows) {
        PageHelper.startPage(pages,rows);
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> tbItems = itemParamMapper.selectByExample(example);
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setRows(tbItems);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(tbItems);
        easyUIDataGridResult.setSize(pageInfo.getPageSize());
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        return easyUIDataGridResult;
    }
}
