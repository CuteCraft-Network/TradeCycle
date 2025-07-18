# TradeCycle

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/S42YT/TradeCycle/Java%20CI%20with%20Gradle?style=for-the-badge) ![GitHub release](https://img.shields.io/github/v/release/S42YT/TradeCycle?style=for-the-badge)

[![Spigot Downloads](https://img.shields.io/spiget/downloads/122805?label=spigot%20downloads&style=for-the-badge&color=ee8917)](https://www.spigotmc.org/resources/tradecycle.122805/) [![Modrinth Downloads](https://img.shields.io/modrinth/dt/tradecycle?label=modrinth%20downloads&style=for-the-badge&color=5da545)](https://modrinth.com/plugin/tradecycle)

### A multiplatform plugin written in Kotlin to cycle through Villager trades from UI. Inspired by [henkelmax](https://modrinth.com/user/henkelmax)'s mod [Trade Cycling](https://modrinth.com/mod/trade-cycling).

<hr>

# Download

You can either download the plugin from the latest release or download it from the following sources:

- [Spigot](https://www.spigotmc.org/resources/tradecycle.122805/) - Spigot Version
- [Modrinth](https://modrinth.com/plugin/tradecycle) - Paper and Spigot Version
- [Hangar](https://hangar.papermc.io/S42yt/TradeCycle) - Paper Version
- [CurseForge](https://www.curseforge.com/minecraft/bukkit-plugins/tradecycle) - Paper Version
- [Github Relases](https://github.com/CuteCraft-Network/TradeCycle/releases/latest) - Paper and Spigot Version

# Features

There are two variants to cycle through trades:

1. **Press F (default)**: Press the button associated with swapping the items to your offhand (**IMPORTANT**: The Cursor
   needs to be in the Inventory due to Minecraft limitations).
2. **Shift Right Click**: Shift right-click on the villager to cycle through trades. However, this variant is disabled
   by default, have a look at our [#Config](#Config) section to enable it.

<img src="assets/cycle_success.gif" alt="cycle_trade" width="500"/>

### 🚨 Note: If a villager has no profession or has been traded with, you cannot cycle through trades.

<img src="assets/villager_locked.png" alt="cant_cycle_trade" width="500"/>

# Config

You can configure which method (or both) should be used to cycle through trades:

```yaml
strategy:
  - KEYBOARD
# - SHIFT_INTERACT
```

'#' in front of a method will disable it.

The SHIFT_INTERACT was made with bedrock and Geyser in mind so you can also cycle trades on the bedrock edition. But it
doesnt work in UI with this strategy.

# Contributing 🩷

Feel free to open an issue or a pull request, I will be happy to help you! Special thanks to every contributor of this
project:

- [Tamikaschu](https://github.com/tamikaschu)
