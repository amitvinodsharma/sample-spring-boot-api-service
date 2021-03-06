/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package org.zowe.commons.zos.security.platform;

/**
 * Creates classes for platform-specific behavior. The purpose is wrap original
 * classes without interfaces and with static methods into classes that
 * implement interfaces. Implementations of this class factory can either return
 * the original z/OS classes or dummy implementations to enable unit testing or
 * running off z/OS for development purposes.
 */
public interface PlatformClassFactory {
    Class<?> getPlatformReturnedClass() throws ClassNotFoundException;

    Class<?> getPlatformUserClass() throws ClassNotFoundException;

    Object getPlatformUser();

    Class<?> getPlatformAccessControlClass() throws ClassNotFoundException;

    Object getPlatformAccessControl() throws ClassNotFoundException;

    PlatformReturned convertPlatformReturned(Object safReturned)
            throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException;
}
