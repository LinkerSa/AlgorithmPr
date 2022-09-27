package com.cl.nv1.imgGetEXIF;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;

/**
 * 获取图片EXIF信息
 */
public class GetImgEXIF {
    public static void main(String[] args) {
        //图片路径
        File file = new File("F:\\test\\2.jpg");
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            for (Directory directory:metadata.getDirectories()) {
                for (Tag tag:directory.getTags()) {
                    System.out.format("[%s] - %s = %s",directory.getName(),tag.getTagName(),tag.getDescription());
                    System.out.println();
                }
                if (directory.hasErrors()){
                    for (String error:directory.getErrors()) {
                        System.out.format("ERROR: %s",error);
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
