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

    /**
     * Return array of in-order stock prices
     * 
     * @return  prices  array of in-order stock prices
     */
    public int[] getPrices() {
        return new int[]{this.market_price, this.open_price, this.close_price, this.high_price, this.low_price};
    }

    public int getVolume() {
        return this.volume;
    }

    /**
     * Increment/Decrement the outstanding_share value of Stock
     * value < 0 : subtract from outstanding_shares
     * value >= 0 : add to outstanding_shares
     * 
     * @param   value   integer number of shares should be incremented/decremented by
     */
    public void addToOutstandingShares(int shares) {
        this.outstanding_shares += shares;
    }
}
