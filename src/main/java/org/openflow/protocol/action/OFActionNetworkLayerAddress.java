/**
*    Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior
*    University
* 
*    Licensed under the Apache License, Version 2.0 (the "License"); you may
*    not use this file except in compliance with the License. You may obtain
*    a copy of the License at
*
*         http://www.apache.org/licenses/LICENSE-2.0
*
*    Unless required by applicable law or agreed to in writing, software
*    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
*    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
*    License for the specific language governing permissions and limitations
*    under the License.
**/

/**
 * @author David Erickson (daviderickson@cs.stanford.edu) - Mar 11, 2010
 */
package org.openflow.protocol.action;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.floodlightcontroller.packet.IPv4;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Represents an ofp_action_nw_addr
 * @author David Erickson (daviderickson@cs.stanford.edu) - Mar 11, 2010
 */
public abstract class OFActionNetworkLayerAddress extends OFAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1332767673988606333L;

	public static int MINIMUM_LENGTH = 8;

    protected int networkAddress;

    /**
     * @return the networkAddress
     */
    public int getNetworkAddress() {
        return networkAddress;
    }

    /**
     * @param networkAddress the networkAddress to set
     */
    public void setNetworkAddress(int networkAddress) {
        this.networkAddress = networkAddress;
    }

    @Override
    public void readFrom(ChannelBuffer data) {
        super.readFrom(data);
        this.networkAddress = data.readInt();
    }

    @Override
    public void writeTo(ChannelBuffer data) {
        super.writeTo(data);
        data.writeInt(this.networkAddress);
    }

    public void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
    	this.networkAddress = stream.readInt();
    }
    
    public void writeObject(ObjectOutputStream stream) throws IOException{
    	super.writeObject(stream);
    	stream.writeInt(this.networkAddress);
    }
    
    @Override
    public int hashCode() {
        final int prime = 353;
        int result = super.hashCode();
        result = prime * result + networkAddress;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof OFActionNetworkLayerAddress)) {
            return false;
        }
        OFActionNetworkLayerAddress other = (OFActionNetworkLayerAddress) obj;
        if (networkAddress != other.networkAddress) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(type);
        builder.append("[");
        builder.append(IPv4.fromIPv4Address(networkAddress));
        builder.append("]");
        return builder.toString();
    }
}