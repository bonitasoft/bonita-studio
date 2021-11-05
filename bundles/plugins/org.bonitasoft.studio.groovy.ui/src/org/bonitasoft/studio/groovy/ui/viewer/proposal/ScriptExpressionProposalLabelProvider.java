package org.bonitasoft.studio.groovy.ui.viewer.proposal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.businessobject.editor.editor.ui.styler.AttributeTypeStyler;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.Category;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptProposal;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

public class ScriptExpressionProposalLabelProvider extends StyledCellLabelProvider {

    private static final Set<String> PRIMITIVES = new HashSet<>();
    static {
        PRIMITIVES.add(Long.class.getName());
        PRIMITIVES.add(Integer.class.getName());
        PRIMITIVES.add(Double.class.getName());
        PRIMITIVES.add(Boolean.class.getName());
        PRIMITIVES.add(Float.class.getName());
        PRIMITIVES.add(String.class.getName());
        PRIMITIVES.add(Date.class.getName());
        PRIMITIVES.add(LocalDate.class.getName());
        PRIMITIVES.add(LocalDateTime.class.getName());
        PRIMITIVES.add(OffsetDateTime.class.getName());
    }
    
    private AttributeTypeStyler attributesSimpleTypeStyler;
    private AttributeTypeStyler attributesComplexTypeStyler;

    public ScriptExpressionProposalLabelProvider() {
        attributesSimpleTypeStyler = new AttributeTypeStyler(ColorConstants.SIMPLE_TYPE_RGB);
        attributesComplexTypeStyler = new AttributeTypeStyler(ColorConstants.COMPLEX_TYPE_RGB);
    }

    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString styledString = getStyledString(element);
        cell.setText(styledString.getString());
        cell.setImage(getImage(element));
        cell.setStyleRanges(styledString.getStyleRanges());
    }

    public StyledString getStyledString(Object element) {
        String name = element instanceof Category
                ? ((Category) element).getName()
                : ((ScriptProposal) element).getName();
        StyledString styledString = new StyledString(name);
        if (element instanceof ScriptProposal && ((ScriptProposal) element).getType() != null) {
            String fullyQualifiedType = ((ScriptProposal) element).getType();
            AttributeTypeStyler styler = isPrimitive(fullyQualifiedType) ? attributesSimpleTypeStyler 
                    : attributesComplexTypeStyler;
            String type = NamingUtils.getSimpleName(fullyQualifiedType);
            if(isList(fullyQualifiedType)) {
                type = "List<" + NamingUtils.getSimpleName(extractGenericType(fullyQualifiedType)) +">";
            }
            styledString.append(String.format(" %s", type), styler);
        }
        return styledString;
    }

    private boolean isPrimitive(String type) {
        if(isList(type)) {
           String extractedType = extractGenericType(type);
           return PRIMITIVES.contains(extractedType);
        }
        return PRIMITIVES.contains(type);
    }

    private boolean isList(String type) {
        return type.startsWith("List<");
    }

    private String extractGenericType(String type) {
        return type.substring(5,type.length()-1);
    }

    public Image getImage(Object element) {
        if (element instanceof Category) {
            return ((Category) element).getIcon();
        }
        if (element instanceof ScriptProposal) {
            return ((ScriptProposal) element).getIcon();
        }
        return null;
    }

    @Override
    public void dispose() {
        attributesSimpleTypeStyler.dispose();
        attributesComplexTypeStyler.dispose();
    }

}
