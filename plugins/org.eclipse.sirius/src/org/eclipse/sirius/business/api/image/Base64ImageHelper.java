/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.image;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * This helper provides utility methods to convert base64 string to an image.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class Base64ImageHelper {
    /**
     * Pattern to find the base64 string.
     */
    public static final String BASE64_IMAGE_PATTERN_WITH_SUBSTRINGS = "\"data:image/([a-zA-Z]{3,4});base64,(.*?)\""; //$NON-NLS-1$

    private static final String BASE64_IMAGE_PATTERN = "\"data:image/[a-zA-Z]{3,4};base64,.*?\""; //$NON-NLS-1$

    private static final String IMAGE_NAME_FORMAT = "yyyyMMdd_HHmmssSSS"; //$NON-NLS-1$

    /**
     * This method converts the base64 string from the String attribute of the given eObject<br/>
     * It creates a file, into the images folder of the project containing the eObject resource, and replaces the base64
     * string by a workspace relative path to the created file.
     * 
     * @param eObject
     *            the eObject to change
     * @param attr
     *            the attribute that must be of type String
     * @return A map
     */
    public Map<Object, String> createFileAndUpdateAttribute(EObject eObject, EAttribute attr) {
        // Matcher matcher = pattern.matcher(notification.getNewStringValue());
        String strValue = (String) eObject.eGet(attr);
        if (strValue != null) {
            Map<Object, String> createdFiles = createFiles(eObject, strValue);

            updateField(eObject, attr, strValue, createdFiles);
            return createdFiles;
        }

        return new HashMap<Object, String>();
    }

    private void updateField(EObject eObject, EAttribute attr, String strValue, Map<Object, String> createdFiles) {
        String newStringValue = strValue;
        // change the attribute value to replace with a path to the created file.
        for (String fileName : createdFiles.values()) {
            newStringValue = newStringValue.replaceFirst(BASE64_IMAGE_PATTERN, "\"" + fileName + "\""); //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (!Objects.equals(newStringValue, strValue)) {
            eObject.eSet(attr, newStringValue);
        }
    }

    private Map<Object, String> createFiles(EObject notifier, String strValue) {
        Pattern pattern = Pattern.compile(BASE64_IMAGE_PATTERN_WITH_SUBSTRINGS);
        Matcher matcher = pattern.matcher(strValue);
        Map<Object, String> createdFiles = new LinkedHashMap<>();

        while (matcher.find()) {
            if (matcher.groupCount() == 2) {
                ImageManager imageManager = ImageManagerProvider.getImageManager();
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat(IMAGE_NAME_FORMAT);
                String strDate = formatter.format(date);

                String simpleImageName = strDate + "." + matcher.group(1); //$NON-NLS-1$

                Map<Object, String> createFile = imageManager.createFile(notifier, simpleImageName, matcher.group(2));

                createdFiles.putAll(createFile);
            }
        }
        return createdFiles;
    }
}
