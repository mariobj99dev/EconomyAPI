package org.maxrio22.economyapi.presentation.commands.account;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.shared.Printer;
import org.maxrio22.economyapi.application.usecase.account.GetAllAccountsUseCase;
import org.maxrio22.economyapi.domain.model.Account;
import org.maxrio22.economyapi.domain.model.Bank;

import java.util.List;

public class GetAllAccountsCommand implements CommandExecutor {
    private final GetAllAccountsUseCase getAllAccountsUseCase;

    public GetAllAccountsCommand(GetAllAccountsUseCase getAllAccountsUseCase) {
        this.getAllAccountsUseCase = getAllAccountsUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<Account> accounts = getAllAccountsUseCase.execute();

        if (accounts.isEmpty()) {
            sender.sendMessage("⚠ No hay cuentas registradas.");
            return true;
        }

        sender.sendMessage("📜 Lista de cuentas:");
        for (Account account : accounts) {
            sender.sendMessage(Printer.printer(account).append(Component.text("")));
        }
        return true;
    }
}
