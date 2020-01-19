/**
 * Copyright (C) 2020 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.editor.refactor;

import java.util.ArrayDeque;
import java.util.Queue;

public class BDMRefactorQueue {

    private static BDMRefactorQueue INSTANCE;

    Queue<DiffElement> queue;

    private BDMRefactorQueue() {
        queue = new ArrayDeque<>();
    }

    public static BDMRefactorQueue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BDMRefactorQueue();
        }
        return INSTANCE;
    }

    public boolean add(DiffElement element) {
        return queue.offer(element); // we use a resizable-array implementation of Queue so no need to worry here
    }

    /**
     * Retrieves and removes the next element to refactor
     */
    public DiffElement poll() {
        return queue.poll();
    }

    /**
     * Retrieves but doesn't remove the next element to refactor
     */
    public DiffElement peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void clear() {
        queue.clear();
    }

}
