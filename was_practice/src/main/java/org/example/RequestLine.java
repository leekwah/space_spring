package org.example;

import java.util.Objects;

public class RequestLine {
    private final String method;
    private final String urlPath;
    private String queryString;
    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryString = queryString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryString, that.queryString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryString);
    }

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        this.urlPath = tokens[1];

        String[] urlPathTokens = tokens[1].split("\\?");

        if(urlPathTokens.length == 2) {
            this.queryString = urlPathTokens[1];
        }
    }

}