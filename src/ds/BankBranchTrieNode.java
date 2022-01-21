package ds;

import models.BankBranch;
import models.Statics;

public class BankBranchTrieNode {

    // TODO: Branches name just can consist small alphabet.
    BankBranchTrieNode[] children = new BankBranchTrieNode[Statics.SMALL_ALPHABET_SIZE]; // children are similar to ports
    BankBranch branch; // if branch is not null so this is the end of branch name

    public BankBranchTrieNode() {
        branch = null;
        for (int i = 0; i < children.length; i++)
            children[i] = null;
    }
}
