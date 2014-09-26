/*************************************************************************
 *                                                                       *
 *  EJBCA: The OpenSource Certificate Authority                          *
 *                                                                       *
 *  This software is free software; you can redistribute it and/or       *
 *  modify it under the terms of the GNU Lesser General Public           *
 *  License as published by the Free Software Foundation; either         *
 *  version 2.1 of the License, or any later version.                    *
 *                                                                       *
 *  See terms of license at gnu.org.                                     *
 *                                                                       *
 *************************************************************************/
package org.cesecore.keybind;

import java.security.cert.Certificate;
import java.util.Collection;

import javax.ejb.Local;

import org.cesecore.authentication.tokens.AuthenticationToken;
import org.cesecore.authorization.AuthorizationDeniedException;
import org.cesecore.certificates.ca.CADoesntExistsException;

/**
 * @see InternalKeyBindingMgmtSession
 * @version $Id$
 */
@Local
public interface InternalKeyBindingMgmtSessionLocal extends InternalKeyBindingMgmtSession {
        
    /**
     * Internal (local only) method to get keybinding info without logging the authorization check
     * (the auth check is performed though).
     * 
     * @see getInternalKeyBindingInfo
     */
    InternalKeyBindingInfo getInternalKeyBindingInfoNoLog(AuthenticationToken authenticationToken, int internalKeyBindingId) throws AuthorizationDeniedException;

    /**
     * Get a reference to a cached InternalKeyBinding object that MAY NOT be modified.
     * 
     * @param authenticationToken is the authentication token
     * @param internalKeyBindingId is the identifier of the InternalKeyBinding
     * @return the InternalKeyBinding for the requested Id or null if none was found
     * @throws AuthorizationDeniedException if the authentication token was not authorized to fetch the requested InternalKeyBinding
     */
    InternalKeyBinding getInternalKeyBindingReference(AuthenticationToken authenticationToken, int internalKeyBindingId) throws AuthorizationDeniedException;

    /**
     * Returns a collection of the trusted certificates defined in the specified InternalKeyBinding along with their issuers' certificate chains.
     * If only trusted CAs are specified, all certificates issued by the specified CAs will be trusted.
     * 
     * @param authenticationToken
     * @param internalKeyBinding
     * @return a collection of the trusted certificates along with their issuers' certificate chains or null if no trusted certificates or CAs are specified
     * @throws CADoesntExistsException
     * @throws AuthorizationDeniedException
     */
    public Collection< Collection<Certificate> > getListOfTrustedCertificates(AuthenticationToken authenticationToken, 
                    InternalKeyBinding internalKeyBinding) throws CADoesntExistsException, AuthorizationDeniedException;
}