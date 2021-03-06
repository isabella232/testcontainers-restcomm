/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2018, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *8
 * This program is distributed in the hope that it will be useful,
 * but OUT ANY WARRANTY; out even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 *  along  this program.  If not, see <http://www.gnu.org/licenses/>
 */

package org.restcomm.testcontainers.olympus;

import org.junit.Test;
import org.restcomm.testcontainers.RestcommIntegrationTest;

/**
 * @author oleg.agafonov@telestax.com (Oleg Agafonov)
 */
public class OlympusTest extends RestcommIntegrationTest {

    @Test
    public void p2pCallTest() throws Exception {
        olympus
                .login(ALICE, PASSWORD)
                .login(BOB, PASSWORD)
                .switchToWindow(BOB)
                .call(ALICE)
                .awaitCallInProgress()
                .switchToWindow(ALICE)
                .awaitCallInProgress(BOB)
                .answerCall();

        // just for showcase...
        Thread.sleep(3000);

        olympus
                .hangupCall()
                .awaitCallFinished()
                .switchToWindow(BOB)
                .awaitCallFinished(ALICE);
    }
}
