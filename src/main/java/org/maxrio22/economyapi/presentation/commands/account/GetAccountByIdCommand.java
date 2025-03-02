package org.maxrio22.economyapi.presentation.commands.account;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.shared.Printer;
import org.maxrio22.economyapi.application.usecase.account.GetAccountByIdUseCase;
import org.maxrio22.economyapi.domain.model.Account;

public class GetAccountByIdCommand implements CommandExecutor {
    private final GetAccountByIdUseCase getAccountByIdUseCase;

    public GetAccountByIdCommand(GetAccountByIdUseCase getAccountByIdUseCase) {
        this.getAccountByIdUseCase = getAccountByIdUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("⚠ Uso: /getaccountbyid <id>");
            return true;
        }

        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage("❌ El ID debe ser un número.");
            return true;
        }

        Account account = getAccountByIdUseCase.execute(id);

        if (account != null) {
            sender.sendMessage("🔍 Propiedades de la cuenta:");
            sender.sendMessage(Printer.printer(account));
        } else {
            sender.sendMessage("❌ No se encontró una cuenta con ese ID.");
        }

        return true;
    }
}
