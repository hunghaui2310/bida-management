package com.bida.management;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.bida.management");

        noClasses()
            .that()
            .resideInAnyPackage("com.bida.management.service..")
            .or()
            .resideInAnyPackage("com.bida.management.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.bida.management.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
