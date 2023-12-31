/*******************************************************************************
 * Copyright (c) 2013 Robin Stocker and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Robin Stocker - extracted API out of CellLabelProvider
 *******************************************************************************/
package org.eclipse.sirius.ext.jface.viewers;

/**
 * Interface to provide tool tip information for a given element.
 *
 * @see org.eclipse.jface.viewers.CellLabelProvider
 *
 * @since 3.10
 */
public interface IToolTipProvider {

    /**
     * Get the text displayed in the tool tip for object.
     *
     * @param element
     *            the element for which the tool tip is shown
     * @return the {@link String} or <code>null</code> if there is not text to
     *         display
     */
    String getToolTipText(Object element);

}
