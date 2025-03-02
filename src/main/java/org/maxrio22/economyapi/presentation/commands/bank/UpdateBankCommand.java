package org.maxrio22.economyapi.presentation.commands.bank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.bank.UpdateBankUseCase;
import org.maxrio22.economyapi.domain.model.Bank;

public class UpdateBankCommand implements CommandExecutor {
    private final UpdateBankUseCase updateBankUseCase;

    public UpdateBankCommand(UpdateBankUseCase updateBankUseCase) {
        this.updateBankUseCase = updateBankUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 7) {
            sender.sendMessage("⚠ Uso correcto: /updatebank <id> <name> <currency_id> <reserve_ratio> <interest_rate> <loan_interest_rate> <transaction_fee> <status>");
            return true;
        }

        try {
            int id = Integer.parseInt(args[0]);
            String name = args[1];
            int currencyId = Integer.parseInt(args[2]);
            float reserveRatio = Float.parseFloat(args[3]);
            float interestRate = Float.parseFloat(args[4]);
            float loanInterestRate = Float.parseFloat(args[5]);
            float transactionFee = Float.parseFloat(args[6]);
            String status = args[7];

            if(!status.equals("active") || !status.equals("bankrupt") || !status.equals("blocked")) {
                sender.sendMessage("❌ El valor de status no es valido. ");
                sender.sendMessage("Asegurate que su valor este en la lista [active,bankrupt,blocked].");
                return true;
            }

            Bank bank = new Bank(id, name, "", currencyId, reserveRatio, interestRate, loanInterestRate, transactionFee, status, null);

            if (updateBankUseCase.execute(bank)) {
                sender.sendMessage("✅ Banco actualizado con éxito.");
            } else {
                sender.sendMessage("❌ Error al actualizar el banco.");
            }
        } catch (NumberFormatException e) {
            sender.sendMessage("⚠ Asegúrate de ingresar valores numéricos válidos.");
        }

        return true;
    }
}
