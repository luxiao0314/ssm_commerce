package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.pojo.PageResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemCat;

/**
 * Created by lucio on 29/11/2017.
 */
public interface ItemService {

    TbItem getItemById(long itemId);

    List<TbItemCat> getItemCatList();

    List<EasyUITreeNode> getItemCatListByParentId(long parentId);

    EasyUIDataGridResult getItemList(int page, int rows);

    PageResult getCatPageList(int page, int rows);

    E3Result deleteItem(long id);

    E3Result addItem(TbItem item, String desc);
}
