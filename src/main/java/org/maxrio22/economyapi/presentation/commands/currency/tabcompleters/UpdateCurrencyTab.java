package org.maxrio22.economyapi.presentation.commands.currency.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class UpdateCurrencyTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return Collections.singletonList("<id>");
        if (args.length == 2) return Collections.singletonList("<name>");
        if (args.length == 3) return Collections.singletonList("<symbol>");
        if (args.length == 4) return Collections.singletonList("<exchange_rate>");
        if (args.length == 5) return Collections.singletonList("<inflation_rate>");
        return Collections.emptyList();
    }
}
