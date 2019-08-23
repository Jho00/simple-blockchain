package blockchain;


public class Main {
	public static void main(String[] args) {
		Blockchain blockchain = Blockchain.getNewBlockChain(5);
		Block firstBlock = new Block("First message", "0");

		blockchain.addBlock(firstBlock);
		for (int i = 0; i < 10; i++) {
			blockchain.addBlock(new Block("Message" + i, blockchain.getLastBlock().getHash()));
			System.out.println("Added");
		}
	}

}
