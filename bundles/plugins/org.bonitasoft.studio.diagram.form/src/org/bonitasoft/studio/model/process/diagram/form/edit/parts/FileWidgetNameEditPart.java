/*
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.diagram.form.edit.parts;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.IFrameWidget;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.diagram.form.edit.policies.ProcessTextSelectionEditPolicy;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessParserProvider;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.diagram.ui.label.ILabelDelegate;
import org.eclipse.gmf.runtime.diagram.ui.label.WrappingLabelDelegate;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.draw2d.labels.SimpleLabelDelegate;
import org.eclipse.gmf.tooling.runtime.edit.policies.DefaultNodeLabelDragPolicy;
import org.eclipse.gmf.tooling.runtime.edit.policies.labels.IRefreshableFeedbackEditPolicy;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class FileWidgetNameEditPart extends CompartmentEditPart implements ITextAwareEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 5189;

	/**
	* @generated
	*/
	private DirectEditManager manager;

	/**
	* @generated
	*/
	private IParser parser;

	/**
	* @generated
	*/
	private List<?> parserElements;

	/**
	* @generated
	*/
	private String defaultText;

	/**
	* @generated
	*/
	private ILabelDelegate labelDelegate;

	private final NotificationListener listener = new NotificationListener() {

		@Override
		public void notifyChanged(Notification event) {
			handleNotificationEvent(event);
		}
	};

	private final NotificationListener expressionListener = new NotificationListener() {

		@Override
		public void notifyChanged(Notification event) {
			java.lang.Object newValue = event.getNewValue();
			if (newValue != null) {
				DiagramEventBroker.getInstance(getEditingDomain()).addNotificationListener((EObject) newValue,
						ExpressionPackage.Literals.EXPRESSION__CONTENT, listener);
				listener.notifyChanged(new ENotificationImpl((InternalEObject) newValue,
						FeatureMapUtil.FeatureENotificationImpl.SET, ExpressionPackage.Literals.EXPRESSION__CONTENT,
						null, ((Expression) newValue).getContent()));
			}

		}
	};

	/**
	* @generated
	*/
	public FileWidgetNameEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	@Override
	public void activate() {
		super.activate();
		final EObject resolveSemanticElement = resolveSemanticElement();
		if (resolveSemanticElement instanceof Widget && !(resolveSemanticElement instanceof MessageInfo)
				&& !(resolveSemanticElement instanceof HiddenWidget) && !(resolveSemanticElement instanceof HtmlWidget)
				&& !(resolveSemanticElement instanceof IFrameWidget)) {
			Widget widget = (Widget) resolveSemanticElement;
			DiagramEventBroker.getInstance(getEditingDomain()).addNotificationListener(widget,
					FormPackage.Literals.WIDGET__DISPLAY_LABEL, expressionListener);
			expressionListener.notifyChanged(
					new ENotificationImpl((InternalEObject) widget, FeatureMapUtil.FeatureENotificationImpl.SET,
							FormPackage.Literals.WIDGET__DISPLAY_LABEL, null, widget.getDisplayLabel()));
			DiagramEventBroker.getInstance(getEditingDomain()).addNotificationListener(widget,
					FormPackage.Literals.WIDGET__SHOW_DISPLAY_LABEL, listener);
		}
	}

	@Override
	public void deactivate() {
		EObject widget = resolveSemanticElement();
		if (widget instanceof Widget && !(widget instanceof MessageInfo) && !(widget instanceof HiddenWidget)
				&& !(widget instanceof HtmlWidget) && !(widget instanceof IFrameWidget)) {
			DiagramEventBroker.getInstance(getEditingDomain())
					.removeNotificationListener(((Widget) widget).getDisplayLabel(), listener);
			DiagramEventBroker.getInstance(getEditingDomain()).removeNotificationListener(((Widget) widget),
					expressionListener);
		}
		super.deactivate();
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ProcessTextSelectionEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new org.bonitasoft.studio.common.editPolicies.WidgetLabelDirectEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new DefaultNodeLabelDragPolicy());
	}

	/**
	* @generated
	*/
	protected String getLabelTextHelper(IFigure figure) {
		if (figure instanceof WrappingLabel) {
			return ((WrappingLabel) figure).getText();
		} else if (figure instanceof Label) {
			return ((Label) figure).getText();
		} else {
			return getLabelDelegate().getText();
		}
	}

	/**
	* @generated using BonitaSoft aspects
	* 
	*/
	protected void setLabelTextHelper(IFigure figure, String text) {
		/*	org.eclipse.emf.ecore.EObject obj = resolveSemanticElement();
			if (obj != null && (obj instanceof org.bonitasoft.studio.model.form.Info 
								|| obj instanceof org.bonitasoft.studio.model.form.FormField 
								|| obj instanceof org.bonitasoft.studio.model.form.FileWidget 
								|| obj instanceof org.bonitasoft.studio.model.form.ImageWidget
								|| obj instanceof org.bonitasoft.studio.model.form.FormButton
								|| obj instanceof org.bonitasoft.studio.model.form.AbstractTable ))
			{
				org.bonitasoft.studio.model.form.Widget wid = (org.bonitasoft.studio.model.form.Widget) obj;
				if(wid.getShowDisplayLabel()==null ||wid.getShowDisplayLabel().booleanValue() ){
					if(wid.getDisplayLabel()!=null 
							&& wid.getDisplayLabel().getContent() != null) {
						text = wid.getDisplayLabel().getContent();
					}else{
						text= "";
					}
				}else{
					text = "";
				}
			}*/
		if (figure instanceof WrappingLabel) {
			((WrappingLabel) figure).setText(text);
		} else {
			((Label) figure).setText(text);
		}
	}

	/**
	* @generated
	*/
	protected Image getLabelIconHelper(IFigure figure) {
		if (figure instanceof WrappingLabel) {
			return ((WrappingLabel) figure).getIcon();
		} else if (figure instanceof Label) {
			return ((Label) figure).getIcon();
		} else {
			return getLabelDelegate().getIcon(0);
		}
	}

	/**
	* @generated
	*/
	protected void setLabelIconHelper(IFigure figure, Image icon) {
		if (figure instanceof WrappingLabel) {
			((WrappingLabel) figure).setIcon(icon);
			return;
		} else if (figure instanceof Label) {
			((Label) figure).setIcon(icon);
			return;
		} else {
			getLabelDelegate().setIcon(icon, 0);
		}
	}

	/**
	* @generated
	*/
	public void setLabel(WrappingLabel figure) {
		unregisterVisuals();
		setFigure(figure);
		defaultText = getLabelTextHelper(figure);
		registerVisuals();
		refreshVisuals();
	}

	/**
	* @generated
	*/
	@SuppressWarnings("rawtypes")
	protected List getModelChildren() {
		return Collections.EMPTY_LIST;
	}

	/**
	* @generated
	*/
	public IGraphicalEditPart getChildBySemanticHint(String semanticHint) {
		return null;
	}

	/**
	* @generated
	*/
	protected EObject getParserElement() {
		final EObject element = resolveSemanticElement();
		if (element instanceof Widget && !(element instanceof MessageInfo) && !(element instanceof HiddenWidget)
				&& !(element instanceof HtmlWidget) && !(element instanceof IFrameWidget)) {
			return ((Widget) element).getDisplayLabel();
		} else {
			return element;
		}
	}

	/**
	* @generated
	*/
	protected Image getLabelIcon() {
		return null;
	}

	/**
	* @generated
	*/
	protected String getLabelText() {
		String text = null;
		EObject parserElement = getParserElement();
		if (parserElement != null && getParser() != null) {
			text = getParser().getPrintString(new EObjectAdapter(parserElement), getParserOptions().intValue());
		}
		if (text == null || text.length() == 0) {
			text = defaultText;
		}
		return text;
	}

	/**
	* @generated
	*/
	public void setLabelText(String text) {
		setLabelTextHelper(getFigure(), text);
		refreshSelectionFeedback();
	}

	/**
	* @generated
	*/
	public String getEditText() {
		if (getParserElement() == null || getParser() == null) {
			return ""; //$NON-NLS-1$
		}
		return getParser().getEditString(new EObjectAdapter(getParserElement()), getParserOptions().intValue());
	}

	/**
	* @generated
	*/
	protected boolean isEditable() {
		return getParser() != null;
	}

	/**
	* @generated
	*/
	public ICellEditorValidator getEditTextValidator() {
		return new ICellEditorValidator() {

			public String isValid(final Object value) {
				if (value instanceof String) {
					final EObject element = getParserElement();
					final IParser parser = getParser();
					try {
						IParserEditStatus valid = (IParserEditStatus) getEditingDomain()
								.runExclusive(new RunnableWithResult.Impl<IParserEditStatus>() {

									public void run() {
										setResult(
												parser.isValidEditString(new EObjectAdapter(element), (String) value));
									}
								});
						return valid.getCode() == ParserEditStatus.EDITABLE ? null : valid.getMessage();
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}

				// shouldn't get here
				return null;
			}
		};
	}

	/**
	* @generated
	*/
	public IContentAssistProcessor getCompletionProcessor() {
		if (getParserElement() == null || getParser() == null) {
			return null;
		}
		return getParser().getCompletionProcessor(new EObjectAdapter(getParserElement()));
	}

	/**
	* @generated
	*/
	public ParserOptions getParserOptions() {
		return ParserOptions.NONE;
	}

	/**
	* @generated
	*/
	public IParser getParser() {
		if (parser == null) {
			parser = ProcessParserProvider.getParser(ProcessElementTypes.FileWidget_2133, getParserElement(),
					ProcessVisualIDRegistry.getType(
							org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidgetNameEditPart.VISUAL_ID));
		}
		return parser;
	}

	/**
	* @generated
	*/
	protected DirectEditManager getManager() {
		if (manager == null) {
			setManager(new TextDirectEditManager(this, null, ProcessEditPartFactory.getTextCellEditorLocator(this)));
		}
		return manager;
	}

	/**
	* @generated
	*/
	protected void setManager(DirectEditManager manager) {
		this.manager = manager;
	}

	/**
	* @generated
	*/
	protected void performDirectEdit() {
		getManager().show();
	}

	/**
	* @generated
	*/
	protected void performDirectEdit(Point eventLocation) {
		if (getManager().getClass() == TextDirectEditManager.class) {
			((TextDirectEditManager) getManager()).show(eventLocation.getSWTPoint());
		}
	}

	/**
	* @generated
	*/
	private void performDirectEdit(char initialCharacter) {
		if (getManager() instanceof TextDirectEditManager) {
			((TextDirectEditManager) getManager()).show(initialCharacter);
		} else //
		{
			performDirectEdit();
		}
	}

	/**
	* @generated
	*/
	protected void performDirectEditRequest(Request request) {
		final Request theRequest = request;
		try {
			getEditingDomain().runExclusive(new Runnable() {

				public void run() {
					if (isActive() && isEditable()) {
						if (theRequest.getExtendedData()
								.get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
							Character initialChar = (Character) theRequest.getExtendedData()
									.get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
							performDirectEdit(initialChar.charValue());
						} else if ((theRequest instanceof DirectEditRequest)
								&& (getEditText().equals(getLabelText()))) {
							DirectEditRequest editRequest = (DirectEditRequest) theRequest;
							performDirectEdit(editRequest.getLocation());
						} else {
							performDirectEdit();
						}
					}
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	* @generated
	*/
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshLabel();
		refreshFont();
		refreshFontColor();
		refreshUnderline();
		refreshStrikeThrough();
	}

	/**
	* @generated
	*/
	protected void refreshLabel() {
		setLabelTextHelper(getFigure(), getLabelText());
		setLabelIconHelper(getFigure(), getLabelIcon());
		refreshSelectionFeedback();
	}

	/**
	* @generated
	*/
	protected void refreshUnderline() {
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel) getFigure()).setTextUnderline(style.isUnderline());
		}
	}

	/**
	* @generated
	*/
	protected void refreshStrikeThrough() {
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel) getFigure()).setTextStrikeThrough(style.isStrikeThrough());
		}
	}

	/**
	* @generated
	*/
	protected void refreshFont() {
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null) {
			FontData fontData = new FontData(style.getFontName(), style.getFontHeight(),
					(style.isBold() ? SWT.BOLD : SWT.NORMAL) | (style.isItalic() ? SWT.ITALIC : SWT.NORMAL));
			setFont(fontData);
		}
	}

	/**
	* @generated
	*/
	private void refreshSelectionFeedback() {
		requestEditPolicyFeedbackRefresh(EditPolicy.PRIMARY_DRAG_ROLE);
		requestEditPolicyFeedbackRefresh(EditPolicy.SELECTION_FEEDBACK_ROLE);
	}

	/**
	* @generated
	*/
	private void requestEditPolicyFeedbackRefresh(String editPolicyKey) {
		Object editPolicy = getEditPolicy(editPolicyKey);
		if (editPolicy instanceof IRefreshableFeedbackEditPolicy) {
			((IRefreshableFeedbackEditPolicy) editPolicy).refreshFeedback();
		}
	}

	/**
	* @generated
	*/
	protected void setFontColor(Color color) {
		getFigure().setForegroundColor(color);
	}

	/**
	* @generated
	*/
	protected void addSemanticListeners() {
		if (getParser() instanceof ISemanticParser) {
			EObject element = resolveSemanticElement();
			parserElements = ((ISemanticParser) getParser()).getSemanticElementsBeingParsed(element);
			for (int i = 0; i < parserElements.size(); i++) {
				addListenerFilter("SemanticModel" + i, this, (EObject) parserElements.get(i)); //$NON-NLS-1$
			}
		} else {
			super.addSemanticListeners();
		}
	}

	/**
	* @generated
	*/
	protected void removeSemanticListeners() {
		if (parserElements != null) {
			for (int i = 0; i < parserElements.size(); i++) {
				removeListenerFilter("SemanticModel" + i); //$NON-NLS-1$
			}
		} else {
			super.removeSemanticListeners();
		}
	}

	/**
	* @generated
	*/
	protected AccessibleEditPart getAccessibleEditPart() {
		if (accessibleEP == null) {
			accessibleEP = new AccessibleGraphicalEditPart() {

				public void getName(AccessibleEvent e) {
					e.result = getLabelTextHelper(getFigure());
				}
			};
		}
		return accessibleEP;
	}

	/**
	* @generated
	*/
	private View getFontStyleOwnerView() {
		return (View) getModel();
	}

	/**
	* @generated
	*/
	private ILabelDelegate getLabelDelegate() {
		if (labelDelegate == null) {
			IFigure label = getFigure();
			if (label instanceof WrappingLabel) {
				labelDelegate = new WrappingLabelDelegate((WrappingLabel) label);
			} else {
				labelDelegate = new SimpleLabelDelegate((Label) label);
			}
		}
		return labelDelegate;
	}

	/**
	* @generated
	*/
	@Override
	public Object getAdapter(Class key) {
		if (ILabelDelegate.class.equals(key)) {
			return getLabelDelegate();
		}
		return super.getAdapter(key);
	}

	/**
	* @generated
	*/
	protected void addNotationalListeners() {
		super.addNotationalListeners();
		addListenerFilter("PrimaryView", this, getPrimaryView()); //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	protected void removeNotationalListeners() {
		super.removeNotationalListeners();
		removeListenerFilter("PrimaryView"); //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	protected void handleNotificationEvent(Notification event) {
		Object feature = event.getFeature();
		if (NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
			Integer c = (Integer) event.getNewValue();
			setFontColor(DiagramColorRegistry.getInstance().getColor(c));
		} else if (NotationPackage.eINSTANCE.getFontStyle_Underline().equals(feature)) {
			refreshUnderline();
		} else if (NotationPackage.eINSTANCE.getFontStyle_StrikeThrough().equals(feature)) {
			refreshStrikeThrough();
		} else if (NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(feature)
				|| NotationPackage.eINSTANCE.getFontStyle_FontName().equals(feature)
				|| NotationPackage.eINSTANCE.getFontStyle_Bold().equals(feature)
				|| NotationPackage.eINSTANCE.getFontStyle_Italic().equals(feature)) {
			refreshFont();
		} else {
			if (getParser() != null && getParser().isAffectingEvent(event, getParserOptions().intValue())) {
				refreshLabel();
			}
			if (getParser() instanceof ISemanticParser) {
				ISemanticParser modelParser = (ISemanticParser) getParser();
				if (modelParser.areSemanticElementsAffected(null, event)) {
					removeSemanticListeners();
					if (resolveSemanticElement() != null) {
						addSemanticListeners();
					}
					refreshLabel();
				}
			}
		}
		if (ExpressionPackage.eINSTANCE.getExpression_Content().equals(feature)) {
			refreshLabel();
		} else if (FormPackage.eINSTANCE.getWidget_ShowDisplayLabel().equals(feature)) {
			java.lang.Object newValue = event.getNewValue();
			if (newValue instanceof Boolean) {
				getNotationView().setVisible((Boolean) newValue);
				refreshVisibility();
			}
		}
		super.handleNotificationEvent(event);

	}

	/**
	* @generated
	*/
	protected IFigure createFigure() {
		// Parent should assign one using setLabel() method
		return null;
	}

}
