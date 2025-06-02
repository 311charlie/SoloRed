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

### **Example Game Interface**
```
Canvas: R  
> P1: R3 R5  
P2: O2  
P3: B1 B4  
P4: I1
Hand: V7 I6 O4  
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

### **Core Game Actions**
#### **Playing to Palettes**
The `playToPalette` action is the primary way to advance the game. Players must correctly identify and play to a palette that changes the current winning palette to avoid losing.

| Validation Check	| Exception Type	| Condition
| --- | --- | --- |
| Game Started	| `IllegalStateException`	| `!startGame`
| Game Not Over	| `IllegalStateException`	| `isGameOver()`
| Palette Not Winning	| `IllegalStateException`	| `isPaletteWinning(paletteIdx)`
| Valid Palette Index	| `IllegalArgumentException`	| `paletteIdx < 0` or `paletteIdx >= numPalettes()`
| Valid Card Index	| `IllegalArgumentException`	| `cardIdxInHand < 0` or `cardIdxInHand >= hand.size()`

After validation, the action removes the card from the player's hand, adds it to the target palette, and checks if the current winning palette changed to the target palette. If not, `gameLost` is set to `true`.

#### **Playing to Canvas**
The `playToCanvas` action changes the game's rule system by replacing the canvas card, which determines which color rule is active.

Key restrictions:
- Cannot play to canvas twice consecutively
- Cannot use the last card in hand for canvas
- Canvas cards are constructed with only color information

#### **Drawing Cards**
The `drawForHand` action automatically fills the player's hand up to `maxHandSize` from the deck. This action occurs automatically after other actions and continues until the hand is full or the deck is empty.

## **Color Rules System**
The game's core mechanic revolves around color-based rules that determine which palette is currently winning. The canvas card's color determines which rule is active:

### **Red Rule - Highest Card Wins**
When the canvas shows Red (R), the palette containing the highest-numbered card wins.

The implementation uses a tie-breaking system where if multiple palettes have cards of the same highest number, the card with the color that appears first in the color enumeration wins.

### **Orange Rule - Most of a Single Number**
When the canvas shows Orange (O), the palette with the most cards of any single number wins.

For example, a palette with three 5s beats a palette with two 7s. The rule counts occurrences of each number within each palette.

### **Blue Rule - Most Different Colors**
When the canvas shows Blue (B), the palette with cards of the most different colors wins.

The rule tracks unique colors in each palette, so a palette with Red, Blue, and Orange cards (3 colors) beats one with only Red and Blue cards (2 colors).

### **Indigo Rule - Longest Run**
When the canvas shows Indigo (I), the palette with the longest consecutive sequence of numbers wins.

The implementation sorts cards by number and finds the longest consecutive sequence. For example, cards numbered 3, 4, 5, 6 form a run of 4.

### **Violet Rule - Most Cards Below 4**
When the canvas shows Violet (V), the palette with the most cards numbered below 4 wins.

Only cards with numbers 1, 2, or 3 count toward this rule.

### **Tie Breaking**
When multiple palettes tie under the active rule, a secondary tie-breaking system determines the winner. The tie-breaking logic varies by rule but generally favors palettes based on card values and color precedence resembling the Red Rule.

## **Win and Loss Conditions**
#### **Game Over Detection**
The game ends when either win or loss conditions are met:

#### **Winning Conditions**
A player wins when all conditions are met:
- Their hand is empty
- The deck is empty
- They have not lost the game
  
#### **Losing Conditions**
A player loses when all conditions are met:
- They play a card to a palette that does not change the currently winning palette
- This is determined by comparing the target palette index with winningPaletteIndex()

## **Initial State Setup**
1. Optionally shuffle the deck using provided `Random` object
2. Initialize palettes by dealing one card to each from the deck
3. Set canvas to Red (default starting rule)
4. Fill player's hand using `drawForHand()`
