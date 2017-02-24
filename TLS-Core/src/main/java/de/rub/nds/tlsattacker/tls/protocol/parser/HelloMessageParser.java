/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2016 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsattacker.tls.protocol.parser;

import de.rub.nds.tlsattacker.tls.constants.HandshakeByteLength;
import de.rub.nds.tlsattacker.tls.constants.HandshakeMessageType;
import de.rub.nds.tlsattacker.tls.protocol.handshake.HelloMessage;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An abstract Parser class for Hello Messages
 * 
 * @author Robert Merget - robert.merget@rub.de
 * @param <T>
 *            Type of the HelloMessage to parse
 */
public abstract class HelloMessageParser<T extends HelloMessage> extends HandshakeMessageParser<T> {

    private static final Logger LOGGER = LogManager.getLogger(HelloMessageParser.class);

    /**
     * Constructor for the Parser class
     * 
     * @param pointer
     *            Position in the array where the HelloMessageParser is supposed
     *            to start parsing
     * @param array
     *            The byte[] which the HelloMessageParser is supposed to parse
     * @param type
     *            Expected Type value for the Message
     */
    public HelloMessageParser(int pointer, byte[] array, HandshakeMessageType type) {
        super(pointer, array, type);
    }

    /**
     * Checks if the message has an ExtensionLength field, by checking if the
     * value specified in the length field is big enough to allow it.
     * 
     * @param message
     *            Message to check
     * @return True if the message has an Extension field
     */
    protected boolean hasExtensionLengthField(HelloMessage message) {
        return message.getLength().getValue() + HandshakeByteLength.MESSAGE_TYPE
                + HandshakeByteLength.MESSAGE_LENGTH_FIELD > getPointer() - getStartPoint();
    }

    /**
     * Checks if the ExtensionsLengthField has a value greater than Zero, eg. if
     * there are Extensions present.
     * 
     * @param message
     *            Message to check
     * @return True if the message has Extensions
     */
    protected boolean hasExtensions(HelloMessage message) {
        return message.getExtensionsLength().getValue() > 0;
    }

    /**
     * Reads the next bytes as a ProtocolVersion and writes them in the message
     * 
     * @param message
     *            Message to write in
     */
    protected void parseProtocolVersion(HelloMessage message) {
        message.setProtocolVersion(parseByteArrayField(HandshakeByteLength.VERSION));
        LOGGER.debug("ProtocolVersion:" + Arrays.toString(message.getProtocolVersion().getValue()));
    }

    /**
     * Reads the next bytes as a Unixtime and writes them in the message
     * 
     * @param message
     *            Message to write in
     */
    protected void parseUnixtime(HelloMessage message) {
        message.setUnixTime(parseByteArrayField(HandshakeByteLength.UNIX_TIME));
        LOGGER.debug("UnixTime:" + Arrays.toString(message.getUnixTime().getValue()));
    }

    /**
     * Reads the next bytes as a the Random and writes them in the message
     * 
     * @param message
     *            Message to write in
     */
    protected void parseRandom(HelloMessage message) {
        message.setRandom(parseByteArrayField(HandshakeByteLength.RANDOM));
        LOGGER.debug("Random:" + Arrays.toString(message.getRandom().getValue()));
    }

    /**
     * Reads the next bytes as the SessionID length and writes them in the
     * message
     * 
     * @param message
     *            Message to write in
     */
    protected void parseSessionIDLength(HelloMessage message) {
        message.setSessionIdLength(parseIntField(HandshakeByteLength.SESSION_ID_LENGTH));
        LOGGER.debug("SessionIDLength:" + message.getSessionIdLength().getValue());
    }

    /**
     * Reads the next bytes as the SessionID and writes them in the message
     * 
     * @param message
     *            Message to write in
     */
    protected void parseSessionID(HelloMessage message) {
        message.setSessionId(parseByteArrayField(message.getSessionIdLength().getOriginalValue()));
        LOGGER.debug("SessionID:" + Arrays.toString(message.getSessionId().getValue()));
    }

    /**
     * Reads the next bytes as the ExtensionLength and writes them in the
     * message
     * 
     * @param message
     *            Message to write in
     */
    protected void parseExtensionLength(HelloMessage message) {
        message.setExtensionsLength(parseIntField(HandshakeByteLength.EXTENSION_LENGTH));
        LOGGER.debug("ExtensionLength:" + message.getExtensionsLength().getValue());
    }

    /**
     * Reads the next bytes as the ExtensionBytes and writes them in the message
     * 
     * @param message
     *            Message to write in
     */
    protected void parseExtensionBytes(HelloMessage message) {
        message.setExtensionBytes(parseByteArrayField(message.getExtensionsLength().getValue()));
        LOGGER.debug("ExtensionBytes:" + message.getExtensionBytes().getValue());
    }
}