package ru.job4j.dip;

public class TestDip {
    private NvidiaGTX970 nvidiaGTX970 = new NvidiaGTX970();
// данный класс зависет от видеокарты, это является нурушением принципа dip

    public int startGame(int usePower) {
    return usePower / nvidiaGTX970.getPower();
    }
}
