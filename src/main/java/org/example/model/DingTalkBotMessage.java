package org.example.model;

import lombok.Data;

import java.util.List;

/**
 * @author feiyin
 * @date 2023/6/30
 * 参考钉钉机器人官方说明 @see <a href=https://open.dingtalk.com/document/orgapp/the-application-robot-in-the-enterprise-sends-group-chat-messages/>
 */
@Data
public class DingTalkBotMessage {

    private String conversationId;

    private List<AtUser> atUsers;

    private String chatbotCorpId;


    private String chatbotUserId;

    private String msgId;

    private String senderNick;

    private String isAdmin;

    private String senderStaffId;

    private Long sessionWebhookExpiredTime;

    private Long createAt;

    private String senderCorpId;

    private String conversationType;

    private String  senderId;

    private String conversationTitle;

    private Boolean isInAtList;

    private Text text;

    private String msgtype;

}
