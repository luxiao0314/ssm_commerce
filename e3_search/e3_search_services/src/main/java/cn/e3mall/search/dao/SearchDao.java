package cn.e3mall.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;

/**
 * 商品搜索dao
 * <p>Title: SearchDao</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 *
 * @version 1.0
 */
@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    /**
     * 根据查询条件查询索引库
     * <p>Title: search</p>
     * <p>Description: </p>
     *
     * @param query
     * @return
     */
    public SearchResult search(SolrQuery query) throws Exception {
        //根据query查询索引库
        QueryResponse queryResponse = solrServer.query(query);
        //取查询结果。
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //取查询结果总记录数
        long numFound = solrDocumentList.getNumFound();
        SearchResult result = new SearchResult();
        result.setRecordCount(numFound);
        //取商品列表，需要取高亮显示
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setId((String) solrDocument.get("id"));
            ArrayList<String> item_category_name = (ArrayList<String>) solrDocument.get("item_category_name");
            item.setCategory_name(item_category_name.get(0));
            ArrayList<String> item_image = (ArrayList<String>) solrDocument.get("item_image");
            item.setImage(item_image.get(0));
            ArrayList<Long> item_price = (ArrayList<Long>) solrDocument.get("item_price");
            item.setPrice(item_price.get(0));
            ArrayList<String> item_sell_point = (ArrayList<String>) solrDocument.get("item_sell_point");
            if (item_sell_point != null && item_sell_point.size() != 0)
                item.setSell_point(item_sell_point.get(0));
            //取高亮显示
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            //添加到商品列表
            itemList.add(item);
        }
        result.setItemList(itemList);
        //返回结果
        return result;
    }

}
