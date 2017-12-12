package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItemParam;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 12/12/2017 5:31 PM
 * @Version
 */
public interface ItemParamsService {

    E3Result queryItemParams(long id);

    E3Result saveItemParams(TbItemParam tbItemParam, long id);

    EasyUIDataGridResult getItemParamList(int pages, int rows);
}
