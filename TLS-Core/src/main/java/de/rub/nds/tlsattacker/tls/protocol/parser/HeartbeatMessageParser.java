/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2016 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsattacker.tls.protocol.parser;

import de.rub.nds.tlsattacker.tls.constants.HeartbeatByteLength;
import de.rub.nds.tlsattacker.tls.constants.ProtocolVersion;
import de.rub.nds.tlsattacker.tls.protocol.message.HeartbeatMessage;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Robert Merget - robert.merget@rub.de
 */
public class HeartbeatMessageParser extends ProtocolMessageParser<HeartbeatMessage> {

    private static final Logger LOGGER = LogManager.getLogger("PARSER");

    public HeartbeatMessageParser(int startposition, byte[] array, ProtocolVersion version) {
        super(startposition, array, version);
    }

    @Override
    protected HeartbeatMessage parseMessageContent() {
        HeartbeatMessage msg = new HeartbeatMessage();
        parseHeartbeatMessageType(msg);
        parsePayloadLength(msg);
        parsePayload(msg);
        parsePadding(msg);
        return msg;
    }

    private void parseHeartbeatMessageType(HeartbeatMessage msg) {
        msg.setHeartbeatMessageType(parseByteField(HeartbeatByteLength.TYPE));
        LOGGER.debug("HeartbeatMessageType: " + msg.getHeartbeatMessageType().getValue());
    }

    private void parsePayloadLength(HeartbeatMessage msg) {
        msg.setPayloadLength(parseIntField(HeartbeatByteLength.PAYLOAD_LENGTH));
        LOGGER.debug("PayloadLength: " + msg.getPayloadLength().getValue());
    }

    private void parsePayload(HeartbeatMessage msg) {
        msg.setPayload(parseByteArrayField(msg.getPayloadLength().getValue()));
        LOGGER.debug("Payload: " + Arrays.toString(msg.getPayload().getValue()));
    }

    private void parsePadding(HeartbeatMessage msg) {
        msg.setPadding(parseByteArrayField(getBytesLeft()));
        LOGGER.debug("Padding: " + Arrays.toString(msg.getPadding().getValue()));
    }

}
