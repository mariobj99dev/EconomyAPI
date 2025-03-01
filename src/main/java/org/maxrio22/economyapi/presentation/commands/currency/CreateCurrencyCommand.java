package org.maxrio22.economyapi.presentation.commands.currency;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.currency.CreateCurrencyUseCase;
import org.maxrio22.economyapi.domain.model.Currency;

public class CreateCurrencyCommand implements CommandExecutor {
    private final CreateCurrencyUseCase createCurrencyUseCase;

    public CreateCurrencyCommand(CreateCurrencyUseCase createCurrencyUseCase) {
        this.createCurrencyUseCase = createCurrencyUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 4) {
            sender.sendMessage(Component.text("Uso correcto: /createcurrency <name> <symbol> <exchange_rate> <inflation_rate>", NamedTextColor.RED));
            return false;
        }

        try {
            String name = args[0];
            String symbol = args[1];
            float exchangeRate = Float.parseFloat(args[2]);
            float inflationRate = Float.parseFloat(args[3]);

            Currency newCurrency = new Currency(0, name, symbol, exchangeRate, inflationRate, null);
            boolean success = createCurrencyUseCase.execute(newCurrency);

            if (success) {
                sender.sendMessage(Component.text("✅ Moneda creada correctamente.", NamedTextColor.GREEN));
            } else {
                sender.sendMessage(Component.text("❌ Error al crear la moneda.", NamedTextColor.RED));
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(Component.text("❌ Los valores de exchange_rate e inflation_rate deben ser números.", NamedTextColor.RED));
        }

        return true;
    }
}
