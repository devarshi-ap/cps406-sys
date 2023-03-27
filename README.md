# CPS406 Phase 4

## Stock-Market System Documentation

---

```java
// 🟡 export transactions to file (user only)
else if (cmd.equalsIgnoreCase("EXPORT")) {
    if (isAdmin)
        System.out.println("**INVESTOR ONLY**");
    else
        investor.exportTransactions();
}

// 🟡 get prices of given stock
else if (cmd.equalsIgnoreCase("STOCK-PRICE")) {
    System.out.print("Enter STS  : ");
    String sts = scanner.nextLine();

    if (Market.verifyStock(sts))
        System.out.print(market.getAllStocks().get(cmd));
    else
        System.out.println("STS (" + sts + ") not found.");
}

// 🟡 deposit funds into wallet (user only)
else if (cmd.equalsIgnoreCase("DEPOSIT")) {
    if (isAdmin) {
        System.out.println("Cannot use this command in this context. (INVESTOR ONLY).");
    } else {
        System.out.println("Please enter the amount that you would like to deposit. \n> ");
        cmd = scanner.nextLine();

        investor.deposit(Integer.valueOf(cmd));
    }
```