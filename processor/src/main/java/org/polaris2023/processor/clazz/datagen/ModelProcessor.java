package org.polaris2023.processor.clazz.datagen;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.modelgen.block.*;
import org.polaris2023.annotation.modelgen.item.*;
import org.polaris2023.annotation.modelgen.other.*;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.Override;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private final List<Annotation> annotations = new ArrayList<>();

    private <T extends Annotation> T register(T t) {
        annotations.add(t);
        return t;
    }

    private boolean isAnnotation() {
        for (Annotation annotation : annotations) {
            if (annotation != null) {
                return false;
            }
        }
        return true;
    }

    public void checkAppend(TypeElement typeElement, VariableElement variableElement, String methodName,Object... appends) {
        check();
        MODEL.append("\n\t\t.")
                .append(methodName)
                .append("(")
                .append(typeElement.getQualifiedName())
                .append(".")
                .append(variableElement.getSimpleName());
        for (Object append : appends) {
            if (append instanceof String) {
                MODEL
                        .append(",")
                        .append("\"")
                        .append(append)
                        .append("\"");
            } else {
                MODEL
                        .append(",")
                        .append(append);
            }
        }
        MODEL.append(")");
    }

    @Override
    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {
        BasicItem typeBasicItem = typeElement.getAnnotation(BasicItem.class);
        BasicItem basicItem = register(variableElement.getAnnotation(BasicItem.class));
        BasicBlockItem basicBlockItem = register(variableElement.getAnnotation(BasicBlockItem.class));
        BasicBlockItemWithSuffix basicBlockItemWithSuffix = register(variableElement.getAnnotation(BasicBlockItemWithSuffix.class));
        CubeAll cube = register(variableElement.getAnnotation(CubeAll.class));
        CubeColumn cubeColumn = register(variableElement.getAnnotation(CubeColumn.class));
        Stairs stairs = register(variableElement.getAnnotation(Stairs.class));
        Slab slab = register(variableElement.getAnnotation(Slab.class));
        Log log = register(variableElement.getAnnotation(Log.class));
        Wood wood = register(variableElement.getAnnotation(Wood.class));
        Button button = register(variableElement.getAnnotation(Button.class));
        SpawnEggItem spawnEggItem = register(variableElement.getAnnotation(SpawnEggItem.class));
        ParentItem parentItem = register(variableElement.getAnnotation(ParentItem.class));
        Fence fence = register(variableElement.getAnnotation(Fence.class));
        FenceGate fenceGate = register(variableElement.getAnnotation(FenceGate.class));
        PressurePlate pressurePlate = register(variableElement.getAnnotation(PressurePlate.class));
        AllWood allWood = register(variableElement.getAnnotation(AllWood.class));
        AllSign allSign = register(variableElement.getAnnotation(AllSign.class));
        AllDoor allDoor = register(variableElement.getAnnotation(AllDoor.class));
        if (typeBasicItem != null &&
                typeBasicItem.used() &&
                variableElement.getModifiers().contains(Modifier.STATIC)) {
            basicSet(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), typeBasicItem, typeBasicItem.value(), true, "");
        }
        if (basicItem != null && basicItem.used()) {
            basicSet(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), basicItem, basicItem.value(), true, "");
        }
        if (basicBlockItem != null) {
            checkAppend(typeElement, variableElement,"basicBlockItem");
        }
        if (basicBlockItemWithSuffix != null) {
            checkAppend(typeElement, variableElement, "basicBlockItemWithSuffix", basicBlockItemWithSuffix.suffix());
        }
        if (cube != null) {
            checkAppend(typeElement, variableElement,"cubeAll", cube.item());
        }
        if (cubeColumn != null) {
            checkAppend(typeElement, variableElement, "cubeColumn", cubeColumn.end(), cubeColumn.side(), cubeColumn.item(), cubeColumn.horizontal(), cubeColumn.horizontal());
        }
        if (allWood != null) {
            checkAppend(typeElement, variableElement, "allWoodBlock");
        }
        if (allDoor != null) {
            checkAppend(typeElement, variableElement, "allDoorBlock");
        }
        if (allSign != null) {
            checkAppend(typeElement, variableElement, "allSignBlock");
        }
        if (pressurePlate != null) {
            checkAppend(typeElement, variableElement, "pressurePlateBlock", pressurePlate.texture(), pressurePlate.item());
        }

        if (stairs != null) {
            String all = stairs.all();
            checkAppend(typeElement, variableElement, "stairsBlock",
                    all.isEmpty() ? stairs.bottom() : all,
                    all.isEmpty() ? stairs.side() : all,
                    all.isEmpty() ? stairs.top() : all,
                    stairs.item()
            );
        }
        if (slab != null) {
            String all = slab.all();
            checkAppend(typeElement, variableElement, "slabBlock",
                    all.isEmpty() ? slab.bottom() : all,
                    all.isEmpty() ? slab.side() : all,
                    all.isEmpty() ? slab.top() : all,
                    slab.item()
            );
        }
        if (log != null) {
            checkAppend(typeElement, variableElement, "logBlock", log.item());
        }
        if (wood != null) {
            checkAppend(typeElement, variableElement, "woodBlock", wood.item());
        }
        if (button != null) {
            checkAppend(typeElement, variableElement, "buttonBlock", button.texture());
        }
        if(fence != null) {
            checkAppend(typeElement, variableElement, "fenceBlock", fence.texture(), fence.item());
        }
        if (fenceGate != null) {
            checkAppend(typeElement, variableElement, "fenceGateBlock", fenceGate.texture(), fenceGate.item());
        }
        if (spawnEggItem != null) {
            checkAppend(typeElement, variableElement, "spawnEggItem");
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
