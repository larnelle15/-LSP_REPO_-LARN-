package org.howard.edu.lsp.midterm.question4;

public class VotingMachineTest {
    public static void main(String[] args) {
        VotingMachine vm = new VotingMachine();
        
        // Adding candidates
        vm.addCandidate("Alice");
        vm.addCandidate("Bob");
        vm.addCandidate("Charlie");
        
        // Casting votes
        vm.castVote("Alice");
        vm.castVote("Alice");
        vm.castVote("Bob");
        vm.castVote("Charlie");
        vm.castVote("Charlie");
        vm.castVote("Charlie");
        
        // Attempt to vote for a non-existent candidate
        boolean success = vm.castVote("Eve");
        System.out.println("Vote for Eve successful? " + success);
        
        // Displaying the winner
        System.out.println("Winner: " + vm.getWinner());
    }
}

class VotingMachine {
    private java.util.Map<String, Integer> candidates = new java.util.HashMap<>();
    
    public void addCandidate(String name) {
        candidates.put(name, 0);
    }
    
    public boolean castVote(String name) {
        if (!candidates.containsKey(name)) return false;
        candidates.put(name, candidates.get(name) + 1);
        return true;
    }
    
    public String getWinner() {
        String winner = "";
        int maxVotes = 0;
        
        for (java.util.Map.Entry<String, Integer> entry : candidates.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }
        
        return winner + " WINS with " + maxVotes + " votes!!";
    }
}
