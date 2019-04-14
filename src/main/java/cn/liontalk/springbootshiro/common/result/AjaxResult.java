package cn.liontalk.springbootshiro.common.result;

import lombok.Data;

/**
 * @author: 周哲
 * @package: cn.liontalk.springbootshiro.common.result
 * @description:
 * @date: 2019/4/14 17:28
 * @version: V1.0
 */


@Data
public class AjaxResult<T> {

    private int code;

    private String message;

    private T data;

    private AjaxResult(T data) {
        this.code = code;
        this.message = "success";
        this.data = data;
    }


    private AjaxResult(CodeMsg codeMsg) {
        if (null == codeMsg) {
            return;
        }
        this.message = codeMsg.getMessage();
        this.code = codeMsg.getCode();
    }

    /**
     * 成功是后调用
     *
     * @param data 参数
     * @param <T>  操作结果
     * @return 返回操作成功数据
     */
    public static <T> AjaxResult <T> success(T data) {
        return new AjaxResult <T>(data);
    }

    /**
     * 失败之后是后调用
     *
     * @param codeMsg 参数
     * @param <T>     操作结果
     * @return 返回操作成功数据
     */
    public static <T> AjaxResult <T> error(CodeMsg codeMsg) {
        if (null == codeMsg) {
            return null;
        }
        return new AjaxResult <T>(codeMsg);
    }

}
