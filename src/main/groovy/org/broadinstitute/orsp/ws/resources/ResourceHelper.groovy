/*
 * The Broad Institute
 * SOFTWARE COPYRIGHT NOTICE AGREEMENT
 * This software and its documentation are copyright 2014 by the
 * Broad Institute/Massachusetts Institute of Technology. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. Neither
 * the Broad Institute nor MIT can be responsible for its use, misuse, or functionality.
 */
package org.broadinstitute.orsp.ws.resources

import org.apache.commons.lang.CharEncoding

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

/**
 *
 * Created: 11/18/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
class ResourceHelper {

    public static void notFoundIfNull(Object obj) {
        errorIfNull(obj, Response.Status.NOT_FOUND)
    }

    public static void errorIfNull(Object obj, Response.Status status) {
        if (obj == null) {
            throw new WebApplicationException(status)
        }
    }

    public static String decode(String str) {
        URLDecoder.decode(str, CharEncoding.UTF_8)
    }


}