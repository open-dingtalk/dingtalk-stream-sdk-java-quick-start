package org.example.model;

import lombok.Data;

/**
 * @author zeymo
 */
@Data
public class DingTalkAIPluginResponse {
    /**
     * response to AIPaaS
     */
    private Object result;
    /**
     * request identity
     */
    private String requestId;
}
