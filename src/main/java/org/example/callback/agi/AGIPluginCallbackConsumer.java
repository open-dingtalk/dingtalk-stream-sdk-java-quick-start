package org.example.callback.agi;

import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import lombok.extern.slf4j.Slf4j;
import org.example.model.DingTalkAGIPluginOutput;
import org.example.model.DingTalkAGIPluginRequest;
import org.example.model.DingTalkAGIPluginResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AGIPluginCallbackConsumer implements OpenDingTalkCallbackListener<DingTalkAGIPluginRequest, DingTalkAGIPluginResponse> {
    @Override
    public DingTalkAGIPluginResponse execute(DingTalkAGIPluginRequest request) {
        log.info("receive AGI plugin request={}", request);
        DingTalkAGIPluginOutput output = new DingTalkAGIPluginOutput();
        switch (request.getAbilityKey()) {
            case "hello" :
                output.setOutput("echo");
                break;
            default:
                output.setOutput("no service");
        }
        DingTalkAGIPluginResponse response = new DingTalkAGIPluginResponse();
        response.setRequestId(request.getRequestId());
        response.setResult(output);
        return response;
    }
}
