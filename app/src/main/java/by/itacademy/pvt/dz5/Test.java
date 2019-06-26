package by.itacademy.pvt.dz5;

public class Test {
    public static void main(String[] args) {
        double[] array = {25, 30, 60};
        double[] arrayAngles = Methods.foundAngles(array);
        for (double i : arrayAngles) {
            System.out.println(i);
        }
    }
}