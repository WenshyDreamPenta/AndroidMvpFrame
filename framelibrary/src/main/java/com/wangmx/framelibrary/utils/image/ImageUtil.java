package com.wangmx.framelibrary.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/30
 *     desc   : image工具类
 * </pre>
 */

public class ImageUtil {

    /**
     * 获取指定resId BitmapDrawable
     */
    public static BitmapDrawable getBmpDrawable(Context context, int resId) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inDither = false;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            opt.inJustDecodeBounds = false;//设置该属性可获得图片的长宽等信息，但是避免了不必要的提前加载动画

            //获取资源图片
            InputStream is = context.getResources().openRawResource(resId);
            bitmap = BitmapFactory.decodeStream(is, null, opt);
            is.close();

        } catch (OutOfMemoryError e) {
            //内存溢出處理，釋放内存，重新獲取
            System.gc();
            System.runFinalization();
            getBmpDrawable(context, resId);
        } catch (IOException Exception) {
            return null;
        }
        return new BitmapDrawable(context.getResources(), bitmap);
    }

	/**
	 * 返回弱引用Bitmap
	 * @param context 上下文
	 * @param resId 资源id
	 * @return 软引用的bitmap
	 */
	public static  Bitmap getSoftRefrenceBitmap(Context context, int resId) {
    	Bitmap bitmap = getBmpDrawable(context, resId).getBitmap();
	    SoftReference<Bitmap> sBitmapReference = new SoftReference<Bitmap>(bitmap);//使用bitmap软引用
	    bitmap = null;//释放强引用
	    return sBitmapReference.get();
    }

	/**
	 * 返回弱引用Bitmap
	 * @param bitmap Bitmap
	 * @return 软引用的bitmap
	 */
    public static Bitmap getSoftRefrenceBitmap(Bitmap bitmap) {
	    SoftReference<Bitmap> sBitmapReference = new SoftReference<Bitmap>(bitmap);//使用bitmap软引用
	    bitmap = null;//释放强引用
	    return sBitmapReference.get();
    }

    /**
     * 指定路径文件生成Bitmap
     */
    public static Bitmap getBitmap(File file) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inDither = false;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            opt.inJustDecodeBounds = false;//设置该属性可获得图片的长宽等信息，但是避免了不必要的提前加载动画

            //获取资源图片
            InputStream is = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(is, null, opt);
            is.close();
        } catch (OutOfMemoryError e) {
            System.gc();
            System.runFinalization();
        } catch (IOException e) {}
        return bitmap;
    }

    /**
     * 创建Bitmap 宽、高、配置
     */
    public static Bitmap createBitmap(int width, int height, Bitmap.Config config) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError e) {
            while (bitmap == null) {
                System.gc();
                System.runFinalization();
                bitmap = createBitmap(width, height, config);
            }
        }
        return bitmap;
    }

    /**
     * 创建Bitmap
     * @return
     */
    public static Bitmap createBitmap(Bitmap source, int x, int y, int width, int height, Matrix m, boolean filter) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(source, x, y, width, height, m, filter);
        } catch (OutOfMemoryError e) {
            while (bitmap == null) {
                System.gc();
                System.runFinalization();
                bitmap = Bitmap.createBitmap(source, x, y, width, height, m, filter);
            }
        }
        return bitmap;
    }

    /**
     * 获取degree
     *
     * @param path 路径
     * @return
     */
    public static int readPictureDegree(String path) {
        File mFile = new File(path);
        if (!mFile.exists()) {
            return 0;
        }

        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {}
        return degree;
    }

    /**
     * 获取指定尺寸Bitmap
     */
    public static Bitmap getScaledBitmap(String mPath, int des_width, int des_height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mPath, options);
        int bmp_width = options.outWidth;
        int bmp_height = options.outHeight;

        if (bmp_width > des_width || bmp_height > des_height) {
            float scale_width = bmp_width / (float) des_width;
            float scale_height = bmp_height / (float) des_height;

            if (scale_width > scale_height) {
                options.inSampleSize = ((int) scale_width) + 1;
            } else {
                options.inSampleSize = ((int) scale_height) + 1;
            }
        }

        if (options.inSampleSize < 1) {
            options.inSampleSize = 1;
        }
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap mBitmap = BitmapFactory.decodeFile(mPath, options);

        int degree = readPictureDegree(mPath);
        if (degree > 0) {
            mBitmap = reviseDegree(mBitmap, mPath, degree);
        }
        return mBitmap;
    }

    /**
     * 修正degree
     */
    public static Bitmap reviseDegree(Bitmap bitmap, String mPath, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return newBmp;
    }

    /**
     * 压缩Bitmap生成文件
     */
    public static void compressBmpToFile(Bitmap bmp, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        if (bmp != null) {
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (Exception e) {}
        }
    }

}
