package org.maxrio22.economyapi.presentation.commands.account.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateAccountTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("player", "company", "government", "bank"); // Tipos de cuenta
        }
        if (args.length == 2) {
            return Collections.singletonList("<bank_id>"); // ID del banco
        }
        if (args.length == 3) {
            return Arrays.asList("personal", "shared", "bank"); // ID de la moneda
        }
        return Collections.emptyList();
    }
}
