# Fix Multiplayer Mob Despawn

A small mod for Fabric which prevent mobs despawning in multiplayer (when they wouldn't in single player).
This allows the use of certain farms such as ianxofour's gold/xp farm without the need to have a second player stand at the overworld bridge.

This is inspired by the [Fix for Mob Despawn](https://github.com/RandomNoobOnInt/Fix-For-Mob-Despawn) plugin.

## Mob Despawning

When a mob is more than 128 blocks away from any player it will despawn, unless there are no players currently in the world.
This is why the aforementioned farm works without issue in the following scenarios:
- single player
- multiplayer when there is a player next to the overworld bridge
- multiplayer when there are no players in the overworld

Because this issue is caused by mob despawning mechanics, rather than chunk loading, it cannot be solved by typical chunk loaders.
Instead one must use a mod like Carpet to spawn a "dummy" player near the overworld bridge.

## This Mod

Rather than requiring the spawning of a dummy player near the overworld bridge, this mod instead detects when a mob has travelled
through a nether portal. If it has then we temporarily disable the 128-block despawn rule for that mob thus allowing it to persist.

In order to minimise unintended consequences, we automatically re-enable the rule after 60 seconds. Note that the mob will not
necessarily despawn after 60 seconds, it will just revert to its original despawning checks.
