package org.lyh.sample.mydroolssample.dao;

/**
 * PaymentInfo fact object
 * 
 * @author liuyuho
 *
 */
public class PaymentInfo implements java.io.Serializable
{

   static final long serialVersionUID = 1L;

   private int moneyAmount;

   private java.lang.String decisionPath;

   public PaymentInfo()
   {
   }

   public int getMoneyAmount()
   {
      return this.moneyAmount;
   }

   public void setMoneyAmount(int moneyAmount)
   {
      this.moneyAmount = moneyAmount;
   }

   public java.lang.String getDecisionPath()
   {
      return this.decisionPath;
   }

   public void setDecisionPath(java.lang.String decisionPath)
   {
      this.decisionPath = decisionPath;
   }

   public PaymentInfo(int moneyAmount, java.lang.String decisionPath)
   {
      this.moneyAmount = moneyAmount;
      this.decisionPath = decisionPath;
   }

}