package cn.han.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.*;


public class FtpUtil {   
    private String server;   

    private int port;
    
    private String username;   
  
    private String password;   
  
    private FTPClient ftp;   
  
    private boolean binaryTransfer = false;   
  
    private final static Logger log = Logger.getLogger(FtpUtil.class);
  
    /**  
     * @param server  
     *            ftp服务器地址  
     * @param username  
     *            ftp服务器登陆用户  
     * @param password  
     *            ftp用户密码  
     */  
    public FtpUtil(String server, int port , String username, String password) {
  
        this.server = server;   
        this.port = port;
        this.username = username;   
        this.password = password;   
        ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");
       // ftp.setControlEncoding("iso-8859-1");
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX); 
        conf.setServerLanguageCode("zh");
        ftp.configure(conf);
        /*  
         * if(Configuration.PrintFTPCommandLog){ //打印FTP命令  
         * ftp.addProtocolCommandListener(new PrintCommandListener()); }  
         */  
    }   
   
  
    public boolean connect() {   
        try {   
            int reply;   
            ftp.connect(server, port);  
            // 连接后检测返回码来校验连接是否成功   
            reply = ftp.getReplyCode();   
  
            if (FTPReply.isPositiveCompletion(reply)) {   
                if (ftp.login(username, password)) {   
                    // 设置为passive模式   
                    ftp.enterLocalPassiveMode();   
                    return true;   
                }   
            } else {   
                ftp.disconnect();   
                log.error("FTP server refused connection.");   
            }   
        } catch (IOException e) {   
            if (ftp.isConnected()) {   
                try {   
                    ftp.disconnect();   
                } catch (IOException f) {   
                }   
            }   
            log.error("Could not connect to server.", e);   
        }   
        return false;   
    }   
  
    /**  
     * 下载一个文件到默认的本地路径中  
     *
     *            文件名称(不含路径)  
     * @param delFile  
     *            成功后是否删除该文件  
     * @return  
     * @throws Exception 
     */  
    public boolean get(String remoteFileName,String localFileName,boolean delFile) throws Exception { 
        return getFile(remoteFileName, localFileName, delFile);   
    }   
  
    
    /**  
     * 上传一个文件到默认的远程路径中  
     *
     *            文件名称(不含路径)  
     * @param delFile  
     *            成功后是否删除该文件  
     * @return  
     */  
    public boolean put(String remoteFileName,String localFileName, boolean delFile) {   
//        String remote = PropertyManager.getProperty("RemotePutPath") + fileName;   
//        String local = PropertyManager.getProperty("LocalPutPath")+ fileName;   
          return putFile(remoteFileName, localFileName, delFile);   
    }   
 
  
    /**  
     * 上传一个文件到远程指定文件  
     *   
     * @param remoteAbsoluteFile  
     *            远程文件名(包括完整路径)  
     * @param content  
     *            文件内容
     * @param encode  
     *            文件编码      
     */ 
    public boolean putContent(String remoteAbsoluteFile, String content, String encode) {     	
    	log.info("=====>远程文件:"+remoteAbsoluteFile);
    	remoteAbsoluteFile = remoteAbsoluteFile.replace("\\", "/");
        String remoteDirectory=remoteAbsoluteFile.substring(0,remoteAbsoluteFile.lastIndexOf("/")); //远程目录
        String remoteFileName = remoteAbsoluteFile.substring(remoteAbsoluteFile.lastIndexOf("/")+1,remoteAbsoluteFile.length()); //远程文件名
        InputStream input = null;
        try {
        	//设置文件传输类型  
        	 if (binaryTransfer) {   
                 ftp.setFileType(FTPClient.BINARY_FILE_TYPE);   
             } else {   
                 ftp.setFileType(FTPClient.ASCII_FILE_TYPE);   
             }
        	 if(!ftp.changeWorkingDirectory(remoteDirectory)){//目录不存在
        		 String[] dirArr = remoteDirectory.split("/");
        		 if(dirArr != null ) {
        			 for(String dir:dirArr){
        				 if(dir != null && !"".equals(dir) && !ftp.changeWorkingDirectory(dir)){
        					 if(!ftp.makeDirectory(dir)){
            					 log.info("=====>创建文件夹失败:"+dir + "||" + remoteDirectory);
                    			 return false;
            				 }
        					 else {
        						 ftp.changeWorkingDirectory(dir);
        					 }
        				 } 
        			 }
        		 }
             }
        	 //ftp.changeWorkingDirectory(remoteDirectory);

        	// 处理传输   
            input = new ByteArrayInputStream(content.getBytes(encode)); 
            ftp.storeFile(remoteFileName, input); 
    		log.info("put 文件内容" + content);  
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	} finally {   
            try {   
                if (input != null) {   
                    input.close();   
                } 
                this.disconnect();
            } catch (Exception e2) {  
            	e2.printStackTrace();
            }   
        }  
    }
    /**  
     * 上传一个本地文件到远程指定文件  
     *   
     * @param remoteAbsoluteFile  
     *            远程文件名(包括完整路径)  
     * @param localAbsoluteFile  
     *            本地文件名(包括完整路径)  
     * @return 成功时，返回true，失败返回false  
     */  
    public boolean putFile(String remoteAbsoluteFile, String localAbsoluteFile,   
            boolean delFile) {   
        InputStream input = null;
        log.info("=====>远程文件:"+remoteAbsoluteFile+",本地文件:"+localAbsoluteFile);
        try {   
            // //设置文件传输类型   
            if (binaryTransfer) {   
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);   
            } else {   
                ftp.setFileType(FTPClient.ASCII_FILE_TYPE);   
            }   
            //判断远程目录是否存在
            remoteAbsoluteFile = remoteAbsoluteFile.replace("\\", "/");
            String remoteDirectory=remoteAbsoluteFile.substring(0,remoteAbsoluteFile.lastIndexOf("/")+1); //远程目录
            if(!ftp.changeWorkingDirectory(remoteDirectory)){//目录不存在
              log.error("====>远程目录:"+remoteDirectory+"不存在");
              return false;
            }
            // 处理传输   
            input = new FileInputStream(localAbsoluteFile);   
            ftp.storeFile(remoteAbsoluteFile, input);   
            log.info("put " + localAbsoluteFile);   
            input.close();   
            if (delFile) {   
                (new File(localAbsoluteFile)).delete(); //删除本地文件 
                log.info("delete " + localAbsoluteFile); 
            }  
           return true;   
        } catch (FileNotFoundException e) {        	
            log.error("local file not found.", e);   
        } catch (IOException e1) {   
            log.error("Could not put file to server.", e1);   
        } finally {   
            try {   
                if (input != null) {   
                    input.close();   
                }   
            } catch (Exception e2) {  
              log.error("cause exception.", e2);
            }   
        }   
  
        return false;   
    }   
  
    /**  
     * 下载一个远程文件到本地的指定文件  
     *   
     * @param remoteAbsoluteFile  
     *            远程文件名(包括完整路径)  
     * @param localAbsoluteFile  
     *            本地文件名(包括完整路径)  
     * @return 成功时，返回true，失败返回false  
     * @throws Exception 
     */  
    public boolean getFile(String remoteAbsoluteFile, String localAbsoluteFile,   
            boolean delFile) throws Exception {   
        OutputStream output = null;   
        try {   
            // 设置文件传输类型   
            if (binaryTransfer) {   
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);   
            } else {   
                ftp.setFileType(FTPClient.ASCII_FILE_TYPE);   
            }
            //将客户端设置为被动模式
            ftp.enterLocalPassiveMode();
            //判断远程目录是否存在
            remoteAbsoluteFile = remoteAbsoluteFile.replace("\\", "/");
            String remoteDirectory=remoteAbsoluteFile.substring(0,remoteAbsoluteFile.lastIndexOf("/")+1); //远程目录
            if(!ftp.changeWorkingDirectory(remoteDirectory)){//目录不存在
              //log.error("====>远程目录:"+remoteDirectory+"不存在");
              throw new Exception("远程目录:"+remoteDirectory+"不存在");
            }
            FTPFile[] listFiles = ftp.listFiles(remoteAbsoluteFile.substring(remoteAbsoluteFile.lastIndexOf("/")+1));
            if(listFiles.length<1) {
            	//log.error("====>远程文件:"+remoteAbsoluteFile.substring(remoteAbsoluteFile.lastIndexOf("/")+1)+"不存在");
                //return false;
            	throw new Exception(""+remoteAbsoluteFile.substring(remoteAbsoluteFile.lastIndexOf("/")+1)+"不存在");
            }
            // 处理传输   
            output = new FileOutputStream(localAbsoluteFile);   
            ftp.retrieveFile(remoteAbsoluteFile, output); 
            
            output.close();   
            if (delFile) { // 删除远程文件   
                ftp.deleteFile(remoteAbsoluteFile);   
            }   
            return true;   
        }finally {   
            try {   
                if (output != null) {   
                    output.close();   
                }   
            } catch (IOException e2) {   
            }   
        }   
    }   
  
    /**  
     * 列出远程目录下所有的文件  
     *   
     * @param remotePath  
     *            远程目录名  
     * @return 远程目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组  
     */  
    public String[] listNames(String remotePath) {   
        String[] fileNames = null;   
        try {   
            FTPFile[] remotefiles = ftp.listFiles(remotePath);   
            fileNames = new String[remotefiles.length];   
            for (int i = 0; i < remotefiles.length; i++) {   
                fileNames[i] = remotefiles[i].getName();   
            }   
  
        } catch (IOException e) {   
            log.error("Could not list file from server.", e);   
        }   
        return fileNames;   
    }   
    
    
    /**  
     * 读取远程文件   
     *   
     * @param remoteAbsoluteFile  
     *            远程文件名(包括完整路径)  
     *  
     */  
    public String getXML(String remoteAbsoluteFile) {   
    	InputStream ins = null; 
    	StringBuilder builder = null;
        try {   
            // 设置文件传输类型   
            if (binaryTransfer) {   
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);   
            } else {   
                ftp.setFileType(FTPClient.ASCII_FILE_TYPE);   
            }
            
            //判断远程目录是否存在
            remoteAbsoluteFile = remoteAbsoluteFile.replace("\\", "/");
            remoteAbsoluteFile = remoteAbsoluteFile.substring(1, remoteAbsoluteFile.length());
           
            String remoteDirectory = remoteAbsoluteFile.substring(0,remoteAbsoluteFile.lastIndexOf("/")+1); //远程目录
            if(!ftp.changeWorkingDirectory(remoteDirectory)){//目录不存在
              log.error("====>远程目录:"+remoteDirectory+"不存在");
              return null;
            }                  
             // 处理传输   
             ins = ftp.retrieveFileStream(remoteAbsoluteFile.substring(remoteAbsoluteFile.lastIndexOf("/")+1, remoteAbsoluteFile.length()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
             String line;
             builder = new StringBuilder(150);
             while ((line = reader.readLine()) != null) {
//              System.out.println(line);
              builder.append(line);
             }
             reader.close();
        } catch (FileNotFoundException e) {   
            log.error("local file not found.", e);   
        } catch (IOException e1) {   
            log.error("Could get file from server.", e1);   
        } finally {   
            try {   
                if (ins != null) {   
                	ins.close();   
                }   
            } catch (IOException e2) {   
            }   
        }
		return builder.toString();
    } 
    /**  
     * 断开ftp连接  
     */  
    public void disconnect() {   
        try {   
            ftp.logout();   
            if (ftp.isConnected()) {   
                ftp.disconnect();   
            }   
        } catch (IOException e) {   
            log.error("Could not disconnect from server.", e);   
        }   
    }   
  
    /**  
     * @return Returns the binaryTransfer.  
     */  
    public boolean isBinaryTransfer() {   
        return binaryTransfer;   
    }   
  
    /**  
     * @param binaryTransfer  
     *            The binaryTransfer to set.  
     */  
    public void setBinaryTransfer(boolean binaryTransfer) {   
        this.binaryTransfer = binaryTransfer;   
    }   
  
    /**
     * 判断目标文件夹是否存在
     * @param path
     * @return
     * @throws IOException 
     */
	public boolean isFtpDirExist(String path) throws IOException {
		return ftp.changeWorkingDirectory(path);
	}
	/**
	 * 判断Ftp服务器目录是否存在,若不存在创建目录
	 * @param remotePath
	 * @param dirName
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
    public void createDir(String remotePath,String dirName) throws IOException 
    {  
		String currentPath = remotePath;
		String[] paras = dirName.split("/");
		for (String para : paras) {
			if (!ftp.changeWorkingDirectory(currentPath)) {
				throw new IOException("ftp不能进入远程目录:"+currentPath);
			}
			if (!isFtpDirExist(currentPath + "/" + para)) {

				if (ftp.makeDirectory(para)) {
					log.info(currentPath + " create " + para + "success !");
				} else {
					throw new IOException("ftp mkdir fail");
				}

			}
			currentPath = currentPath + "/" + para;
		}

    } 
    
    public static void main(String[] args) {   

			//get("ftp://sitv:sitv@itvdev.wasu.cn:701/cacheFolder/201907/29/307601/Z1201907282432_poster.jpg","d:/p1.jpg");
			try {
				get("ftp://ftpuser:ftpuser123@125.210.163.11/youkusp/youkuspcbfa1156962411de83b1/youkuspcbfa1156962411de83b1HugeThumb.jpgxx","d:/p1.jpg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    } 
    public static boolean get(String ftpUrl ,String destPath) throws Exception { 
		String host = null;
		String user = null;
		String psw = null;
		int port=21;
		String path = null;
		
    	String[] ss = ftpUrl.split("/+");
		if (ss.length >= 2) {
			ss = ss[1].split("@");
			if (ss.length == 2) {
				String[] userPsw=ss[0].split(":");
				String[] hostPort = ss[1].split(":");
				if (hostPort.length == 2) {
					host=hostPort[0];
					port=Integer.parseInt(hostPort[1]);
					int indexOf = ftpUrl.lastIndexOf(String.valueOf(port));
					path = ftpUrl.substring(indexOf+String.valueOf(port).length());
				}else {
					host=hostPort[0];
					int indexOf = ftpUrl.lastIndexOf(host);
					path = ftpUrl.substring(indexOf+host.length());
				}
				if (userPsw.length == 2) {
					user=userPsw[0];
					psw=userPsw[1];
				}
			}
		}
		
		FtpUtil ftpUtil = new FtpUtil(host,port,user,psw);
		if(ftpUtil.connect()){
			boolean b = ftpUtil.get(path, destPath, false);	
			return b;
		}else {
			return false;
		}
    }
}  


