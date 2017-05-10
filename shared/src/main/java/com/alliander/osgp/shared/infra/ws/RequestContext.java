/**
 * Copyright 2017 Smart Society Services B.V.
 */
package com.alliander.osgp.shared.infra.ws;

import java.util.HashMap;
import java.util.Map;

public class RequestContext {

    public static final String BYPASS_RETRY = "BypassRetry";
    public static final String MESSAGE_PRIORITY = "MessagePriority";
    private final int messagePriority;
    private final boolean bypassRetry;

    private RequestContext(final boolean bypassRetryValue, final int messagePriority) {
        this.bypassRetry = bypassRetryValue;
        this.messagePriority = messagePriority;
    }

    public Map<String, String> asMap() {

        final Map<String, String> map = new HashMap<>();

        map.put(MESSAGE_PRIORITY, String.valueOf(this.messagePriority));
        map.put(BYPASS_RETRY, String.valueOf(this.bypassRetry));

        return map;
    }

    public int getMessagePriority() {
        return this.messagePriority;
    }

    public boolean isBypassRetry() {
        return this.bypassRetry;
    }

    public static class Builder {
        private int messagePriority = RequestPriority.DEFAULT_REQUEST_PRIORITY.getValue();
        private boolean bypassRetry = false;

        public Builder bypassRetryValue(final boolean bypassRetry) {
            this.bypassRetry = bypassRetry;
            return this;
        }

        public Builder messagePriority(final int messagePriority) {
            this.messagePriority = messagePriority;
            return this;
        }

        public RequestContext build() {
            return new RequestContext(this.bypassRetry, this.messagePriority);
        }
    }

}
