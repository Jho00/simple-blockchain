package blockchain;

import blockchain.common.BlockInterface;
import blockchain.util.ConsoleLogger;
import java.util.ArrayList;

public class Blockchain<T extends BlockInterface> {
    private ArrayList<T> blockchain = new ArrayList<>();
    private int difficulty;

    public void addBlock(T newBlock) {
        newBlock.mineBlock(difficulty);
        this.blockchain.add(newBlock);
    }

    public Blockchain(int difficulty) {
        this.difficulty = difficulty;
    }

    public static Blockchain getNewBlockChain(int difficulty) {
        return new Blockchain(difficulty);
    }

    public Boolean isChainValid() {
        T currentBlock;
        T previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            ConsoleLogger.log("Start compare block with hash: " + currentBlock.getHash());
            ConsoleLogger.log("comparing with previous block with hash: " + previousBlock.getHash());

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                ConsoleLogger.log("Current Hashes not equal"); // TODO: make inject project-logger
                return false;
            }

            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                ConsoleLogger.log("Previous Hashes not equal");
                return false;
            }

            if (!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
                ConsoleLogger.log("This block hasn't been mined");
                return false;
            }

        }
        return true;
    }

    public T getLastBlock() {
        return blockchain.get(blockchain.size() - 1);
    }
}
