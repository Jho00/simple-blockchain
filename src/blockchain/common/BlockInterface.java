package blockchain.common;

public interface BlockInterface {
    public void mineBlock(int difficulty);
    public String calculateHash();
    public String getHash();
    public String getPreviousHash();
}
