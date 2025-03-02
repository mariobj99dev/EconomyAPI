package org.maxrio22.economyapi.presentation.commands.bank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.maxrio22.economyapi.application.usecase.bank.CreateBankUseCase;
import org.maxrio22.economyapi.domain.model.Bank;

public class CreateBankCommand implements CommandExecutor {
    private final CreateBankUseCase createBankUseCase;

    public CreateBankCommand(CreateBankUseCase createBankUseCase) {
        this.createBankUseCase = createBankUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 6) {
            sender.sendMessage("⚠ Uso correcto: /createbank <name> <currency_id> <reserve_ratio> <interest_rate> <loan_interest_rate> <transaction_fee>");
            return true;
        }

        String name = args[0];
        int currencyId;
        float reserveRatio, interestRate, loanInterestRate, transactionFee;

        try {
            currencyId = Integer.parseInt(args[1]);
            reserveRatio = Float.parseFloat(args[2]);
            interestRate = Float.parseFloat(args[3]);
            loanInterestRate = Float.parseFloat(args[4]);
            transactionFee = Float.parseFloat(args[5]);
        } catch (NumberFormatException e) {
            sender.sendMessage("⚠ Algunos valores deben ser números válidos.");
            return true;
        }

        Player player = (Player) sender;
        Bank bank = new Bank(0, name, player.getName(), currencyId, reserveRatio, interestRate, loanInterestRate, transactionFee, "active", null);

        if (createBankUseCase.execute(bank)) {
            sender.sendMessage("✅ Banco '" + name + "' creado con éxito.");
        } else {
            sender.sendMessage("❌ Error al crear el banco.");
        }

        return true;
    }
}
