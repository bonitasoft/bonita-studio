/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.URLEncodableInputValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.ErrorEvent;
import org.bonitasoft.studio.model.process.Gateway;
import org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageEvent;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SignalEvent;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.TimerEvent;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.gmf.runtime.emf.type.core.ElementType;

/**
 * @author Mickael Istria
 * @author Romain Bioteau
 */
public class NamingUtils {

	private static final String VERSION_SEPARATOR = "-";
	private static final String UTF8 = "UTF-8";
	private final MainProcess process;
	private static Map<MainProcess, NamingUtils> instances = new HashMap<MainProcess, NamingUtils>();

	private NamingUtils(MainProcess process) {
		this.process = process;
	}

	public static NamingUtils getInstance(Element element) {
		MainProcess process = ModelHelper.getMainProcess(element);
		//        NamingUtils instance = null;
		//        if (!instances.containsKey(process)) {
		//            instance = new NamingUtils(process);
		//            instances.put(process, instance);
		//        } else {
		//            instance = instances.get(process);
		//        }

		return new NamingUtils(process);
	}

	public static Expression generateConstantExpression(String name){
		return generateConstantExpression( name, false);
	}

	public static Expression generateConstantExpression(String name, boolean returnTypeFixed){
		return generateConstantExpression(name, String.class.getName(), returnTypeFixed);
	}

	public static Expression generateConstantExpression(String name, String typeName, boolean returnTypeFixed){
		Expression expr = ExpressionFactory.eINSTANCE.createExpression();
		expr.setName(name);
		expr.setContent(name);
		expr.setType(ExpressionConstants.CONSTANT_TYPE);
		expr.setReturnType(typeName);
		expr.setReturnTypeFixed(returnTypeFixed);
		return expr;
	}

	public String generateName(Element newItem, Element existingItem) {
		Adapter adapter = new ProcessItemProviderAdapterFactory().createAdapter(newItem);
		ItemProviderAdapter itemProvider = (ItemProviderAdapter) adapter;
		String label = null;
		// the container of the newItem (where we search for the number max)
		EObject mainContainer;

		label = getDefaultNameFor(newItem);

		if (label == null) {
			label = itemProvider.getText(newItem);
		}
        if (newItem instanceof Widget) {
            if (existingItem instanceof PageFlow || existingItem instanceof ViewPageFlow) {
                mainContainer = existingItem;
            } else {
                mainContainer = ModelHelper.getForm((Widget) newItem);
                if (mainContainer.eContainer() != null) {
                    mainContainer = mainContainer.eContainer();
                }
            }
//        } else if (newItem instanceof BoundaryEvent) {
//            mainContainer = newItem.eContainer();
        } else {
            mainContainer = process;
        }

		if (newItem instanceof Widget) {
			if (existingItem instanceof PageFlow || existingItem instanceof ViewPageFlow) {
				mainContainer = existingItem;
			} else {
				mainContainer = ModelHelper.getForm((Widget) newItem);
				if (mainContainer.eContainer() != null) {
					mainContainer = mainContainer.eContainer();
				}
			}
		} else if (newItem instanceof BoundaryEvent) {
			mainContainer = newItem.eContainer();
		} else {
			mainContainer = process;
		}


		int number = getMaxElements((Element) mainContainer, label);
		number++;
		label += number;

		newItem.setName(label);
		return label;
	}

