/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.common.emf.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.ScriptTask;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.ServiceTask;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationDataContainer;
import org.bonitasoft.studio.model.simulation.SimulationTransition;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 *
 */
public class ModelHelper {

    public static final String STRING_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.stringDataType);

    public static final String INTEGER_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.integerDataType);

    public static final String FLOAT_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.floatDataType);

    public static final String BOOLEAN_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.booleanDataType);

    public static final String DATE_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.dateDataType);

    public static final String JAVA_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.javaDataType);

    /**
     * To be used when dealing with scopes (for groups or datatypes)
     *
     * @param a
     *            model object
     * @return the list of parent processes for this object. Includes object if
     *         object is a process. This basically contains: * Only the Main
     *         process in case of a top level node which is contained in main
     *         process * Main process and pool for pools and items that are
     *         contained inside a pool
     */
    public static List<AbstractProcess> getAllParentProcesses(EObject eObject) {
        final List<AbstractProcess> res = new ArrayList<AbstractProcess>();
        while (eObject != null) {
            if (eObject instanceof AbstractProcess) {
                res.add((AbstractProcess) eObject);
            }
            eObject = eObject.eContainer();
        }
        return res;
    }

    public static List<AbstractProcess> getAllProcesses(final Element eObject) {
        final List<AbstractProcess> res = new ArrayList<AbstractProcess>();
        final MainProcess mainProcess = getMainProcess(eObject);
        if (mainProcess != null) {
            findAllProcesses(mainProcess, res);
        }
        return res;
    }

    public static FileWidgetInputType getDefaultFileWidgetInputType(final FileWidget widget) {
        final Form parentForm = ModelHelper.getParentForm(widget);
        if (parentForm == null) {
            return FileWidgetInputType.RESOURCE;
        }
        if (ModelHelper.isAnEntryPageFlowOnAPool(parentForm)) {
            return FileWidgetInputType.RESOURCE;
        }
        return FileWidgetInputType.DOCUMENT;
    }

    private static List<AbstractProcess> findAllProcesses(final Element element, final List<AbstractProcess> processes) {

        //		List<URI> newwayFindProcesses = newwayFindProcesses(element, processes);
        //		ResultSet resultSet = newWayFindProcessesWithQueriesInObjectFormat(element);
        final List<AbstractProcess> oldwayFindProcesses = oldwayFindProcesses(element, processes);
        //		ResultSet resultSet2 = newwayFindProcessesWithXtextQuery(element);
        //
        //		if(resultSet2.getSize() != oldwayFindProcesses.size()){
        //			System.out.println("erdf:\n"+resultSet2.getSize()+"\n\n"+oldwayFindProcesses);
        //		} else {
        //			System.out.println("all is fine using Xtext");
        //		}
        //
        //		if(resultSet.getSize() != oldwayFindProcesses.size()){
        //			System.out.println("erdf:\n"+resultSet.getSize()+"\n\n"+oldwayFindProcesses);
        //		} else {
        //			System.out.println("all is fine using Object format");
        //		}
        //		if(newwayFindProcesses.size() != oldwayFindProcesses.size()){
        //			System.out.println("erdf:\n"+newwayFindProcesses+"\n\n"+oldwayFindProcesses);
        //		} else {
        //			System.out.println("all is fine using basic query");
        //		}
        return oldwayFindProcesses;
    }

    //	private static List<URI> newwayFindProcesses(Element element,
    //			List<AbstractProcess> processes) {
    //		final EObjectQuery<EObjectDescriptor> eObjectQuery = IndexQueryFactory.createEObjectQuery();
    //		eObjectQuery.eClassURI(EcoreUtil.getURI(ProcessPackage.Literals.POOL));
    //		final List<URI> result = new ArrayList<URI>();
    //		getIndexFactory().executeQueryCommand(new QueryCommand() {
    //
    //			public void execute(QueryExecutor queryExecutor) {
    //				QueryResult<EObjectDescriptor> execute = queryExecutor.execute(eObjectQuery);
    //				for (EObjectDescriptor objDesc : execute) {
    //					URI candidateResourceUri = objDesc.getResourceURI();
    //					//boolean isInScope = scope.contains(candidateResourceUri);
    // // if (isInclusiveScope == isInScope) {
    //						// add uri of instance to result
    //						result.add(candidateResourceUri.appendFragment(objDesc.getFragment()));
    // // }
    //				}
    //			}
    //		});
    //		return result;
    //	}
    //
    //	private static Model model;
    //	private static QueryProcessor queryProcessor;
    //	private static XtextResourceSet set;
    //	private static boolean firstQuery;
    //
    //	private static ResultSet newwayFindProcessesWithXtextQuery(final Element element) {
    //
    //		MQLquery query = findQuery("searchAllProcesses");
    //		Query internalQuery = QueryTransformer.transform(query);
    //		Index indexFactory = getIndexFactory();
    //		QueryProcessor queryProcessor = getQueryProcessorFactory().createQueryProcessor(indexFactory);
    //		final ResultSet result = queryProcessor.execute(internalQuery, new QueryContext() {
    //
    //			public ResourceSet getResourceSet() {
    //				return element.eResource().getResourceSet();
    //			}
    //
    //			public URI[] getResourceScope() {
    //				return new URI[]{element.eResource().getURI()};
    //			}
    //		});
    //
    //		return result;
    //	}
    //
    //	private static MQLquery findQuery(String queryName) {
    //		if(model == null){
    //			new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("..");
    //			Injector injector = new QueryStandaloneSetup().createInjectorAndDoEMFRegistration();
    //			set = injector.getInstance(XtextResourceSet.class);
    //			set.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
    //			URI resourceURI = URI.createURI(
    //			         "platform:/plugin/org.bonitasoft.studio.common/bonita.query");
    //			URI normalized = set.getURIConverter().normalize(resourceURI);
    //			LazyLinkingResource xtextResource = (LazyLinkingResource) set.getResource(normalized, true);
    //			Index indexFactory = getIndexFactory();
    //			queryProcessor = getQueryProcessorFactory().createQueryProcessor(indexFactory);
    //			model = (Model) xtextResource.getContents().get(0);
    //		}
    //		if (queryName == null) {
    //			return model.getDefaultQuery();
    //		}
    //		for (NamedQuery query : model.getNamedQueries()) {
    //			if (query.getName().equals(queryName)) {
    //				return query.getQuery();
    //			}
    //		}
    //		return null;
    //	}
    //
    //	private static boolean indexHasChangedSinceLastGetAllProcessesRequest = true;
    //	private static ResultSet allProcessesCache;
    //
    //	public static ResultSet getAllProcesses() {
    //		if(firstQuery){
    //			IndexFactory.getInstance().getEventListenerRegistry().addIndexChangeListener(new IndexChangeListener() {
    //
    //				public void indexChanged(IndexChangeEvent arg0) {
    //					indexHasChangedSinceLastGetAllProcessesRequest = true;
    //				}
    //			});
    //		}
    //		if(indexHasChangedSinceLastGetAllProcessesRequest){
    //			final List<URI> resourcesList = new ArrayList<URI>();
    //			executeQueryCommandToRetrieveResourceScope(resourcesList);
    //			final URI[] resourceScopes = resourcesList.toArray(new URI[resourcesList.size()]);
    //
    //			//		List<String> attrs = new ArrayList<String>();
    //			//		attrs.add("name");
    //			//		attrs.add("version");
    //
    //			//		SelectEntry selectPoolEntry = new SelectAttrs("pool", attrs);//new SelectAlias("pool");
    //
    //			FromType fromType = constructFromType(resourceScopes);
    //
    //			final Query query = constructQuery(fromType);
    //
    //			QueryProcessor queryProcessor = constructQueryProcessor();
    //			allProcessesCache = executeQuery(resourceScopes, query,
    //					queryProcessor);
    //			indexHasChangedSinceLastGetAllProcessesRequest = false;
    //		}
    //		return allProcessesCache;

    //		MQLquery query = findQuery("searchAllProcesses");
    //		Query internalQuery = QueryTransformer.transform(query);
    //		final ResultSet result = queryProcessor.execute(internalQuery, new QueryContext() {
    //
    //			public ResourceSet getResourceSet() {
    //				return new ResourceSetImpl();
    //			}
    //
    //			public URI[] getResourceScope() {
    //				final List<URI> result = new ArrayList<URI>();
    //				IndexFactory.getInstance().executeQueryCommand(new QueryCommand() {
    //
    //					public void execute(QueryExecutor queryExecutor) {
    //						ResourceQuery<ResourceDescriptor> resourceQuery = IndexQueryFactory.createResourceQuery();
    //						for (ResourceDescriptor desc : queryExecutor.execute(resourceQuery)) {
    //							result.add(desc.getURI());
    //						}
    //					}
    //
    //				});
    //				return result.toArray(new URI[result.size()]);
    //			}
    //		});
    //
    //		return result;
    //
    //	}
    //
    //	protected static void executeQueryCommandToRetrieveResourceScope(
    //			final List<URI> resourcesList) {
    //		getIndexFactory().executeQueryCommand(new QueryCommand() {
    //
    //			public void execute(QueryExecutor queryExecutor) {
    //				ResourceQuery<ResourceDescriptor> resourceQuery = IndexQueryFactory.createResourceQuery();
    //				for (ResourceDescriptor desc : queryExecutor.execute(resourceQuery)) {
    //					resourcesList.add(desc.getURI());
    //				}
    //			}
    //
    //		});
    //	}
    //
    //	protected static ResultSet executeQuery(final URI[] resourceScopes,
    //			final Query query, QueryProcessor queryProcessor) {
    //		final ResultSet result = queryProcessor.execute(query, new QueryContext() {
    //
    //			public ResourceSet getResourceSet() {
    //				return new ResourceSetImpl();
    //			}
    //
    //			public URI[] getResourceScope() {
    //				return resourceScopes;
    //			}
    //		});
    //		return result;
    //	}
    //
    //	protected static QueryProcessor constructQueryProcessor() {
    //		QueryProcessor queryProcessor = getQueryProcessorFactory().createQueryProcessor(getIndexFactory());
    //		return queryProcessor;
    //	}
    //
    //	protected static Query constructQuery(FromType fromType) {
    //		final Query query = new Query(new SelectEntry[] {new SelectAlias("pool")/*selectPoolEntry*/}, new FromType[] { fromType });
    //		return query;
    //	}
    //
    //	protected static FromType constructFromType(final URI[] resourceScopes) {
    //		FromType fromType = new FromType("pool", EcoreUtil.getURI(ProcessPackage.Literals.POOL), true,
    //				new  TypeScopeProvider() {
    //
    //			public boolean isInclusiveScope() {
    //				return true;
    //			}
    //			public URI[] getResourceScope() {
    //				return resourceScopes;
    //			}
    //		});
    //		return fromType;
    //	}
    //
    //	protected static QueryProcessorFactory getQueryProcessorFactory() {
    //		return QueryProcessorFactory.getDefault();
    //	}
    //
    //	protected static Index getIndexFactory() {
    //		return IndexFactory.getInstance();
    //	}
    //
    //
    //
    //	private static ResultSet newWayFindProcessesWithQueriesInObjectFormat(final Element element){
    //
    //		SelectEntry selectPoolEntry = new SelectAlias("pool");
    //
    //		FromType fromType = new FromType("pool", EcoreUtil.getURI(ProcessPackage.Literals.POOL), false,
    //				new  TypeScopeProvider() {
    //
    //			public boolean isInclusiveScope() {
    //				return true;
    //			}
    //			public URI[] getResourceScope() {
    //				return new URI[]{element.eResource().getURI()};
    //			}
    //		});
    //
    //		final Query query = new Query(new SelectEntry[] {selectPoolEntry}, new FromType[] { fromType });
    //
    //		Index indexFactory = getIndexFactory();
    //
    //		QueryProcessor queryProcessor = getQueryProcessorFactory().createQueryProcessor(indexFactory);
    //		final ResultSet result = queryProcessor.execute(query, new QueryContext() {
    //
    //			public ResourceSet getResourceSet() {
    //				return element.eResource().getResourceSet();
    //			}
    //
    //			public URI[] getResourceScope() {
    //				return new URI[]{element.eResource().getURI()};
    //			}
    //		});
    //		return result;
    //	}

    protected static List<AbstractProcess> oldwayFindProcesses(final Element element,
            final List<AbstractProcess> processes) {
        if (element instanceof AbstractProcess && !(element instanceof MainProcess)) {

            processes.add((AbstractProcess) element);
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                oldwayFindProcesses(e, processes);
            }

        }
        return processes;
    }

    /**
     * Return Top Parent execlude embedded process
     *
     * @param object
     * @return process
     */
    public static AbstractProcess getParentProcess(final EObject object) {
        EObject process = object;
        while (process != null &&  !(process instanceof AbstractProcess && !(process instanceof SubProcessEvent))) {
            if (process.eContainer() != null) {
                process = process.eContainer();
            } else {
                break;
            }
        }
        if (process instanceof AbstractProcess) {
            return (AbstractProcess) process;
        } else {
            return null;
        }
    }

    //	public static AbstractProcess getParentProcessIncludedEmbedded(EObject object) {
    //		EObject process = object;
    //		while (process != null &&  !(process instanceof AbstractProcess)) {
    //			if (process.eContainer() != null) {
    //				process = process.eContainer();
    //			} else {
    //				break;
    //			}
    //		}
    //		if (process instanceof AbstractProcess) {
    //			return (AbstractProcess) process;
    //		} else {
    //			return null;
    //		}
    //	}

    public static Container getParentContainer(final EObject object) {
        EObject container = object;
        while (container != null &&  !(container instanceof Container)) {
            if (container.eContainer() != null) {
                container = container.eContainer();
            } else {
                break;
            }
        }
        if (container instanceof Container) {
            return (Container) container;
        } else {
            return null;
        }
    }

    public static MainProcess getMainProcess(final EObject object) {
        if (object == null) {
            return null;
        }
        EObject process = object;
        while (!(process instanceof MainProcess)) {
            process = process.eContainer();
            if (process == null) {
                return null;
            }
        }
        return (MainProcess) process;
    }

    public static ConnectableElement getConnectableElement(final EObject object) {
        EObject element = object;
        while (!(element instanceof ConnectableElement)) {
            element = element.eContainer();
        }
        return (ConnectableElement) element;
    }

    /**
     * Applies the specifier {@link ElementProcessor} to all instance of eClass
     * in specified model and children
     *
     * @param model
     * @param eClass
     * @param elementProcessor
     */
    @SuppressWarnings("unchecked")
    public static <T extends EObject> void applyTo(final EObject model, final EClass eClass, final ElementProcessor<T> elementProcessor) {
        if (eClass.isSuperTypeOf(model.eClass())) {
            elementProcessor.apply((T) model);
        }
        for (final EObject content : model.eContents()) {
            applyTo(content, eClass, elementProcessor);
        }
    }

    private static void addAllDataWithType(final DataAware element, final List<Data> datas, final EClass dataType) {
        if (dataType == null) {
            datas.addAll(element.getData());
        } else {
            for (final Data data : element.getData()) {
                if (dataType.isSuperTypeOf(data.getDataType().eClass())) {
                    datas.add(data);
                }
            }
        }
    }

    public static List<Pool> getChildrenProcess(final MainProcess process) {
        final List<Pool> result = new ArrayList<Pool>();
        for (final Element e : process.getElements()) {
            if (e instanceof Pool) {
                result.add((Pool) e);
            }
        }
        return result;
    }

    public static List<Data> getAccessibleDataInForms(final EObject element, final EStructuralFeature feature) {
        if (element instanceof Pool && ProcessPackage.Literals.PAGE_FLOW__FORM.equals(feature)) { // Only add transient form data
            EReference trFeature;
            final List<Data> accessibleData = new ArrayList<Data>();
            if (feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM)) {
                trFeature = ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA;
            } else if (feature.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM)) {
                trFeature = ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA;
            } else {
                trFeature = ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA;
            }
            if (trFeature != null) {
                accessibleData.addAll((List<Data>) element.eGet(trFeature));
            }
            return accessibleData;
        } else {
            return getAccessibleDataInFormsWithNoRestriction(element, feature);
        }
    }

    public static List<Data> getAccessibleDataInFormsWithNoRestriction(final EObject pageflow, final EStructuralFeature feature) {
        final List<Data> accessibleData = getAccessibleData(pageflow, null);
        if (feature != null) {
            EReference trFeature;
            if (feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM)) {
                trFeature = ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA;
            } else if (feature.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM)) {
                trFeature = ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA;
            } else {
                trFeature = ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA;
            }
            accessibleData.addAll((List<Data>) pageflow.eGet(trFeature));
        }
        return accessibleData;
    }

    public static List<Data> getAccessibleData(final EObject element) {
        return getAccessibleData(element, null);
    }

    public static List<Data> getAccessibleData(EObject element, final EClass dataType) {
        final List<Data> data = new ArrayList<Data>();
        boolean processFound = false;
        while (!processFound && element != null) {
            if (element instanceof SequenceFlow) {
                if (((SequenceFlow) element).getSource() instanceof DataAware) {
                    addAllDataWithType((DataAware) ((SequenceFlow) element).getSource(), data, dataType);
                }
            }
            if (element instanceof DataAware) {
                addAllDataWithType((DataAware) element, data, dataType);
            }
            processFound = element instanceof AbstractProcess && !(element instanceof SubProcessEvent);
            element = element.eContainer();
        }
        return data;
    }

    public static boolean containsResources(final Element element) {
        boolean containsResources = false;
        if (element instanceof AbstractProcess) {
            final AbstractProcess process = (AbstractProcess) element;
            containsResources = process.getResourceFolders().size() > 0 || process.getResourceFiles().size() > 0 || process.getLogInPage() != null
                    || process.getProcessTemplate() != null || process.getConfirmationTemplate() != null || process.getErrorTemplate() != null
                    || process.getWelcomePage() != null && !process.getWelcomePageInternal();
            if (containsResources) {
                return true;
            }
        }
        if (element instanceof PageFlow) {
            containsResources = containsResources || ((PageFlow) element).getConfirmationTemplate() != null;
            if (containsResources) {
                return true;
            }
            for (final Element el : ((PageFlow) element).getForm()) {
                containsResources = containsResources || containsResources(el);
                if (containsResources) {
                    return true;
                }
            }
        }
        if (element instanceof Form) {
            containsResources = containsResources || ((Form) element).getHtmlTemplate() != null;
            if (containsResources) {
                return true;
            }
        }
        if (element instanceof Container && !(element instanceof AbstractProcess)) {
            for (final Element el : ((Container) element).getElements()) {
                containsResources = containsResources || containsResources(el);
                if (containsResources) {
                    return true;
                }
            }
        }

        return containsResources;
    }

    /**
     * @param proc
     */
    public static void addDataTypes(final MainProcess proc) {
        // bool
        final DataType boolDataType = ProcessFactory.eINSTANCE.createBooleanType();
        boolDataType.setName(DataTypeLabels.booleanDataType);
        proc.getDatatypes().add(boolDataType);
        // date
        final DataType dateDataType = ProcessFactory.eINSTANCE.createDateType();
        dateDataType.setName(DataTypeLabels.dateDataType);
        proc.getDatatypes().add(dateDataType);
        // int
        final DataType intDataType = ProcessFactory.eINSTANCE.createIntegerType();
        intDataType.setName(DataTypeLabels.integerDataType);
        proc.getDatatypes().add(intDataType);
        // long
        final DataType longDataType = ProcessFactory.eINSTANCE.createLongType();
        longDataType.setName(DataTypeLabels.longDataType);
        proc.getDatatypes().add(longDataType);
        // long
        final DataType doubleDataType = ProcessFactory.eINSTANCE.createDoubleType();
        doubleDataType.setName(DataTypeLabels.doubleDataType);
        proc.getDatatypes().add(doubleDataType);
        // float NOT SUPORTED IN ENGINE YET
        //        DataType floatDataType = ProcessFactory.eINSTANCE.createFloatType();
        //        floatDataType.setName(DataTypeLabels.floatDataType);
        //        proc.getDatatypes().add(floatDataType);
        // string
        final DataType stringDataType = ProcessFactory.eINSTANCE.createStringType();
        stringDataType.setName(DataTypeLabels.stringDataType);
        proc.getDatatypes().add(stringDataType);
        //        // attachement
        //        DataType attachedDocDataType = ProcessFactory.eINSTANCE.createAttachmentType();
        //        attachedDocDataType.setName(DataTypeLabels.attachedDocDataType);
        //
        //        proc.getDatatypes().add(attachedDocDataType);
        // java
        final DataType javaDataType = ProcessFactory.eINSTANCE.createJavaType();
        javaDataType.setName(NamingUtils.convertToId(DataTypeLabels.javaDataType, javaDataType));
        proc.getDatatypes().add(javaDataType);
        // xml
        final DataType xmlDataType = ProcessFactory.eINSTANCE.createXMLType();
        xmlDataType.setName(DataTypeLabels.xmlDataType);
        proc.getDatatypes().add(xmlDataType);

        // xml
        final DataType businessObjectType = ProcessFactory.eINSTANCE.createBusinessObjectType();
        businessObjectType.setName(NamingUtils.convertToId(DataTypeLabels.businessObjectType));
        proc.getDatatypes().add(businessObjectType);
    }

    public static DataType getDataTypeForID(final EObject elem, final String name) {
        final MainProcess proc = getMainProcess(elem);
        if (proc != null) {
            for (final DataType type : proc.getDatatypes()) {
                if (type.getName().equals(NamingUtils.convertToId(name))) {
                    return type;
                }
            }
        }
        return null;
    }

    public static List<Data> getMessageSourceAccessibleData(final MessageFlow messageFlow) {
        final List<Data> datas = new ArrayList<Data>();

        final FlowElement source = messageFlow.getSource();
        if (source instanceof DataAware) {
            datas.addAll(((DataAware) source).getData());
        }

        datas.addAll(ModelHelper.getParentProcess(source).getData());

        return datas;
    }

    public static Message findEvent(final Element element, final String eventName) {
        final List<Message> events = new ArrayList<Message>();
        findAllEvents(ModelHelper.getMainProcess(element), events);
        // first names, then labels
        for (final Message event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    public static void findAllEvents(final Element element, final List<Message> events) {

        if (element instanceof ThrowMessageEvent) {
            for (final Message ev : ((ThrowMessageEvent) element).getEvents()) {
                events.add(ev);
            }
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllEvents(e, events);
            }

        }
    }

    public static List<AbstractCatchMessageEvent> getAllCatchEvent(final AbstractProcess proc) {
        return getAllItemsOfType(proc, ProcessPackage.eINSTANCE.getAbstractCatchMessageEvent());
    }

    public static void findAllCatchEvents(final Element element, final List<AbstractCatchMessageEvent> events) {

        if (element instanceof AbstractCatchMessageEvent) {
            events.add((AbstractCatchMessageEvent) element);
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllCatchEvents(e, events);
            }

        }
    }

    public static void findAllElements(final Element element, final List<Element> elements, final List<EClass> types) {

        for (final EClass eClass : types) {
            if (eClass.isSuperTypeOf(element.eClass())) {
                elements.add(element);
                break;
            }
        }

        if (element instanceof DataAware) {
            for (final Data d : ((DataAware) element).getData()) {
                findAllElements(d, elements, types);
            }
        }
        // boundary
        if (element instanceof Activity) {
            for (final BoundaryEvent b : ((Activity) element).getBoundaryIntermediateEvents()) {
                findAllElements(b, elements, types);
            }
        }

        if (element instanceof AbstractProcess) {
            for (final Connection c : ((AbstractProcess) element).getConnections()) {
                findAllElements(c, elements, types);
            }
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllElements(e, elements, types);
            }
        }
    }

    public static AbstractProcess findProcess(final Element element, final String procName) {
        //		if(findNewProcess(element, procName).getSize() != 1){
        //			System.out.println("it doesn't work to find process");
        //		}

        return findOldProcesses(element, procName);
    }

    //
    //	private static ResultSet findNewProcess(final Element element, String procName) {
    //		MQLquery query = findQuery("findProcess");
    //		Query internalQuery = QueryTransformer.transform(query, new Object[]{procName});
    //		final ResultSet result = queryProcessor.execute(internalQuery, new QueryContext() {
    //
    //			public ResourceSet getResourceSet() {
    //				return new ResourceSetImpl();
    //			}
    //
    //			public URI[] getResourceScope() {
    //				return new URI[]{element.eResource().getURI()};
    //			}
    //		});
    //
    //		return result;
    //
    //	}

    protected static AbstractProcess findOldProcesses(final Element element,
            final String procName) {
        final MainProcess proc = getMainProcess(element);
        final List<AbstractProcess> processes = getAllProcesses(proc);
        for (final AbstractProcess p : processes) {
            if (procName.equals(p.getName())) {
                return p;
            }
        }

        return null;
    }

    public static List<EObject> getAllConnectors(final MainProcess process) {
        final List<EObject> connectors = new ArrayList<EObject>();
        findAllConnectors(process, connectors);
        return connectors;
    }

    public static void findAllConnectors(final Element element, final List<EObject> connectors) {

        if (element instanceof ConnectableElement) {
            for (final Connector c : ((ConnectableElement) element).getConnectors()) {
                connectors.add(c);
            }
        }

        if (element instanceof PageFlow) {
            for (final Connector c : ((PageFlow) element).getPageFlowConnectors()) {
                connectors.add(c);
            }
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllConnectors(e, connectors);
            }

        }

    }

    public static String getDataTypeNLLabel(final String name) {

        if (STRING_DATA_TYPE.equals(name)) {
            return Messages.StringType;
        }
        if (INTEGER_DATA_TYPE.equals(name)) {
            return Messages.IntegerType;
        }
        if (FLOAT_DATA_TYPE.equals(name)) {
            return Messages.FloatType;
        }
        if (BOOLEAN_DATA_TYPE.equals(name)) {
            return Messages.BooleanType;
        }
        if (DATE_DATA_TYPE.equals(name)) {
            return Messages.DataType;
        }
        if (JAVA_DATA_TYPE.equals(name)) {
            return Messages.JavaType;
        }

        return name;
    }

    public static String getDataTypeID(final String label) {

        if (label.equals(Messages.StringType)) {
            return STRING_DATA_TYPE;
        }
        if (label.equals(Messages.IntegerType)) {
            return INTEGER_DATA_TYPE;
        }
        if (label.equals(Messages.FloatType)) {
            return FLOAT_DATA_TYPE;
        }
        if (label.equals(Messages.BooleanType)) {
            return BOOLEAN_DATA_TYPE;
        }
        if (label.equals(Messages.DataType)) {
            return DATE_DATA_TYPE;
        }
        if (label.equals(Messages.JavaType)) {
            return JAVA_DATA_TYPE;
        }

        return label;
    }

    public static AbstractProcess findProcess(final List<AbstractProcess> allProcesses, final String subDefName) {
        for (final AbstractProcess p : allProcesses) {
            if (subDefName.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }

    /**
     * @param subProcessName
     */
    public static AbstractProcess findProcess(final String subProcessName, final String version, final List<? extends Element> elements) {
        if (version == null || version.trim().isEmpty()) {// it's the latest version
            final List<AbstractProcess> processes = new ArrayList<AbstractProcess>();
            findProcessRecursivly(subProcessName, version, elements, processes);
            if (processes.size() > 0) {
                Collections.sort(processes, new Comparator<AbstractProcess>() {

                    @Override
                    public int compare(final AbstractProcess o1, final AbstractProcess o2) {
                        return o1.getVersion().compareTo(o2.getVersion());
                    }
                });
                return processes.get(processes.size() - 1);
            }
        } else {
            for (final Element item : elements) {
                if (item instanceof AbstractProcess) {
                    final AbstractProcess process = (AbstractProcess) item;
                    if (process.getName().equals(subProcessName) && version.equals(process.getVersion())) {
                        return process;
                    } else {
                        final AbstractProcess res = findProcess(subProcessName, version, process.getElements());
                        if (res != null) {
                            return res;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param subProcessName
     * @param version
     *            : this is not use by now
     * @param elements
     * @param processes
     */
    private static void findProcessRecursivly(final String subProcessName, final String version, final List<? extends Element> elements, final List<AbstractProcess> processes) {
        // TODO : use the version argument
        for (final Element item : elements) {
            if (item instanceof AbstractProcess) {
                final AbstractProcess process = (AbstractProcess) item;
                if (process.getName().equals(subProcessName)) {
                    processes.add(process);
                } else {
                    findProcessRecursivly(subProcessName, version, process.getElements(), processes);
                }
            }
        }
    }

    public static Set<Expression> findAllScriptAndConditionsExpressionWithReferencedElement(final EObject container, final EObject element) {
        final Set<Expression> result = new HashSet<Expression>();
        for (final EObject o : ModelHelper.getAllItemsOfType(container, ExpressionPackage.Literals.EXPRESSION)) {
            final Expression expr = (Expression) o;
            if (ExpressionConstants.SCRIPT_TYPE.equals(expr.getType()) || ExpressionConstants.CONDITION_TYPE.equals(expr.getType())
                    || ExpressionConstants.PATTERN_TYPE.equals(expr.getType())) {
                if (isElementIsReferencedInScript(expr, element)) {
                    result.add(expr);
                }
            }
        }
        return result;

    }

    private static boolean isElementIsReferencedInScript(final Expression expr, final EObject element) {
        if (!expr.getReferencedElements().isEmpty()) {
            for (final EObject o : expr.getReferencedElements()) {
                if (element instanceof Element && o instanceof Element && isSameElement(element, o)) {
                    return true && !isReferencedElementIsInExpression(expr);
                } else {
                    if (element instanceof Parameter && o instanceof Parameter && ((Parameter) element).getName().equals(((Parameter) o).getName())) {
                        return true && !isReferencedElementIsInExpression(expr);
                    }
                }
            }
        }
        return false;
    }

    public static boolean isReferencedElementIsInExpression(final EObject target) {
        EObject container = target.eContainer();
        while (container != null) {
            if (container instanceof Expression) {
                return true;
            }
            container = container.eContainer();
        }
        return false;
    }

    public static Set<Form> getAllFormsContainedIn(final EObject copiedElement) {
        // TODO : improve algo
        final Set<Form> res = new HashSet<Form>();
        /* search in all subcontents */
        final TreeIterator<?> ti = copiedElement.eAllContents();
        while (ti.hasNext()) {
            final Object o = ti.next();
            if (o instanceof Form) {
                res.add((Form) o);
            }
        }
        return res;
    }

    /**
     *
     * @param element
     * @param x
     * @param y
     * @return the widget in (x,y) in the form (the widget may be inside a group
     *         but not a group )
     */
    public static Widget getWidgetIn(final Element element, final int x, final int y, final boolean includeGroups) {
        Widget result = null;
        if (element instanceof Form) {
            for (final Widget w : ((Form) element).getWidgets()) {
                result = getWidgetIn(w, x, y, includeGroups);
                if (result != null) {
                    return result;
                }
            }

        } else if (element instanceof Group) {
            for (final Widget w : ((Group) element).getWidgets()) {
                result = getWidgetIn(w, x, y, includeGroups);
                if (result != null) {
                    return result;
                }
            }
            if (includeGroups) {

                int dx = 0;
                int dy = 0;
                EObject container = element.eContainer();
                while (container != null && !(container instanceof Form)) {
                    if (container instanceof Group) {
                        dx += ((Widget) container).getWidgetLayoutInfo().getColumn();
                        dy += ((Widget) container).getWidgetLayoutInfo().getLine();
                    }
                    container = container.eContainer();
                }
                if (y >= ((Widget) element).getWidgetLayoutInfo().getLine() + dy
                        && y < ((Widget) element).getWidgetLayoutInfo().getLine() + ((Widget) element).getWidgetLayoutInfo().getVerticalSpan() + dy
                        && x >= ((Widget) element).getWidgetLayoutInfo().getColumn() + dx
                        && x < ((Widget) element).getWidgetLayoutInfo().getColumn() + ((Widget) element).getWidgetLayoutInfo().getHorizontalSpan() + dx) {
                    return (Widget) element;
                }
            }
        } else {
            int dx = 0;
            int dy = 0;
            EObject container = element.eContainer();
            while (container != null && !(container instanceof Form)) {
                if (container instanceof Group) {
                    dx += ((Widget) container).getWidgetLayoutInfo().getColumn();
                    dy += ((Widget) container).getWidgetLayoutInfo().getLine();
                }
                container = container.eContainer();
            }
            if (y >= ((Widget) element).getWidgetLayoutInfo().getLine() + dy
                    && y < ((Widget) element).getWidgetLayoutInfo().getLine() + ((Widget) element).getWidgetLayoutInfo().getVerticalSpan() + dy
                    && x >= ((Widget) element).getWidgetLayoutInfo().getColumn() + dx
                    && x < ((Widget) element).getWidgetLayoutInfo().getColumn() + ((Widget) element).getWidgetLayoutInfo().getHorizontalSpan() + dx) {
                return (Widget) element;
            }
        }
        return null;

        /*
         * for (Widget w : parent.getWidgets()) { if (!(w instanceof Group) ||
         * includeGroups) { if (y >= w.getWidgetLayoutInfo().getLine() && y <
         * w.getWidgetLayoutInfo().getLine() +
         * w.getWidgetLayoutInfo().getVerticalSpan() && x >=
         * w.getWidgetLayoutInfo().getColumn() && x <
         * w.getWidgetLayoutInfo().getColumn() +
         * w.getWidgetLayoutInfo().getHorizontalSpan()) return w; } else { for
         * (Widget wg : ((Group) w).getWidgets()) { int dx =
         * w.getWidgetLayoutInfo().getColumn(); int dy =
         * w.getWidgetLayoutInfo().getLine(); if (y >=
         * wg.getWidgetLayoutInfo().getLine() + dy && y <
         * wg.getWidgetLayoutInfo().getLine() +
         * wg.getWidgetLayoutInfo().getVerticalSpan() + dy && x >=
         * wg.getWidgetLayoutInfo().getColumn() + dx && x <
         * wg.getWidgetLayoutInfo().getColumn() +
         * wg.getWidgetLayoutInfo().getHorizontalSpan() + dx) return w; } }
         * } return null;
         */
    }

    /**
     *
     * return the neareast empty widget slot available
     *
     * @param near
     */
    public static boolean findAvailableLocation(final Form form, final Point near) {
        return findAvailableLocation(form, near, new Point(1, 1));
    }

    /**
     *
     * return the neareast empty widget slot available
     *
     * @param near
     */
    public static boolean findAvailableLocation(final Form form, final Point near, final Point size) {

        final List<Point> slots = new ArrayList<Point>();
        for (int i = 0; i < form.getNColumn(); i++) {
            for (int j = 0; j < form.getNLine(); j++) {// for all cases in the grid
                if (getWidgetIn(form, i, j, false) == null) {// if there is no widget in the case
                    boolean available = true;
                    final Group potentialGroup = (Group) getWidgetIn(form, i, j, true);// check if we are in a group or not
                    if (potentialGroup != null) {
                        /* So we are in a Group */
                        for (int k = 0; k < size.x; k++) {
                            for (int l = 0; l < size.y; l++) {
                                if (k != 0 || l != 0) {
                                    final WidgetLayoutInfo groupLayoutInfo = potentialGroup.getWidgetLayoutInfo();
                                    EObject parent = potentialGroup.eContainer();
                                    int colOffset = groupLayoutInfo.getColumn();
                                    int lineOffset = groupLayoutInfo.getLine();
                                    while (parent instanceof Group) { // Manage group recursion
                                        final WidgetLayoutInfo layout = ((Group) parent).getWidgetLayoutInfo();
                                        colOffset = colOffset + layout.getColumn();
                                        lineOffset = lineOffset + layout.getLine();
                                        parent = parent.eContainer();
                                    }
                                    if (getWidgetIn(form, i + k, j + l, false) != null
                                            || i + k >= groupLayoutInfo.getHorizontalSpan() + colOffset
                                            || j + l >= groupLayoutInfo.getVerticalSpan() + lineOffset) {
                                        available = false;
                                        break;
                                    }
                                }
                            }
                            if (!available) {
                                break;
                            }
                        }
                    } else {
                        final EObject container = getWidgetContainer(form, i, j);
                        for (int k = 0; k < size.x; k++) {
                            for (int l = 0; l < size.y; l++) {// For all the place that the copied element took
                                if (k != 0 || l != 0) {
                                    if (getWidgetIn(form, i + k, j + l, false) != null
                                            || i + k >= form.getNColumn()
                                            || j + l >= form.getNLine()
                                            || !getWidgetContainer(form, i + k, j + l).equals(container)) {// check that the case is empty and that we are still
                                        // on the grid
                                        available = false;
                                        break;
                                    }
                                }
                            }
                            if (!available) {
                                break;
                            }
                        }
                    }
                    if (available) {
                        slots.add(new Point(i, j));
                    }
                }
            }
        }
        Point nearest = new Point(-1, -1);
        int min = Integer.MAX_VALUE;
        int dist;
        for (final Point point : slots) {
            dist = near.getDistance2(point);
            if (dist < min) {
                min = dist;
                nearest = point;
            }

        }
        if (nearest.x >= 0 && nearest.y >= 0) {
            near.x = nearest.x;
            near.y = nearest.y;
            return true;
        } else {
            return false;
        }
    }

    public static EObject getWidgetContainer(final Form form, final int i, final int j) {
        final Widget w = getWidgetIn(form, i, j, true);
        if (w != null) {
            if (w instanceof Group) {
                return w;
            } else {
                return w.eContainer();
            }
        } else {
            return form;
        }

    }

    /**
     * @param widget
     * @return the form in which widget is contained.
     */
    public static Form getForm(final Widget widget) {
        EObject o = widget;
        while (!(o instanceof Form) && o != null) {
            o = o.eContainer();
        }
        return (Form) o;
    }

    /**
     * @param form
     * @return all widgets contained in the form.
     */
    public static List<Widget> getAllWidgetInsideForm(final Form form) {
        final List<Widget> res = new ArrayList<Widget>();
        for (final Widget w : form.getWidgets()) {
            res.addAll(getWidgets(w));
        }
        return res;
    }

    /**
     * @param pageFlow
     * @param eContainmentFeature
     * @return
     */
    public static List<Widget> getAllWidgetInsidePageFlow(final Element pageFlow, final EReference eContainmentFeature) {
        final List<Widget> res = new ArrayList<Widget>();
        @SuppressWarnings("unchecked")
        final
        List<Form> forms = (List<Form>) pageFlow.eGet(eContainmentFeature);
        for (final Form form : forms) {
            final List<Widget> widgets = getAllWidgetInsideForm(form);
            for (final Widget element : widgets) {
                if (!res.contains(element)) {
                    res.add(element);
                }
            }
        }
        return res;
    }

    /**
     * @param pageFlow
     * @param eContainmentFeature
     * @param relativeWidget
     * @return
     */
    public static List<Widget> getAllAccessibleFieldsInsidePageFlow(final Element pageFlow, final EReference eContainmentFeature, final Element relativeWidget) {
        final ArrayList<Widget> res = new ArrayList<Widget>();
        if (pageFlow != null) {
            for (final Form form : (List<Form>) pageFlow.eGet(eContainmentFeature)) {
                for (final Widget widget2 : form.getWidgets()) {
                    getAccessibleFields(res, widget2, relativeWidget);
                }
            }
        }
        return res;
    }

    /**
     * @param res
     * @param widget2
     * @param widget
     */
    private static void getAccessibleFields(final ArrayList<Widget> res, final Widget widget, final Element relativeWidget) {
        if (widget instanceof Group) {
            final Group group = (Group) widget;
            if (group.isDuplicate()) {
                res.add(group);
                if (isTargetWidgetInsideGroup(group, relativeWidget)) {
                    for (final Widget widget2 : group.getWidgets()) {
                        if (widget2 instanceof FormField) {
                            getAccessibleFields(res, widget2, relativeWidget);
                        }
                    }
                }
            } else {
                res.add(group);
                for (final Widget widget2 : group.getWidgets()) {
                    if (widget2 instanceof FormField) {
                        getAccessibleFields(res, widget2, relativeWidget);
                    }
                }
            }
        } else if (widget instanceof FormField) {
            res.add(widget);
        }
    }

    private static boolean isTargetWidgetInsideGroup(final Group group, final Element relativeWidget) {
        if (relativeWidget != null) {
            for (final Widget widgetInGroup : group.getWidgets()) {
                if (widgetInGroup.equals(relativeWidget)) {
                    return true;
                } else {
                    if (widgetInGroup instanceof Group) {
                        return isTargetWidgetInsideGroup((Group) widgetInGroup, relativeWidget);
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param widget
     * @return the widget and if it is a Group all of its underlying widgets.
     */
    protected static List<Widget> getWidgets(final Widget widget) {
        if (widget instanceof Group) {
            final List<Widget> res = new ArrayList<Widget>();
            res.add(widget);
            for (final Widget w : ((Group) widget).getWidgets()) {
                res.addAll(getWidgets(w));
            }
            return res;
        } else {
            return Collections.singletonList(widget);
        }
    }

    /**
     * @param modelProcess2
     * @return
     */
    public static List<FlowElement> getFlowElements(final Container container, final boolean includeSubContainers) {
        final ArrayList<FlowElement> flowElements = new ArrayList<FlowElement>();
        for (final Element el : container.getElements()) {
            if (el instanceof FlowElement) {
                flowElements.add((FlowElement) el);
            }
            if (includeSubContainers && el instanceof Container) {
                flowElements.addAll(getFlowElements((Container) el, includeSubContainers));
            }
        }
        return flowElements;
    }

    /**
     * @param name
     * @return
     */
    public static Element findElement(final Container container, final String name, final boolean includeSubContainers) {
        for (final Element el : container.getElements()) {
            if (el.getName().equals(name)) {
                return el;
            }
            if (includeSubContainers && el instanceof Container) {
                final Element res = findElement((Container) el, name, includeSubContainers);
                if (res != null) {
                    return res;
                }
            }
        }
        return null;
    }

    /**
     * @param form
     * @return the diagram corresponding to the form.
     */
    public static Diagram getDiagramFor(final EObject element, final Resource resource) {
        if (element == null) {
            return null;
        }
        if (!resource.isLoaded()) {
            throw new IllegalStateException("EMF Resource is not loaded.");
        }

        final RunnableWithResult<Diagram> runnableWithResult = new DiagramForElementRunnable(resource, element);
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(resource);
        if (editingDomain != null) {
            try {
                editingDomain.runExclusive(runnableWithResult);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        } else {
            runnableWithResult.run();
        }
        return runnableWithResult.getResult();
    }

    public static Diagram getDiagramFor(final EObject element) {
        if (element != null && element.eResource() != null) {
            return getDiagramFor(element, TransactionUtil.getEditingDomain(element.eResource()));
        }
        return null;
    }

    public static Diagram getDiagramFor(final EObject element, EditingDomain domain) {
        if (element == null) {
            return null;
        }
        Resource resource = null;
        if (domain == null) {
            domain = TransactionUtil.getEditingDomain(element);
            if (domain != null) {
                resource = domain.getResourceSet().getResource(element.eResource().getURI(), true);
            } else {
                resource = element.eResource();
            }

        } else {
            resource = domain.getResourceSet().getResource(element.eResource().getURI(), true);
        }
        return getDiagramFor(element, resource);
    }

    public static Widget getWidgetById(final Form form, final String id) {
        for (final Widget w : ModelHelper.getAllWidgetInsideForm(form)) {
            if (w.getName().equals(id)) {
                return w;
            }
        }
        return null;
    }

    public static boolean isInDuplicatedGrp(final Object object) {

        if (object instanceof Widget) {
            EObject parent = ((EObject) object).eContainer();
            if (parent instanceof Group) {
                while (parent != null && !(parent instanceof Form)) {
                    if (((Group) parent).isDuplicate()) {
                        return true;
                    }
                    parent = parent.eContainer();
                }
            }
        }
        return false;
    }

    public static boolean isInEntryPageFlowOnAPool(final Widget widget) {
        final Form form = getParentForm(widget);
        return isAnEntryPageFlowOnAPool(form);
    }

    public static boolean isAnEntryPageFlowOnAPool(final Form element) {
        return !(element instanceof ViewForm) && (PageFlow) element.eContainer() instanceof Pool;
    }

    /**
     * @return
     *
     */
    public static String getEObjectID(final EObject eObject) {
        if (eObject == null) {
            return null;
        }
        final Resource eResource = eObject.eResource();
        if (eResource != null) {
            return eResource.getURIFragment(eObject);
        }
        return null;
    }

    public static boolean isDuplicated(final Object object) {
        if (object instanceof Duplicable) {
            if (((Duplicable) object).isDuplicate()) {
                return true;
            }
        }
        return false;
    }

    public static void findAllThrowLinks(final Element root, final List<ThrowLinkEvent> events) {
        if (root instanceof ThrowLinkEvent) {
            events.add((ThrowLinkEvent) root);
        }

        if (root instanceof Container) {
            for (final Element e : ((Container) root).getElements()) {
                findAllThrowLinks(e, events);
            }

        }

    }

    public static void findAllCatchLinks(final Element root, final List<CatchLinkEvent> events) {
        if (root instanceof CatchLinkEvent) {
            events.add((CatchLinkEvent) root);
        }

        if (root instanceof Container) {
            for (final Element e : ((Container) root).getElements()) {
                findAllCatchLinks(e, events);
            }

        }

    }

    public static List<EnumType> getAllUserDatatype(final MainProcess process) {
        final List<EnumType> result = new ArrayList<EnumType>();
        for (final DataType d : process.getDatatypes()) {
            if (d instanceof EnumType) {
                result.add((EnumType) d);
            }
        }
        return result;
    }

    public static AbstractPageFlow getPageFlow(final Widget w) {
        return (AbstractPageFlow) ModelHelper.getForm(w).eContainer();
    }

    public static List<Widget> getAllWidgetInsidePageFlow(final PageFlow pageFlow) {
        return getAllWidgetInsidePageFlow(pageFlow, ProcessPackage.Literals.PAGE_FLOW__FORM);
    }

    /**
     * @param pageFlow
     * @return
     */
    public static List<Widget> getAllWidgetInsideConsultationPageFlow(final ViewPageFlow pageFlow) {
        return getAllWidgetInsidePageFlow(pageFlow, ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM);
    }

    /**
     *
     * remove all element that are referenced outside the object and remove
     * element that are missing required fields
     *
     * @param widget
     */
    public static void removedReferencedEObjects(final EObject eObject, final EObject targetContainer) {

        final Set<EObject> containedEObjects = new HashSet<EObject>();
        // get all contained EObjects
        getContainedObjectRecusivly(containedEObjects, eObject);
        containedEObjects.add(eObject);
        for (final EObject toCheck : containedEObjects) {
            for (final EReference reference : toCheck.eClass().getEAllReferences()) {
                final Object o = toCheck.eGet(reference);
                if (o instanceof EObject) {
                    final EObject child = (EObject) o;
                    // keep enum reference from the same diagram
                    if (child instanceof Data && ((Data) child).getDataType() instanceof EnumType) {
                        final MainProcess mainProcess = ModelHelper.getMainProcess(targetContainer);
                        final MainProcess childMainProcess = ModelHelper.getMainProcess(child);
                        if (mainProcess != null
                                && childMainProcess != null
                                && mainProcess.equals(childMainProcess)) {
                            break;
                        }
                    }
                    if (!containedEObjects.contains(child)) {
                        boolean removeReference = true;
                        if (child instanceof DataType) { // retrieve the equivalent Data Type in the target MainProcess
                            final MainProcess mainProcess = ModelHelper.getMainProcess(targetContainer);
                            final MainProcess childMainProcess = ModelHelper.getMainProcess(child);
                            if (mainProcess != null
                                    && childMainProcess != null
                                    && mainProcess.equals(childMainProcess)) {
                                removeReference = false;
                            } else {
                                for (final DataType dt : mainProcess.getDatatypes()) {
                                    if (dt.eClass().equals(child.eClass())) {
                                        if (dt.eClass().equals(ProcessPackage.Literals.ENUM_TYPE)) {
                                            if (((EnumType) child).getName().equals(dt.getName())) {
                                                toCheck.eSet(reference, dt);
                                                removeReference = false;
                                                break;
                                            }
                                        } else {
                                            toCheck.eSet(reference, dt);
                                            removeReference = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (removeReference) {
                            // referenced outside: we unset it
                            toCheck.eUnset(reference);
                            // must not be the main eobject
                            if (reference.isRequired() && !toCheck.equals(eObject)) {
                                // field is required: we remove it
                                EcoreUtil.remove(toCheck);
                                break;
                            }
                        }
                    }
                }

            }
        }
    }

    /**
     *
     * remove all element that are referenced outside the object and remove
     * element that are missing required fields
     *
     * @param widget
     */
    public static void removedInvalidReferencedEObjects(final EObject eObject) {

        final Set<EObject> containedEObjects = new HashSet<EObject>();
        // get all contained EObjects
        getContainedObjectRecusivly(containedEObjects, eObject);
        containedEObjects.add(eObject);
        for (final EObject toCheck : containedEObjects) {
            for (final EReference reference : toCheck.eClass().getEAllReferences()) {
                final Object o = toCheck.eGet(reference);

                if (o instanceof EObject) {
                    final EObject child = (EObject) o;
                    if (child.eResource() != null && !child.eResource().equals(eObject.eResource())) {
                        // referenced outside: we unset it
                        toCheck.eUnset(reference);

                    }
                }
            }
        }
    }

    private static void getContainedObjectRecusivly(final Set<EObject> objects, final EObject eObject) {

        for (final EReference reference : eObject.eClass().getEAllContainments()) {

            final Object o = eObject.eGet(reference);
            if (o instanceof EObject) {
                final EObject child = (EObject) o;
                if (child != null) {
                    objects.add(child);
                    getContainedObjectRecusivly(objects, child);
                }
            } else if (o instanceof List<?>) {
                for (final Object childO : (List<?>) o) {
                    if (childO instanceof EObject) {
                        objects.add((EObject) childO);
                        getContainedObjectRecusivly(objects, (EObject) childO);
                    }
                }
            }

        }
    }

    public static void findAllElementsByNature(final Element element, final List<Element> foundElement, final List<EClass> supertypes) {

        for (final EClass eClass : supertypes) {
            if (eClass.isSuperTypeOf(element.eClass())) {
                if (!foundElement.contains(element)) {
                    foundElement.add(element);
                }
            }
        }

        if (element instanceof DataAware) {
            for (final Data d : ((DataAware) element).getData()) {
                findAllElementsByNature(d, foundElement, supertypes);
            }
        }

        if (element instanceof AbstractProcess) {
            for (final Connection c : ((AbstractProcess) element).getConnections()) {
                findAllElementsByNature(c, foundElement, supertypes);
            }
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllElementsByNature(e, foundElement, supertypes);
            }
        }

    }

    /**
     * @param model
     * @return
     */
    public static List<Widget> getAllWidgetsInModel(final EObject container) {
        return getAllItemsOfType(container, FormPackage.Literals.WIDGET);
    }

    public static <T extends EObject> List<T> getAllItemsOfType(final EObject container, final EClass eClass) {
        final List<T> res = new ArrayList<T>();
        addAllWidgetsOfContainer(container, res, eClass);
        return res;
    }

    /**
     * @param container
     * @param res
     */
    private static <T extends EObject> void addAllWidgetsOfContainer(final EObject container, final List<T> res, final EClass eClass) {
        if (container != null) {
            if (eClass.isSuperTypeOf(container.eClass())) {
                res.add((T) container);
            }
            for (final EObject child : container.eContents()) {
                addAllWidgetsOfContainer(child, res, eClass);
            }
        }

    }

    public static Set<String> getExistingConnectorsName(final Element eObject) {
        final Set<String> result = new HashSet<String>();
        final MainProcess mainProc = ModelHelper.getMainProcess(eObject);
        final List<Connector> connectors = getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);

        for (final Connector c : connectors) {
            if (c.getName().trim().length() > 0) {
                result.add(c.getName());
            }
        }

        return result;
    }

    public static boolean isInEvenementialSubProcessPool(final EObject semanticElement) {
        EObject tempPart = semanticElement;
        while (tempPart != null && !(tempPart instanceof SubProcessEvent)) {
            tempPart = tempPart.eContainer();
        }
        return tempPart instanceof SubProcessEvent;
    }

    public static List<AbstractCatchMessageEvent> findAllCatchEventsCatching(final MainProcess mainProcess, final String eventName) {
        final List<AbstractCatchMessageEvent> result = new ArrayList<AbstractCatchMessageEvent>();
        for (final AbstractCatchMessageEvent ev : getAllCatchEvent(mainProcess)) {
            if (ev.getEvent() != null && ev.getEvent().equals(eventName)) {
                result.add(ev);
            }
        }
        return result;
    }

    public static SubProcessEvent findSubprocessEvent(final AbstractProcess process, final String name) {
        for (final EObject e : ModelHelper.getAllItemsOfType(process, ProcessPackage.eINSTANCE.getSubProcessEvent())) {
            if (((Element) e).getName().equals(name)) {
                return (SubProcessEvent) e;
            }
        }
        return null;
    }

    /**
     * As we did a really bad architecture, we need to differentiate concrete Activity which are Abstract Task
     *
     * @param model
     * @return
     */
    public static boolean isActivityButNotAbstractTask(final Activity model) {
        return model instanceof ReceiveTask
                || model instanceof ScriptTask
                || model instanceof SendTask
                || model instanceof ServiceTask
                || model instanceof CallActivity
                || model instanceof Task;
    }

    public static List<Data> getAccessibleData(final EObject element, final boolean includeTransientData) {
        final List<Data> result = new ArrayList<Data>();
        if (includeTransientData) {
            final Form parentForm = getParentForm(element);
            if (parentForm != null) {
                result.addAll(ModelHelper.getAccessibleDataInFormsWithNoRestriction(parentForm.eContainer(), parentForm.eContainingFeature()));
            }
            for (final Data d : ModelHelper.getAccessibleData(element)) {
                if (!result.contains(d)) {
                    result.add(d);
                }
            }
            return result;
        } else {
            final List<Data> data = new ArrayList<Data>();
            for (final Data d : ModelHelper.getAccessibleData(element)) {
                if (!d.isTransient()) {
                    data.add(d);
                }
            }
            return data;
        }
    }

    public static ConnectableElement getParentConnectableElement(final Element element) {
        if (element != null) {
            EObject result = element;
            if (element instanceof Widget) {
                result = ModelHelper.getPageFlow((Widget) element);
            }
            while (result != null && !(result instanceof ConnectableElement)) {
                result = result.eContainer();
            }
            if (result != null) {
                return (ConnectableElement) result;
            }
        }
        return null;
    }

    public static List<AbstractProcess> findAllProcesses(
            final List<AbstractProcess> allProcesses, final String subDefName) {
        final List<AbstractProcess> processes = new ArrayList<AbstractProcess>();
        for (final AbstractProcess p : allProcesses) {
            if (subDefName.equals(p.getName())) {
                processes.add(p);
            }
        }
        return processes;
    }

    /**
     *
     * Check if the form have a custome page template set
     *
     * @param element
     * @return
     */
    public static boolean formIsCustomized(final Form form) {
        return form.getHtmlTemplate() != null && form.getHtmlTemplate().getPath() != null && !form.getHtmlTemplate().getPath().isEmpty();
    }


    public static Document getDocumentReferencedInExpression(final Expression expr) {
        final List<EObject> refs = expr.getReferencedElements();
        for (final EObject ref : refs) {
            if (ref instanceof Document && ((Document) ref).getName().equals(expr.getContent())) {
                return (Document) ref;
            }
        }
        return null;
    }

    public static Lane getParentLane(final EObject element) {
        EObject lane = element.eContainer();
        while (lane != null && !(lane instanceof Lane)) {
            lane = lane.eContainer();
        }
        return (Lane) lane;
    }

    public static List<SimulationData> getAccessibleSimulationData(final EObject context) {
        final List<SimulationData> result = new ArrayList<SimulationData>();
        if (context instanceof SimulationDataContainer) {
            SimulationDataContainer container = (SimulationDataContainer) context;
            while (container != null) {
                for (final SimulationData d : container.getSimulationData()) {
                    result.add(d);
                }
                if (container.eContainer() instanceof SimulationDataContainer) {
                    container = (SimulationDataContainer) container.eContainer();
                } else {
                    container = null;
                }
            }

        } else if (context instanceof SimulationTransition) {
            if (((Connection) context).eContainer() instanceof SimulationDataContainer) {
                for (final SimulationData d : ((SimulationDataContainer) ((Connection) context).eContainer()).getSimulationData()) {
                    result.add(d);
                }
            }
        }
        return result;
    }

    public static FlowElement getParentFlowElement(final EObject eObject) {
        EObject flowElement = eObject;
        while (flowElement != null && !(flowElement instanceof FlowElement)) {
            flowElement = flowElement.eContainer();
        }
        return flowElement != null ? (FlowElement) flowElement : null;
    }

    public static Widget getParentWidget(final EObject eObject) {
        EObject widget = eObject;
        while (widget != null && !(widget instanceof Widget)) {
            widget = widget.eContainer();
        }
        return widget != null ? (Widget) widget : null;
    }

    public static Form getParentForm(final EObject eObject) {
        EObject form = eObject;
        while (form != null && !(form instanceof Form)) {
            form = form.eContainer();
        }
        return form != null ? (Form) form : null;
    }

    public static boolean isAnExpressionCopy(final Expression expression) {
        EObject current = expression;
        EObject container = current.eContainer();
        while (container != null) {
            if (current.eContainingFeature().equals(ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS)) {
                return true;
            }
            current = container;
            container = current.eContainer();
        }
        return false;
    }

    public static Element getParentElement(final EObject object) {
        EObject element = object;
        while (element != null && !(element instanceof Element)) {
            element = element.eContainer();
        }
        return (Element) element;
    }

    public static  List<Widget> getAllAccessibleWidgetInsideForm(final Form form) {
        final List<Widget> res = new ArrayList<Widget>();
        for (final Widget w : form.getWidgets()) {
            res.addAll(getAccessibleWidgets(w));
        }
        return res;
    }

    protected static List<Widget> getAccessibleWidgets(final Widget widget) {
        if (widget instanceof Group) {
            final List<Widget> res = new ArrayList<Widget>();
            res.add(widget);
            if (!((Group) widget).isDuplicate()) {
                for (final Widget w : ((Group) widget).getWidgets()) {
                    res.addAll(getWidgets(w));
                }
            }
            return res;
        } else {
            return Collections.singletonList(widget);
        }
    }

    public static Group getParentGroup(final EObject context) {
        Widget widget = getParentWidget(context);

        if(context.equals(widget) && widget instanceof Group && widget.eContainer() instanceof Group){
            widget = (Widget) widget.eContainer();
        }

        if(widget != null){
            Widget parentGroup = widget;
            while (parentGroup != null && !(parentGroup instanceof Group)) {
                final EObject parent = parentGroup.eContainer();
                if (parent instanceof Widget) {
                    parentGroup = (Widget) parent;
                } else {
                    parentGroup = null;
                }
            }
            if (parentGroup != null) {
                return (Group) parentGroup;
            }
        }
        return null;
    }


    public static boolean isObjectIsReferencedInExpression(final Expression expr, final Object elementToDisplay) {
        for (final EObject referencedElement:expr.getReferencedElements()){
            if (referencedElement instanceof Parameter && elementToDisplay instanceof Parameter && ((Parameter)referencedElement).getName().equals(((Parameter)elementToDisplay).getName())){
                return true;
            }

            if (referencedElement instanceof SearchIndex && elementToDisplay instanceof SearchIndex && ((SearchIndex)referencedElement).getName().getName().equals(((SearchIndex)elementToDisplay).getName().getName())){
                return true;
            }

            if (referencedElement instanceof Element && elementToDisplay instanceof Element && isSameElement((Element) elementToDisplay, referencedElement)) {
                return true;
            }
        }
        return false;
    }

    protected static Activity getReferencedDataActivityContainer(final Data refData) {
        EObject container = refData.eContainer();
        while (container != null && !(container instanceof Activity)) {
            container = container.eContainer();
        }
        if (container != null) {
            if (getDataOnActivity(refData, container) != null) {
                return (Activity) container;
            }
        }
        return null;
    }

    protected static Pool getReferencedDataPoolContainer(final Data refData) {
        EObject container = refData.eContainer();
        while (container != null && !(container instanceof Pool)) {
            container = container.eContainer();
        }
        if (container != null) {
            if (getDataOnPool(refData, container) != null) {
                return (Pool) container;
            }
        }
        return null;
    }

    /**
     * @param refData
     * @param container
     */
    protected static Data getDataOnActivity(final Data refData, final EObject container) {
        final List<Data> datas = ((Activity) container).getData();
        for (final Data data : datas) {
            if (data.getName().equals(refData.getName())) {
                return data;
            }
        }
        return null;
    }

    protected static Data getDataOnPool(final Data refData, final EObject container) {
        final List<Data> datas = ((Pool) container).getData();
        for (final Data data : datas) {
            if (data.getName().equals(refData.getName())) {
                return data;
            }
        }
        return null;
    }

    protected static boolean isSameContainer(final EObject referencedElement, final EObject element) {
        if (referencedElement instanceof Data) {
            final Activity stepContainer = getReferencedDataActivityContainer((Data) referencedElement);
            if (stepContainer!=null){
                return stepContainer.equals(element.eContainer());
            }
            final Pool poolContainer = getReferencedDataPoolContainer((Data) referencedElement);
            if (poolContainer != null) {
                return poolContainer.equals(element.eContainer());
            }
            return false;
        }
        return true;
    }

    /**
     * @param elementToDisplay
     * @param referencedElement
     * @return
     */
    protected static boolean isSameElement(final EObject elementToDisplay, final EObject referencedElement) {
        if (elementToDisplay.eContainer() != null) {
            return ((Element) referencedElement).getName().equals(((Element) elementToDisplay).getName())
                    && isSameContainer(referencedElement, elementToDisplay);
        } else {
            return EcoreUtil.equals(elementToDisplay, referencedElement);
        }
    }

}
