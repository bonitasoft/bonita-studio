/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.core.mapping.treeMaching;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.web.designer.model.contract.DataReference;

public class TreeNode {

    private ContractInput input;
    private DataReference ref;
    private boolean mandatory = true;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(ContractInput input, DataReference ref,boolean mandatory) {
        this.input = input;
        this.ref = ref;
        this.mandatory = mandatory;
    }

    public TreeNode addNode(ContractInput input, DataReference ref,boolean mandatory) {
        TreeNode node = new TreeNode(input, ref,mandatory);
        this.children.add(node);
        return node;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public ContractInput getInput() {
        return input;
    }

    public DataReference getRef() {
        return ref;
    }
    
    public boolean isMandatory() {
        return mandatory;
    }

    public TreeNode addLeafNode(ContractInput input, boolean mandatory) {
        TreeNode node = new TreeNode(input, null, mandatory);
        this.children.add(node);
        return node;
    }
}
