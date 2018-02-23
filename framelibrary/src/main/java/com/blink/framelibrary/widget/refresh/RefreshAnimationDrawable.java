package com.blink.framelibrary.widget.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Handler;

import com.blink.framelibrary.utils.CommonUtil;
import com.blink.framelibrary.utils.image.ImageUtil;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2017/08/31
 *     desc   : 刷新动画drawable
 * </pre>
 */
public class RefreshAnimationDrawable extends RefreshDrawable implements Runnable {

    private boolean isRunning;
    private Handler mHandler = new Handler();
    private int mOffset;
    private static int drawableMiddleWidth;
    private static int drawableMiddleHeight;
    private static int[] bitmaps;//设置为静态，减少加载
    private RectF rectF = new RectF();
    private int ANIMINATION_DELAY = 40;
    private int DISPLAY_WIDTH = 0;

    public RefreshAnimationDrawable(PullRefreshLayout layout, int resId) {
        super(layout);
	    bitmaps = getData(getContext(), resId);
	    drawableMiddleWidth =  CommonUtil.dp2px(getContext(), 33);
	    drawableMiddleHeight = CommonUtil.dp2px(getContext(), 40);
    }

    @Override
    public void setPercent(float percent) {
        int centerX = getBounds().centerX()== 0 ? DISPLAY_WIDTH / 2 : getBounds().centerX();
        int centerY = - CommonUtil.dp2px(getContext(), PullRefreshLayout.DRAG_MAX_DISTANCE);

        rectF.left = centerX - drawableMiddleWidth ;
        rectF.right = centerX + drawableMiddleWidth ;
        rectF.top = centerY - drawableMiddleHeight ;
        rectF.bottom = centerY + drawableMiddleHeight ;
    }

    @Override
    public void setColorSchemeColors(int[] colorSchemeColors) {}

    @Override
    public void offsetTopAndBottom(int offset) {
        mOffset += offset;
        invalidateSelf();
    }

    @Override
    public void start() {
        isRunning = true;
        mHandler.postDelayed(this, 50);
    }

    @Override
    public void run() {
        if (isRunning) {
            mHandler.postDelayed(this, 50);
            invalidateSelf();
        }
    }

    @Override
    public void stop() {
        isRunning = false;
        mHandler.removeCallbacks(this);
    }

    @Override
    public boolean isRunning()
    {
        return isRunning;
    }

    @Override
    public void draw(Canvas canvas) {
        int size = bitmaps.length;
        if(size == 0) return;
        int num = (int) (System.currentTimeMillis() / ANIMINATION_DELAY % size);
        final int saveCount = canvas.save();
        canvas.translate(0, mOffset + CommonUtil.dp2px(getContext(), 40));
        Bitmap bitmap = ImageUtil.getSoftRefrenceBitmap(getContext(), bitmaps[num]);
        if(bitmap == null) {
        	return;
        }
        canvas.drawBitmap(bitmap, null, rectF, null);
        canvas.restoreToCount(saveCount);
    }

    //设置设备分辨率宽度
    public void setDisplayWidth(int DISPLAY_WIDTH) {
        this.DISPLAY_WIDTH = DISPLAY_WIDTH;
    }

    //从xml中读取帧数组
	private int[] getData(Context context, int resId) {
		TypedArray array = context.getResources().obtainTypedArray(resId);
		int len = array.length();
		int[] intArray = new int[array.length()];
		for (int i = 0; i < len; i++) {
			intArray[i] = array.getResourceId(i, 0);
		}
		array.recycle();
		return intArray;
	}

}
