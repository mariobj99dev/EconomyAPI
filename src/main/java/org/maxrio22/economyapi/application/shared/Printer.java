package org.maxrio22.economyapi.application.shared;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class Printer {
    private static final TextColor PRIMARY_COLOR = TextColor.color(0x3D8D7A);  // Verde oscuro
    private static final TextColor SECONDARY_COLOR = TextColor.color(0xB3D8A8); // Verde suave
    private static final TextColor DETAIL_COLOR = TextColor.color(0xFBFFE4);   // Crema claro

    public static Component printer(Object obj) {
        if (obj == null) {
            return Component.text("❌ Objeto nulo.", PRIMARY_COLOR);
        }

        AtomicReference<Component> message = new AtomicReference<>(Component.text().build());

        // Obtener todos los métodos y filtrar los que son "getters"
        Method[] methods = obj.getClass().getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("get") && method.getParameterCount() == 0) // Filtra métodos "get"
                .forEach(method -> {
                    try {
                        Object value = method.invoke(obj); // Llamar al getter
                        String propertyName = formatProperty(method.getName().substring(3)); // Quitar "get"

                        message.set(message.get()
                                .append(Component.text(" - " + propertyName + ": ", SECONDARY_COLOR))
                                .append(Component.text(String.valueOf(value), DETAIL_COLOR))
                                .append(Component.newline()));
                    } catch (Exception e) {
                        message.set(message.get()
                                .append(Component.text("❌ Error al acceder a " + method.getName(), PRIMARY_COLOR))
                                .append(Component.newline()));
                    }
                });

        return message.get();
    }

    private static String formatProperty(String property) {
        String[] propertySplitted = property.split("(?=[A-Z])");

        for (int i = 1; i < propertySplitted.length; i++) {
            propertySplitted[i] = propertySplitted[i].toLowerCase();
        }
        return String.join(" ", propertySplitted);
    }
}
