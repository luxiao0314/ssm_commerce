package cn.e3mall.service.impl;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.zookeeper.server.SessionTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.pojo.PageResult;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.service.ItemService;

import javax.annotation.Resource;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by lucio on 29/11/2017.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Autowired
    private TbItemMapper itemMapper;
    //    @Autowired
//    private TbItemDescMapper itemDescMapper;
//    @Autowired
//    private JmsTemplate jmsTemplate;
//    @Resource
//    private Destination topicDestination;

    @Override
    public TbItem getItemById(long itemId) {
        //根据主键查询
        //TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andIdEqualTo(itemId);
        //执行查询
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbItemCat> getItemCatList() {
        TbItemCatExample example = new TbItemCatExample();
        return itemCatMapper.selectByExample(example);
    }

    @Override
    public List<EasyUITreeNode> getItemCatListByParentId(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
        ArrayList<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCats) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(tbItemCat.getId());
            easyUITreeNode.setText(tbItemCat.getName());
            easyUITreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            easyUITreeNodes.add(easyUITreeNode);
        }
        return easyUITreeNodes;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = itemMapper.selectByExample(tbItemExample);
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setRows(tbItems);
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        easyUIDataGridResult.setSize(pageInfo.getPageSize());
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        return easyUIDataGridResult;
    }

    @Override
    public PageResult getCatPageList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbItemCatExample example = new TbItemCatExample();
        List<TbItemCat> tbItems = itemCatMapper.selectByExample(example);
        PageInfo<TbItemCat> pageInfo = new PageInfo<>(tbItems);
        PageResult<TbItemCat> pageResult = new PageResult<>();
        pageResult.setList(tbItems);
        pageResult.setSize(pageInfo.getSize());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPages(pageInfo.getPages());
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }

    @Override
    public E3Result deleteItem(long id) {
        TbItemExample example = new TbItemExample();
        example.createCriteria().andIdEqualTo(id);
        itemMapper.deleteByExample(example);
        return E3Result.ok();
    }

//    @Override
//    public E3Result addItem(TbItem item, String desc) {
//        //生成商品id
//        final long itemId = IDUtils.genItemId();
//        //补全item的属性
//        item.setId(itemId);
//        //1-正常，2-下架，3-删除
//        item.setStatus((byte) 1);
//        item.setCreated(new Date());
//        item.setUpdated(new Date());
//        //向商品表插入数据
//        itemMapper.insert(item);
//        //创建一个商品描述表对应的pojo对象。
//        TbItemDesc itemDesc = new TbItemDesc();
//        //补全属性
//        itemDesc.setItemId(itemId);
//        itemDesc.setItemDesc(desc);
//        itemDesc.setCreated(new Date());
//        itemDesc.setUpdated(new Date());
//        //向商品描述表插入数据
//        itemDescMapper.insert(itemDesc);
//        //发送商品添加消息
//        jmsTemplate.send(topicDestination, new MessageCreator() {
//
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(itemId + "");
//            }
//        });
//        //返回成功
//        return E3Result.ok();
//    }

}
