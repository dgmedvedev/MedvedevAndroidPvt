package by.itacademy.pvt.dz5;

public class Test {
    public static void main(String[] args) {
        int[] array = {1, 2, 2};
        float[] arrayAngles = Methods.foundAngles(array);
        for (int i = 0; i< arrayAngles.length; i++) {
            System.out.println("Угол" + i + " = " + arrayAngles[i]);
        }
    }
}