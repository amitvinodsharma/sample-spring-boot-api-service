/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package org.zowe.commons.rest.response;

import org.junit.Test;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.junit.Assert.*;

public class BasicMessageTest {
    private Message getTestErrorMessage() {
        return new BasicMessage(MessageType.ERROR, "MAS0001", "Message text.");
    }

    private Message getTestInfoMessage() {
        return new BasicMessage(MessageType.INFO, "MAS0002", "Message text.");
    }

    @Test
    public void toReadableText() {
        Message message = getTestErrorMessage();
        assertEquals("MAS0001E Message text.", message.toReadableText());
    }

    @Test
    public void testHashCode() {
        Message message = getTestErrorMessage();
        assertNotEquals(0, message.hashCode());
    }

    @Test
    public void testEquals() {
        Message message = getTestErrorMessage();
        Message sameMessage = message;
        Message differentMessage = getTestInfoMessage();
        assertTrue(message.equals(sameMessage));
        assertFalse(message.equals(differentMessage));
    }

    @Test
    public void basicJsonFormat() {
        assertThatJson(getTestErrorMessage()).whenIgnoringPaths("messageInstanceId").isEqualTo("{\n" + "  \"messageType\" : \"ERROR\",\n"
                + "  \"messageNumber\" : \"MAS0001\",\n" + "  \"messageContent\" : \"Message text.\"\n" + "}");
    }
}
