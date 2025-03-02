package org.maxrio22.economyapi.presentation.commands.bank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.bank.DeleteBankUseCase;

public class DeleteBankCommand implements CommandExecutor {
    private final DeleteBankUseCase deleteBankUseCase;

    public DeleteBankCommand(DeleteBankUseCase deleteBankUseCase) {
        this.deleteBankUseCase = deleteBankUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("⚠ Uso correcto: /deletebank <id>");
            return true;
        }

        try {
            int id = Integer.parseInt(args[0]);

            if (deleteBankUseCase.execute(id)) {
                sender.sendMessage("✅ Banco eliminado con éxito.");
            } else {
                sender.sendMessage("❌ Error al eliminar el banco.");
            }
        } catch (NumberFormatException e) {
            sender.sendMessage("⚠ El ID debe ser un número válido.");
        }

        return true;
    }
}
