/**
*    Copyright 2011, Big Switch Networks, Inc. 
*    Originally created by David Erickson, Stanford University
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

package org.openflow.protocol.serializers;

import java.io.IOException;
import org.openflow.protocol.statistics.OFFlowStatisticsReply;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.openflow.protocol.OFFeaturesReply;
import org.openflow.protocol.serializers.OFMatchJSONSerializer;
import org.openflow.util.HexString;

public class OFFlowStatisticsReplyJSONSerializer extends JsonSerializer<OFFlowStatisticsReply> {
    
    /**
     * Performs the serialization of a OFFeaturesReply object
     */
    @Override
    public void serialize(OFFlowStatisticsReply reply, JsonGenerator jGen, SerializerProvider serializer) throws IOException, JsonProcessingException {
        OFMatchJSONSerializer matchSer = new OFMatchJSONSerializer();
        //OFActionListJSONSerializer actionListSer = new OFActionListJSONSerializer();
    	jGen.writeStartObject();
        jGen.writeNumberField("tableId", reply.getTableId());
        jGen.writeNumberField("cookie", reply.getCookie());
        jGen.writeNumberField("priority", reply.getPriority());
        jGen.writeNumberField("length", reply.getLength());
        jGen.writeNumberField("hardTimeout",reply.getHardTimeout());
        jGen.writeNumberField("idleTimeout", reply.getIdleTimeout());
        jGen.writeNumberField("duration_sec", reply.getDurationSeconds());
        jGen.writeNumberField("duration_nsec", reply.getDurationNanoseconds());
        jGen.writeNumberField("byteCount", reply.getByteCount());
        jGen.writeNumberField("packetCount", reply.getPacketCount());
        matchSer.serialize(reply.getMatch(), jGen, serializer);
        //actionListSer.serialize(reply.getActions(), jGen, serializer);
        jGen.writeEndObject();
    }

    /**
     * Tells SimpleModule that we are the serializer for OFFeaturesReply
     */
    @Override
    public Class<OFFlowStatisticsReply> handledType() {
        return OFFlowStatisticsReply.class;
    }
}
