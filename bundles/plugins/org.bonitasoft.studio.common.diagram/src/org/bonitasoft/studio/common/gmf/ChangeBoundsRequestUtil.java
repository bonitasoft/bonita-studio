/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
ense, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.gmf;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

public class ChangeBoundsRequestUtil {

    public static boolean isMovingToAnotherProcess(final EditPart host, final ChangeBoundsRequest request) {
        //find the target EditPart on which we will move the getHost()
        if (host.getViewer().findObjectAt(request.getLocation()) instanceof IGraphicalEditPart) {
            final IGraphicalEditPart target = (IGraphicalEditPart) host.getViewer().findObjectAt(request.getLocation());
            EObject source = null;
            for (final Object e : request.getEditParts()) {
                source = ((IGraphicalEditPart) e).resolveSemanticElement();
            }
            return areInSameProcess(source, target.resolveSemanticElement());
        }
        return true;
    }

    private static boolean areInSameProcess(final EObject o, final EObject hostObject) {
        return ModelHelper.getParentProcess(o).equals(ModelHelper.getParentProcess(hostObject));
    }

}
