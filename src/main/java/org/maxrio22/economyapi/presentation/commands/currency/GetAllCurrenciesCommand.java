package org.maxrio22.economyapi.presentation.commands.currency;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.currency.GetAllCurrenciesUseCase;
import org.maxrio22.economyapi.domain.model.Currency;

import java.util.List;

public class GetAllCurrenciesCommand implements CommandExecutor {
    private final GetAllCurrenciesUseCase getAllCurrenciesUseCase;

    public GetAllCurrenciesCommand(GetAllCurrenciesUseCase getAllCurrenciesUseCase) {
        this.getAllCurrenciesUseCase = getAllCurrenciesUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            sender.sendMessage(Component.text("Uso correcto /getcurrencies", NamedTextColor.RED));
            return false;
        }

        List<Currency> currencies = getAllCurrenciesUseCase.execute();

        currencies.forEach(currency -> {
            sender.sendMessage(Component.text("[ Divisa ").color(TextColor.color(0x3F704D)).append(Component.text(currency.getName(), NamedTextColor.GRAY)).append(Component.text(" ]").color(TextColor.color(0x3F704D))));
            sender.sendMessage(Component.text("- Id: ").color(TextColor.color(0x3F704D)).append(Component.text(currency.getId(), NamedTextColor.GRAY)));;
            sender.sendMessage(Component.text("- Símbolo: ").color(TextColor.color(0x3F704D)).append(Component.text(currency.getSymbol(), NamedTextColor.GRAY)));
            sender.sendMessage(Component.text("- Tasa de Cambio: ").color(TextColor.color(0x3F704D)).append(Component.text(currency.getExchangeRate(), NamedTextColor.GRAY)));
            sender.sendMessage(Component.text(""));
        });

        return true;
    }
}
