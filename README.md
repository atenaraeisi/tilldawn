# 20 Minutes Till Dawn - Java Edition

A 2D survival action game written in **Java** using the **libGDX** framework.

The game features 360-degree shooting mechanics, multiple unlockable heroes and weapons, an ability progression system, diverse enemy types (including boss fights), and a complete user authentication and save/load system.

Originally developed as an individual project for the **Advanced Programming** course at Sharif University of Technology.

<p align="center">
  <img src="docs\images\Start.png" alt="20 Minutes Till Dawn Main Menu" width="900">
</p>

## Features

- **MVC Architecture:** Clean separation of game logic, rendering, and controllers
- **Multiple Heroes:** Play as Shana, Diamond, Scarlet, Lilith, or Dasher with unique base stats
- **Weapon Arsenal:** Choose between Revolver, Shotgun, and Dual SMGs with different reload and damage mechanics
- **Enemy AI:** Survive waves of Tentacle Monsters, projectile-shooting Eyebats, and an Elder Boss with dynamic shielding
- **Progression System:** Earn XP to level up and draft abilities (Vitality, Damager, Speedy, Procrease, Amocrease)
- **Account System:** Full user registration, login, and profile management (username/password validation, avatars)
- **Data Persistence:** Save and continue functionality, along with a persistent dynamic leaderboard using JSON
- **Custom Controls:** WASD movement, mouse aiming, auto-aim support, and configurable keybindings

## Gameplay

Survive waves of procedurally spawned enemies while collecting XP to upgrade your character.

<table>
<tr>
<td align="center"><b>Basic Combat</b></td>
<td align="center"><b>Swarm Survival</b></td>
</tr>
<tr>
<td><img src="docs\images\game2.png" width="500"></td>
<td><img src="docs\images\game3.png" width="500"></td>
</tr>
</table>

## Progression and Boss Fights

Level up to unlock new abilities and survive long enough to encounter the shielding Elder Boss.

<table>
<tr>
<td align="center"><b>Ability Selection</b></td>
<td align="center"><b>Elder Boss Fight</b></td>
</tr>
<tr>
<td><img src="docs\images\levelUp.png" width="500"></td>
<td><img src="docs\images\game4.png" width="500"></td>
</tr>
</table>

## User Interface

The game includes comprehensive pre-game configuration and profile management menus.

<table>
<tr>
<td align="center"><b>Pre-Game Setup</b></td>
<td align="center"><b>Profile Management</b></td>
</tr>
<tr>
<td><img src="docs\images\PreGameMenu.png" width="500"></td>
<td><img src="docs\images\ProfileMenu.png" width="500"></td>
</tr>
</table>

## Built With

- **Java**
- **libGDX**
- **Gradle**
- **JSON** (Data Persistence)

## Build and Run

### Requirements

You need:
- Java 17 or higher

### Build

Clone the repository and run the application via the provided Gradle wrapper.

**On Windows:**
```cmd
gradlew.bat lwjgl3:run
```

**On Linux / macOS:**
```bash
./gradlew lwjgl3:run
```

## Project Structure
The codebase is organized strictly around the Model-View-Controller pattern.

```text
core/src/main/java/com/tilldawn
├── Control/         # Input handling, game loops, physics (e.g., PlayerController, GameController)
├── Model/           # Game state and domain entities (Player, Bullet, Enemies, GameState)
├── View/            # UI, rendering, and menus (GameView, ScoreboardView, WinningPage)
└── Main.java        # Application entry point
```
## Notes
Runtime-generated user data (such as users.json and saved game states) are intentionally excluded from version control.
