package com.delhivery.notifier.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by pradeep on 31/8/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {

    @JsonProperty("queue")
    private String queue;

    @JsonProperty("protocol")
    private String protocol;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("channel_drn")
    private String channelDRN;

    @JsonProperty("event")
    private MetaData event;

    private long index;

    private long time;

    public long getTime() {
        return time;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getChannelDRN() {
        return channelDRN;
    }

    public void setChannelDRN(String channelDRN) {
        this.channelDRN = channelDRN;
    }

    public MetaData getEvent() {
        return event;
    }

    public void setEvent(MetaData event) {
        this.event = event;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MetaData {
        @JsonProperty("endpoint")
        private String endpoint;

        @JsonProperty("data")
        private String data;

        @JsonProperty("method")
        private String method;

        @JsonProperty("headers")
        private Map<String, String> headers;

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

//        @JsonIgnoreProperties(ignoreUnknown = true)
//        public static class HeaderInfo {
//
//            @JsonProperty("Content-Type")
//            private String contentType;
//
//            public String getContentType() {
//                return contentType;
//            }
//
//            public void setContentType(String contentType) {
//                this.contentType = contentType;
//            }
//
//            //constructor to make test notification
//            public HeaderInfo(String contentType) {
//                this.contentType = contentType;
//            }
//
//            public HeaderInfo() {
//                //default constructor
//            }
//        }

        //constructor to make test notification
        public MetaData(String endpoint, String data, String method, Map<String, String> headers) {
            this.endpoint = endpoint;
            this.data = data;
            this.method = method;
            this.headers = headers;
        }

        public MetaData() {
            //default constructor
        }
    }

    // constructor to make test notification
    public Notification(String queue, String protocol, String accountId, String requestId, String channelDRN, MetaData event, long index) {
        this.queue = queue;
        this.protocol = protocol;
        this.accountId = accountId;
        this.requestId = requestId;
        this.channelDRN = channelDRN;
        this.event = event;
        this.time = System.nanoTime();
        this.index = index;
    }

    public Notification() {
        //default constructor
    }
}

