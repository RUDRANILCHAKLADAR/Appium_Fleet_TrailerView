package core.http;

/**
 * A friendly http client exception.
 *
 */
public class HttpClientException extends Exception {
    public HttpClientException(String msg) {
        super(msg);
    }
    public HttpClientException(Throwable throwable) {
        super(throwable);
    }
}
