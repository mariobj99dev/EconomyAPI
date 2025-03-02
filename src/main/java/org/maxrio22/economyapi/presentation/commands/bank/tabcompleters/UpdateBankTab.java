package org.maxrio22.economyapi.presentation.commands.bank.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UpdateBankTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return Collections.singletonList("<id>");
        if (args.length == 2) return Collections.singletonList("<name>");
        if (args.length == 3) return Collections.singletonList("<currency_id>");
        if (args.length == 4) return Collections.singletonList("<reserve_ratio>");
        if (args.length == 5) return Collections.singletonList("<interest_rate>");
        if (args.length == 6) return Collections.singletonList("<loan_interest_rate>");
        if (args.length == 7) return Collections.singletonList("<transaction_fee>");
        if (args.length == 8) return Arrays.asList("active", "bankrupt", "blocked");
        return Collections.emptyList();
    }
}
