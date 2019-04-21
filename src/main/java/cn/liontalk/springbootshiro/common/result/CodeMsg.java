package cn.liontalk.springbootshiro.common.result;

import lombok.Data;

/**
 * @author: 周哲
 * @package: cn.liontalk.springbootshiro.common.result
 * @description:
 * @date: 2019/4/14 17:29
 * @version: V1.0
 */
@Data
public class CodeMsg {

    private int code;

    private String message;


    private CodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 服务器错误
     */
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务器错误");

    /**
     * 数据校验异常
     */
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");

    /**
     * 调用成功
     */
    public static CodeMsg SUCCESS = new CodeMsg(200, "操作成功");


    /**
     * 删除操作错误
     */
    public static CodeMsg DELETE_ERROR = new CodeMsg(500102, "删除操作错误");


    /**
     * 批量删除操作错误
     */
    public static CodeMsg BATCH_DELETE_ERROR = new CodeMsg(500103, "批量删除操作错误");


    /**
     * 请选择要删除的数据
     */
    public static CodeMsg DELETE_DATA_EMPTY = new CodeMsg(500104, "请选择要删除的数据！");
}
