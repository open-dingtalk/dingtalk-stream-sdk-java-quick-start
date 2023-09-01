package org.example.model;

import lombok.Data;

import java.util.Map;

/**
 * @author zeymo
 */
@Data
public class DingTalkAGIPluginInput {
    /**
     * user input
     */
    private String userInput;

    /**
     * userId
     */
    private String userId;

    /**
     * corpId
     */
    private String corpId;

    /**
     * scenario context
     */
    private Map<String, Object> scenarioContext;
}
