package com.czt.temprxb.http.exception;

/**
 * Created by {冯中萌} on 2017/6/6.
 */

public class RxBaseException extends Exception {

    private int errorCode;
    private int httpcode;
    private String errorMsg;

    public int getHttpcode() {
        return httpcode;
    }

    public void setHttpcode(int httpcode) {
        this.httpcode = httpcode;
    }


    public RxBaseException() {
    }

    public RxBaseException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    public RxBaseException(int errorCode,int httpcode,String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.httpcode = httpcode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 错误码 统一分类
     */
    public static final int API_ERROR = 0x0;//api的错误
    public static final int HTTP_ERROR = 0x2;//http错误
    public static final int JSON_ERROR = 0x3;//json 解析错误
    public static final int UNKNOPWN_ERROR = 0x4;//未知错误
    public static final int SOCKET_TIMEOUT_ERROR = 0x7;//连接超时
    public static final int SOCKET_NO_ERROR = 0x8;//无网络连接


    /**
     * API 错误码
     */
    public static final int API_ERROR_CODE = 4000;//失败
    public static final int API_TOKENOUT_CODE = 5000;//登录超时
    public static final int API_ERRORTOKEN_CODE = 3000;//token 失败


    /**
     * HTTP 错误码
     */
    public static final int HTTP_100 = 100;//客户必须继续发出请求
    public static final int HTTP_101 = 101;//客户要求服务器根据请求转换HTTP协议版本
    public static final int HTTP_201 = 201;//201——提示知道新文件的URL
    public static final int HTTP_202 = 202;//202——接受和处理、但处理未完成
    public static final int HTTP_203 = 203;//203——返回信息不确定或不完整
    public static final int HTTP_204 = 204;//204——请求收到，但返回信息为空
    public static final int HTTP_205 = 205;//205——服务器完成了请求，用户代理必须复位当前已经浏览过的文件
    public static final int HTTP_206 = 206;//206——服务器已经完成了部分用户的GET请求
    public static final int HTTP_300 = 300;//300——请求的资源可在多处得到
    public static final int HTTP_301 = 301;//301——删除请求数据
    public static final int HTTP_302 = 302;//302——在其他地址发现了请求数据
    public static final int HTTP_303 = 303;//303——建议客户访问其他URL或访问方式
    public static final int HTTP_304 = 304;//304——客户端已经执行了GET，但文件未变化
    public static final int HTTP_305 = 305;//305——请求的资源必须从服务器指定的地址得到
    public static final int HTTP_307 = 307;//307——申明请求的资源临时性删除
    public static final int HTTP_400 = 400;//400——错误请求，如语法错误
    public static final int HTTP_401 = 401;//401——请求授权失败
    public static final int HTTP_402 = 402;//402——保留有效ChargeTo头响应
    public static final int HTTP_403 = 403;//403——请求不允许
    public static final int HTTP_404 = 404;//404——没有发现文件、查询或URl
    public static final int HTTP_405 = 405;//405——用户在Request-Line字段定义的方法不允许
    public static final int HTTP_406 = 406;//406——根据用户发送的Accept拖，请求资源不可访问
    public static final int HTTP_407 = 407;//407——类似401，用户必须首先在代理服务器上得到授权
    public static final int HTTP_408 = 408;//408——客户端没有在用户指定的饿时间内完成请求
    public static final int HTTP_409 = 409;//409——对当前资源状态，请求不能完成
    public static final int HTTP_410 = 410;//410——服务器上不再有此资源且无进一步的参考地址
    public static final int HTTP_411 = 411;//411——服务器拒绝用户定义的Content-Length属性请求
    public static final int HTTP_412 = 412;//412——一个或多个请求头字段在当前请求中错误
    public static final int HTTP_413 = 413;//413——请求的资源大于服务器允许的大小
    public static final int HTTP_414 = 414;//414——请求的资源URL长于服务器允许的长度
    public static final int HTTP_415 = 415;//415——请求资源不支持请求项目格式
    public static final int HTTP_416 = 416;//416——请求中包含Range请求头字段，在当前请求资源范围内没有range指示值，请求    也不包含If-Range请求头字段
    public static final int HTTP_417 = 417;//417——服务器不满足请求Expect头字段指定的期望值，如果是代理服务器，可能是下一级服务器不能满足请求
    public static final int HTTP_500 = 500;//500——服务器产生内部错误
    public static final int HTTP_501 = 501;//501——服务器不支持请求的函数
    public static final int HTTP_502 = 502;//502——服务器暂时不可用，有时是为了防止发生系统过载
    public static final int HTTP_503 = 503;//503——服务器过载或暂停维修
    public static final int HTTP_504 = 504;//504——关口过载，服务器使用另一个关口或服务来响应用户，等待时间设定值较长
    public static final int HTTP_505 = 505;//505——服务器不支持或拒绝支请求头中指定的HTTP版本

}
