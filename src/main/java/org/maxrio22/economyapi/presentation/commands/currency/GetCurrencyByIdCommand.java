package org.maxrio22.economyapi.presentation.commands.currency;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.currency.GetCurrencyByIdUseCase;
import org.maxrio22.economyapi.domain.model.Currency;

public class GetCurrencyByIdCommand implements CommandExecutor {
    private final GetCurrencyByIdUseCase getCurrencyByIdUseCase;

    public GetCurrencyByIdCommand(GetCurrencyByIdUseCase getCurrencyByIdUseCase) {
        this.getCurrencyByIdUseCase = getCurrencyByIdUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(Component.text("Uso correcto /getcurrency <id>", NamedTextColor.RED));
            return false;
        }

        try {
            int currencyId = Integer.parseInt(args[0]);
            Currency currency = getCurrencyByIdUseCase.execute(currencyId);

            if (currency != null) {
                sender.sendMessage(Component.text("✅ Moneda encontrada:").color(TextColor.color(0x3F704D)));
                sender.sendMessage(Component.text("Nombre: ").color(TextColor.color(0x3F704D)).append(Component.text(currency.getName(), NamedTextColor.GRAY)));
                sender.sendMessage(Component.text("Símbolo: ").color(TextColor.color(0x3F704D)).append(Component.text(currency.getSymbol(), NamedTextColor.GRAY)));
                sender.sendMessage(Component.text("Tasa de Cambio: ").color(TextColor.color(0x3F704D)).append(Component.text(currency.getExchangeRate(), NamedTextColor.GRAY)));
            } else {
                sender.sendMessage(Component.text("❌ No se encontró una moneda con ID " + currencyId, NamedTextColor.RED));
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(Component.text("❌ El ID debe ser un número.", NamedTextColor.RED));
        }

        return true;
    }
}
