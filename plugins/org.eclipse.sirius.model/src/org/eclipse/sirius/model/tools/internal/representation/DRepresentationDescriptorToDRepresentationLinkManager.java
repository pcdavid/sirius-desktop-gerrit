/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.model.tools.internal.representation;

import java.util.HashMap;
import java.util.Optional;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * This class is intended to manage the link between the {@link DRepresentationDescriptor} and its
 * {@link DRepresentation} through the {@link DRepresentationDescriptor#repPath} attribute.
 * 
 * @author fbarbin
 *
 */
public class DRepresentationDescriptorToDRepresentationLinkManager {
    private DRepresentationDescriptor repDescriptor;

    /**
     * Default constructor.
     * 
     * @param repDescriptor
     *            the {@link DRepresentationDescriptor}.
     */
    public DRepresentationDescriptorToDRepresentationLinkManager(DRepresentationDescriptor repDescriptor) {
        this.repDescriptor = repDescriptor;
    }

    /**
     * Set the repPath attribute according to the given newRepresentation. This method should not be called by client.
     * Call {@link DRepresentationDescriptor#setRepresentation(DRepresentation)} instead.
     * 
     * @param representation
     *            the representation to set. Can be null. If the representation is not null, representation.eResource
     *            must not be null.
     */
    public void setRepresentation(DRepresentation representation) {
        if (representation != null) {
            String iD = representation.getUid();
            Optional.ofNullable(representation.eResource()).map(resource -> resource.getURI().appendFragment(iD)).ifPresent(uri -> repDescriptor.setRepPath(new ResourceDescriptor(uri)));
        } else {
            repDescriptor.setRepPath(null);
        }
    }

    /**
     * Retrieves the DRepresentation using the repPath attribute. This method should not be called by client. Call
     * {@link DRepresentationDescriptor#getRepresentation()} instead.
     * 
     * @param loadOnDemand
     *            whether to create and load the resource, if it doesn't already exists.
     * @return an Optional DRepresentation.
     */
    public Optional<DRepresentation> getRepresentation(boolean loadOnDemand) {
        Optional<DRepresentation> representation = getRepresentationInternal(false);
        if (loadOnDemand && !representation.isPresent()) {
            representation = getRepresentationInternal(true);

            representation.ifPresent(rep -> Optional.ofNullable(ECrossReferenceAdapter.getCrossReferenceAdapter(repDescriptor.eResource())).ifPresent(crossRef -> {
                crossRef.setTarget(repDescriptor);
                rep.eAdapters().add(crossRef);
            }));

            if (representation.isPresent()) {
                NotificationImpl setRepresentationNotification = new NotificationImpl(Notification.RESOLVE, null, representation.get()) {
                    @Override
                    public Object getNotifier() {
                        return repDescriptor;
                    }

                    @Override
                    public Object getFeature() {
                        return ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation();
                    }
                };
                repDescriptor.eNotify(setRepresentationNotification);
            }
        }

        return representation;
    }

    private Optional<DRepresentation> getRepresentationInternal(boolean loadOnDemand) {
        Optional<DRepresentation> dRepresentationOpt = Optional.empty();

        ResourceDescriptor resourceDescriptor = repDescriptor.getRepPath();
        Resource resource = repDescriptor.eResource();

        // We retrieve the URI from descriptor.
        Optional<URI> uri = Optional.ofNullable(resourceDescriptor).map(desc -> desc.getResourceURI());
        if (uri.isPresent()) {
            URI repResourceURI = uri.get().trimFragment();
            // We retrieve the representation resource from the uri.
            // If loadOnDemand equals true, call resourceSet.getResource(uri, true) only if the resource exists.
            // If loadOnDemand equals false, do not check the existence, the iteration over the resourceSet resources is
            // sufficient.
            //@formatter:off
            Optional<Resource> representationResource = Optional.ofNullable(resource)
                    .map(rsr -> rsr.getResourceSet())
                    .filter(resourceSet -> !loadOnDemand || resourceSet.getURIConverter().exists(repResourceURI, new HashMap<>()))
            //@formatter:on
                    .map(resourceSet -> {
                        Resource res = null;
                        try {
                            res = resourceSet.getResource(repResourceURI, loadOnDemand);
                            // CHECKSTYLE:OFF
                        } catch (RuntimeException e) {
                            // CHECKSTYLE:ON
                            // an exception may occur if the segment part is malformed or if the resource does not
                            // exists in case the representation is in its own resource.
                        }
                        return res;
                    });
            String repId = uri.get().fragment();
            if (representationResource.isPresent() && repId != null) {
                // We look for the representation with the repId (retrieved from
                // the uri fragment) within the representation resource.
                try {
                    dRepresentationOpt = representationResource.get().getContents().stream().filter(DRepresentation.class::isInstance).map(DRepresentation.class::cast)
                            .filter(dRepresentation -> repId.equals(dRepresentation.getUid())).findFirst();
                } catch (NullPointerException e) {
                    // In some particular condition, when the Sirius session is a collaborative session based on EMF
                    // CDO, DelegatingSessionProtocol.getSession throws a NPE when trying to load the object revision.
                    // It corresponds to a case when the session has been closed and the CDORevisionManagerImpl has been
                    // disposed.
                }
            }
        }
        return dRepresentationOpt;
    }
}
