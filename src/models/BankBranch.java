package models;

public class BankBranch {
    // in main branch of bank name and bank name is equal
    private final String name;
    private final String bankName;
    private final Coordinate location;
    public BankBranch parent;
    public BankBranch left;
    public BankBranch right;
    private int indexInBankBranchesList;


    public BankBranch(
            String name,
            String bankName,
            Coordinate location
    ) {
        this.name = name;
        this.bankName = bankName;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getBankName() {
        return bankName;
    }

    public Coordinate getLocation() {
        return location;
    }

    public int getIndexInBankBranchesList() {
        return indexInBankBranchesList;
    }

    public void setIndexInBankBranchesList(int indexInBankBranchesList) {
        this.indexInBankBranchesList = indexInBankBranchesList;
    }

    @Override
    public String toString() {
        return "BankBranch{" +
                "name='" + name + '\'' +
                ", bankName='" + bankName + '\'' +
                ", location=" + location +
                '}';
    }

    public boolean isEqualWith(BankBranch branch) {
        return this.getLocation() == branch.getLocation();
    }
}
