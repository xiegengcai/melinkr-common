package com.melinkr.common;

/**
 * 常量类,所有常量集中管理
 * Created by miller on 2016/9/7.
 */
public interface Constant {

    /**
     * 指令码定义
     */
    interface Commands {
        // 终端指令
        short stopService         = 103;//国际加速开关
        // 后台指令
        short reportFlashId       = 501;// 上报FlashID
        short terminalAuth        = 502;// 终端认证
        short queryTerminalInfo   = 503;// 查询终端信息
        short heartbeat           = 504;// 心跳
        short getCaptcha          = 505;// 获取验证码
        short personalActivate    = 506;// 个人激活
        short corpActivate        = 507;// 公司激活
        short queryControlParams  = 508;// 获取控制参数
        short queryVPSList        = 509;// 查询VPS
        short applyAccount        = 510;// 申请账号
        short useInfoReport       = 511;// 使用信息上报
        short warnReport          = 512;// 告警上报
        short testResultReport    = 513;// 测试结果上报
        short remoteControl       = 514;// 发送远程控制
        short check4Update        = 515;// 升级检测
        short remoteControlResult = 516;// 远程控制结果上报
        short pushMessage         = 517;// 推送消息
        short vpsAmount           = 518;// 标签及可用VPS数量获取
    }

    enum TopicTags {
        REMOTE_CONTROL          //远程控制消息
        ,NOTICE                 //通知消息
        ,OTA_UPGRADE            //OTA升级
        ,OTA_TASK_ACTIVATION    //OTA任务激活
        ,SERVICE_CHANGE         //服务状态变更
    }

    enum MedelTypes {
        KF1
        , KF1S
        , KF2
        , KF2S
        , KF3
        , SR1
        , SR2
        , SR3
    }

    int PAGE_SIZE = 20;//默认每页展示数
    //excel2003扩展名
    String EXCEL03_EXTENSION = ".xls";
    //excel2007扩展名
    String EXCEL07_EXTENSION = ".xlsx";

    public enum LikeType {
        PRE_MATCH, POST_MATCH, BOTH;
    }

    /**
     * 通用状态枚举
     */
    public enum CommonStatus {
        INVALID     // 0 无效
        , VALID     // 1 有效
        ;
    }

    /**
     * datatables Column
     */
    enum  Column {
        data,
        name,
        searchable,
        orderable,
        searchValue,
        searchRegex
    }
    /**
     * datatables Search
     */
    enum Search{
        value,
        regex
    }
    /**
     * datatables Order
     */
    enum Order {
        column,
        dir
    }

}