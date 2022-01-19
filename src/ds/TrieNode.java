package ds;

import models.BankBranch;
import models.Statics;

public class TrieNode {

    // TODO: Branches name can consist small alphabet
    TrieNode[] children = new TrieNode[Statics.SMALL_ALPHABET_SIZE]; // children are similar to ports
    BankBranch branch; // if branch is not null so this is the end of branch name

    public TrieNode() {
        branch = null;
        for (int i = 0; i < children.length; i++)
            children[i] = null;
    }
}
