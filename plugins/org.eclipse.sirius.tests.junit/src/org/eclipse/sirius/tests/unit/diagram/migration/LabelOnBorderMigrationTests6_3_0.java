/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.internal.migration.LabelOnBorderMigrationParticipant;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Version;

/**
 * A test ensuring that labels on border locations are "reverted" because of the revert of feature of bugzilla 549887
 * that allowed more locations for label on border (all around the node). With this data, it is supposed to have some
 * change to really revert some coordinates (on contrary to {@link LabelOnBorderMigrationTestsBefore6_3_0}).
 * 
 * @author Laurent Redor
 * 
 */
public class LabelOnBorderMigrationTests6_3_0 extends SiriusDiagramTestCase {

    /**
     * Folder containing data before migration and moves done in 6.3.0.
     */
    private static final String TEST_FOLDER_PATH_BEFORE_6_3_0 = "/data/unit/migration/do_not_migrate/labelOnBorder";

    /**
     * Folder containing data after migration and moves done in 6.3.0.
     */
    private static final String TEST_FOLDER_PATH_6_3_0 = "/data/unit/migration/do_not_migrate/labelOnBorder6.3.0";

    private static final String SESSION_RESOURCE_FILENAME = "My.aird";

    private static final String SEMANTIC_RESOURCE_FILENAME = "My.ecore";

    private static final String VSM_RESOURCE_FILENAME = "My.odesign";

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String VSM_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_FILENAME;

    private static final Point DIAGRAM1_EXPECTED_GMF_NODE_LABEL_LOCATION = new Point(0, -22);

    private static final Point DIAGRAM1_INITIAL_GMF_BORDER_NODE_LABEL_LOCATION = new Point(-10, 6);

    private static final Point DIAGRAM2_INITIAL_GMF_NODE_LABEL_LOCATION = new Point(-13, 81);

    private static final Point DIAGRAM2_INITIAL_GMF_BORDER_NODE_LABEL_LOCATION = new Point(-51, 31);

    private static final Point DIAGRAM3_INITIAL_GMF_NODE_LABEL_LOCATION = new Point(140, 137);

    private static final Point DIAGRAM3_INITIAL_GMF_BORDER_NODE_LABEL_LOCATION = new Point(-134, 13);

