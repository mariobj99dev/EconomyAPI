package org.maxrio22.economyapi.presentation.commands.account;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.account.GetAccountByIdUseCase;
import org.maxrio22.economyapi.application.usecase.account.UpdateAccountUseCase;
import org.maxrio22.economyapi.domain.model.Account;

public class UpdateAccountCommand implements CommandExecutor {
    private final UpdateAccountUseCase updateAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;

    public UpdateAccountCommand(UpdateAccountUseCase updateAccountUseCase, GetAccountByIdUseCase getAccountByIdUseCase) {
        this.updateAccountUseCase = updateAccountUseCase;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage("⚠ Uso: /updateaccount <id> <balance> <bloqueado:true/false>");
            return true;
        }

        int id;
        float balance;
        boolean blocked;

        try {
            id = Integer.parseInt(args[0]);
            balance = Float.parseFloat(args[1]);
            blocked = Boolean.parseBoolean(args[2]);
        } catch (Exception e) {
            sender.sendMessage("❌ Datos inválidos.");
            return true;
        }

        Account account = getAccountByIdUseCase.execute(id);
        if (account == null) {
            sender.sendMessage("❌ No se encontró la cuenta.");
            return true;
        }

        account.setBalance(balance);
        account.setBlocked(blocked);

        if (updateAccountUseCase.execute(account)) {
            sender.sendMessage("✅ Cuenta actualizada.");
        } else {
            sender.sendMessage("❌ No se pudo actualizar la cuenta. Puede que la cuenta no exista o hubo un error en la base de datos.");
        }

        return true;
    }

}
