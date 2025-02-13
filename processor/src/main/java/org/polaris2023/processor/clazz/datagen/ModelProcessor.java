package org.polaris2023.processor.clazz.datagen;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.modelgen.block.Button;
import org.polaris2023.annotation.modelgen.block.CubeAll;
import org.polaris2023.annotation.modelgen.block.Slab;
import org.polaris2023.annotation.modelgen.block.Stairs;
import org.polaris2023.annotation.modelgen.item.BasicBlockItem;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.annotation.modelgen.item.ParentItem;
import org.polaris2023.annotation.modelgen.item.SpawnEggItem;
import org.polaris2023.annotation.modelgen.other.*;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.Override;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ModelProcessor extends ClassProcessor {

    public static final StringBuilder MODEL = new StringBuilder();

    public ModelProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    public static void check() {
        if (MODEL.isEmpty()) {
            MODEL.append("this");
        }
    }

    @Override
    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {
        BasicItem typeBasicItem = typeElement.getAnnotation(BasicItem.class);
        BasicItem basicItem = variableElement.getAnnotation(BasicItem.class);
        BasicBlockItem basicBlockItem = variableElement.getAnnotation(BasicBlockItem.class);
        CubeAll cube = variableElement.getAnnotation(CubeAll.class);
        Stairs stairs = variableElement.getAnnotation(Stairs.class);
        Slab slab = variableElement.getAnnotation(Slab.class);
        Button button = variableElement.getAnnotation(Button.class);
        SpawnEggItem spawnEggItem = variableElement.getAnnotation(SpawnEggItem.class);
        ParentItem parentItem = variableElement.getAnnotation(ParentItem.class);
        if (typeBasicItem != null && typeBasicItem.used() && variableElement.getModifiers().contains(Modifier.STATIC) &&
                basicItem== null && basicBlockItem == null && cube == null && stairs == null && slab == null && button == null && spawnEggItem == null && parentItem == null) {

            basicSet(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), typeBasicItem, typeBasicItem.value(), true, "");
        }
        if (basicItem != null && basicItem.used()) {
            basicSet(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), basicItem, basicItem.value(), true, "");
        }
        if (basicBlockItem != null) {
            check();
            MODEL.append("\n\t\t")
                    .append(".basicBlockItem(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(")");
        }
        if (cube != null) {
            check();
            MODEL.append("\n\t\t")
                    .append(".cubeAll(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(")");
        }
        if (stairs != null) {
            check();
            MODEL.append("\n\t\t")
                    .append(".stairsBlock(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(", \"")
                    .append(stairs.bottom())
                    .append(", \"")
                    .append(stairs.side())
                    .append(", \"")
                    .append(stairs.top())
                    .append("\")");
        }
        if (slab != null) {
            check();
            MODEL.append("\n\t\t")
                    .append(".slabBlock(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(", \"")
                    .append(slab.bottom())
                    .append(", \"")
                    .append(slab.side())
                    .append(", \"")
                    .append(slab.top())
                    .append(")");
        }
        if (button != null) {
            check();
            MODEL.append("\n\t\t")
                    .append(".buttonBlock(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(", \"")
                    .append(button.texture())
                    .append("\")");
        }
        if (spawnEggItem != null) {
            check();
            MODEL.append("\n\t\t")
                    .append(".spawnEggItem(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(")");
        }
        if (parentItem != null) {
            check();
            MODEL.append("\n\t\t")
                    .append(".parentItem(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(", \"")
                    .append(parentItem.parent())
                    .append("\"");
            if (parentItem.textures().length > 0) {
                MODEL.append(",")
                        .append(Arrays
                                .stream(parentItem.textures())
                                .map(kv -> "\"" + kv.key() + "\"" + ", \"" + kv.value() + "\"")
                                .collect(Collectors.joining(",")));
            }
            MODEL.append(")");
        }
    }

    private static void basicSet(String name, BasicItem basicItem, Addition addition, boolean first, String prefix) {
        check();
        MODEL.append("\n\t\t")
                .append(".basicItem(")
                .append(name);
        if (addition.display().length > 0) {
            MODEL.append(", Map.of(");
            // Map.of (name, Map.of("scale", List.of(scale.x, scale.y, scale.z)))
            for (int i = 0; i < addition.display().length; i++) {
                if (i != 0) {
                    MODEL.append(", ");
                }
                Display display = addition.display()[i];
                XYZ scale = display.scale();

                XYZ rotation = display.rotation();
                XYZ translation = display.translation();
                MODEL
                        .append("\"")
                        .append(display.name())
                        .append("\", Map.of(\"scale\", List.of(")
                        .append(scale.x()).append(",")
                        .append(scale.y()).append(",")
                        .append(scale.z()).append(")")
                        .append(", \"rotation\", List.of(")
                        .append(rotation.x()).append(",")
                        .append(rotation.y()).append(",")
                        .append(rotation.z()).append(")")
                        .append(", \"translation\", List.of(")
                        .append(translation.x()).append(",")
                        .append(translation.y()).append(",")
                        .append(translation.z()).append("))")
                ;
            }

            MODEL.append(")");
        }
        if (addition.overrides().length > 0) {
            MODEL.append(", List.of(");
            for (int i = 0; i < addition.overrides().length; i++) {
                var override = addition.overrides()[i];
                Predicate[] predicates = override.predicate();
                String model = override.model();
                if(i != 0) {
                    MODEL.append(", ");
                }
                MODEL.append("Map.of(");
                if (predicates.length > 0) {
                    MODEL.append("\"predicate\", Map.of(");
                    for (int i1 = 0; i1 < predicates.length; i1++) {
                        if(i1 != 0) {
                            MODEL.append(", ");
                        }
                        Predicate predicate = predicates[i1];
                        MODEL.append("\"")
                                .append(predicate.name())
                                .append("\",")
                                .append(predicate.value())
                        ;
                    }
                    MODEL
                            .append(")")
                            .append(", \"model\", ")
                            .append("\"")
                            .append(model)
                            .append("\"");
                }
                MODEL.append(")");

            }
            MODEL.append(")");

        }
        if (!prefix.isEmpty()) {
            MODEL
                    .append(", \"")
                    .append(prefix)
                    .append("\"");
        }
        MODEL.append(")");

        if (first) {
            for (KeyAddition keyAddition : basicItem.more()) {
                basicSet(name, basicItem, keyAddition.value(), false, keyAddition.key());
            }
        }
    }
}
