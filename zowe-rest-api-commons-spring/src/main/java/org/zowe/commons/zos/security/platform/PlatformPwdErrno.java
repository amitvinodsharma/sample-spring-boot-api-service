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

import java.util.HashMap;
import java.util.Map;

/**
 * Provides explanation for error codes for authentication as described at
 * documentation for BPX4PWD:
 * https://www.ibm.com/support/knowledgecenter/SSLTBW_2.4.0/com.ibm.zos.v2r4.bpxb100/pwd.htm
 */
public enum PlatformPwdErrno {
    EACCES("EACCES", 111, "Permission is denied; the specified password is incorrect", PlatformErrorType.DEFAULT),
    EINVAL("EINVAL", 121, "Invalid input parameters", PlatformErrorType.DEFAULT),
    EMVSERR("EMVSERR", 157, "An MVS environmental error has been detected", PlatformErrorType.INTERNAL),
    EMVSEXPIRE("EMVSEXPIRE", 168, "The password for the specified identity has expired", PlatformErrorType.USER_EXPLAINED),
    EMVSPASSWORD("EMVSPASSWORD", 169, "The new password is not valid", PlatformErrorType.USER_EXPLAINED),
    EMVSSAF2ERR("EMVSSAF2ERR", 164, "An error occurred in the security product", PlatformErrorType.INTERNAL),
    EMVSSAFEXTRERR("EMVSSAFEXTRERR", 163, "A SAF authorization error has occurred", PlatformErrorType.DEFAULT),
    ENOSYS("ENOSYS", 134, "The function is not supported on this system", PlatformErrorType.INTERNAL),
    EPERM("EPERM", 139, "The calling address space is not authorized to use this service or a load from a not program-controlled library was done in the address space", PlatformErrorType.INTERNAL),
    ESRCH("ESRCH", 143, "The identity that was specified is not defined to the security product", PlatformErrorType.DEFAULT);

    private static final Map<Integer, PlatformPwdErrno> BY_ERRNO = new HashMap<>();

    static {
        for (PlatformPwdErrno e : values()) {
            BY_ERRNO.put(e.errno, e);
        }
    }

    public final String shortErrorName;
    public final int errno;
    public final String explanation;
    public final PlatformErrorType errorType;

    private PlatformPwdErrno(String shortErrorName, int errno, String explanation, PlatformErrorType errorType) {
        this.shortErrorName = shortErrorName;
        this.errno = errno;
        this.explanation = explanation;
        this.errorType = errorType;
    }

    public static PlatformPwdErrno valueOfErrno(int errno) {
        return BY_ERRNO.getOrDefault(errno, null);
    }
}
