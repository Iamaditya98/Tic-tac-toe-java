@RestController
@RequestMapping("/api/game")
public class GameController {
    private Game game = new Game();
    @PostMapping("/move")
    public ResponseEntity<String> makeMove(@RequestParam int row, @RequestParam int col) {
        if (game.makeMove(row, col)) {
            char winner = game.checkWinner();
            if (winner != '-') {
                return ResponseEntity.ok("Player " + winner + " wins!");
            } else if (game.isBoardFull()) {
                return ResponseEntity.ok("It's a draw!");
            } else {
                return ResponseEntity.ok("Move successful. Next player: " + game.getCurrentPlayer());
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid move.");
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetGame() {
        game = new Game();
        return ResponseEntity.ok("Game reset.");
    }
}