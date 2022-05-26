package cn.han.workdemo;


import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * @Author han_s
 * @Date 2022/4/21 10:53
 * @ProName cp-parent
 */
@Service
public class CyxClient {
    private static final Logger logger = LoggerFactory.getLogger(CyxClient.class);
    private String rootPath;
    private String successPath;
    private String failPath;
    private String movePath;
    private String separator = File.separator;
    private boolean readFlag = true;
    private File moveFile;
    private File successFile;
    private File failFile;
    private SimpleDateFormat sdf;
    private SimpleDateFormat sdfYear;
    private CyxSystem cyxSystem;
    private CyxMetaFilm cyxMetaFilm;
    private CyxFileImage cyxFileImage;
    private CyxFileMainMedia cyxFileMainMedia;

    public void startMethod() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            rootPath = "/data/caiyixin/ugc-guangyingshijie";
            rootPath = "D:\\huashu";
        //成功目录
        successPath = rootPath + separator + sdf.format(new Date()) + "-BAKUP";
        successFile = new File(successPath);
        //失败目录
        failPath = rootPath + separator + sdf.format(new Date()) + "-ERROR";
        failFile = new File(failPath);

        File directory = new File(rootPath);
        if (directory.exists()&&directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String name = fileName.substring(fileName.lastIndexOf(".") + 1);
                    if ("imp".equals(name)) {
                        //读取文件
                        readFileImd(file);
                        if (readFlag) {
                            //读取成功
                            if (cyxSystem!=null){
                                System.out.println("读取结束"+cyxSystem.toString());
                            }
                            if (cyxMetaFilm!=null){
                                System.out.println("读取结束"+cyxMetaFilm.toString());
                            }
                            if (cyxFileMainMedia!=null) {
                                System.out.println("读取结束" + cyxFileMainMedia.toString());
                            }
                            if (cyxFileImage!=null){
                                System.out.println("读取结束"+cyxFileImage.toString());
                            }
                            System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
                            if (!successFile.exists()) {
                                successFile.mkdir();
                            }
                            movePath = successPath +separator+ file.getName() + ".bakup";
                            moveFile = new File(movePath);
                            boolean renameTo = file.renameTo(moveFile);
                            if (renameTo) {
                                moveFile = null;
                            }

                        } else {//读取失败
                            if (!failFile.exists()) {
                                failFile.mkdir();
                            }
                            movePath = failPath + separator+file.getName() + ".error";
                            moveFile = new File(movePath);
                            boolean renameTo = file.renameTo(moveFile);
                            if (renameTo) {
                                moveFile = null;
                            }
                        }
                    }
                }
            }
        }
    }

    public void readFileImd(File file) {
        SAXReader reader = new SAXReader();
        InputStream stream = null;
        InputStreamReader streamReader=null;
        try {
            stream = new FileInputStream(file);
            streamReader = new InputStreamReader(stream, "gbk");
            Document dc= reader.read(streamReader);
            Element rootElement = dc.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                //Asset层
                Element elementOne = (Element) iterator.next();
                Iterator elementIterator1 = elementOne.elementIterator();
                while (elementIterator1.hasNext()) {
                    //三个主要部分
                    Element smfElement = (Element) elementIterator1.next();
                    assembleEntities(smfElement,file);
                }
            }
        } catch (Exception e) {
            logger.info(file.getName(),e);
            readFlag = false;
        }finally {
            if (streamReader!=null){
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean assembleEntities(Element smfElement, File file) {
        if (!smfElement.getName().isEmpty()) {
            if ("System".equals(smfElement.getName())) {
                cyxSystem = new CyxSystem();
                Iterator iteratorProperty = smfElement.elementIterator();
                while (iteratorProperty.hasNext()) {
                    Element elementProperty = (Element) iteratorProperty.next();
                    try {
                        systemAssemble(elementProperty);
                    } catch (ParseException e) {
                        logger.error("日期格式解析失败：", e);
                    }
                }

            } else if ("Meta".equals(smfElement.getName())) {
                cyxMetaFilm = new CyxMetaFilm();
                Iterator iteratorProperty = smfElement.elementIterator();
                while (iteratorProperty.hasNext()) {
                    Element elementProperty = (Element) iteratorProperty.next();
                    try {
                        metaFilmAssemble(elementProperty);
                    } catch (ParseException e) {
                        logger.error("日期格式解析失败：", e);
                    }
                }
            } else if ("File".equals(smfElement.getName())) {
                Iterator iteratorFile = smfElement.elementIterator();
                while (iteratorFile.hasNext()) {
                    Element fileElement = (Element) iteratorFile.next();
                    if ("Image".equals(fileElement.getName())) {
                        cyxFileImage = new CyxFileImage();
                        cyxFileImage.setIndex(Integer.valueOf(fileElement.attributeValue("index")));
                        Iterator iteratorImage = fileElement.elementIterator();
                        while (iteratorImage.hasNext()) {
                            Element elementProperty = (Element) iteratorImage.next();
                            fileImageAssemble(elementProperty);
                        }
                    } else if ("Media".equals(fileElement.getName())) {
                        cyxFileMainMedia = new CyxFileMainMedia();
                        cyxFileMainMedia.setIndex(Integer.valueOf(fileElement.attributeValue("index")));
                        Iterator iteratorMedia = fileElement.elementIterator();
                        while (iteratorMedia.hasNext()) {
                            Element elementProperty = (Element) iteratorMedia.next();
                            fileMediaAssemble(elementProperty);
                        }
                    }
                }
            } else {
                logger.error("文件：file={},内容为null", file);
                logger.info("文件：file={},内容为null", file);
                return false;
            }

        }
        return false;
    }
    private void fileMediaAssemble(Element elementProperty) {
        if (!elementProperty.getName().isEmpty()) {
            if ("Type".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setType(elementProperty.getText());
            } else if ("Location".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setLocation(elementProperty.getText());
            } else if ("DRMType".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setDRMType(elementProperty.getText());
            } else if ("BiteRate".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setBiteRate(elementProperty.getText());
            } else if ("mediano".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setMediano(elementProperty.getText());
            } else if ("locationtype".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setLocationtype(Integer.valueOf(elementProperty.getText()));
            } else if ("caption".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setCaption(elementProperty.getText());
            } else if ("audio".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setAudio(elementProperty.getText());
            } else if ("imageAspect".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setImageAspect(elementProperty.getText());
            } else if ("medium".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setMedium(Integer.valueOf(elementProperty.getText()));
            } else if ("ratio".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setRatio(Integer.valueOf(elementProperty.getText()));
            } else if ("fileMD5".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setFileMD5(elementProperty.getText());
            } else if ("description".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setDescription(elementProperty.getText());
            } else if ("playlength".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setPlaylength(elementProperty.getText());
            } else if ("drmkey".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setDrmkey(elementProperty.getText());
            } else if ("format".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setFormat(elementProperty.getText());
            } else if ("orgfilesize".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setOrgfilesize(elementProperty.getText());
            } else if ("director".equals(elementProperty.attributeValue("name"))) {
                cyxFileMainMedia.setDirector(elementProperty.getText());
            } else {
                logger.info("name={},text={}：没有对应实体类属性", elementProperty.attributeValue("name"), elementProperty.getText());
            }
        }
    }

    private void fileImageAssemble(Element elementProperty) {
        if (!elementProperty.getName().isEmpty()) {
            if ("Type".equals(elementProperty.attributeValue("name"))) {
                cyxFileImage.setType(elementProperty.getText());
            } else if ("Location".equals(elementProperty.attributeValue("name"))) {
                cyxFileImage.setLocation(elementProperty.getText());
            } else if ("mediano".equals(elementProperty.attributeValue("name"))) {
                cyxFileImage.setMediano(elementProperty.getText());
            } else if ("locationtype".equals(elementProperty.attributeValue("name"))) {
                cyxFileImage.setLocationtype(Integer.valueOf(elementProperty.getText()));
            } else if ("description".equals(elementProperty.attributeValue("name"))) {
                cyxFileImage.setDescription(elementProperty.getText());
            } else if ("fileindex".equals(elementProperty.attributeValue("name"))) {
                cyxFileImage.setFileindex(Long.valueOf(elementProperty.getText()));
            } else if ("orgfilesize".equals(elementProperty.attributeValue("name"))) {
                cyxFileImage.setOrgfilesize(elementProperty.getText());
            } else {
                logger.info("name={},text={}：没有对应实体类属性", elementProperty.attributeValue("name"), elementProperty.getText());
            }
        }
    }

    private void metaFilmAssemble(Element elementProperty) throws ParseException {
        if (!elementProperty.getName().isEmpty()) {
            sdfYear = new SimpleDateFormat("yyyy");
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            if ("company".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setCompany(elementProperty.getText());
            } else if ("agent".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setAgent(elementProperty.getText());
            } else if ("region".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setRegion(elementProperty.getText());
            } else if ("language".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setLanguage(elementProperty.getText());
            } else if ("format".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setFormat(Integer.valueOf(elementProperty.getText()));
            } else if ("year".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setYear(sdfYear.parse(elementProperty.getText().trim()));
            } else if ("director".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setDirector(elementProperty.getText());
            } else if ("actor".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setActor(elementProperty.getText());
            } else if ("actress".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setActress(elementProperty.getText());
            } else if ("coactress".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setCoactress(elementProperty.getText());
            } else if ("coactor".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setCoactor(elementProperty.getText());
            } else if ("superviser".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setSuperviser(elementProperty.getText());
            } else if ("background".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setBackground(elementProperty.getText());
            } else if ("description".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setDescription(elementProperty.getText());
            } else if ("englishdesc".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setEnglishdesc(elementProperty.getText());
            } else if ("iptvdesc".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setIptvdesc(elementProperty.getText());
            } else if ("viewpoint".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setViewpoint(elementProperty.getText());
            } else if ("mediaformat".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setMediaformat(elementProperty.getText());
            } else if ("keyword".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setKeyword(elementProperty.getText());
            } else if ("starlevel".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setStarlevel(elementProperty.getText());
            } else if ("rating".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setRating(elementProperty.getText());
            } else if ("length".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setLength(elementProperty.getText());
            } else if ("awards".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setAwards(elementProperty.getText());
            } else if ("englishname".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setEnglishname(elementProperty.getText());
            } else if ("name".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setName(elementProperty.getText());
            } else if ("category".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setCategory(elementProperty.getText());
            } else if ("cpcode".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setCpcode(elementProperty.getText());
            } else if ("licensetype".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setLicensetype(Integer.valueOf(elementProperty.getText()));
            } else if ("licensevaliddate".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setLicensevaliddate(sdf.parse(elementProperty.getText().trim()));
            } else if ("licenseexpiredate".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setLicenseexpiredate(sdf.parse(elementProperty.getText().trim()));
            } else if ("externalno".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setExternalno(elementProperty.getText());
            } else if ("externalurl".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setExternalurl(elementProperty.getText());
            } else if ("reserve1".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setReserve1(elementProperty.getText());
            } else if ("reserve2".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setReserve2(elementProperty.getText());
            } else if ("comments".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setComments(elementProperty.getText());
            } else if ("duber".equals(elementProperty.attributeValue("name"))) {
                cyxMetaFilm.setDuber(elementProperty.getText());
            } else {
                logger.info("name={},text={}：没有对应实体类属性", elementProperty.attributeValue("name"), elementProperty.getText());
            }
        }
    }
    private void systemAssemble(Element elementProperty) throws ParseException {
        if (!elementProperty.getName().isEmpty()){
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if ("assetid".equals(elementProperty.attributeValue("name"))) {
                cyxSystem.setAssetid(Long.valueOf(elementProperty.getText()));

            }else if ("name".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setName(elementProperty.getText());

            }else if("code".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setCode(elementProperty.getText());

            }else if("assetType".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setAssetType(elementProperty.getText());

            }else if("contentbankid".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setContentbankid(Long.valueOf(elementProperty.getText()));

            }else if("folderid".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setFolderid(Long.valueOf(elementProperty.getText()));

            }else if("spCode".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setSpCode(elementProperty.getText());

            }else if("cpcode".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setCpcode(elementProperty.getText());

            }else if("folderCode".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setFolderCode(elementProperty.getText());

            }else if("folderName".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setFolderName(elementProperty.getText());

            }else if("createtime".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setCreatetime(sdf.parse(elementProperty.getText().trim()));

            }else if("priority".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setPriority(Integer.valueOf(elementProperty.getText()));

            }else if("assetClass".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setAssetClass(Integer.valueOf(elementProperty.getText()));

            }else if("price".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setPrice(Double.valueOf(elementProperty.getText()));

            }else if("actType".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setActType(Integer.valueOf(elementProperty.getText()));

            }else if("searchCode".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setSearchCode(elementProperty.getText());

            }else if("demandChargeType".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setDemandChargeType(Integer.valueOf(elementProperty.getText()));

            }else if("firstGradeFolderType".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setFirstGradeFolderType(elementProperty.getText());
            }else if("secondGradeFolderType".equals(elementProperty.attributeValue("name"))){
                cyxSystem.setSecondGradeFolderType(elementProperty.getText());
            }else {
                logger.info("name={},text={}：没有对应实体类属性",elementProperty.attributeValue("name"),elementProperty.getText());
            }
        }
    }

}
