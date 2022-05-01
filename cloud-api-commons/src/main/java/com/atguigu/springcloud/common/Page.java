package com.atguigu.springcloud.common;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 总条数 */
    private Long allRow = 0L;
    /** 当前页 */
    private Integer currentPage = 1;
    /** 每页多少条*/
    private int pageSize = 10;
    /** 总页数 */
    private int totalPage;
    /** 开始行数 */
    private Long endRow;
    /** 结束行数 */
    private long startRow;
    /** 是否还有分页 */
    private boolean hasNext;
    /** 分页数据 */
    private List<T> dataList = new ArrayList<>();

    public Page() {
    }

    public Page(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * mybatis 分页插件 pageHelper 使用
     * @param pageInfo
     * @param dataList
     *       <!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper-spring-boot-starter -->
     *         <dependency>
     *             <groupId>com.github.pagehelper</groupId>
     *             <artifactId>pagehelper-spring-boot-starter</artifactId>
     *             <version>1.4.2</version>
     *         </dependency>
     */
    public Page(PageInfo pageInfo, List<T> dataList) {
        this.allRow = pageInfo.getTotal();
        this.pageSize = pageInfo.getPageSize();
        this.currentPage = pageInfo.getPageNum();
        this.totalPage = pageInfo.getPages();
        this.startRow = pageInfo.getStartRow();
        this.dataList = dataList;
    }
    // TODO

}
