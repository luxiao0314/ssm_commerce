package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.pojo.PageResult;

public interface ItemCatService {

    List<EasyUITreeNode> getItemCatlist(long parentId);

    List<EasyUITreeNode> getItemCatListByParentId(long parentId);

    PageResult getCatPageList(int page, int rows);
}
