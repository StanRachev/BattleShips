# Battleships Game

Welcome to the Battleships game! This is a console-based implementation of the classic Battleships game where you play against an AI. Tha player vs player feature is coming out soon!

## Features

- Player vs AI mode
- Manual and automatic ship placement

## How to Play

1. Choose your game mode:
   - Player vs AI
   - Player vs Player (coming soon)

2. Place your ships on the grid:
   - Set them yourself
   - Use the automatic placement

3. Start the game and take turns guessing the positions of the opponent's ships.

4. The game ends when all ships of one player are destroyed.

## How to play

To play the game, run the BattleShips class.

## Game Rules

1. Ships are placed on a 10x10 grid.
2. Players take turns guessing the locations of the opponent's ships.
3. A hit is marked if a guessed location contains a part of a ship; otherwise, it is a miss.
4. The game continues until one player's fleet is completely destroyed.

## Code Structure

### Classes:

1. **BattleShips**: Entry point for the game.
2. **AI**: Contains the logic for the AI opponent.
3. **GameManager**: Manages the flow of the game.
4. **MapPlayer**: Handles the game map and ship placement.
5. **Player**: Manages player inputs and interactions.
6. **Ship**: Represents the ship object and its properties.
7. **UI**: Handles user interface and input.
8. **Cell**: Represents a cell on the game grid.

## Enjoy the game!