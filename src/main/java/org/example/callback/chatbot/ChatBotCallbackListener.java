package org.example.callback.chatbot;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import com.dingtalk.open.app.api.models.bot.MessageContent;
import org.example.service.RobotGroupMessagesService;
import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 机器人消息回调
 *
 * @author zeymo
 */
@Slf4j
@Component
public class ChatBotCallbackListener implements OpenDingTalkCallbackListener<ChatbotMessage, JSONObject> {
    private RobotGroupMessagesService robotGroupMessagesService;

    @Autowired
    public ChatBotCallbackListener(RobotGroupMessagesService robotGroupMessagesService) {
        this.robotGroupMessagesService = robotGroupMessagesService;
    }

    /**
     * https://open.dingtalk.com/document/orgapp/the-application-robot-in-the-enterprise-sends-group-chat-messages
     *
     * @param message
     * @return
     */
    @Override
    public JSONObject execute(ChatbotMessage message) {
        try {
            MessageContent text = message.getText();
            if (text != null) {
                String msg = text.getContent();
                log.info("receive bot message from user={}, msg={}", message.getSenderId(), msg);
                String openConversationId = message.getConversationId();
                try {
                    //发送机器人消息
                    robotGroupMessagesService.send(openConversationId, "hello");
                } catch (Exception e) {
                    log.error("send group message by robot error:" + e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error("receive group message by robot error:" + e.getMessage(), e);
        }
        return new JSONObject();
    }
}
