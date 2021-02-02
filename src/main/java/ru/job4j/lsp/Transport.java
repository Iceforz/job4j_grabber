package ru.job4j.lsp;

public interface Transport {

    String getModel();

    String getType();
// грузовой / легковой

    int getSize();
//для подбора подходящего места

    String getId();

}
