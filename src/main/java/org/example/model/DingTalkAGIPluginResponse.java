package org.example.model;

import lombok.Data;

/**
 * @author zeymo
 */
@Data
public class DingTalkAGIPluginResponse {
    /**
     * response to AIPaaS
     */
    private Object result;
    /**
     * request identity
     */
    private String requestId;
}