    private static final int EXPECTED_LABEL_HEIGHT = 21;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, TEST_FOLDER_PATH_6_3_0 + "/" + SESSION_RESOURCE_FILENAME,
                "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, TEST_FOLDER_PATH_BEFORE_6_3_0 + "/" + SEMANTIC_RESOURCE_FILENAME,
                "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, TEST_FOLDER_PATH_BEFORE_6_3_0 + "/" + VSM_RESOURCE_FILENAME,
                "/" + TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_FILENAME);
        genericSetUp(SEMANTIC_MODEL_PATH, VSM_MODEL_PATH, SESSION_PATH);
    }

    /**
     * Ensures that the data used in the test requires migration.
     */
    public void testMigrationIsNeededOnData() {
        Version versionOfDAnalysis = checkRepresentationFileMigrationStatus(
                URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + TEST_FOLDER_PATH_BEFORE_6_3_0 + "/" + SESSION_RESOURCE_FILENAME, true), true);

        // Check that the migration is needed.
        Version migration = LabelOnBorderMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", versionOfDAnalysis == null || migration.compareTo(versionOfDAnalysis) > 0);
    }

    /**
     * Check locations of labels in a diagram freshly created (without moving labels) .
     * 
     * @throws Exception
     *             if unexpected issue occurs while performing this test
     */
    @Test
    public void testLabelCoordinatesOfFreshlyCreatedDiagram() throws Exception {
        DDiagram diagram = (DDiagram) getRepresentationsByName("diagramFreshlyCreated").toArray()[0];
        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.synchronizationWithUIThread();
        // Check that label of node is graphically centered
        IAbstractDiagramNodeEditPart nodeEditPart = (IAbstractDiagramNodeEditPart) getEditPart(diagram.getDiagramElements().get(0), editorPart);
        assertNotNull("The diagram should contain one node.", nodeEditPart);
        Rectangle nodeBounds = nodeEditPart.getFigure().getBounds();
        DNodeNameEditPart nodeLabelEditPart = getOnlyElement(nodeEditPart.getChildren(), DNodeNameEditPart.class);
        Node gmfNode = (Node) nodeLabelEditPart.getNotationView();
        Dimension labelSize;
        if (Display.getCurrent() != null) {
            labelSize = GMFHelper.getLabelDimension(gmfNode, new Dimension(50, 20));
        } else {
            // We call the method GMFHelper.getLabelDimension in UI Thread to have "real dimension".
            RunnableWithResult<Dimension> getLabelDimension = new RunnableWithResult.Impl<Dimension>() {
                @Override
                public void run() {
                    setResult(GMFHelper.getLabelDimension(gmfNode, new Dimension(50, 20)));
                }
            };
            EclipseUIUtil.displaySyncExec(getLabelDimension);
            labelSize = getLabelDimension.getResult();
        }
        if (labelSize.height() != EXPECTED_LABEL_HEIGHT) {
            // The migration participant is based on the label height. If it is not the expected height, the OS is not
            // adapted to this test and the result is not representative.
            return;
        }

        Rectangle labelBounds = nodeLabelEditPart.getFigure().getBounds();
        assertEquals("Wrong x coordinate for the label: The label must be centered to its node.", nodeBounds.x() - ((labelBounds.width() - nodeBounds.width()) / 2), labelBounds.x());
        assertEquals("Wrong y coordinate for the label: The label must be on top of its node with one pixel between node and label.",
                nodeBounds.y() - labelBounds.height + IBorderItemOffsets.NO_OFFSET.height, labelBounds.y());
        // Check that GMF coordinates (model) of label of node have been changed
        Location gmfNodeLabelLocation = (Location) gmfNode.getLayoutConstraint();
        assertEquals("Wrong x GMF coordinate for the label: x should be reset to 0", DIAGRAM1_EXPECTED_GMF_NODE_LABEL_LOCATION.x(), gmfNodeLabelLocation.getX());
        assertEquals("Wrong y GMF coordinate for the label: y should be not changed.", DIAGRAM1_EXPECTED_GMF_NODE_LABEL_LOCATION.y(), gmfNodeLabelLocation.getY());

        // Check that GMF coordinates (model) of label of border node have not been changed (because it is not a label
        // to keep centered)
        AbstractDiagramBorderNodeEditPart borderNodeEditPart = getOnlyElement(nodeEditPart.getChildren(), AbstractDiagramBorderNodeEditPart.class);
        DNodeNameEditPart borderNodeLabelEditPart = getOnlyElement(borderNodeEditPart.getChildren(), DNodeNameEditPart.class);
        Location gmfBorderNodeLabelLocation = ((Location) ((Node) borderNodeLabelEditPart.getNotationView()).getLayoutConstraint());
        assertEquals("Wrong x GMF coordinate for the label: x should be the same because it is not a label to keep centered (not concerned by the migration)",
                DIAGRAM1_INITIAL_GMF_BORDER_NODE_LABEL_LOCATION.x(), gmfBorderNodeLabelLocation.getX());
        assertEquals("Wrong y GMF coordinate for the label: y should be the same because it is not a label to keep centered (not concerned by the migration)",
                DIAGRAM1_INITIAL_GMF_BORDER_NODE_LABEL_LOCATION.y(), gmfBorderNodeLabelLocation.getY());
    }

    /**
     * Check locations of labels in a diagram where labels have been manually moved. The GMF locations are already and
     * must remains the same.
     * <UL>
     * <LI>The label of node was centered on bottom of node. It must stay like this (and GMF coordinates are already
     * done).</LI>
     * <LI>The label of border node was centered on bottom of node. It must stay like this (and GMF coordinates are
     * already done).</LI>
     * </UL>
     * 
     * @throws Exception
     *             if unexpected issue occurs while performing this test
     */
    @Test
    public void testLabelCoordinatesOfDiagramWithMovedLabels() throws Exception {
        DDiagram diagram = (DDiagram) getRepresentationsByName("diagramWithMovedLabels").toArray()[0];
        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.synchronizationWithUIThread();
        // Check that label of node is graphically centered
        IAbstractDiagramNodeEditPart nodeEditPart = (IAbstractDiagramNodeEditPart) getEditPart(diagram.getDiagramElements().get(0), editorPart);
        assertNotNull("The diagram should contain one node.", nodeEditPart);
        Rectangle nodeBounds = nodeEditPart.getFigure().getBounds();
        DNodeNameEditPart nodeLabelEditPart = getOnlyElement(nodeEditPart.getChildren(), DNodeNameEditPart.class);
        Rectangle nodeLabelBounds = nodeLabelEditPart.getFigure().getBounds();
        assertEquals("Wrong x coordinate for the label: The label must be centered to its node.", nodeBounds.x() - ((nodeLabelBounds.width() - nodeBounds.width()) / 2), nodeLabelBounds.x());
        assertEquals("Wrong y coordinate for the label: The label must be on bottom of its node with one pixel between node and label.", nodeBounds.bottom() - IBorderItemOffsets.NO_OFFSET.height,
                nodeLabelBounds.y());
        // Check that GMF coordinates (model) of label of node have been changed (revert of previous migration)
        Location gmfNodeLabelLocation = ((Location) ((Node) nodeLabelEditPart.getNotationView()).getLayoutConstraint());
        assertNotSame("Wrong x GMF coordinate for the label: x should not be the same because it has been migrated with the previous migration participant in 6.3.0",
                DIAGRAM2_INITIAL_GMF_NODE_LABEL_LOCATION.x(), gmfNodeLabelLocation.getX());
        assertEquals("Wrong y GMF coordinate for the label: y should be the same because the previous migration participant in 6.3.0 changes nothing for y",
                DIAGRAM2_INITIAL_GMF_NODE_LABEL_LOCATION.y(), gmfNodeLabelLocation.getY());

        // Check that label of border node is graphically centered
        AbstractDiagramBorderNodeEditPart borderNodeEditPart = getOnlyElement(nodeEditPart.getChildren(), AbstractDiagramBorderNodeEditPart.class);
        assertNotNull("The diagram should contain one border node.", borderNodeEditPart);
        Rectangle borderNodeBounds = borderNodeEditPart.getFigure().getBounds();
        DNodeNameEditPart borderNodeLabelEditPart = getOnlyElement(borderNodeEditPart.getChildren(), DNodeNameEditPart.class);
        Rectangle borderNodelabelBounds = borderNodeLabelEditPart.getFigure().getBounds();
        assertEquals("Wrong x coordinate for the label: The label must be centered to its node.", borderNodeBounds.x() - ((borderNodelabelBounds.width() - borderNodeBounds.width()) / 2),
                borderNodelabelBounds.x());
        assertEquals("Wrong y coordinate for the label: The label must be on bottom of its node with one pixel between node and label.",
                borderNodeBounds.bottom() - IBorderItemOffsets.NO_OFFSET.height, borderNodelabelBounds.y());

        // Check that GMF coordinates (model) of label of border node have been changed (revert of previous migration)
        Location gmfBorderNodeLabelLocation = ((Location) ((Node) borderNodeLabelEditPart.getNotationView()).getLayoutConstraint());
        assertNotSame("Wrong x GMF coordinate for the label: x should not be the same because it has been migrated with the previous migration participant in 6.3.0",
                DIAGRAM2_INITIAL_GMF_BORDER_NODE_LABEL_LOCATION.x(), gmfBorderNodeLabelLocation.getX());
        assertEquals("Wrong y GMF coordinate for the label: y should be the same because the previous migration participant in 6.3.0 changes nothing for y",
                DIAGRAM2_INITIAL_GMF_BORDER_NODE_LABEL_LOCATION.y(), gmfBorderNodeLabelLocation.getY());
    }

    /**
     * Check locations of labels in a diagram where labels have been manually moved. The GMF locations are already and
     * must remains the same.
     * <UL>
     * <LI>The label of node was centered on bottom of node. It must stay like this (and GMF coordinates are already
     * done).</LI>
     * <LI>The label of border node was centered on bottom of node. It must stay like this (and GMF coordinates are
     * already done).</LI>
     * </UL>
     * 
     * @throws Exception
     *             if unexpected issue occurs while performing this test
     */
    @Test
    public void testLabelCoordinatesOfDiagramWithMovedLabelsInSirius6_3_0() throws Exception {
        DDiagram diagram = (DDiagram) getRepresentationsByName("diagramWithMovedLabelsInSirius6.3.0").toArray()[0];
        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.synchronizationWithUIThread();
        // Check that GMF coordinates (model) of label of node have been changed (to respect bounds of Node)
        IAbstractDiagramNodeEditPart nodeEditPart = (IAbstractDiagramNodeEditPart) getEditPart(diagram.getDiagramElements().get(0), editorPart);
        assertNotNull("The diagram should contain one node.", nodeEditPart);
        DNodeNameEditPart nodeLabelEditPart = getOnlyElement(nodeEditPart.getChildren(), DNodeNameEditPart.class);
        Location gmfNodeLabelLocation = ((Location) ((Node) nodeLabelEditPart.getNotationView()).getLayoutConstraint());
        assertNotSame("Wrong x GMF coordinate for the label: x should not be the same because it has been moved manually in Sirius 6.3.0 out of the bounds of Node.",
                DIAGRAM3_INITIAL_GMF_NODE_LABEL_LOCATION.x(), gmfNodeLabelLocation.getX());
        assertEquals("Wrong y GMF coordinate for the label: y should be the same because the label is not on East or West side.", DIAGRAM3_INITIAL_GMF_NODE_LABEL_LOCATION.y(),
                gmfNodeLabelLocation.getY());

        // Check that GMF coordinates (model) of label of border node have been changed (to respect bounds of
        // BorderNode)
        AbstractDiagramBorderNodeEditPart borderNodeEditPart = getOnlyElement(nodeEditPart.getChildren(), AbstractDiagramBorderNodeEditPart.class);
        assertNotNull("The diagram should contain one border node.", borderNodeEditPart);
        DNodeNameEditPart borderNodeLabelEditPart = getOnlyElement(borderNodeEditPart.getChildren(), DNodeNameEditPart.class);
        Location gmfBorderNodeLabelLocation = ((Location) ((Node) borderNodeLabelEditPart.getNotationView()).getLayoutConstraint());
        assertEquals("Wrong x GMF coordinate for the label: x should be the same because the label is not on North or South side.", DIAGRAM3_INITIAL_GMF_BORDER_NODE_LABEL_LOCATION.x(),
                gmfBorderNodeLabelLocation.getX());
        assertNotSame("Wrong y GMF coordinate for the label: y should not be the same because it has been moved manually in Sirius 6.3.0 out of the bounds of Node.",
                DIAGRAM3_INITIAL_GMF_BORDER_NODE_LABEL_LOCATION.y(), gmfBorderNodeLabelLocation.getY());
    }

    /**
     * Return the only item in the list. This method fails the test if the list contains 0 or more than one element.
     * 
     * @param list
     *            The list containing the element
     * @param t
     *            The type of the element to be returned from this list
     * @return the only item in the list
     */
    protected <T> T getOnlyElement(List<?> list, Class<T> t) {
        Optional<T> optionalElement = list.stream().filter(child -> t.isInstance(child)).map(t::cast).findFirst();
        if (optionalElement.isPresent()) {
            if (list.stream().filter(child -> t.isInstance(child)).map(t::cast).count() > 1) {
                fail("The list contains several elements of type \"" + t.getCanonicalName() + "\".");
            }
            return optionalElement.get();
        } else {
            fail("The list does not contain one element of type \"" + t.getCanonicalName() + "\".");
        }
        return null;
    }
}
