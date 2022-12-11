package concur.core.config;

import lombok.Getter;

@Getter
public enum Topic {
    COUNT("count");

    private final String topicName;

    Topic(String topicName) {
        this.topicName = topicName;
    }
}
