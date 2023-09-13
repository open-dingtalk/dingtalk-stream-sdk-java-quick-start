package org.example.model;

import lombok.Data;

/**
 * @author zeymo
 */
@Data
public class DingTalkAGIPluginRequest {
    /**
     * plugin identity
     */
    private String pluginId;

    /**
     * plugin version
     */
    private String pluginVersion;

    /**
     * ability identity
     */
    private String abilityKey;

    /**
     * data from AIPaaS
     */
    private Object data;

    /**
     * request identity
     */
    private String requestId;
}
