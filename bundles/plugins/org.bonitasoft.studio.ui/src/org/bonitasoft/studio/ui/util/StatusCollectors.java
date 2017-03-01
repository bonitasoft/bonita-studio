package org.bonitasoft.studio.ui.util;

import java.util.function.Supplier;
import java.util.stream.Collector;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class StatusCollectors {

    private StatusCollectors() {
    }

    public static Collector<IStatus, ?, MultiStatus> toMultiStatus() {
        return Collector.of(multiStatusSupplier(),
                MultiStatus::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                });
    }

    private static Supplier<MultiStatus> multiStatusSupplier() {
        return () -> new MultiStatus("unknownId", IStatus.OK, "", null);
    }

}
