package com.yuerhuixue.utils;

import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件名相关操作工具类
 */
public class FileManage {

    /**
     * 检测图片后缀是否为jpg,jpeg,png
     * @param imgName 图片名
     * @return 如果是返回true,不是返回false
     */
    public static Boolean checkImg(String imgName){
        String imgSuffix = imgName.substring(imgName.lastIndexOf(".") + 1);
        if (imgSuffix.equalsIgnoreCase("jpg")||
                imgSuffix.equalsIgnoreCase("jpeg")||
                imgSuffix.equalsIgnoreCase("png")){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 检测视频后缀是否为mp4,mkv,mov,avi
     * @param videoName 视频名
     * @return 如果是返回true,不是返回false
     */
    public static Boolean checkVideo(String videoName){
        String videoSuffix = videoName.substring(videoName.lastIndexOf(".") + 1);
        if (videoSuffix.equalsIgnoreCase("mp4")||
                videoSuffix.equalsIgnoreCase("mkv")||
                videoSuffix.equalsIgnoreCase("mov")||
                videoSuffix.equalsIgnoreCase("avi")){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 使用uuid给文件更名，防止重复
     * @param fileName 文件名
     * @return 更名后字符串
     */
    public static String FileRename(String fileName){

        //生成uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");

        //拼接文件名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return uuid + suffix;
    }

    /**
     * 图片上传
     * @param img 图片
     * @param imgdir 上传目录
     * @return 执行结果
     * @throws IOException 异常
     */
    public static ResultVO imgUpload(MultipartFile img, String imgdir) throws IOException {

        //图片项目路径
        String targetPath = ClassUtils.getDefaultClassLoader().getResource(imgdir).getPath();

        //判断是否为图片格式
        String imgRealName = img.getOriginalFilename();
        if (FileManage.checkImg(imgRealName)){

            //是图片，则重命名
            String imgRename = FileManage.FileRename(imgRealName);

            //完整路径
            File ToImgpath = new File(targetPath + "/" + imgRename);
            img.transferTo(ToImgpath);

            //返回
            return new ResultVO(StatusCode.OK, "上传成功！", imgRename);
        }else {

            //格式不符合
            return new ResultVO(StatusCode.NO, "格式错误，上传失败！", null);
        }
    }

    /**
     * 视频上传
     * @param video 视频
     * @param videodir 上传目录
     * @return 执行结果
     * @throws IOException 异常
     */
    public static ResultVO videoUpload(MultipartFile video, String videodir) throws IOException {

        //视频项目路径
        String targetPath = ClassUtils.getDefaultClassLoader().getResource(videodir).getPath();

        //判断是否为视频格式
        String videoRealName = video.getOriginalFilename();
        if (FileManage.checkVideo(videoRealName)){

            //是视频，则重命名
            String videoRename = FileManage.FileRename(videoRealName);

            //完整路径
            File ToImgpath = new File(targetPath + "/" + videoRename);
            video.transferTo(ToImgpath);

            //返回
            return new ResultVO(StatusCode.OK, "上传成功！", videoRename);
        }else {

            //格式不符合
            return new ResultVO(StatusCode.NO, "格式错误，上传失败！", null);
        }
    }

    public static void fileDelete(String FileName, String fileDir){

        //文件路径
        String targetPath = ClassUtils.getDefaultClassLoader().getResource(fileDir).getPath();

        //删除文件
        File file = new File(targetPath + "/" + FileName);
        file.delete();
    }

}
