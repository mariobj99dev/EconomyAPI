package org.maxrio22.economyapi.presentation.commands.account;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.account.DeleteAccountUseCase;

public class DeleteAccountCommand implements CommandExecutor {
    private final DeleteAccountUseCase deleteAccountUseCase;

    public DeleteAccountCommand(DeleteAccountUseCase deleteAccountUseCase) {
        this.deleteAccountUseCase = deleteAccountUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("⚠ Uso: /deleteaccount <id>");
            return true;
        }

        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage("❌ El ID debe ser un número.");
            return true;
        }

        if (deleteAccountUseCase.execute(id)) {
            sender.sendMessage("✅ Cuenta eliminada con éxito.");
        } else {
            sender.sendMessage("❌ No se encontró una cuenta con ese ID.");
        }

        return true;
    }
}
