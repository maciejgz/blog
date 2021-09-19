package pl.mg.blog;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ArchUnitTests {

    @Test
    public void cyclesFreeTest() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("pl.mg.blog");
        slices().matching("pl.mg.blog.").should().beFreeOfCycles().check(importedClasses);
    }

    @Test
    public void entitiesShallNotBeComponents() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("pl.mg.blog");
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                .resideInAPackage("pl.mg.post..")
                .and()
                .areAnnotatedWith(Document.class)
                .should()
                .notBeAnnotatedWith(Component.class)
                .because("Entities are not components.");
        rule.check(importedClasses);
    }

}
