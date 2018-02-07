package com.blink.framelibrary.utils.animator;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.blink.framelibrary.utils.image.ImageUtil;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/30
 *     desc   : 帧动画播放
 *               使用：1.帧数组资源id在arrays.xml
 *                    2.适合做帧数较大的帧动画使用，避免OOM
 * </pre>
 */
public class FrameAnimator implements LifecycleObserver{

    private int FPS;  // 每秒播放帧数，fps = 1/t，t-动画两帧时间间隔
    private Context mContext;
    // 单例
    private static FrameAnimator mInstance;

    // 从xml中读取资源ID数组
    private int[] mProgressAnimFrames;

    public static FrameAnimator getInstance() {
        if (mInstance == null) {
            mInstance = new FrameAnimator();
        }
        return mInstance;
    }

    public FrameAnimator setParameters(Context context, int resId, int fps){
        mInstance.mContext = context;
        mInstance.FPS = fps;
        mInstance.mProgressAnimFrames = mInstance.getData(resId);
        return mInstance;

    }

    public FramesSequenceAnimation createFramesAnim(ImageView imageView) {
        return new FramesSequenceAnimation(imageView, mProgressAnimFrames, FPS);
    }


    /**
     * 内部类-帧动画播放类
     */
    public class FramesSequenceAnimation {
        private int[] mFrames; // 帧数组
        private int mIndex; // 当前帧
        private boolean mShouldRun; // 开始/停止播放用
        private boolean mIsRunning; // 动画是否正在播放，防止重复播放
        private SoftReference<ImageView> mSoftReferenceImageView; // 软引用ImageView，以便及时释放掉
        private Handler mHandler;
        private int mDelayMillis;
        private OnAnimationStoppedListener mOnAnimationStoppedListener; //播放停止监听
        private ArrayList<Bitmap> bmpList = new ArrayList<>();
        private boolean isRecycle = false;


        FramesSequenceAnimation(ImageView imageView, int[] frames, int fps) {
            //参数初始化
            mHandler = new Handler();
            mFrames = frames;
            mIndex = -1;
            mSoftReferenceImageView = new SoftReference<ImageView>(imageView);
            mShouldRun = false;
            mIsRunning = false;
            mDelayMillis = 1000 / fps;//帧动画时间间隔，毫秒

            //设置第一帧
            Bitmap bitmap = ImageUtil.getSoftRefrenceBitmap(mContext, mFrames[0]);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                bmpList.add(bitmap);
            }

        }

        //设置是否循环
        public FramesSequenceAnimation setIsRecycle(boolean isRecycle) {
            this.isRecycle = isRecycle;

            return this;
        }

        //播放动画，同步锁防止多线程读帧时，数据安全问题
        public synchronized void start() {
            mShouldRun = true;
            if (mIsRunning){
                return;
            }

            //读帧线程
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    ImageView imageView = mSoftReferenceImageView.get();
                    if (!mShouldRun || imageView == null) {
                        mIsRunning = false;
                        if (mOnAnimationStoppedListener != null) {
                            mOnAnimationStoppedListener.AnimationStopped();
                        }
                        mHandler.removeCallbacks(this);
                        return;
                    }

                    mIsRunning = true;
                    //新开线程去读下一帧
                    mHandler.postDelayed(this, mDelayMillis);

                    if (imageView.isShown()) {
                        int imageRes = getNextFrame();
                        if (mShouldRun) {
                            try {
                                Bitmap getBitmap = ImageUtil.getSoftRefrenceBitmap(mContext, imageRes);
                                if (getBitmap != null) {
                                    imageView.setImageBitmap(getBitmap);
                                    if (bmpList.size() < mFrames.length) {
                                        bmpList.add(getBitmap);
                                    }
                                }
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }
            };

            mHandler.post(runnable);
        }

        //停止
        public synchronized void stop() {
            mShouldRun = false;
            releaseBitmap();
        }

        //获取下一帧
        private int getNextFrame() {
            mIndex++;
            if (mIndex >= mFrames.length) {
                if (!isRecycle) {
                    mIndex = mFrames.length - 1;
                    mShouldRun = false;//数组遍历完成，则停止动画，当前停留在最后一帧
                }
                else {
                    mIndex = 0;
                    mShouldRun = true;
                }

            }
            return mFrames[mIndex];
        }

        public void setOnAnimStopListener(OnAnimationStoppedListener listener) {
            this.mOnAnimationStoppedListener = listener;
        }

        private void releaseBitmap() {

            if (bmpList.size() != 0) {
                for (Bitmap bmp : bmpList) {
                    if (bmp != null && !bmp.isRecycled()) {
                        bmp.recycle();
                    }
                }
                bmpList.clear();
                bmpList = null;
            }

        }

    }

    /**
     * 从xml中读取帧数组
     *
     * @param resId 数组地址
     * @return 资源ID数组
     */
    private int[] getData(int resId) {

        TypedArray array = mContext.getResources().obtainTypedArray(resId);
        int len = array.length();
        int[] intArray = new int[array.length()];
        for (int i = 0; i < len; i++) {
            intArray[i] = array.getResourceId(i, 0);
        }
        array.recycle();
        return intArray;

    }

    public interface OnAnimationStoppedListener {
        void AnimationStopped();
    }

    public void releaseAnimator() {
        mInstance.mContext = null;
        mInstance = null;
    }
}