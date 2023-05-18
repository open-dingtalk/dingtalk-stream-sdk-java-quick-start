package org.example.callback.robot;

import com.alibaba.fastjson.JSONObject;
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
public class RobotMsgCallbackConsumer implements OpenDingTalkCallbackListener<JSONObject, JSONObject> {
    private RobotGroupMessagesService robotGroupMessagesService;

    @Autowired
    public RobotMsgCallbackConsumer(RobotGroupMessagesService robotGroupMessagesService) {
        this.robotGroupMessagesService = robotGroupMessagesService;
    }

    /**
     * https://open.dingtalk.com/document/orgapp/the-application-robot-in-the-enterprise-sends-group-chat-messages
     *
     * @param request
     * @return
     */
    @Override
    public JSONObject execute(JSONObject request) {
        try {
            JSONObject text = request.getJSONObject("text");
            if (text != null) {
                String msg = text.getString("content").trim();
                log.info("receive bot message from user:" + request.get("senderId") + ", msg:" + msg);
                String openConversationId = request.getString("conversationId");
                try {
                    robotGroupMessagesService.send(openConversationId, "hello");
                } catch (Exception e) {
                    log.error("send group message by robot error:" + e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error("receive group message by robot error:" +e.getMessage(), e);
        }
        return new JSONObject();
    }
}
