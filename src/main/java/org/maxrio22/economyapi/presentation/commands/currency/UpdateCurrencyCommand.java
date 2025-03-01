package org.maxrio22.economyapi.presentation.commands.currency;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.currency.UpdateCurrencyUseCase;
import org.maxrio22.economyapi.domain.model.Currency;

public class UpdateCurrencyCommand implements CommandExecutor {
    private final UpdateCurrencyUseCase updateCurrencyUseCase;

    public UpdateCurrencyCommand(UpdateCurrencyUseCase updateCurrencyUseCase) {
        this.updateCurrencyUseCase = updateCurrencyUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 5) {
            sender.sendMessage(Component.text("Uso correcto: /updatecurrency <id> <name> <symbol> <exchange_rate> <inflation_rate>", NamedTextColor.RED));
            return false;
        }

        try {
            int id = Integer.parseInt(args[0]);
            String name = args[1];
            String symbol = args[2];
            float exchangeRate = Float.parseFloat(args[3]);
            float inflationRate = Float.parseFloat(args[4]);

            Currency updatedCurrency = new Currency(id, name, symbol, exchangeRate, inflationRate, null);
            boolean success = updateCurrencyUseCase.execute(updatedCurrency);

            if (success) {
                sender.sendMessage(Component.text("✅ Moneda actualizada correctamente.", NamedTextColor.GREEN));
            } else {
                sender.sendMessage(Component.text("❌ Error al actualizar la moneda o no existe.", NamedTextColor.RED));
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(Component.text("❌ El ID, exchange_rate e inflation_rate deben ser números.", NamedTextColor.RED));
        }

        return true;
    }
}
