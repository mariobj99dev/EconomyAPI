package org.maxrio22.economyapi.presentation.commands.account.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class DeleteAccountTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Collections.singletonList("<account_id>"); // ID de la cuenta a eliminar
        }
        return Collections.emptyList();
    }
}
