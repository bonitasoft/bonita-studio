package org.bonitasoft.studio.application.statistics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import org.bonitasoft.studio.application.event.ExtensionEvent;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.common.ProductVersion;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;

@ExtendWith(MockitoExtension.class)
class DefaultStatisticsManagerTest {

    @Mock
    private IEventBroker eventBroker;
    @Mock
    private StatisticsHttpClientBuilder httpClientBuilder;
    @Mock
    private HttpClient httpClient;

    @Test
    void initializeShouldSubscribeToInstallExtensionTpoic() {
        var manager = new DefaultStatisticsManager(eventBroker, httpClientBuilder);

        manager.initialize();

        verify(eventBroker).subscribe(ExtensionEvent.INSTALL_TOPIC, manager);
    }

    @Test
    void postExtensionInstalledEventWenEnabled() {
        var manager = new DefaultStatisticsManager(eventBroker, httpClientBuilder);
        var event = new ExtensionEvent.ExtensionInstalledEvent("g", "a", "v", ArtifactType.CONNECTOR);

        manager.extensionInstalled(event);

        verify(eventBroker).post(ExtensionEvent.INSTALL_TOPIC, event);
    }

    @Test
    void handleEventShouldSendRequest() {
        when(httpClientBuilder.get()).thenReturn(httpClient);
        var manager = new DefaultStatisticsManager(eventBroker, httpClientBuilder);
        var data = new ExtensionEvent.ExtensionInstalledEvent("g", "a", "v", ArtifactType.CONNECTOR);
        var event = createEvent(data);

        manager.handleEvent(event);

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient).sendAsync(requestCaptor.capture(), eq(BodyHandlers.discarding()));

        var request = requestCaptor.getValue();
        assertThat(request.uri())
                .hasToString(String.format(
                        "https://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=765&bos_redirect_major_version=%s&bos_redirect_minor_version=%s&bos_redirect_product=bos&groupId=g&artifactId=a&version=v&extensionType=connector",
                        ProductVersion.minorVersion(),
                        ProductVersion.maintenanceVersion()));
    }

    @Test
    void handleEventShouldthrowIllegalArgumentException() {
        var manager = new DefaultStatisticsManager(eventBroker, httpClientBuilder);
        var event = createEvent(Map.of());

        assertThrows(IllegalArgumentException.class, () -> manager.handleEvent(event));
    }

    private Event createEvent(Object data) {
        Dictionary<String, Object> d = new Hashtable<>(2);
        d.put(EventConstants.EVENT_TOPIC, ExtensionEvent.INSTALL_TOPIC);
        d.put(IEventBroker.DATA, data);
        return new Event(ExtensionEvent.INSTALL_TOPIC, d);
    }

}
