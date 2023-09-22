package org.example.callback.ai;

import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import com.dingtalk.open.app.api.graph.GraphAPIRequest;
import com.dingtalk.open.app.api.graph.GraphAPIResponse;
import com.dingtalk.open.app.api.graph.StatusLine;
import com.dingtalk.open.app.api.models.ai.AIPluginHeaders;
import com.dingtalk.open.app.api.util.GraphUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AIGraphPluginCallbackListener implements OpenDingTalkCallbackListener<GraphAPIRequest, GraphAPIResponse> {
    @Override
    public GraphAPIResponse execute(GraphAPIRequest request) {
        log.info("receive AI graph plugin request={}", request);
        String abilityKey = request.getHeader(AIPluginHeaders.ABILITY_KEY_NAME);
        String pluginId = request.getHeader(AIPluginHeaders.PLUGIN_ID_NAME);
        String pluginVersion = request.getHeader(AIPluginHeaders.PLUGIN_VERSION_NAME);
        //业务数据的json字符串
        String data = request.getBody();
        //获取graph的路径请求{version}/{resource}
        String path = request.getRequestLine().getPath();
        return GraphUtils.failed(StatusLine.INTERNAL_ERROR);
    }
}
