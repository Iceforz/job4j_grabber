package ru.job4j.isp;

public interface IspTest {
     void getDiscount();

     void getPromoCode();

     void setSize();

     void setExpireDate();

     void setPrice();
     // Если наш товар не попадает под скидку или промокод , то эти методы не будут
    // использоватся в нашем классе, поэтому лучше разбить наш интерфейс на мелкие,
    //чтобы избежать нарушения isp

}

