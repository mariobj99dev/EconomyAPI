package org.maxrio22.economyapi.presentation.commands.currency;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.currency.DeleteCurrencyUseCase;

public class DeleteCurrencyCommand implements CommandExecutor {
    private final DeleteCurrencyUseCase deleteCurrencyUseCase;

    public DeleteCurrencyCommand(DeleteCurrencyUseCase deleteCurrencyUseCase) {
        this.deleteCurrencyUseCase = deleteCurrencyUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(Component.text("Uso correcto: /deletecurrency <id>", NamedTextColor.RED));
            return false;
        }

        try {
            int id = Integer.parseInt(args[0]);
            boolean success = deleteCurrencyUseCase.execute(id);

            if (success) {
                sender.sendMessage(Component.text("✅ Moneda eliminada correctamente.", NamedTextColor.GREEN));
            } else {
                sender.sendMessage(Component.text("❌ No se encontró la moneda con ID " + id, NamedTextColor.RED));
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(Component.text("❌ El ID debe ser un número.", NamedTextColor.RED));
        }

        return true;
    }
}
