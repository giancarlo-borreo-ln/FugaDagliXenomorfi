# Fuga dagli Xenomorfi

**Fuga dagli Xenomorfi** is a strategy survival game simulation where players manage resources, protect crew members, and escape a spaceship infested by Xenomorphs. This game involves strategic resource allocation, population management, and dynamic map exploration as players strive to repair the spaceship and outmaneuver evolving alien threats.

---

## Features

- **Dynamic Spaceship Environment**: The spaceship is modeled as a linked list of rooms, each with unique properties like equipment, barricades, and populations.
- **Xenomorph Dynamics**: Xenomorphs evolve and move unpredictably, requiring players to adapt strategies to survive.
- **Event System**: Random events like malfunctions, attacks, or discoveries influence gameplay unpredictably.
- **Victory Conditions**: Repair all critical modules or survive long enough for rescue while managing limited resources.

---

## Endpoints

### Game Management (`/game`)
- **Start Game**: `POST /game/start?shipName={name}` - Initialize a new game with a named spaceship.
- **Advance Turn**: `PUT /game/turn` - Move the game to the next turn.
- **Get Status**: `GET /game/status` - Retrieve the current game status.
- **Game Summary**: `GET /game/summary` - Get an overview of the game's progress.
- **Explore**: `POST /game/action/explore` - Execute an exploration action.
- **Fight**: `POST /game/action/fight` - Perform an attack against Xenomorphs.
- **Repair**: `POST /game/action/repair` - Repair spaceship modules.
- **Gather Resources**: `POST /game/action/gather` - Collect available resources.
- **Reinforce**: `POST /game/action/reinforce` - Fortify rooms.
- **End Game**: `POST /game/end` - Terminate the game session.

### Xenomorph Management (`/xenomorfi`)
- **List Xenomorphs**: `GET /xenomorfi` - Retrieve all Xenomorphs.
- **Get Xenomorph by ID**: `GET /xenomorfi/{id}` - Get details for a specific Xenomorph.
- **Create Xenomorph**: `POST /xenomorfi` - Add a new Xenomorph (params: hp, attack, evolved, roomId).
- **Move Xenomorph**: `PUT /xenomorfi/{id}/sposta` - Relocate a Xenomorph to another room.
- **Update HP**: `PUT /xenomorfi/{id}/hp` - Change Xenomorph health.
- **Evolve Xenomorph**: `PUT /xenomorfi/{id}/evolvi` - Apply evolution bonuses.
- **Delete Xenomorph**: `DELETE /xenomorfi/{id}` - Remove a Xenomorph.

### Event Management
