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
/*
 * Copyright 2005-2006 MULTICERT S.A.
 * All rights reserved.
 */
package org.ejbca.core.model.ca.certificateprofiles;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.asn1.x509.PolicyQualifierId;


/** Class encapsulating the CertificatePolicy X509 certificate extensions. See rfc3280.
 * Contains an OID and optionally a policy qualifier. Several CertificatePolicy classes 
 * can be created with the same oid, for different qualifiers
 * 
 * @author Nuno Ponte of MultiCert
 * @version $Id: CertificatePolicy.java,v 1.2 2007-11-09 11:41:24 anatom Exp $
 */
public class CertificatePolicy implements Serializable, Cloneable {
    private static final long serialVersionUID = -6384137742329979249L;

    // Policy qualifier Ids are taken from BC classes
    public static final String id_qt_cps = PolicyQualifierId.id_qt_cps.getId();
    public static final String id_qt_unotice = PolicyQualifierId.id_qt_unotice.getId();
    
    /**
     * The special <code>anyPolicy</code> policy OID.
     */
    public static final String ANY_POLICY_OID = "2.5.29.32.0";
    
    private String policyID;
    /** CPS uri */
    private String qualifierId;
    /** user notice text */
    private String qualifier;

    public CertificatePolicy() {
        super();
    }

    /**
     * 
     * @param policyID
     * @param notice user notice text
     * @param uri cps uri
     */
    public CertificatePolicy(String policyID, String qualifierId, String qualifier) {
        this.policyID = policyID;
        this.qualifierId = qualifierId;
        this.qualifier = qualifier;
    }

    /**
     * @return the policyID
     */
    public String getPolicyID() {
        return this.policyID;
    }

    /**
     * @param policyID the policyID to set
     */
    public void setPolicyID(String policyID) {
        this.policyID = policyID;
    }

    
    /**
     * @return the qualifier string
     */
    public String getQualifier() {
        return this.qualifier;
    }

    
    /**
     * @param uri the uri to set
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * @return the QualifierId
     */
    public String getQualifierId() {
        return this.qualifierId;
    }

    
    /**
     * @param qualifierId the QualifierId to set
     */
    public void setQualifierId(String qualifierId) {
        this.qualifierId = qualifierId;
    }
    
    /**
     * @see java.lang.Object#clone()
     */
    protected Object clone() {
        return new CertificatePolicy(this.policyID, this.qualifierId, this.qualifier);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer strBuffer = new StringBuffer("CertificatePolicy(");

        strBuffer.append("policyID=");
        strBuffer.append(this.policyID);
        strBuffer.append(", qualifierId=");
        strBuffer.append(this.qualifierId);
        strBuffer.append(", qualifier=");
        strBuffer.append(this.qualifier);
        strBuffer.append(")");

        return strBuffer.toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if((obj == null) || !(obj instanceof CertificatePolicy)) {
            return false;
        }
        CertificatePolicy policy = (CertificatePolicy) obj;

        // We want to let both null and "" be the same value here, i.e. an empty value
        // Simply because, especially in gui code, it is somewhat tricky to trust which is a non-existant value
        boolean policyeq = false;
        if (StringUtils.isEmpty(policy.getPolicyID()) && StringUtils.isEmpty(this.policyID)) {
        	policyeq = true;
        } else if (StringUtils.equals(policy.getPolicyID(), this.policyID)) {
        	policyeq = true;
        }
        boolean qualifierideq = false;
        if (StringUtils.isEmpty(policy.getQualifierId()) && StringUtils.isEmpty(this.qualifierId)) {
        	qualifierideq = true;
        } else if (StringUtils.equals(policy.getQualifierId(), this.qualifierId)) {
        	qualifierideq = true;
        }
        boolean qualifier = false;
        if (StringUtils.isEmpty(policy.getQualifier()) && StringUtils.isEmpty(this.qualifier)) {
        	qualifier = true;
        } else if (StringUtils.equals(policy.getQualifier(), this.qualifier)) {
        	qualifier = true;
        }
        return policyeq && qualifierideq && qualifier; 
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

}
