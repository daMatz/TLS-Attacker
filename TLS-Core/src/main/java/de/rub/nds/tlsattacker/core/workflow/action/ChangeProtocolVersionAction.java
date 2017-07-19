/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2017 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsattacker.core.workflow.action;

import de.rub.nds.tlsattacker.core.constants.ProtocolVersion;
import de.rub.nds.tlsattacker.core.exceptions.WorkflowExecutionException;
import de.rub.nds.tlsattacker.core.state.TlsContext;
import static de.rub.nds.tlsattacker.core.workflow.action.TLSAction.LOGGER;
import de.rub.nds.tlsattacker.core.workflow.action.executor.ActionExecutor;
import java.util.Objects;

/**
 * 
 * @author Robert Merget - robert.merget@rub.de
 */
public class ChangeProtocolVersionAction extends TLSAction {
    private ProtocolVersion newValue;
    private ProtocolVersion oldValue = null;

    public ChangeProtocolVersionAction(ProtocolVersion newValue) {
        super();
        this.newValue = newValue;
    }

    public ChangeProtocolVersionAction() {
    }

    public void setNewValue(ProtocolVersion newValue) {
        this.newValue = newValue;
    }

    public ProtocolVersion getNewValue() {
        return newValue;
    }

    public ProtocolVersion getOldValue() {
        return oldValue;
    }

    @Override
    public void execute(TlsContext tlsContext, ActionExecutor executor) throws WorkflowExecutionException {
        if (isExecuted()) {
            throw new WorkflowExecutionException("Action already executed!");
        }
        oldValue = tlsContext.getSelectedProtocolVersion();
        tlsContext.setSelectedProtocolVersion(newValue);
        LOGGER.info("Changed ProtocolVersion from " + oldValue.name() + " to " + newValue.name());
        setExecuted(true);
    }

    @Override
    public void reset() {
        oldValue = null;
        setExecuted(null);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.newValue);
        hash = 83 * hash + Objects.hashCode(this.oldValue);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChangeProtocolVersionAction other = (ChangeProtocolVersionAction) obj;
        if (this.newValue != other.newValue) {
            return false;
        }
        return this.oldValue == other.oldValue;
    }

}