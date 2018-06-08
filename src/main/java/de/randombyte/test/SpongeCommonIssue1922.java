package de.randombyte.test;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

@Plugin(
    id = "sponge-common-issue-1922",
    name = "Sponge-common-issue-1922"
)
public class SpongeCommonIssue1922 {

  @Listener
  public void onServerStart(GameInitializationEvent event) {
    Sponge.getCommandManager().register(this, CommandSpec.builder()
        .arguments(GenericArguments.integer(Text.of("i")))
        .executor((src, args) -> {

          int i = args.<Integer>getOne("i").get();

          if (i < 1) {
            Sponge.getCommandManager().process(src, "say Test finished!");
            return CommandResult.success();
          }

          Sponge.getCommandManager().process(src, "test " + String.valueOf(i - 1));

          return CommandResult.success();
        })
        .build(), "test");
  }
}
