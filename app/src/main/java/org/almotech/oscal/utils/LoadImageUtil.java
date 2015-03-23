package org.almotech.oscal.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.almotech.oscal.R;

/**
 * Created by Armando Shkurti on 2015-03-23.
 */
public class LoadImageUtil {

    //protected ImageLoaderConfiguration imageConfigLoader;
    public static final int MY_PROFILE_FRAGMENT = 104;
    public static final int WINE_PROFILE_ACTIVITY_1 = 105;
    public static final int WINE_PROFILE_ACTIVITY_2 = 106;
    public static final int NO_ON_LOADING_IMAGE = 107;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private LoadImageListener loadImageListener;

    DisplayImageOptions options;

    public boolean memoryManage(int item) {// sherbejne per fshirjne e memorjes cache dhe disk
        switch (item) {
            case 0:
                if(imageLoader.isInited())
                    imageLoader.clearMemoryCache();
                return true;
            case 1:
                if(imageLoader.isInited())
                    imageLoader.clearDiskCache();
                return true;
            default:
                return false;
        }
    }

    /**
     * konstruktori pret vetem kontekstin dhe do bej inicializimin e
     * settings te nevojshme per loadim imazhi, kjo eshte setting default
     * */
    public LoadImageUtil(Context mContext) {
        initLibrarySettings(mContext);
    }

    /**
     * konstruktori pret  kontekstin dhe nje int qe cakton se cfare lloj inicializimi duhet per
     * settings te nevojshme per loadim imazhi, sipas rastit qe do te duhet
     * */
    public LoadImageUtil(Context mContext, int TAG) {

        switch (TAG){// pasi ketu na duhet nje inicializim i vecante
            case MY_PROFILE_FRAGMENT:
                initMyProfileFragmentSettings(mContext);
                break;
            case WINE_PROFILE_ACTIVITY_1:// per cover image configuration
                initWineProfileSettings(mContext,TAG);
                break;
            case WINE_PROFILE_ACTIVITY_2:// per profile image configuration qe te vendosim rounded corner
                initWineProfileSettings(mContext,TAG);
                break;
            case NO_ON_LOADING_IMAGE:
                initNoOnLoadingImage(mContext);
                break;
            default:
                initLibrarySettings(mContext);
                break;
        }
    }

    public void setLoadImageListener(LoadImageListener loadImageListener) {
        this.loadImageListener = loadImageListener;
    }

    public void initNoOnLoadingImage(Context mContext) {
        options = new DisplayImageOptions.Builder()// e hoqa pjesen qe i ben reset imazhit para se ta loadoj
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .build();

        initLibrarySettings(mContext);
    }
    public void initLibrarySettings(Context mContext) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .threadPriority(Thread.NORM_PRIORITY)
                .threadPoolSize(5)// rekomandohet perdorim vlera nga 1-5
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        //.diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new WeakMemoryCache())
                        //.writeDebugLogs() // Remove for release app
                .build();

        // Initialize ImageLoader with configuration.
        //ImageLoader.getInstance().init(config);

        // Initialize ImageLoader with configuration.
        imageLoader.init(config);

        if(options == null) {
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.ic_access_time_grey600_18dp)
                    .showImageOnLoading(R.drawable.ic_access_time_grey600_18dp)
                    .showImageOnFail(R.drawable.ic_access_time_grey600_18dp)
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .build();
        }
    }

    public void initWineProfileSettings(Context mContext, int FragmentTAG) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .threadPriority(Thread.NORM_PRIORITY)
                .threadPoolSize(5)// rekomandohet perdorim vlera nga 1-5
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .memoryCache(new WeakMemoryCache())
                        //.writeDebugLogs() // Remove for release app
                .build();

        // Initialize ImageLoader with configuration.
        //ImageLoader.getInstance().init(config);

        // Initialize ImageLoader with configuration.
        imageLoader.init(config);

        if(WINE_PROFILE_ACTIVITY_1 == FragmentTAG) {
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.ic_access_time_grey600_18dp)
                    .showImageOnLoading(R.drawable.ic_access_time_grey600_18dp)
                    .showImageOnFail(R.drawable.ic_access_time_grey600_18dp)
                    .resetViewBeforeLoading(true)
                    .cacheOnDisc(true)
                    .cacheInMemory(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .build();
        }else{
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.ic_access_time_grey600_18dp)
                    .showImageOnLoading(R.drawable.ic_access_time_grey600_18dp)
                    .showImageOnFail(R.drawable.ic_access_time_grey600_18dp)
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .displayer(new RoundedBitmapDisplayer(20))
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .build();
        }
    }

    /**
     * kjo settings duhet per loadim imazhesh te grid view qe i perket profilit te userit
     * pasi ky loadim nuk sjell probleme ne scroll te grid View
     * */
    public void initMyProfileFragmentSettings(Context mContext){

        //ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_access_time_grey600_18dp)
                .showImageForEmptyUri(R.drawable.ic_access_time_grey600_18dp)
                .showImageOnFail(R.drawable.ic_access_time_grey600_18dp)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

    }

    public void loadBitmapToImageView(ImageView myImageView, String ImagePath) {

        imageLoader.displayImage(ImagePath, myImageView, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                // Log.e("onLoadingFailed : " + imageUri, "failReason: " + failReason);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                super.onLoadingCancelled(imageUri, view);
            }
        });
    }


}
