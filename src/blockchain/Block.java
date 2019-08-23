package blockchain;

import blockchain.common.BlockInterface;
import blockchain.util.StringUtil;

import java.util.Date;

public class Block<D> implements BlockInterface {
    public String hash;
    public String previousHash;
    private D data;
    private long timestamp; 
    private int nonce;

    public Block(D data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();

        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }

    //Calculate new hash based on blocks contents
    public String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        timestamp +
                        nonce +
                        data
        );
    }

    @Override
    public String getHash() {
        return this.hash;
    }

    @Override
    public String getPreviousHash() {
        return this.previousHash;
    }

    //Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

}
