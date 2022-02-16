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
import java.util.Objects;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.web.designer.model.contract.DataReference;

public class TreeResult {

    private List<TreeNode> nodes = new ArrayList<>();

    public void addNode(TreeNode node) {
        nodes.add(node);
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public DataReference getDataReference(ContractInput input) {
        return findReference(input, nodes);
    }

    private DataReference findReference(ContractInput input, List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (Objects.equals(node.getInput(), input)) {
                return node.getRef();
            }
            DataReference reference = findReference(input, node.getChildren());
            if (reference != null) {
                return reference;
            }
        }
        return null;
    }

    private TreeNode findNode(ContractInput input, List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (Objects.equals(node.getInput(), input)) {
                return node;
            }
            TreeNode foundNode = findNode(input, node.getChildren());
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }

    public boolean isMandatory(ContractInput input) {
        TreeNode node = findNode(input, nodes);
        return node != null ? node.isMandatory() : true;
    }

    public boolean isReadOnly(ContractInput input) {
        TreeNode node = findNode(input, nodes);
        return node != null ? node.isReadOnly() : false;
    }
}
