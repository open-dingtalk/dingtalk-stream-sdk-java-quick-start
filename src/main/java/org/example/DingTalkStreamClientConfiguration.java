package org.example;

import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.callback.OpenDingTalkStreamTopics;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import org.example.callback.ai.AIGraphPluginCallbackListener;
import org.example.callback.chatbot.ChatBotCallbackListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zeymo
 */
@Configuration
public class DingTalkStreamClientConfiguration {

    @Value("${app.appKey}")
    private String clientId;
    @Value("${app.appSecret}")
    private String clientSecret;

    /**
     * 配置OpenDingTalkClient客户端并配置初始化方法(start)
     *
     * @param chatBotCallbackListener
     * @param aiGraphPluginCallbackListener
     * @return
     * @throws Exception
     */
    @Bean(initMethod = "start")
    public OpenDingTalkClient configureStreamClient(@Autowired ChatBotCallbackListener chatBotCallbackListener,
                                                    @Autowired AIGraphPluginCallbackListener aiGraphPluginCallbackListener) throws Exception {
        // init stream client
        return OpenDingTalkStreamClientBuilder.custom().preEnv()
                //配置应用的身份信息, 企业内部应用分别为appKey和appSecret, 三方应用为suiteKey和suiteSecret
                .credential(new AuthClientCredential(clientId, clientSecret))
                //注册机器人回调
                .registerCallbackListener(OpenDingTalkStreamTopics.BOT_MESSAGE_TOPIC, chatBotCallbackListener)
                //注册graph api回调
                .registerCallbackListener(OpenDingTalkStreamTopics.GRAPH_API_TOPIC, aiGraphPluginCallbackListener).build();
    }
}
