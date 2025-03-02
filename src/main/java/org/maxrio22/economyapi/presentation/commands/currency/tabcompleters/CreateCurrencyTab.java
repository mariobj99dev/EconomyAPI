package org.maxrio22.economyapi.presentation.commands.currency.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class CreateCurrencyTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Collections.singletonList("<name>"); // Primer parámetro sugerido
        }
        if (args.length == 2) {
            return Collections.singletonList("<symbol>"); // Segundo parámetro sugerido
        }
        if (args.length == 3) {
            return Collections.singletonList("<exchange_rate>"); // Tercer parámetro sugerido
        }
        if (args.length == 4) {
            return Collections.singletonList("<inflation_rate>"); // Cuarto parámetro sugerido
        }
        return Collections.emptyList();
    }
}
