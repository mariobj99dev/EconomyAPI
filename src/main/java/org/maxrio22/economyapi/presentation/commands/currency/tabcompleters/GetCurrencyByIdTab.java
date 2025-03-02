package org.maxrio22.economyapi.presentation.commands.currency.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class GetCurrencyByIdTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Collections.singletonList("<id>"); // ID de la moneda a consultar
        }
        return Collections.emptyList();
    }
}
