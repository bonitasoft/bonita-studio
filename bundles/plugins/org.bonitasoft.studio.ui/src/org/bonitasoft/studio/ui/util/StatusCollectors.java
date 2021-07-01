package org.bonitasoft.studio.ui.util;

import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class StatusCollectors {

    private StatusCollectors() {
    }

    public static Collector<IStatus, ?, MultiStatus> toMultiStatus() {
        return Collector.of(multiStatusSupplier(),
                multiStatusAccumulator(),
                (left, right) -> {
                    left.addAll(right);
                    return left;
                });
    }

    private static Supplier<MultiStatus> multiStatusSupplier() {
        return () -> new MultiStatus("unknownId", IStatus.OK, "", null);
    }

    private static BiConsumer<MultiStatus, IStatus> multiStatusAccumulator() {
        return (multi, status) -> {
            if (status.isMultiStatus()) {
                multi.addAll(status);
            } else {
                multi.add(status);
            }
        };
    }

}
