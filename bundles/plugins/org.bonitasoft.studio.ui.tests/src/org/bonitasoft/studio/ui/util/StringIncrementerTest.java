package org.bonitasoft.studio.ui.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringIncrementerTest {

    private String defaultFileName = "defaultFileName";

    @Test
    public void should_increment_file_name() {
        List<String> existingFileNameList = new ArrayList<>();
        existingFileNameList.add(defaultFileName + "a");
        String filename = StringIncrementer.getNextIncrement(defaultFileName, existingFileNameList);
        assertThat(filename).isEqualTo(defaultFileName);
        existingFileNameList.add(filename);
        filename = StringIncrementer.getNextIncrement(defaultFileName, existingFileNameList);
        assertThat(filename).isEqualTo(defaultFileName + "1");
        existingFileNameList.add(filename.toLowerCase());
        filename = StringIncrementer.getNextIncrementIgnoringCase(defaultFileName, existingFileNameList);
        assertThat(filename).isEqualTo(defaultFileName + "2");
    }

}
