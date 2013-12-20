/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.util.Range;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * .
 * 
 * @author mporhel
 * 
 */
public final class ParentOperandFinder {

    private static Collection<Class<?>> types = new ArrayList<Class<?>>();
    {
        types.add(Lifeline.class);
        types.add(AbstractNodeEvent.class);
        types.add(Execution.class);
        types.add(State.class);
        types.add(InteractionUse.class);
        types.add(CombinedFragment.class);
        types.add(Message.class);
    }

    private Function<ISequenceEvent, Range> rangeFunction = ISequenceEvent.VERTICAL_RANGE;

    private ISequenceEvent event;

    /**
     * Default constructor.
     * 
     * @param event
     *            a supported {@link ISequenceEvent} : {@linkLifeline},
     *            {@link AbstractNodeEvent}, {@link Operand}.
     */
    public ParentOperandFinder(ISequenceEvent event) {
        Preconditions.checkArgument(types.contains(event.getClass()));
        Preconditions.checkNotNull(event);
        this.event = event;
    }

    /**
     * Default constructor.
     * 
     * @param event
     *            a supported {@link ISequenceEvent} : {@linkLifeline},
     *            {@link AbstractNodeEvent}, {@link Operand}.
     * @param rangeFunction
     *            function to compute expected range.
     */
    public ParentOperandFinder(ISequenceEvent event, Function<ISequenceEvent, Range> rangeFunction) {
        this(event);
        Preconditions.checkNotNull(rangeFunction);
        this.rangeFunction = rangeFunction;
    }

    /**
     * Find the parent operand if exists.
     * 
     * @return the potential parent operand.
     */
    public Option<Operand> getParentOperand() {
        Range verticalRange = rangeFunction.apply(event);
        return getParentOperand(verticalRange);
    }

    /**
     * Finds the deepest Operand container convering the range if existing.
     * 
     * @param verticalRange
     *            the range where to look for the deepest operand
     * @return the deepest Operand convering this lifeline at this range
     * @see ISequenceEvent#getParentOperand()
     */
    public Option<Operand> getParentOperand(final Range verticalRange) {
        SequenceDiagram diagram = event.getDiagram();
        Set<Operand> allOperands = diagram.getAllOperands();

        Predicate<Operand> coveredLifeline = new Predicate<Operand>() {
            // Filter the operands that cover the execution parent lifeline
            public boolean apply(Operand input) {
                Collection<Lifeline> computeCoveredLifelines = input.computeCoveredLifelines();
                return computeCoveredLifelines != null && computeCoveredLifelines.contains(event.getLifeline().get());
            }
        };

        Predicate<Operand> includingExecutionRange = new Predicate<Operand>() {
            // Filter the operands having a range that contains the execution
            // range (we consider the insertion point : lowerbound of range)
            public boolean apply(Operand input) {
                return rangeFunction.apply(input).includes(new Range(verticalRange.getLowerBound(), verticalRange.getLowerBound()));
                // return rangeFunction.apply(input).includes(verticalRange);
            }
        };

        Operand deepestCoveringOperand = null;

        for (Operand operand : Iterables.filter(allOperands, Predicates.and(coveredLifeline, includingExecutionRange))) {
            // Find the deepest operand among the filtered ones
            if (deepestCoveringOperand == null || rangeFunction.apply(deepestCoveringOperand).includes(rangeFunction.apply(operand))) {
                deepestCoveringOperand = operand;
            }
        }
        if (deepestCoveringOperand != null) {
            return Options.newSome(deepestCoveringOperand);
        } else {
            return Options.newNone();
        }
    }
}
