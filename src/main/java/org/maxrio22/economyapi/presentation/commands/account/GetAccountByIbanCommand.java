package org.maxrio22.economyapi.presentation.commands.account;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.account.GetAccountByIbanUseCase;
import org.maxrio22.economyapi.domain.model.Account;

public class GetAccountByIbanCommand implements CommandExecutor {
    private final GetAccountByIbanUseCase getAccountByIbanUseCase;

    public GetAccountByIbanCommand(GetAccountByIbanUseCase getAccountByIbanUseCase) {
        this.getAccountByIbanUseCase = getAccountByIbanUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("⚠ Uso: /getaccountbyiban <iban>");
            return true;
        }

        String iban = args[0];
        Account account = getAccountByIbanUseCase.execute(iban);

        if (account != null) {
            sender.sendMessage("✅ Cuenta encontrada: " + account.toString());
        } else {
            sender.sendMessage("❌ No se encontró una cuenta con ese IBAN.");
        }

        return true;
    }
}
