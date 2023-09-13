package org.example.callback.ai;

import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import lombok.extern.slf4j.Slf4j;
import org.example.model.DingTalkAIPluginRequest;
import org.example.model.DingTalkAIPluginResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AIPluginCallbackConsumer implements OpenDingTalkCallbackListener<DingTalkAIPluginRequest, DingTalkAIPluginResponse> {
    @Override
    public DingTalkAIPluginResponse execute(DingTalkAIPluginRequest request) {
        log.info("receive AI plugin request={}", request);
        String abilityKey = request.getAbilityKey();
        // dos something with abilityKey
        DingTalkAIPluginResponse response = new DingTalkAIPluginResponse();
        response.setRequestId(request.getRequestId());
        response.setResult("echo");
        return response;
    }
}
