# **What is SoloRed?**
SoloRed is a single-player card game where players manage four palettes of cards and interact with a canvas card that determines winning conditions through color-based rules. The system implements a clean Model-View-Controller architecture with command pattern support for extensible user interactions.

The game features:

- Four color-coded palettes that players fill with cards
- A canvas card that determines which palette wins based on color rules
- Hand management with card drawing from a deck
- Multiple game termination conditions (win/loss scenarios)

## **Prerequisites**
The SoloRed application requires:

- Java Runtime Environment (JRE) or Java Development Kit (JDK)
- Compiled SoloRed application classes
- Terminal or command line interface

## **Running the Application**
The primary way to start `SoloRed` is through the SoloRed class main method. The application uses a command-line interface for game interaction.

### **Basic Command Syntax**
```
java cs3500.solored.SoloRed <game-type> [num-palettes] [hand-size]
```
### **Command Line Arguments**

| Argument	| Position	| Required	| Default	| Description |
| --- | --- | --- | --- | --- |
| `game-type`	| 1	| Yes |	-	| Game variant: `basic` or `advanced` |
| `num-palettes`	| 2	| No	| 4	| Number of palette stacks (integer) |
| `hand-size`	| 3	| No |	7	| Maximum cards in player hand (integer) |

## **Usage Examples**
### **Example 1: Basic Game with Defaults**
```
java cs3500.solored.SoloRed basic
```
This starts a basic game with 4 palettes and hand size of 7.

### **Example 2: Advanced Game with Custom Settings**
```
java cs3500.solored.SoloRed advanced 6 5
```
This starts an advanced game with 6 palettes and hand size of 5.

### **Example 3: Basic Game with Custom Palettes Only**
```
java cs3500.solored.SoloRed basic 3
```
This starts a basic game with 3 palettes and default hand size of 7.

## **Alternative Entry Point**
For development and testing purposes, an alternative entry point exists in `Main.java`. This class provides a pre-configured game setup with specific cards and arrangements.

### **Main.java Features**
- Creates a `SoloRedGameModel` directly without using `RedGameCreator`
- Pre-defines specific cards and deck arrangement for consistent testing
- Uses hardcoded game configuration (2 palettes, hand size 4)
- Useful for debugging specific game scenarios
To use the alternative entry point:
```
java cs3500.solored.controller.Main
```

## **Game Interaction**
Once the application starts, you interact with the game through text commands entered via the terminal. The `SoloRedTextController` handles input processing and displays the game state.

Common game commands include:
- Playing cards to palettes
- Playing cards to canvas
- Quitting the game (`q`)

### **Error Handling**
The application includes several error handling mechanisms:

| Error Condition	| Behavior	|
| --- | --- |
| Invalid game type	| `IllegalArgumentException` thrown	|
| Invalid deck size	| `IllegalStateException` thrown	|
| Invalid numeric arguments	| Ignored, defaults used |
| Controller exceptions	| Ignored during playGame call |	


