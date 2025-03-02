package org.maxrio22.economyapi.presentation.commands.account;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.maxrio22.economyapi.application.usecase.account.CreateAccountUseCase;
import org.maxrio22.economyapi.domain.model.Account;

public class CreateAccountCommand implements CommandExecutor {
    private final CreateAccountUseCase createAccountUseCase;

    public CreateAccountCommand(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("❌ Solo los jugadores pueden usar este comando.");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage("⚠ Uso: /createaccount <tipo> <banco_id> <moneda_id>");
            return true;
        }

        Player player = (Player) sender;
        String ownerType = args[0];
        String accountType = args[2];
        String owner;

        switch (ownerType) {
            case "player":
                owner = player.getUniqueId().toString();
                break;
            case "company":
                owner = "NIF [TODO]";
                break;
            case "government":
                owner = "goverment";
                break;
            case "bank":
                owner = "bank";
                break;
            default:
                sender.sendMessage("❌ El tipo de cuenta es invalida. \nTipos disponibles: [player,company,government,bank]");
                return true;
        }

        if (!accountType.equals("personal") && !accountType.equals("shared") && !accountType.equals("bank")) {
            sender.sendMessage("❌ El tipo de cuenta es invalida. \nTipos disponibles: [personal,shared,bank]");
            return false;
        }

        int bankId;

        try {
            bankId = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("❌ El banco y la moneda deben ser números.");
            return true;
        }

        Account newAccount = new Account(0, null, ownerType, owner, bankId, 0.0f, accountType, false, null);

        if (createAccountUseCase.execute(newAccount)) {
            sender.sendMessage("✅ Cuenta creada exitosamente. IBAN: " + newAccount.getIban());
        } else {
            sender.sendMessage("❌ No se pudo crear la cuenta.");
        }

        return true;
    }
}
