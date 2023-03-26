package main;

public class Stock {
    /**
     * Instance variables for Stock objects
     */
    String name, sts;
    int market_price, open_price, close_price, high_price, low_price, volume, outstanding_shares, floating_shares;

    /**
     * Instantiate a Stock object
     */
    public Stock(String name, String sts, int[] prices, int volume, int outstanding_shares) {
        this.name = name;
        this.sts = sts;
        this.market_price = prices[0];
        this.open_price = prices[1];
        this.close_price = prices[2];
        this.high_price = prices[3];
        this.low_price = prices[4];
        this.volume = volume;
        this.outstanding_shares = outstanding_shares;
        this.floating_shares = 0;
    }

    public String toString() {
        return "------+\n" + this.name + " (" + this.sts + ")"
        + " Stock prices"
        + "\nMarket Price: \t$" + this.market_price
        + "\nOpening Price: \t$" + this.open_price
        + "\nClosing Price: \t$" + this.close_price
        + "\nHigh Price: \t$" + this.high_price
        + "\nLow Price: \t$" + this.low_price
        + "\n------+";
    }

    /**
     * Return sts of stock
     * 
     * @return  sts  string stock ticker symbol
     */
    public String getSTS() {
        return this.sts;
    }

    /**
     * Return array of in-order stock prices
     * 
     * @return  prices  integer array of in-order stock prices
     */
    public int[] getPrices() {
        return new int[]{this.market_price, this.open_price, this.close_price, this.high_price, this.low_price};
    }

    /**
     * Return volume of stock
     * 
     * @return   volume   integer value of stock volume
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * Increment (shares >= 0)/Decrement (shares < 0)  the outstanding_share value of Stock
     * 
     * @param   value   integer number of shares should be incremented/decremented by
     */
    public void addToOutstandingShares(int shares) {
        this.outstanding_shares += shares;
    }

    /**
     * Increment (shares >= 0)/Decrement (shares < 0) the floating_shares value of Stock
     * 
     * @param   value   integer number of shares should be incremented/decremented by
     */
    public void addToFloatingShares(int shares) {
        this.floating_shares += shares;
    }
}
