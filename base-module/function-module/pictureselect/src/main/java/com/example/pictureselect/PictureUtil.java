package com.example.pictureselect;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class PictureUtil {

   // .selectionMedia(selectList)// 手写是否传入已选图片

    private CustomPictureSelectionModel pictureSelectionModel;

    public PictureUtil(CustomPictureSelectionModel pictureSelectionModel){
        this.pictureSelectionModel=pictureSelectionModel;
    }

    public enum Type{
        CAMER,GALLERY
    }

    /**
     *
     * @param themeStyleId 主题样式
     * @return
     */
    public PictureUtil setThemeStyle(@StyleRes int themeStyleId){
        pictureSelectionModel.theme(themeStyleId);
        return this;
    }

    /**
     * @param activity 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
     * @param mimeType
     * @return
     * type CAMER开启相机 GA开启相册
     */
    public static PictureUtil creatOpenGallery(Activity activity,int mimeType,Type type){
        switch (type){
            case CAMER:

                return new PictureUtil(create(activity).openCamera(mimeType));
            case GALLERY:

                return new PictureUtil(create(activity).openGallery(mimeType));
        }
        return null;
    }
    public static PictureUtil creatOpenGallery(Fragment fragment,int mimeType,Type type){
        switch (type){
            case CAMER:

                return new PictureUtil(create(fragment).openCamera(mimeType));
            case GALLERY:

                return new PictureUtil(create(fragment).openGallery(mimeType));
        }
        return null;
    }

    /**
     * @param max 最大选择
     * @param min 最小选择
     * @param spacecount 每行显示条目
     * @return
     */
    public PictureUtil setSelectNum(int max,int min,int spacecount){
        pictureSelectionModel.maxSelectNum(max).minSelectNum(min).imageSpanCount(spacecount);
        return this;
    }

    /**
     * @param modeType 选择模式
     *                  多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
     * @return
     */
    public PictureUtil setSelectMode(int modeType){

        pictureSelectionModel.selectionMode(modeType);
        return this;
    }

    /**
     * @param ispImage 是否可预览图片 true or false
     * @param isPVideo 是否可预览视频 true or false
     * @param isPAudio 是否可播放音频 true or false
     * @param previewEggs 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
     * @return
     */
    public PictureUtil previewSet(boolean ispImage,boolean isPVideo,boolean isPAudio,boolean previewEggs){
        pictureSelectionModel.previewImage(ispImage).previewVideo(isPVideo).enablePreviewAudio(isPAudio).previewEggs(previewEggs);
        return this;
    }


    /**
     *
     * @param iscamer 是否显示拍照按钮 true or false
     * @param isGif   是否显示gif图片 true or false
     * @param isOpenClickSound 是否开启点击声音 true or false
     * @param videoMaxSecond 显示多少秒以内的视频or音频
     * @param videoMinSecond 显示多少秒以内的视频or音频
     * @return
     */
    public PictureUtil gallerySet(boolean iscamer,boolean isGif,boolean isOpenClickSound,int videoMaxSecond,int videoMinSecond){
        pictureSelectionModel.isCamera(iscamer).isGif(isGif).openClickSound(isOpenClickSound).videoMaxSecond(videoMaxSecond).videoMinSecond(videoMinSecond);
        return this;
    }


    /**
     * @param enableCrop 是否裁剪 true or false
     * @param withAspectRatioX 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
     * @param withAspectRatioX 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
     * @param freeStyleCropEnabled 裁剪框是否可拖拽 true or false
     * @param circleDimmedLayer 是否圆形裁剪 true or false
     * @param showCropFrame 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
     * @param showCropGrid  是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
     * @param cropCompressQuality 裁剪压缩质量 默认90
     * @param cropW 裁剪宽高比，设置如果大于图片本身宽高则无效
     * @param cropW 裁剪宽高比，设置如果大于图片本身宽高则无效
     * @param rotateEnabled 裁剪是否可旋转图片
     * @param scaleEnabled  裁剪是否可放大缩小图片
     * @param hideBottomControls 是否显示uCrop工具栏，默认不显示 true or false
     * @return
     */
    public PictureUtil cropSet(boolean enableCrop,int withAspectRatioX,int withAspectRatioY,boolean freeStyleCropEnabled,boolean circleDimmedLayer,boolean showCropFrame,
                               boolean showCropGrid,int cropCompressQuality,int cropW,int cropH,boolean rotateEnabled,boolean scaleEnabled,boolean hideBottomControls){
        pictureSelectionModel.enableCrop(enableCrop).withAspectRatio(withAspectRatioX,withAspectRatioY).freeStyleCropEnabled(freeStyleCropEnabled)
                .circleDimmedLayer(circleDimmedLayer).showCropFrame(showCropFrame).showCropGrid(showCropGrid).cropCompressQuality(cropCompressQuality)
                .cropWH(cropW,cropH).rotateEnabled(rotateEnabled).scaleEnabled(scaleEnabled).hideBottomControls(hideBottomControls);
        return this;
    }

    /**
     *
     * @param compress 是否压缩 true or false
     * @param compressSavePath 压缩图片保存地址
     * @param minimumCompressSize 小于多少kb的图片不压缩
     * @param synOrAsy 同步true或异步false 压缩 默认同步
     * @return
     */
    public PictureUtil compressSet(boolean compress,String compressSavePath,int minimumCompressSize,boolean synOrAsy){
        pictureSelectionModel.compress(compress).compressSavePath(compressSavePath).minimumCompressSize(minimumCompressSize).synOrAsy(synOrAsy);
        return this;
    }

    /**
     *
     * @param videoQuality 视频录制质量 0 or 1
     * @param recordVideoSecond 视频秒数录制 默认60s
     * @return
     */
    public PictureUtil videoSet(int videoQuality,int recordVideoSecond){
        pictureSelectionModel.videoQuality(videoQuality).recordVideoSecond(recordVideoSecond);
        return this;
    }

    /**
     * @param code 回调状态码 最后必须调
     * @return
     */
    public PictureUtil forResult(int code){
        pictureSelectionModel.forResult(code);
        return this;
    }

    /**
     * @param code 回调状态码 最后必须调
     * @return
     */
    public PictureUtil imageFormat(String suffixType){
        pictureSelectionModel.imageFormat(suffixType);
        return this;
    }


    public static CustomPictureSelector create(Activity activity) {
        return  CustomPictureSelector.create(activity);
    }
    public static CustomPictureSelector create(Fragment fragment) {
        return  CustomPictureSelector.create(fragment);
    }

    public static void deleteCache(final Context context, Activity activity) {
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(activity);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(context);
                } else {
                    Toast.makeText(context,
                            context.getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     *
     * @param activity
     * @param themeStyleId
     * @param position
     * @param path
     * @param list
     * @param listener
     */
    public static void preView(Activity activity, @StyleRes int themeStyleId, int position, String path, List list,
                               PictureExternalPreviewCustomActivity.OnImageLongClickListener listener){

        create(activity).themeStyle(themeStyleId).setImageListener(listener).openExternalPreview(position,path,list);
    }
    public static void preView(Fragment fragment, @StyleRes int themeStyleId, int position, String path, List list,
                               PictureExternalPreviewCustomActivity.OnImageLongClickListener listener){

        create(fragment).themeStyle(themeStyleId).setImageListener(listener).openExternalPreview(position,path,list);
    }
}
