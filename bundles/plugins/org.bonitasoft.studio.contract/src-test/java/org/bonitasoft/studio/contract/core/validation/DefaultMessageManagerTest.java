package org.bonitasoft.studio.contract.core.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultMessageManagerTest {

    private DefaultMessageManager defaultMessageManager;

    @Before
    public void setUp() throws Exception {
        defaultMessageManager = new DefaultMessageManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddMessageObjectStringObjectInt() throws Exception {
        defaultMessageManager.addMessage(null, null, null, 0);
    }

    @Test
    public void testAddMessageObjectStringObjectIntControl() throws Exception {
        defaultMessageManager.addMessage(null, null, null, 0, null);
    }

    @Test
    public void testCreateSummary() throws Exception {
        defaultMessageManager.createSummary(null);
    }

    @Test
    public void testGetDecorationPosition() throws Exception {
        defaultMessageManager.getDecorationPosition();
    }

    @Test
    public void testGetMessagePrefixProvider() throws Exception {
        defaultMessageManager.getMessagePrefixProvider();
    }

    @Test
    public void testIsAutoUpdate() throws Exception {
        defaultMessageManager.isAutoUpdate();
    }

    @Test
    public void testRemoveAllMessages() throws Exception {
        defaultMessageManager.removeAllMessages();
    }

    @Test
    public void testRemoveMessageObject() throws Exception {
        defaultMessageManager.removeMessage(null);
    }

    @Test
    public void testRemoveMessageObjectControl() throws Exception {
        defaultMessageManager.removeMessage(null, null);
    }

    @Test
    public void testRemoveMessages() throws Exception {
        defaultMessageManager.removeMessages();
    }

    @Test
    public void testRemoveMessagesControl() throws Exception {
        defaultMessageManager.removeMessages(null);
    }

    @Test
    public void testSetAutoUpdate() throws Exception {
        defaultMessageManager.setAutoUpdate(false);
    }

    @Test
    public void testSetDecorationPosition() throws Exception {
        defaultMessageManager.setDecorationPosition(0);
    }

    @Test
    public void testSetMessagePrefixProvider() throws Exception {
        defaultMessageManager.setMessagePrefixProvider(null);
    }

    @Test
    public void testUpdate() throws Exception {
        defaultMessageManager.update();
    }

}
