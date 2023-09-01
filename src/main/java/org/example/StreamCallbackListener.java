package org.example;

import org.example.callback.agi.AGIPluginCallbackConsumer;
import org.example.callback.robot.RobotMsgCallbackConsumer;
import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zeymo
 */
@Component
public class StreamCallbackListener {
    @Value("${app.appKey}")
    private String appKey;

    @Value("${app.appSecret}")
    private String appSecret;

    @Value("${robot.msg.topic}")
    private String robotMsgTopic;

    @Value("${agi.plugin.topic}")
    private String agiPluginTopic;

    private RobotMsgCallbackConsumer robotMsgCallbackConsumer;

    private AGIPluginCallbackConsumer agiPluginCallbackConsumer;

    public StreamCallbackListener(@Autowired RobotMsgCallbackConsumer robotMsgCallbackConsumer,
                                  @Autowired AGIPluginCallbackConsumer agiPluginCallbackConsumer) {
        this.robotMsgCallbackConsumer = robotMsgCallbackConsumer;
        this.agiPluginCallbackConsumer = agiPluginCallbackConsumer;
    }

    @PostConstruct
    public void init() throws Exception {
        // init stream client
        OpenDingTalkClient client = OpenDingTalkStreamClientBuilder
                .custom()
                .credential(new AuthClientCredential(appKey, appSecret))
                .registerCallbackListener(robotMsgTopic, robotMsgCallbackConsumer)
                .registerCallbackListener(agiPluginTopic, agiPluginCallbackConsumer)
                .build();
        client.start();
    }
}
