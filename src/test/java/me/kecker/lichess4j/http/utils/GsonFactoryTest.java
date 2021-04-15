package me.kecker.lichess4j.http.utils;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.burningwave.core.assembler.ComponentContainer;
import org.burningwave.core.assembler.ComponentSupplier;
import org.burningwave.core.classes.ClassHunter;
import org.burningwave.core.classes.SearchConfig;
import org.junit.Test;

public class GsonFactoryTest {

    private static final String ADAPTERS_PACKAGE = "me.kecker.lichess4j.http.adapters";

    @Test
    public void getGson_happyDay_includesAllTypeAdapters() {
        Gson gson = GsonFactory.getGson();

        Collection<Class<?>> declaredCustomTypeAdapters = findClassesInPackage(ADAPTERS_PACKAGE)
                .stream()
                .filter(c -> !c.getSimpleName()
                        .endsWith("Test"))
                .collect(Collectors.toSet());
        Collection<Class<?>> usedCustomTypeAdapters = declaredCustomTypeAdapters.stream()
                .map(GsonFactoryTest::getTargetClass)
                .map(gson::getAdapter)
                .map(TypeAdapter::getClass)
                .collect(Collectors.toSet());

        assertThat(declaredCustomTypeAdapters).hasSameElementsAs(usedCustomTypeAdapters);

    }

    private Collection<Class<?>> findClassesInPackage(String packageName) {
        ComponentSupplier componentSupplier = ComponentContainer.getInstance();
        ClassHunter classHunter = componentSupplier.getClassHunter();
        try (ClassHunter.SearchResult result = classHunter.findBy(SearchConfig.forResources(
                packageName.replace('.', '/')))) {
            return result.getClasses();
        }
    }

    @SneakyThrows({ IllegalAccessException.class, IllegalArgumentException.class,
            InvocationTargetException.class, NoSuchMethodException.class, SecurityException.class })
    private static Class<?> getTargetClass(Class<?> typeAdapter) {
        return (Class<?>) typeAdapter.getMethod("getTargetClass")
                .invoke(null);
    }

}
