//package org.bonitasoft.studio.decision.ui.condition;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.bonitasoft.studio.decision.core.DecisionTableUtil;
//import org.bonitasoft.studio.decision.ui.DecisionTableWizard;
//import org.bonitasoft.studio.groovy.ui.widgets.AbstractComboElementsProvider;
//import org.bonitasoft.studio.groovy.ui.widgets.ComboElementsProvider;
//import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
//import org.bonitasoft.studio.model.process.Element;
//import org.bonitasoft.studio.model.process.ProcessPackage;
//import org.bonitasoft.studio.model.process.SequenceFlow;
//import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
//import org.bonitasoft.studio.model.process.decision.DecisionTable;
//import org.bonitasoft.studio.model.process.decision.DecisionTableAction;
//import org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction;
//import org.bonitasoft.studio.model.process.decision.transitions.TransitionsFactory;
//import org.eclipse.emf.common.command.CompoundCommand;
//import org.eclipse.emf.ecore.EClass;
//import org.eclipse.emf.edit.command.SetCommand;
//import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
//import org.eclipse.emf.edit.domain.EditingDomain;
//import org.eclipse.jface.dialogs.Dialog;
//import org.eclipse.jface.viewers.IElementComparer;
//import org.eclipse.jface.wizard.WizardDialog;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.widgets.Shell;
//
//public class DecisionTableComboProvider extends AbstractComboElementsProvider implements ComboElementsProvider {
//
//	public static final String SKIP_DECISION_TABLE_PROVIDER = "Skip Decision Table combo provider";
//
//	public DecisionTableComboProvider() {
//	}
//
//	public boolean appliesTo(TextOrData textOrData) {
//		if (Boolean.TRUE.equals(textOrData.getControl().getData(SKIP_DECISION_TABLE_PROVIDER))) {
//			return false;
//		}
//		return textOrData.getElement() instanceof SequenceFlow;
//	}
//
//	public List<String> getElements(TextOrData textOrData, Element eObject, Collection<EClass> eClasses) {
//		List<String> res = new ArrayList<String>();
//		final SequenceFlow transition = (SequenceFlow)eObject;
//		final String condition = transition.getCondition();
//		if (condition != null && condition.trim().length() > 0 && transition.getConditionType() != SequenceFlowConditionType.GROOVY_EXPRESSION) {
//			res.add(condition);
//		}
//		res.add(DecisionTableUtil.DECISION_TABLE);
//		return res;
//	}
//
//	public List<SelectionListener> getListeners(final TextOrData textOrData) {
//		if(listeners == null){
//			listeners = new ArrayList<SelectionListener>();
//			listeners.add(new SelectionListener() {
//				public void widgetSelected(SelectionEvent e) {
//					EditingDomain domain = textOrData.getEditingDomain();
//					if (textOrData.getText().equals(DecisionTableUtil.DECISION_TABLE)) {
//						textOrData.setText(proceed(textOrData));
//					} else {
//						if(domain == null && textOrData.getElement() != null ){
//							domain = AdapterFactoryEditingDomain.getEditingDomainFor(textOrData.getElement()) ;
//						}
//						if(domain != null){
//							SetCommand cmd = new SetCommand(domain, textOrData.getElement(), ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION_TYPE, SequenceFlowConditionType.GROOVY_EXPRESSION);
//							domain.getCommandStack().execute(cmd);
//						}
//					}
//				}
//
//				public void widgetDefaultSelected(SelectionEvent e) {
//					widgetSelected(e) ;
//				}
//			});
//		}
//		return listeners;
//	}
//
//
//	public String proceed(TextOrData textOrData) {
//		final EditingDomain domain = textOrData.getEditingDomain();
//		Shell shell = textOrData.getControl().getShell();
//		final SequenceFlow transition = (SequenceFlow) textOrData.getElement();
//
//		DecisionTableWizard wizard = new DecisionTableWizard(transition, transition.getDecisionTable());
//		final TakeTransitionAction takeTransitionAction = TransitionsFactory.eINSTANCE.createTakeTransitionAction();
//		takeTransitionAction.setTakeTransition(true);
//		final TakeTransitionAction dontTakeTransitionAction = TransitionsFactory.eINSTANCE.createTakeTransitionAction();
//		dontTakeTransitionAction.setTakeTransition(false);
//		wizard.setAvailableLineActions(new DecisionTableAction[] { takeTransitionAction, dontTakeTransitionAction });
//		wizard.setAvailableTableActions(new DecisionTableAction[] { dontTakeTransitionAction, takeTransitionAction });
//		wizard.setActionsLabelProvider(new TakeTransitionLabelProvider());
//		wizard.setActionsComparer(new IElementComparer() {
//			public int hashCode(Object element) {
//				return element.hashCode();
//			}
//
//			public boolean equals(Object a, Object b) {
//				if (a instanceof TakeTransitionAction && b instanceof TakeTransitionAction) {
//					return ((TakeTransitionAction)a).isTakeTransition() == ((TakeTransitionAction)b).isTakeTransition();	
//				} else {
//					return a.equals(b);
//				}
//
//			}
//		});
//
//		WizardDialog dialog = new MaximizableWizardDialog(shell, wizard);
//		if (dialog.open() == Dialog.OK) {
//			DecisionTable table = wizard.getDecisionTable();
//			CompoundCommand cmd = new CompoundCommand();
//			cmd.append(new SetCommand(domain, textOrData.getElement(), ProcessPackage.Literals.SEQUENCE_FLOW__DECISION_TABLE, table));
//			cmd.append(new SetCommand(domain, textOrData.getElement(), ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION_TYPE, SequenceFlowConditionType.DECISION_TABLE));
//			domain.getCommandStack().execute(cmd);
//			return DecisionTableUtil.DECISION_TABLE;
//		} else if (transition.getConditionType() == SequenceFlowConditionType.DECISION_TABLE) {
//			return DecisionTableUtil.DECISION_TABLE;
//		} else if (transition.getConditionType() == SequenceFlowConditionType.GROOVY_EXPRESSION && transition.getCondition() != null) {
//			return transition.getCondition();
//		} else {
//			return "";
//		}
//	}
//
//}
