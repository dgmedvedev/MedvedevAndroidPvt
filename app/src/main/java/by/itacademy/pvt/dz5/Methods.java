package by.itacademy.pvt.dz5;

class Methods {

    static float[] foundAngles(int [] arr) {
        float sumArray = 0;
        int sizeArray = arr.length;
        float[] arrayAngles = new float[sizeArray];

        for (int i : arr) {
            sumArray += i;
        }
        for (int i = 0; i < arr.length; i++) {
            float angle = ((float)arr[i] * 360) / sumArray;
            arrayAngles[i] = angle;
        }
        return arrayAngles;
    }
}