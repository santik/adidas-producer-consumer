package com.adidas.producer.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class TestingSession {
    private static final String CHANNEL_PREFIX = "TestMessage-";
    private static final TestingSession INSTANCE = new TestingSession();

    private ConcurrentMap<String, Object> session = new ConcurrentHashMap<>();

    private TestingSession() {
    }

    public static TestingSession getInstance() {
        return INSTANCE;
    }

    public void putMessage(String channel, Object message) {
        String key = CHANNEL_PREFIX + channel;
        session.putIfAbsent(key, new ArrayList<>());
        ((List<Object>) session.get(key)).add(message);
    }

    public List<Object> getMessages(String channel) {
        String key = CHANNEL_PREFIX + channel;
        return (List<Object>) session.getOrDefault(key, new ArrayList<>());
    }
}
