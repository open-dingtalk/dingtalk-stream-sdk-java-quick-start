package org.example.callback.robot;

import com.alibaba.fastjson.JSONObject;
import org.example.model.DingTalkBotMessage;
import org.example.model.Text;
import org.example.service.RobotGroupMessagesService;
import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zeymo
 */
@Slf4j
@Component
public class RobotMsgCallbackConsumer implements OpenDingTalkCallbackListener<DingTalkBotMessage, JSONObject> {
    private RobotGroupMessagesService robotGroupMessagesService;

    @Autowired
    public RobotMsgCallbackConsumer(RobotGroupMessagesService robotGroupMessagesService) {
        this.robotGroupMessagesService = robotGroupMessagesService;
    }

    /**
     * https://open.dingtalk.com/document/orgapp/the-application-robot-in-the-enterprise-sends-group-chat-messages
     *
     * @param message
     * @return
     */
    @Override
    public JSONObject execute(DingTalkBotMessage message) {
        try {
            Text text = message.getText();
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
