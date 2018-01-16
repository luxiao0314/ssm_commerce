package cn.e3mall.search.service.impl

import cn.e3mall.common.pojo.SearchResult
import cn.e3mall.search.dao.SearchDao
import cn.e3mall.search.service.SearchService
import org.apache.solr.client.solrj.SolrQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 10/01/2018 4:24 PM
 * @Version
 */
@Service
class SearchServiceImpl : SearchService {

    @Autowired
    private val searchDao: SearchDao? = null

    override fun search(keyword: String?, page: Int, rows: Int): SearchResult {
        var page = page
        val query = SolrQuery()
        query.query = keyword //设置查询条件
        //设置分页条件
        if (page <= 0) page = 1
        query.start = (page - 1) * rows
        query.rows = rows
        //设置默认搜索域
        query.set("df", "item_title")
        query.highlight = true
        query.addHighlightField("item_title")
        query.highlightSimplePre = "<em style=\"color:red\">";
        query.highlightSimplePost = "</em>";
        //调用dao执行查询
        val searchResult = searchDao?.search(query)
        //计算总页数
        val recordCount = searchResult!!.recordCount
        var totalPage = (recordCount / rows).toInt()
        if (recordCount % rows > 0)
            totalPage++
        //添加到返回结果
        searchResult.totalPages = totalPage
        return searchResult
    }

}