	/**
	 * @param item
	 * @return
	 */
	public static String getDefaultNameFor(Element item) {
		if (item instanceof SubProcessEvent) {
			return Messages.SubprocessEventDefaultName ;
		} 	else if (item instanceof Gateway) {
			return Messages.GatewayDefaultName;
		} else if (item instanceof Activity) {
			return Messages.StepDefaultName;
		} else if (item instanceof Pool) {
			return Messages.PoolDefaultName;
		} else if (item instanceof SequenceFlow) {
			return Messages.SequenceFlowDefaultName;
		} else if (item instanceof MessageFlow) {
			return Messages.MessageFlowDefaultName;
		} else if (item instanceof StartEvent) {
			return Messages.startEventDefaultName;
		} else if (item instanceof EndEvent) {
			return Messages.endEventDefaultName;
		} else if (item instanceof SignalEvent) {
			return Messages.signalEventDefaultName;
		} else if (item instanceof MessageEvent) {
			return Messages.intermediateMessageEventDefaultName;
		}else if(item instanceof BoundaryMessageEvent){
			return "Boundary "+Messages.intermediateMessageEventDefaultName;
		}else if(item instanceof BoundaryTimerEvent){
			return "Boundary "+Messages.intermeiateTimerEventDefaultName;
		} else if(item instanceof IntermediateErrorCatchEvent){
			return "Boundary "+Messages.IntermediateErrorCatchEventLabel;
		}  else if (item instanceof TimerEvent) {
			return Messages.intermeiateTimerEventDefaultName;
		} else if (item instanceof ThrowLinkEvent || item instanceof CatchLinkEvent) {
			return Messages.linkEventDefaultName;
		} else if (item instanceof ErrorEvent) {
			return Messages.IntermediateErrorCatchEventLabel;
		} else if (item instanceof Widget) {
			String name = null;
			name = getFormPaletteText(false, item.eClass());
			if (name != null) {
				return convertToId(name);
			} else {
				return Messages.WidgetDefaultLabel;
			}
		}
		String name = null;
		name = getPaletteText(false, item.eClass());
		if (name != null) {
			return name;
		} else {
			return null;
		}
	}

	/**
	 * get the max number of elements prefixed if label
	 * 
	 * @param element
	 * @param label
	 * @return
	 */
	public static int getMaxElements(Element element, String label) {
		int max = 0;
		if (element != null) {
			for (Iterator<?> iterator = element.eAllContents(); iterator.hasNext();) {
				EObject eObject = (EObject) iterator.next();
				if (eObject instanceof Element) {
					Element child = (Element) eObject;
					if (child.getName().startsWith(label)) {
						String name = child.getName();
						int index = child.getName().indexOf(label) + label.length();
						name = name.substring(index);
						try {
							max = Math.max(Integer.valueOf(name), max);
						} catch (NumberFormatException n) {
						}
					}
				}
			}
		}
		return max;

	}

	public static String convertToId(Element item) {
		return convertToId(item.getName(), item);
	}

	public static String convertToId(String label, Element item) {

		if (label != null) {
			if (item instanceof MainProcess) {
				AbstractProcess p = (AbstractProcess) item;
				return convertToId(p.getName());
			} else {
				return convertToId(label);
			}
		}
		return ""; //$NON-NLS-1$

	}

	public static String convertToId(String label, boolean toLowerCaseFirstChar) {
		String result = convertToId(label);
		if (toLowerCaseFirstChar) {
			if (result.trim().length() > 0) {
				return result.substring(0, 1).toLowerCase() + result.substring(1);
			} else {
				return result;
			}
		} else {
			return result;
		}
	}

	public static String convertToId(String label) {

		StringBuffer tmp = new StringBuffer();
		char car;
		char toAppendChar;

		int i = 0;
		if (label != null) {
			while (i < label.length()) {
				car = label.charAt(i);
				car = StringOperation.sansAccent(car) ;
				toAppendChar = (Character.isJavaIdentifierPart(car) ? car : '_');

				if (i == 0) {

					if (Character.UnicodeBlock.of(car) == Character.UnicodeBlock.BASIC_LATIN
							&& !('$' == car)) {
						tmp.append(car);
					} else {
						tmp.append('_');
						if (toAppendChar != '_') {
							tmp.append(toAppendChar);
						}
					}
				} else {
					if (Character.isJavaIdentifierPart(car)
							&& Character.UnicodeBlock.of(car) == Character.UnicodeBlock.BASIC_LATIN
							&& !('$' == car)) {
						tmp.append(car);
					} else {
						tmp.append('_');
					}
				}

				i++;
			}

			return tmp.toString();
		}
		return ""; //$NON-NLS-1$

	}


