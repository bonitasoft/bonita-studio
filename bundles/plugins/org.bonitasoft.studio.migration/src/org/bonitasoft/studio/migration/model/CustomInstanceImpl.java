package org.bonitasoft.studio.migration.model;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl;

public class CustomInstanceImpl extends InstanceImpl {

    @Override
    public void set(EStructuralFeature feature, Object newValue) {
        if (feature.isMany()) {
            final Collection<?> oldValues = (Collection<?>) this.get(feature);
            for (final Object value : oldValues) {
                this.remove(feature, value);
            }
            final Collection<?> newValues = (Collection<?>) newValue;
            for (final Object value : newValues) {
                this.add(feature, value);
            }
        } else {
            //Bonita Patch for Notation model
            if (newValue instanceof List<?> && feature.getEType() != null && feature.getEType().getInstanceClass() != null
                    && Collection.class.isAssignableFrom(feature.getEType().getInstanceClass())) {
                final Object oldValue = this.get(feature);
                if (oldValue != newValue) {
                    if (isSet(feature) && oldValue != null) {
                        this.remove(feature, oldValue);
                    }
                    if (newValue != null) {
                        this.add(feature, newValue);
                    }
                }
            } else if (newValue instanceof List<?> && feature.getEType() != null
                    && feature.getEType().isInstance(EcorePackage.Literals.EJAVA_OBJECT)) {
                final Object oldValue = this.get(feature);
                if (oldValue != newValue) {
                    if (isSet(feature) && oldValue != null) {
                        this.remove(feature, oldValue);
                    }
                    if (newValue != null) {
                        this.add(feature, newValue);
                    }
                }
            } else if (newValue instanceof List<?> && feature.getEType() != null && feature.getEType() instanceof EEnum) {
                final Object oldValue = this.get(feature);
                if (oldValue != newValue) {
                    if (isSet(feature) && oldValue != null) {
                        this.remove(feature, oldValue);
                    }
                    if (newValue != null) {
                        this.add(feature, newValue);
                    }
                }
            } else {
                if (newValue instanceof List<?>) {
                    throw new IllegalArgumentException("Single value expected, but list found"); //$NON-NLS-1$
                }
                final Object oldValue = this.get(feature);
                if (oldValue != newValue || feature.isUnsettable()) {
                    if (isSet(feature) && oldValue != null) {
                        this.remove(feature, oldValue);
                    }
                    if (newValue != null || feature.isUnsettable()) {
                        this.add(feature, newValue);
                    }
                }
            }
        }
    }
    
}
