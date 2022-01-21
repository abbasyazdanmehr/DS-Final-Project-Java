package ds;

import models.Bank;
import models.Statics;

public class BankTrieNode {
    // TODO: Branches name just can consist small alphabet.
    BankTrieNode[] children = new BankTrieNode[Statics.SMALL_ALPHABET_SIZE]; // children are similar to ports
    Bank bank; // if bank is not null so this is the end of bank name

    public BankTrieNode() {
        bank = null;
        for (int i = 0; i < children.length; i++)
            children[i] = null;
    }
}
