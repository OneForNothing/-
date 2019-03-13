package com.example.machenike.hangzhou;



public class IncomeBean {
    /**
     * 我的收益
     */
        /**
         * tradeDate : 20180502
         * value : 0.03676598
         */
        private String tradeDate;
        private double value;

        protected void setValue(double val){
            this.value=val;
        }
        protected double getValue(){
            return value;
        }

        protected void setTradeDate(String date){
            this.tradeDate=date;
        }
        protected String getTradeDate(){
            return tradeDate;
        }
}