	/**
	 * Think to use JavaConventions.validateXXX() instead of this one when possible
	 * @param text
	 * @param uppercaseFirst
	 * @return
	 */
	public static String toJavaIdentifier(String text, Boolean uppercaseFirst) {

		boolean isStart = true;
		boolean nextIsUpperCase = true;
		StringBuffer res = new StringBuffer();
		for (char c : text.toCharArray()) {
			boolean isValid = false;
			if (isStart) {
				isValid = Character.isJavaIdentifierStart(c);
			} else {
				isValid = Character.isJavaIdentifierPart(c);
			}
			if (isValid) {
				if (isStart) {
					res.append(uppercaseFirst ? Character.toUpperCase(c) : Character.toLowerCase(c));
				} else {
					res.append(nextIsUpperCase ? Character.toUpperCase(c) : c);
				}
				nextIsUpperCase = false;
				isStart = false;
			} else {
				nextIsUpperCase = true;
			}
		}

		return res.toString();
	}

	public static String getEventDefaultId(MessageFlow self) {
		if (self.getTarget() != null && self.getTarget().getEvent() != null) {
			Message event = ModelHelper.findEvent(self.getSource(), self.getTarget().getEvent());
			if(event != null){
				self.setName(event.getName());
				return event.getName();
			}else{
				self.setName("");
				return "";
			}
		} else if (self.getSource() != null && self.getSource().getEvents().size() > 0) {
			self.setName(self.getSource().getEvents().get(0).getName());
			self.getTarget().setEvent(self.getName());
			return self.getSource().getEvents().get(0).getName();
		}
		return ""; //$NON-NLS-1$
	}

