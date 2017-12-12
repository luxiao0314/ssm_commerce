package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

/**
 * Created by lucio on 11/12/2017.
 */
public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCatList(long parentId);

    E3Result addContentCategory(long parentId, String name);

    EasyUIDataGridResult queryContentList(long categoryId,Integer page, Integer rows);

    E3Result deleteContent(long id);
}
