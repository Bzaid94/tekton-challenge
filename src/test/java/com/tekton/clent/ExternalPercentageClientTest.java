package com.tekton.clent;

import com.tekton.client.ExternalPercentageClient;
import com.tekton.exception.ExternalServiceUnavailableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExternalPercentageClientTest {
    private final ExternalPercentageClient client = new ExternalPercentageClient();

    @Test
    void fetchPercentage_WhenServiceAvailable_Returns10() {
        assertEquals(10.0, client.fetchPercentage(false));
    }

    @Test
    void fetchPercentage_WhenServiceUnavailable_Throws() {
        assertThrows(ExternalServiceUnavailableException.class,
                () -> client.fetchPercentage(true));
    }

    @Test
    void getCachedPercentage_ReturnsNull() {
        assertNull(client.getCachedPercentage());
    }

}
