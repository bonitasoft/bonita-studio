///**
// * Copyright (C) 2011 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.common.repository.migration;
//
//import org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.core.runtime.IStatus;
//
///**
// * @author Romain Bioteau
// *
// */
//public interface IMigrationTool<T extends AbstractRepositoryArtifact> {
//	
//	public static final String VERSION_5_0_PREVIEW = "5.0.preview"; //$NON-NLS-1$
//	public static final String VERSION_5_0_M4 = "5.0.M4"; //$NON-NLS-1$
//	public static final String VERSION_5_0_M5 = "5.0.M5"; //$NON-NLS-1$
//	public static final String VERSION_5_0_RC1 = "5.0.RC1"; //$NON-NLS-1$
//	public static final String VERSION_5_0 = "5.0"; //$NON-NLS-1$
//	public static final String VERSION_5_1_M1 = "5.1.M1"; //$NON-NLS-1$
//	public static final String VERSION_5_1 = "5.1"; //$NON-NLS-1$
//	public static final String VERSION_5_2 = "5.2"; //$NON-NLS-1$
//	public static final String VERSION_5_3 = "5.3"; //$NON-NLS-1$
//	public static final String VERSION_5_4 = "5.4"; //$NON-NLS-1$	
//	public static final String VERSION_5_5 = "5.5"; //$NON-NLS-1$	
//	public static final String VERSION_5_6 = "5.6"; //$NON-NLS-1$
//	public static final String VERSION_6_0 = "6.0"; //$NON-NLS-1$	
//	public static final String LATEST_BONITA_VERSION = VERSION_6_0;
//	public static final String LATEST_MODEL_VERSION = VERSION_6_0;
//	
//	public static String[] SORTED_VERSIONS = new String[] {
//		VERSION_5_0_PREVIEW,
//		VERSION_5_0_M4,
//		VERSION_5_0_M5,
//		VERSION_5_0_RC1,
//		VERSION_5_0,
//		VERSION_5_1_M1,
//		VERSION_5_1,
//		VERSION_5_2,
//		VERSION_5_3,
//		VERSION_5_4,
//		VERSION_5_5,
//		VERSION_5_6,
//		VERSION_6_0
//	};
//	
//	public IStatus moveToCurrentVersion(T artifact, IProgressMonitor monitor) ;
//
//	public Object getResult() ;
//}