	public static String convertToPackage(String name, String version) {
		return name + "_" + version.replace(".", "_"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * @param elementTypes
	 * @return
	 */
	public static String getPaletteDescription(List<?> elementTypes) {
		return getPaletteText(true, ((ElementType) elementTypes.get(0)).getEClass());
	}

	/**
	 * @param elementTypes
	 * @return
	 */
	public static String getPaletteTitle(List<?> elementTypes) {
		return getPaletteText(false, ((ElementType) elementTypes.get(0)).getEClass());
	}

	/**
	 * @param elementTypes
	 * @return
	 */
	public static String getFormPaletteDescription(List<?> elementTypes) {
		return getFormPaletteText(true, ((ElementType) elementTypes.get(0)).getEClass());
	}

	/**
	 * @param elementTypes
	 * @return
	 */
	public static String getFormPaletteTitle(List<?> elementTypes) {
		return getFormPaletteText(false, ((ElementType) elementTypes.get(0)).getEClass());
	}

	/**
	 * @param b
	 * @param eClass
	 * @return
	 */
	public static String getFormPaletteText(boolean isDescription, EClass eClass) {
		int id = eClass.getClassifierID();
		switch (id) {
		case FormPackage.CHECK_BOX_SINGLE_FORM_FIELD:
			if (isDescription) {
				return Messages.Checkbox_desc;
			} else {
				return Messages.Checkbox_title;
			}
		case FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD:
			if (isDescription) {
				return Messages.CheckboxList_desc;
			} else {
				return Messages.CheckboxList_title;
			}
		case FormPackage.DATE_FORM_FIELD:
			if (isDescription) {
				return Messages.Date_desc;
			} else {
				return Messages.Date_title;
			}
		case FormPackage.PASSWORD_FORM_FIELD:
			if (isDescription) {
				return Messages.Password_desc;
			} else {
				return Messages.Password_title;
			}
		case FormPackage.DURATION_FORM_FIELD:
			if (isDescription) {
				return Messages.Duration_desc;
			} else {
				return Messages.Duration_title;
			}
		case FormPackage.LIST_FORM_FIELD:
			if (isDescription) {
				return Messages.List_desc;
			} else {
				return Messages.List_title;
			}
		case FormPackage.RADIO_FORM_FIELD:
			if (isDescription) {
				return Messages.Radio_desc;
			} else {
				return Messages.Radio_title;
			}
		case FormPackage.SELECT_FORM_FIELD:
			if (isDescription) {
				return Messages.Select_desc;
			} else {
				return Messages.Select_title;
			}
		case FormPackage.SUGGEST_BOX:
			if (isDescription) {
				return Messages.SuggestBox_desc;
			} else {
				return Messages.SuggestBox_title;
			}
		case FormPackage.TEXT_INFO:
			if (isDescription) {
				return Messages.Text_desc;
			} else {
				return Messages.Text_title;
			}
		case FormPackage.TEXT_FORM_FIELD:
			if (isDescription) {
				return Messages.TextBox_desc;
			} else {
				return Messages.TextBox_title;
			}
		case FormPackage.TEXT_AREA_FORM_FIELD:
			if (isDescription) {
				return Messages.TextArea_desc;
			} else {
				return Messages.TextArea_title;
			}
		case FormPackage.RICH_TEXT_AREA_FORM_FIELD:
			if (isDescription) {
				return Messages.RichTextArea_desc;
			} else {
				return Messages.RichTextArea_title;
			}
		case FormPackage.MESSAGE_INFO:
			if (isDescription) {
				return Messages.Message_desc;
			} else {
				return Messages.Message_title;
			}
		case FormPackage.SUBMIT_FORM_BUTTON:
			if (isDescription) {
				return Messages.Submit_desc;
			} else {
				return Messages.Submit_title;
			}
		case FormPackage.PREVIOUS_FORM_BUTTON:
			if (isDescription) {
				return Messages.Previous_desc;
			} else {
				return Messages.Previous_title;
			}
		case FormPackage.NEXT_FORM_BUTTON:
			if (isDescription) {
				return Messages.Next_desc;
			} else {
				return Messages.Next_title;
			}
		case FormPackage.FORM_BUTTON:
			if (isDescription) {
				return Messages.SimpleButton_desc;
			} else {
				return Messages.SimpleButton_title;
			}
		case FormPackage.FILE_WIDGET:
			if (isDescription) {
				return Messages.File_desc;
			} else {
				return Messages.File_title;
			}
		case FormPackage.IMAGE_WIDGET:
			if (isDescription) {
				return Messages.Image_desc;
			} else {
				return Messages.Image_title;
			}
		case FormPackage.TABLE:
			if (isDescription) {
				return Messages.Table_desc;
			} else {
				return Messages.Table_title;
			}
		case FormPackage.DYNAMIC_TABLE:
			if (isDescription) {
				return Messages.EditableGrid_desc;
			} else {
				return Messages.EditableGrid_title;
			}
		case FormPackage.HIDDEN_WIDGET:
			if (isDescription) {
				return Messages.Hidden_desc;
			} else {
				return Messages.Hidden_title;
			}
		case FormPackage.IFRAME_WIDGET:
			if (isDescription) {
				return Messages.IFrame_desc;
			} else {
				return Messages.IFrame_title;
			}
		case FormPackage.HTML_WIDGET:
			if (isDescription) {
				return Messages.HTML_desc;
			} else {
				return Messages.HTML_title;
			}
		case FormPackage.GROUP:
			if (isDescription) {
				return Messages.Group_desc;
			} else {
				return Messages.Group_title;
			}
		default:
			break;
		}
		return null;
	}

	/**
	 * @param b
	 * @param object
	 * @return
	 */
	public static String getPaletteText(boolean isDescription, EClass eClass) {
		int id = eClass.getClassifierID();
		switch (id) {
		case ProcessPackage.ACTIVITY:
			if (isDescription) {
				return Messages.Step_desc;
			} else {
				return Messages.Step_title;
			}
		case ProcessPackage.TASK:
			if (isDescription) {
				return Messages.Human_desc;
			} else {
				return Messages.Human_title;
			}
		case ProcessPackage.CALL_ACTIVITY:
			if (isDescription) {
				return Messages.CallActivity_desc;
			} else {
				return Messages.CallActivity_title;
			}
		case ProcessPackage.AND_GATEWAY:
			if (isDescription) {
				return Messages.Gate_desc;
			} else {
				return Messages.Gate_title;
			}
		case ProcessPackage.XOR_GATEWAY:
			if (isDescription) {
				return Messages.XORGate_desc;
			} else {
				return Messages.XORGate_title;
			}
		case ProcessPackage.INCLUSIVE_GATEWAY:
			if (isDescription) {
				return Messages.InclusiveGate_desc;
			} else {
				return Messages.InclusiveGate_title;
			}
		case ProcessPackage.THROW_LINK_EVENT:
			if (isDescription) {
				return Messages.ThrowLink_desc;
			} else {
				return Messages.ThrowLink_title;
			}
		case ProcessPackage.CATCH_LINK_EVENT:
			if (isDescription) {
				return Messages.CatchLink_desc;
			} else {
				return Messages.CatchLink_title;
			}
		case ProcessPackage.POOL:
			if (isDescription) {
				return Messages.Pool_desc;
			} else {
				return Messages.Pool_title;
			}
		case ProcessPackage.LANE:
			if (isDescription) {
				return Messages.Lane_desc;
			} else {
				return Messages.Lane_title;
			}
		case ProcessPackage.TEXT_ANNOTATION:
			if (isDescription) {
				return Messages.TextAnnotation_desc;
			} else {
				return Messages.TextAnnotation_title;
			}
		case ProcessPackage.START_EVENT:
			if (isDescription) {
				return Messages.Start_desc;
			} else {
				return Messages.Start_title;
			}
		case ProcessPackage.START_TIMER_EVENT:
			if (isDescription) {
				return Messages.StartTimer_desc;
			} else {
				return Messages.StartTimer_title;
			}
		case ProcessPackage.START_MESSAGE_EVENT:
			if (isDescription) {
				return Messages.StartMessage_desc;
			} else {
				return Messages.StartMessage_title;
			}
		case ProcessPackage.START_SIGNAL_EVENT:
			if (isDescription) {
				return Messages.StartSignal_desc;
			} else {
				return Messages.StartSignal_title;
			}
		case ProcessPackage.START_ERROR_EVENT:
			if (isDescription) {
				return Messages.StartError_desc;
			} else {
				return Messages.StartError_title;
			}
		case ProcessPackage.END_EVENT:
			if (isDescription) {
				return Messages.End_desc;
			} else {
				return Messages.End_title;
			}
		case ProcessPackage.END_MESSAGE_EVENT:
			if (isDescription) {
				return Messages.EndMessage_desc;
			} else {
				return Messages.EndMessage_title;
			}
		case ProcessPackage.END_SIGNAL_EVENT:
			if (isDescription) {
				return Messages.EndSignal_desc;
			} else {
				return Messages.EndSignal_title;
			}
		case ProcessPackage.END_ERROR_EVENT:
			if (isDescription) {
				return Messages.EndError_desc;
			} else {
				return Messages.EndError_title;
			}
		case ProcessPackage.BOUNDARY_TIMER_EVENT:
			if (isDescription) {
				return Messages.Timer_desc;
			} else {
				return Messages.Timer_title;
			}
		case ProcessPackage.TIMER_EVENT:
			if (isDescription) {
				return Messages.Timer_desc;
			} else {
				return Messages.Timer_title;
			}
		case ProcessPackage.INTERMEDIATE_THROW_MESSAGE_EVENT:
			if (isDescription) {
				return Messages.ThrowMessage_desc;
			} else {
				return Messages.ThrowMessage_title;
			}
		case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT:
			if (isDescription) {
				return Messages.CatchMessage_desc;
			} else {
				return Messages.CatchMessage_title;
			}
		case ProcessPackage.BOUNDARY_MESSAGE_EVENT:
			if (isDescription) {
				return Messages.CatchMessage_desc;
			} else {
				return Messages.CatchMessage_title;
			}
		case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT:
			if (isDescription) {
				return Messages.ThrowSignal_desc;
			} else {
				return Messages.ThrowSignal_title;
			}
		case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT:
			if (isDescription) {
				return Messages.CatchSignal_desc;
			} else {
				return Messages.CatchSignal_title;
			}
		case ProcessPackage.BOUNDARY_SIGNAL_EVENT:
			if (isDescription) {
				return Messages.CatchSignal_desc;
			} else {
				return Messages.CatchSignal_title;
			}
		case ProcessPackage.INTERMEDIATE_ERROR_CATCH_EVENT:
			if (isDescription) {
				return Messages.CatchError_desc;
			} else {
				return Messages.CatchError_title;
			}
		case ProcessPackage.EVENT:
			if (isDescription) {
				return Messages.Event_desc;
			} else {
				return Messages.Event_title;
			}
		case ProcessPackage.SERVICE_TASK:
			if (isDescription) {
				return Messages.ServiceTask_desc;
			} else {
				return Messages.ServiceTask_title;
			}
		case ProcessPackage.SCRIPT_TASK:
			if (isDescription) {
				return Messages.ScriptTask_desc;
			} else {
				return Messages.ScriptTask_title;
			}
		case ProcessPackage.RECEIVE_TASK:
			if (isDescription) {
				return Messages.ReceiveTask_desc;
			} else {
				return Messages.ReceiveTask_title;
			}
		case ProcessPackage.SEND_TASK:
			if (isDescription) {
				return Messages.SendTask_desc;
			} else {
				return Messages.SendTask_title;
			}
		case ProcessPackage.SEQUENCE_FLOW:
			if (isDescription) {
				return Messages.Transition_desc;
			} else {
				return Messages.Transition_title;
			}
		case ProcessPackage.END_TERMINATED_EVENT:
			if (isDescription) {
				return Messages.TerminateEnd_desc;
			} else {
				return Messages.TerminateEnd_title;
			}
		case ProcessPackage.SUB_PROCESS_EVENT:
			if (isDescription) {
				return Messages.SubprocessEvent_desc;
			} else {
				return Messages.SubprocessEvent_title;
			}
		default:
			break;
		}
		return eClass.getName();
	}

	public static String generateNewName(Set<String> existingNames, String defaultName) {
		int cpt = 1 ;
		while(existingNames.contains(defaultName+cpt)){
			cpt++;
		}
		return defaultName+cpt;
	}

	public static String toConnectorDefinitionFilename(String definitionId,String defVersion, boolean inculdeExtension) {
		if(!inculdeExtension){
			return definitionId+VERSION_SEPARATOR+defVersion;
		}else{
			return definitionId+VERSION_SEPARATOR+defVersion+".def";
		}
	}

	public static String toConnectorImplementationFilename(String implementationId, String implementationVersion, boolean inculdeExtension) {
		if(!inculdeExtension){
			return implementationId+VERSION_SEPARATOR+implementationVersion;
		}else{
			return implementationId+VERSION_SEPARATOR+implementationVersion+".impl";
		}
	}

	public static String getIdFromConnectorFilename(String filename) {
		return filename.substring(0,filename.lastIndexOf('-'));
	}

	public static String getVersionFromConnectorFilename(String filename) {
		return filename.substring(filename.lastIndexOf('-')+1,filename.lastIndexOf('.'));
	}

	public static boolean isUTF8String(String inputString) throws UnsupportedEncodingException {
		String encoded = URLEncoder.encode(inputString,UTF8)  ;
		String decoded = URLDecoder.decode(encoded,UTF8) ;
		return inputString.equals(decoded);
	}

	public static String toDiagramFilename(MainProcess diagram) {
		return toDiagramFilename(diagram.getName(),diagram.getVersion());
	}

	public static String toDiagramFilename(String processName, String baseVersion) {
		return NamingUtils.convertToValidURI(processName)+VERSION_SEPARATOR+NamingUtils.convertToValidURI(baseVersion) + ".proc" ;
	}

	private static String convertToValidURI(String input) {
		String result = new String(input);
		for(String invalidChar :   URLEncodableInputValidator.reservedChars){
			if(input.contains(invalidChar)){
				result = result.replace(invalidChar, "_");
			}
		}
		return result;
	}

}
