package com.wine.delivery.push;


import java.io.Serializable;

/**
 * @author 柏江龙
 * @ClassName Message
 * @Description 消息实体
 * @date 2015-4-11
 */
public class Message implements Serializable {

    /**
     * 支付成功
     */
    public static final String PAY_SUCCESS = "pay_success";

    /**
     * 订单改变
     */
    public static final String ORDER_CHANGE = "order_change";

    /**
     * 优惠活动
     */
    public static final String PREFERENTIAL_ACTIVITIES = "preferential_activities";

    /**
     * 版本跟新
     */
    public static final String VERSION_UPGRADE = "version_upgrade";

    /**
     * 展示 信息
     */
    public String alertMessage;


    /**
     * 信息类型
     */
    public String messageType;


    /**
     * 更多消息实体
     */
    public Object moreMessage;

    /**
     * 是否阅读
     */
    public boolean isRead;
    /**
     * 消息时间
     */
    public String messageTime;

    /**
     * 消息索引
     */

    public String index;

    /**
     * 构造函数
     *
     * @param date    时间
     * @param message 消息
     * @param type    类型
     */
    public Message(String date, String message, MessageType type) {
        this.messageTime = date;

        this.alertMessage = message;

    }


    /**
     * 构造函数
     */
    public Message() {
    }

    /**
     * 获得消息类型
     *
     * @return 消息类型
     */
    public MessageType getMessageType() {
        MessageType type = MessageType.ORDER;
        if (messageType.equals(ORDER_CHANGE)) {
            type = MessageType.ORDER;
        } else if (messageType.equals(PAY_SUCCESS)) {
            type = MessageType.ORDER;
        } else if (messageType.equals(VERSION_UPGRADE)) {
            type = MessageType.VERSION;
        } else if (messageType.equals(PREFERENTIAL_ACTIVITIES)) {
            type = MessageType.COUPON;
        }
        return type;
    }

    /**
     * 消息类型实体
     */
    public enum MessageType {
        ORDER, COUPON, VERSION
    }
}
