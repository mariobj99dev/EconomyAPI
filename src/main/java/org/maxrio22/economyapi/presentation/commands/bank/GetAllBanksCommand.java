package org.maxrio22.economyapi.presentation.commands.bank;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.maxrio22.economyapi.application.shared.Printer;
import org.maxrio22.economyapi.application.usecase.bank.GetAllBanksUseCase;
import org.maxrio22.economyapi.domain.model.Bank;

import java.util.List;

public class GetAllBanksCommand implements CommandExecutor {
    private final GetAllBanksUseCase getAllBanksUseCase;

    public GetAllBanksCommand(GetAllBanksUseCase getAllBanksUseCase) {
        this.getAllBanksUseCase = getAllBanksUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<Bank> banks = getAllBanksUseCase.execute();

        if (banks.isEmpty()) {
            sender.sendMessage("⚠ No hay bancos registrados.");
            return true;
        }

        sender.sendMessage("🏦 Bancos registrados:");
        for (Bank bank : banks) {
            sender.sendMessage(Printer.printer(bank).append(Component.text("")));
        }

        return true;
    }
}
