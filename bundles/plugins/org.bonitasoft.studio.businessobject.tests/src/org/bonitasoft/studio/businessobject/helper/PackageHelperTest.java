package org.bonitasoft.studio.businessobject.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.junit.Test;

public class PackageHelperTest {

    @Test
    public void should_retrieve_package() {
        BusinessObject bo = new BusinessObject("com.company.model.MyObject");
        String packageName = PackageHelper.getPackageName(bo);
        assertThat(packageName).isEqualTo("com.company.model");
    }

    @Test
    public void should_retrieve_all_package() {
        BusinessObject bo1 = new BusinessObject("com.company.model.MyObject");
        BusinessObject bo2 = new BusinessObject("com.company.model.MySecondObject");
        BusinessObject bo3 = new BusinessObject("com.company.model.bis.MyThirdObject");
        BusinessObjectModel model = new BusinessObjectModel();
        model.addBusinessObject(bo1);
        model.addBusinessObject(bo2);
        model.addBusinessObject(bo3);

        List<String> allPackages = PackageHelper.getAllPackages(model);
        assertThat(allPackages).hasSize(2);
        assertThat(allPackages).containsExactlyInAnyOrder("com.company.model", "com.company.model.bis");
    }

    @Test
    public void should_return_true_if_package_already_exists() {
        BusinessObject bo1 = new BusinessObject("com.company.model.MyObject");
        BusinessObjectModel model = new BusinessObjectModel();
        model.addBusinessObject(bo1);

        assertThat(PackageHelper.packageAlreadyExists(model, "com.company.model")).isTrue();
        assertThat(PackageHelper.packageAlreadyExists(model, "com.company.model.bis")).isFalse();
    }

    @Test
    public void should_generate_next_package_name() {
        BusinessObject bo1 = new BusinessObject("com.company.model.MyObject");
        BusinessObjectModel model = new BusinessObjectModel();
        model.addBusinessObject(bo1);

        String nextPackageName = PackageHelper.getNextPackageName(model);
        assertThat(nextPackageName).isEqualTo("com.company.model1");
    }

    @Test
    public void should_return_all_business_object_with_the_same_package() {
        BusinessObject bo1 = new BusinessObject("com.company.model.MyObject");
        BusinessObject bo2 = new BusinessObject("com.company.model.MySecondObject");
        BusinessObject bo3 = new BusinessObject("com.company.model.bis.MyThirdObject");
        BusinessObjectModel model = new BusinessObjectModel();
        model.addBusinessObject(bo1);
        model.addBusinessObject(bo2);
        model.addBusinessObject(bo3);

        List<BusinessObject> businessObjects = PackageHelper.getAllBusinessObjects(model, "com.company.model");
        assertThat(businessObjects).containsExactlyInAnyOrder(bo1, bo2);
    }
}
