package by.itacademy.pvt.dz5;

class Methods {

    static double[] foundAngles(double[] arr) {
        double sumArray = 0;
        int sizeArray = arr.length;
        double[] arrayAngles = new double[sizeArray];

        for (double i : arr) {
            sumArray += i;
        }
        for (int i = 0; i < arr.length; i++) {
            double angle = (arr[i] * 360) / sumArray;
            arrayAngles[i] = angle;
        }
        return arrayAngles;
    }
}