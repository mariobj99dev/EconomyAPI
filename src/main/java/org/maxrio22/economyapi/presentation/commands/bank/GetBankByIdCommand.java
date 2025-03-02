package org.maxrio22.economyapi.presentation.commands.bank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.usecase.bank.GetBankByIdUseCase;
import org.maxrio22.economyapi.domain.model.Bank;
import org.maxrio22.economyapi.application.shared.Printer;

public class GetBankByIdCommand implements CommandExecutor {
    private final GetBankByIdUseCase getBankByIdUseCase;

    public GetBankByIdCommand(GetBankByIdUseCase getBankByIdUseCase) {
        this.getBankByIdUseCase = getBankByIdUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("⚠ Uso correcto: /getbank <id>");
            return true;
        }

        try {
            int bankId = Integer.parseInt(args[0]);
            Bank bank = getBankByIdUseCase.execute(bankId);

            if (bank != null) {
                sender.sendMessage("🔍 Propiedades del Banco:");
                sender.sendMessage(Printer.printer(bank));
            } else {
                sender.sendMessage("❌ No se encontró un banco con el ID " + bankId);
            }
        } catch (NumberFormatException e) {
            sender.sendMessage("⚠ El ID debe ser un número válido.");
        }

        return true;
    }
}
