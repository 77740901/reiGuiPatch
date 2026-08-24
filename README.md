# REI GUI Patch

A Fabric client-side mixin mod that fixes GUI rendering and interaction issues when using [Roughly Enough Items (REI)](https://modrinth.com/mod/rei) on Minecraft 26.2.

## Fixes

| Issue | Description |
|-------|-------------|
| **Scissor clipping** | REI overlay renders outside its bounds due to `ScreenRectangle.empty()` |
| **Crafting table icon** | The craftable filter button is missing its crafting table icon |
| **Tooltip z-order** | Menu entry tooltips render behind the overlay instead of on top |
| **Submenu direction** | Submenus incorrectly open to the right, going off-screen on high GUI scale |
| **Click-through** | Creative mode inventory tabs and slots can be clicked through the REI menu |
| **Tooltip bleed** | Underlying screen tooltips (e.g. "Spawn Egg") leak through the REI menu |

## Requirements

- Minecraft 26.2
- Fabric Loader >= 0.16.0
- [Roughly Enough Items (REI)](https://modrinth.com/mod/rei) 26.2.x

## Installation

Download the latest release jar and place it in your `mods/` folder alongside REI.

## Building

```bash
./gradlew build
```

The built jar will be in `build/libs/`.

## License

[MIT](LICENSE)
