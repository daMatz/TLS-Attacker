/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2016 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsattacker.tls.protocol.handler;

import de.rub.nds.tlsattacker.tls.protocol.message.UnknownMessage;
import de.rub.nds.tlsattacker.tls.protocol.parser.Parser;
import de.rub.nds.tlsattacker.tls.protocol.parser.UnknownMessageParser;
import de.rub.nds.tlsattacker.tls.protocol.preparator.Preparator;
import de.rub.nds.tlsattacker.tls.protocol.preparator.UnknownMessagePreparator;
import de.rub.nds.tlsattacker.tls.protocol.serializer.Serializer;
import de.rub.nds.tlsattacker.tls.protocol.serializer.UnknownMessageSerializer;
import de.rub.nds.tlsattacker.tls.workflow.TlsContext;

/**
 *
 * @author Robert Merget - robert.merget@rub.de
 */
public class UnknownMessageHandler extends ProtocolMessageHandler<UnknownMessage> {

    public UnknownMessageHandler(TlsContext tlsContext) {
        super(tlsContext);
    }

    @Override
    protected Parser getParser(byte[] message, int pointer) {
        return new UnknownMessageParser(pointer, message);
    }

    @Override
    protected Preparator getPreparator(UnknownMessage message) {
        return new UnknownMessagePreparator(tlsContext, message);
    }

    @Override
    protected Serializer getSerializer(UnknownMessage message) {
        return new UnknownMessageSerializer(message);
    }

    @Override
    protected void adjustTLSContext(UnknownMessage message) {
        // Nothing to do
    }

}
