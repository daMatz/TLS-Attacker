/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2017 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsattacker.core.workflow.action;

import de.rub.nds.tlsattacker.core.exceptions.WorkflowExecutionException;
import de.rub.nds.tlsattacker.core.state.TlsContext;
import de.rub.nds.tlsattacker.core.workflow.action.executor.ActionExecutor;
import java.io.IOException;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Robert Merget - robert.merget@rub.de
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class TLSAction implements Serializable {

    protected static final Logger LOGGER = LogManager.getLogger("Action");

    private static final boolean EXECUTED_DEFAULT = false;

    private Boolean executed = null;

    public boolean isExecuted() {
        if (executed == null) {
            return EXECUTED_DEFAULT;
        }
        return executed;
    }

    public void setExecuted(Boolean executed) {
        this.executed = executed;
    }

    public abstract void execute(TlsContext tlsContext, ActionExecutor executor) throws WorkflowExecutionException,
            IOException;

    public boolean isMessageAction() {
        return this instanceof MessageAction;
    }

    public abstract void reset();
}