package core.http;

import lombok.Data;

import java.util.Map;

/**
 * Http response wrapper.
 *
 */
@Data
public class Response<T> {
    private int statusCode;
    private String statusText;
    private Map<String, String> headers;
    private String rawBody;
    private T parsedObject;
}