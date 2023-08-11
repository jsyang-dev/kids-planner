package info.kidsplanner.common.infrastructure;

public interface ConnectionProperties {
    String getDomain();
    Integer getConnectionTimeout();
    Integer getResponseTimeout();
    Integer getRetryMaxAttempts();
    Integer getRetryMaxDelay();
}
