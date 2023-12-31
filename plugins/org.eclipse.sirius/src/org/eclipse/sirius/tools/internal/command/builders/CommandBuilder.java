/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.command.builders;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;

/**
 * A generic command builder interface.
 * 
 * @author mchauvin
 */
public interface CommandBuilder {

    /**
     * Initialize the builder.
     * 
     * @param accessor
     *            the model accessor
     * @param domain
     *            the editing domain
     * @param ui
     *            the ui callback
     */
    void init(ModelAccessor accessor, TransactionalEditingDomain domain, UICallBack ui);

    /**
     * Build a command.
     * 
     * @return the builded command
     */
    Command buildCommand();

}
