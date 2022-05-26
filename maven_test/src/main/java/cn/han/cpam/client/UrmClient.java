package cn.han.cpam.client;

import cn.han.cpam.util.UrmConfig;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.URL;
import java.util.*;

public class UrmClient implements Serializable{
	private static final long serialVersionUID = -4812035180494801204L;
	private static String appCode;
	private static String urmUrl;
	private static boolean isLocalUrm=false;
	
	private User user;
	private HttpSession session;
	private Set<String> unAuthActions = new HashSet<String>();
	private Map<String, Resource> resourceCodeMap = new HashMap<String, Resource>();
	private Map<String, String> paramMap = new HashMap<String, String>();
	private List<Resource> resourceTree = new ArrayList<Resource>();
	
	private static final String URM_SESSION = "urmSession";
	
	public UrmClient(HttpSession session){
		this.session = session;
		this.session.setAttribute(URM_SESSION, this);
	}
	
	public static cn.han.cpam.client.UrmClient get(HttpSession session){
		Object client = session.getAttribute(URM_SESSION);
		return client == null ? null : (cn.han.cpam.client.UrmClient)client;
	}
	
	/**
	 * 验证旧密码
	 */
	public String validationPassWord(String loginName, String password) throws Exception{
		URL url;
		if (isLocalUrm)
		{
			 url = new URL(urmUrl + "/user_validationPassWord.action?loginName=" + loginName + "&password=" + password);
		} else
		{
		 url = new URL(urmUrl + "/user_validationPassWord?loginName=" + loginName + "&password=" + password);
		}
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(new InputStreamReader(url.openStream(),"UTF-8"), sw);
		LoginResponse resp = new Gson().fromJson(sw.toString(), LoginResponse.class);
		if(!"0".equals(resp.getRet())){
			throw new UrmException(resp.getRet(), resp.getRetInfo());
		}
		return resp.getRet();
	}
	
	/**
	 * 修改密码
	 */
	public String editPassword(String loginName, String password) throws Exception{
		URL url;
		if (isLocalUrm)
		{
			 url = new URL(urmUrl + "/user_editPassword.action?loginName=" + loginName + "&password=" + password);
		} else
		{
			 url = new URL(urmUrl + "/user_editPassword?loginName=" + loginName + "&password=" + password);
		}
		StringWriter sw = new StringWriter();
		IOUtils.copy(new InputStreamReader(url.openStream(),"UTF-8"), sw);
		LoginResponse resp = new Gson().fromJson(sw.toString(), LoginResponse.class);
		if(!"0".equals(resp.getRet())){
			throw new UrmException(resp.getRet(), resp.getRetInfo());
		}
		return resp.getRet();
	}
	
	/**
	 * 登录
	 */
	@SuppressWarnings("unchecked")
	public User login(String name, String password) throws Exception{
		URL url;
		if (isLocalUrm)
		{
			 url = new URL(urmUrl + "/user/login.do?appCode=" + appCode + "&loginName=" + name + "&password=" + password);
		} else
		{
			 url = new URL(urmUrl + "/user/login?appCode=" + appCode + "&loginName=" + name + "&password=" + password);
		}
	
		StringWriter sw = new StringWriter();
		IOUtils.copy(new InputStreamReader(url.openStream(), "UTF-8"), sw);
		LoginResponse resp = new Gson().fromJson(sw.toString(), LoginResponse.class);
		if (!"0".equals(resp.getRet())) {
			throw new UrmException(resp.getRet(), resp.getRetInfo());
		}
		user = resp.getResult();
		
		unAuthActions.clear();
		resourceCodeMap.clear();
		Map<String, Resource> resourceIdMap = new HashMap<String, Resource>();
		this.session.removeAttribute("hasAduit");
		
		for(Resource resource : this.user.getResources()){
						
			if ("relaAduit".equals(resource.getCode()) && resource.getAuth()==1) {
				
				this.session.setAttribute("hasAduit", "has");
			}
			
			resourceCodeMap.put(resource.getCode(), resource);
			resourceIdMap.put(resource.getId().toString(), resource);
			if(!StringUtils.isBlank(resource.getAction()) && resource.getAuth() == 0){				
				unAuthActions.add(resource.getAction());
			}
		}
		
		Collections.sort(this.user.getResources(), new BeanComparator("rank"));
		resourceTree.clear();
		for(Resource resource : this.user.getResources()){
			if (StringUtils.isBlank(resource.getParentId().toString())) {
				resourceTree.add(resource);
			}else{
				Resource parent = resourceIdMap.get(resource.getParentId());
				if(parent != null){
					parent.getSubResources().add(resource);
				}
			}
		}
		
		paramMap.clear();
		
		return user;
	}
	
	public String createAuthScript(){
		return createAuthScript("id");
	}
	
	public String createAuthScript(String name){
		cn.han.cpam.model.urm.User user = (cn.han.cpam.model.urm.User) session.getAttribute("user");
		//User use = (User) session.getAttribute("user");
		String script = "<script>\r\n$(function(){\r\n";
		if(user == null || user.getResources() == null){
			return "";
		}
		for(cn.han.cpam.model.urm.Resource resource : user.getResources()){
			if(resource.getAuth()==null||resource.getAuth() .equals(Long.valueOf(0))){
				script += "$(\"[" + name + "='" + resource.getCode() + "']\").remove();\r\n";
			}else if(!StringUtils.isBlank(resource.getAction())){			
				script += "$(\"[" + name + "='" + resource.getCode() + "']\").attr('action','" + resource.getAction() + "');\r\n";
			}
		}
		return script+"});\r\n</script>\r\n";
	}

	public boolean isOneAppManager(){
		return UrmConfig.isOneApp() && user.isManager();
	}
	
	public boolean hasAduitRoot(){
		
		if("has".equals(this.session.getAttribute("hasAduit"))){
			
			return true;
		}
		return false;
	}
	
	public Set<String> getUnAuthActions() {
		return unAuthActions;
	}

	public boolean isUnAuth(String url) {
		for(String action : unAuthActions){			
			if(url.endsWith(action)){
				return true;
			}
		}
		return false;
	}
	
	public static void setAppCode(String appCode) {
		cn.han.cpam.client.UrmClient.appCode = appCode;
	}

	public static void setUrmUrl(String urmUrl) {
		cn.han.cpam.client.UrmClient.urmUrl = urmUrl;
	}

	public static boolean isLocalUrm()
	{
		return isLocalUrm;
	}

	public static void setLocalUrm(boolean isLocalUrm)
	{
		cn.han.cpam.client.UrmClient.isLocalUrm = isLocalUrm;
	}
	public User getUser() {
		return user;
	}
	
	public Resource getResource(String code) {
		return resourceCodeMap.get(code);
	}

	public List<Resource> getResourceTree() {
		return resourceTree;
	}

	public String getParam(String name) {
		return paramMap.get(name);
	}

}