package by.itacademy.pvt.dz3;

import android.graphics.*;

public class Border implements com.squareup.picasso.Transformation {
    private final int radius;
    private final int margin;  // dp

    // radius is corner radii in dp
    // margin is the board in dp
    public Border(final int radius, final int margin) {
        this.radius = radius;
        this.margin = margin;
    }

    @Override
    public Bitmap transform(final Bitmap source) {


        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin,
                source.getHeight() - margin), radius, radius, paint);

        if (source != output) {
            source.recycle();
        }

        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.WHITE);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(3);
        canvas.drawCircle((source.getWidth() - margin) / 2f, (source.getHeight() - margin) / 2f, radius - 2, paint1);

        return output;
    }

    @Override
    public String key() {
        return "rounded";
    }
}