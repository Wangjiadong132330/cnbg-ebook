package com.cnbg.zs.ebook.common.file;

import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;




/**
* @author Faye.Wang
* @version 1.0
* @date 2020/11/4 18:43
* @Description
* 文件上传工具类
*/
public class FileUtils {

    /**
    *
    * @param files
    * @param filePath 文件路径
    * @param fileModel 业务模块文件夹 -以租户为单位
    * @return
    */
    public static List<FileBean>  uploadFiles(MultipartFile[] files,String filePath,String fileModel,Integer projectId){
        List<FileBean> fileMaps = new ArrayList<>();
        FileBean fileMap = null;
        for(MultipartFile file:files){
            fileMap = new FileBean();
            String originalFilename = file.getOriginalFilename();
            String suffix = StringUtils.substringAfterLast(originalFilename,".");
            String orgName = StringUtils.substringBeforeLast(originalFilename,".");
            String newFileName = orgName+"_"+ StringToolUtils.getGeneratorUUID()+"."+suffix;
            String lastFilePath = filePath + File.separator+projectId+File.separator+fileModel;
            String lastFile = lastFilePath +File.separator+ newFileName;
            fileMap.setFileOrgName(originalFilename);
            fileMap.setFileOrgNewName(newFileName);
            fileMap.setFilePath(lastFilePath); // 物理路径
            fileMaps.add(fileMap);
        try {
            //判断父目录是否存在
            File diskFile = new File(lastFilePath);
            if (!diskFile.exists()) {
                org.apache.commons.io.FileUtils.forceMkdir(diskFile);
            }
                file.transferTo(new File(lastFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileMaps;
    }

    /**
    * 把文件打成压缩包并输出到客户端浏览器中
    */
    public static void downloadZipFiles(HttpServletResponse response, List<String> srcFiles, String zipFileName) {
        try {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-msdownload"); // 不同类型的文件对应不同的MIME类型 // 重点突出
            // 对文件名进行编码处理中文问题
            zipFileName = new String(zipFileName.getBytes(), StandardCharsets.UTF_8);
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(zipFileName, "UTF-8"));

            // --设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            zipFile(srcFiles, zos);
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadZipFiles(HttpServletResponse response, String zipPath,String zipFileName) {
        try {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-msdownload"); // 不同类型的文件对应不同的MIME类型 // 重点突出
            // 对文件名进行编码处理中文问题
            zipFileName = new String(zipFileName.getBytes(), StandardCharsets.UTF_8);
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(zipFileName, "UTF-8"));

            toZip(new File(zipPath), response.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * 压缩文件
    *
    * @param filePaths 需要压缩的文件路径集合
    * @throws IOException
    */
    private static void zipFile(List<String> filePaths, ZipOutputStream zos) {
        //设置读取数据缓存大小
        byte[] buffer = new byte[4096];
        try {
            //循环读取文件路径集合，获取每一个文件的路径
            for (String filePath : filePaths) {
                File inputFile = new File(filePath);
                //判断文件是否存在
                if (inputFile.exists()) {
                    //判断是否属于文件，还是文件夹
                    if (inputFile.isFile()) {
                    //创建输入流读取文件
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                    //将文件写入zip内，即将文件进行打包
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));
                    //写入文件的方法，同上
                    int size = 0;
                    //设置读取数据缓存大小
                    while ((size = bis.read(buffer)) > 0) {
                        zos.write(buffer, 0, size);
                    }
                    //关闭输入输出流
                    zos.closeEntry();
                    bis.close();
                } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                    File[] files = inputFile.listFiles();
                    List<String> filePathsTem = new ArrayList<String>();
                    for (File fileTem : files) {
                            filePathsTem.add(fileTem.toString());
                    }
                    zipFile(filePathsTem, zos);
                    }
                }
            }
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void toZip(File sourceFile, OutputStream out, boolean KeepDirStructure)
                throws RuntimeException{
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                boolean KeepDirStructure) throws Exception{
        byte[] buf = new byte[4096];
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
                File[] listFiles = sourceFile.listFiles();
                if(listFiles == null || listFiles.length == 0){
                    // 需要保留原来的文件结构时,需要对空文件夹进行处理
                    if(KeepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            }else {
                for (File file : listFiles) {
                // 判断是否需要保留原来的文件结构
                if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),KeepDirStructure);
                    }
                }
            }
        }
    }
        /**
        * 删除文件
        * @param filePath
        */
        public static void deleteFile(String filePath){
            try {
                org.apache.commons.io.FileUtils.forceDeleteOnExit(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
        * 拷贝文件到指定目录下 并重命名
        * @param file
        * @param targetPath
        */
        public static  void copyFileToTargetPath(String file,String targetPath,String newFileName){
        try {
            //判断父目录是否存在
            File diskFile = new File(targetPath);
            if (!diskFile.exists()) {
                org.apache.commons.io.FileUtils.forceMkdir(diskFile);
            }

        org.apache.commons.io.FileUtils.copyFileToDirectory(new File(file),new File(targetPath));
        String fileName = StringUtils.substringAfterLast(file,File.separator);
            if(StringUtils.isNotBlank(newFileName)){
                File oldFile = new File(targetPath+File.separator+fileName);
                File newFile = new File(targetPath+File.separator+newFileName);
                oldFile.renameTo(newFile);
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
