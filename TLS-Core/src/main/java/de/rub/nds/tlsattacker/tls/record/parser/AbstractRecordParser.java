/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2016 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsattacker.tls.record.parser;

import de.rub.nds.tlsattacker.tls.constants.ProtocolVersion;
import de.rub.nds.tlsattacker.tls.protocol.parser.Parser;

/**
 *
 * @author Robert Merget <robert.merget@rub.de>
 * @param <AbstractRecord>
 */
public abstract class AbstractRecordParser<AbstractRecord> extends Parser<AbstractRecord> {

    protected ProtocolVersion version;

    public AbstractRecordParser(int startposition, byte[] array, ProtocolVersion version) {
        super(startposition, array);
        this.version = version;
    }
}