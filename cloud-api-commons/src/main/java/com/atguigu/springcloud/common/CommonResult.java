package com.atguigu.springcloud.common;

import cn.hutool.db.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 通用返回结果对象
 */
@Data
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 业务编码 */
    private Integer code;

    /** 业务编码对应信息 */
    private String codeText;

    /** 消息 */
    private String message;

    /** 接口是否请求成功 */
    private Boolean success;

    /** 时间戳 */
    private Long timeStamp;

    /** 业务结果数据*/
    private List<T> data;
    /** 分页信息 */
    private Page pager;

    public CommonResult() {
        this.timeStamp = System.currentTimeMillis();
    }

    public CommonResult(Integer code, String codeText, String message, Boolean success, List<T> data, Page pager) {
        this.code = code;
        this.codeText = codeText;
        this.message = message;
        this.success = success;
        this.timeStamp = System.currentTimeMillis();
        this.data = data;
        this.pager = pager;
    }

    public CommonResult(Integer code, String codeText, String message, Boolean success) {
        this.code = code;
        this.codeText = codeText;
        this.message = message;
        this.success = success;
        this.timeStamp = System.currentTimeMillis();
    }
}
