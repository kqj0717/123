package com.atguigu.jf.console.user.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.mail.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.velocity.runtime.directive.Foreach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.common.util.VerifyCodeUtil;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.LoginService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import net.sf.ehcache.util.concurrent.LongAdder;

@Controller
@RequestMapping("LoginController")
public class LoginController extends  AuthorizingRealm{
   
	private static final Logger Logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	//用户登录的密码加密
	@RequestMapping("getVerifyCode")
	public void getVerifyCode(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
	
		response.setHeader("Cache-Control", "no-cache"); //告诉所有的缓存机制是否可以缓存及哪种类型
		response.setHeader("Pragma", "no-cache"); //包括实现特定的指令，它可应用到响应链上的任何接收方
		response.setDateHeader("Expires", 0); //响应过期的日期和时间
		
		//方法里面其中type是指验证码类型定义的是数字，后面的是验证码的长度定义的是四位，最后的是需要排除的字符
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		
		session.setAttribute("verifyCode", verifyCode);
		
		//根据验证码生成图片流
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 4, true, Color.WHITE, Color.BLACK, null);
		
		response.setContentType("image/jpeg");
		ImageIO.write(bufferedImage, "jpeg", response.getOutputStream());
		
	}
	
	//根据用户名和密码获取用户的登录信息
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username,
			            @RequestParam("password") String password,
			            @RequestParam("verifyCode") String verifyCode,
			            HttpSession session,
			            RedirectAttributes redirectAttributes) throws Exception{
		
		//验证验证码,主要是从session域中获取验证码
		String currentverifyCode = (String) session.getAttribute("verifyCode");
		//判断获取的用户的验证码是否和生成的验证码一致
		if(!currentverifyCode.equals(verifyCode)){
			
			redirectAttributes.addFlashAttribute("errMsg", "验证码验证失败！");
			return "redirect:/login";
		}
		    //***********以上处理验证码
		
		//获取到当前的subject对象，代表着当前的用户信息
		Subject currentUser = SecurityUtils.getSubject();
		
		//验证是否登录
		if(!currentUser.isAuthenticated()){
			//使用前台的用户名和密码构造UsernamePasswordtoken
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
		
		try{
			//执行登录，传入的是上面包装好的token
			currentUser.login(token);
		}
		catch(AuthenticationException ae){
			
			//捕获所有的shiro认证的异常
			Logger.error("认证发生异常"+ae.getMessage());
			redirectAttributes.addFlashAttribute("errMsg", "用户名或密码错误！");
			 return "redirect:/login";
		   }
		}
		//放入用户实体到Session
		session.setAttribute("currentUser", currentUser.getPrincipal());
		return "redirect:/index";
	}
	
	
	/**
	 * 
	 * @方法名: selectFuncIdByRoleId  
	 * @功能描述: 获取所有的父节点和子节点
	 * @param map
	 * @return
	 * @作者 kqj 
	 * @日期 2016年11月29日
	 */
	//selectFuncIdByRoleId
	@RequestMapping("selectFuncId")
	@ResponseBody
	public List<SysFuncBean> selectFuncIdByRoleId(Long roleId){
		
		 Map<String, Object> map = new HashMap<>();
	     map.put("roleId", roleId);
		 List<SysFuncBean> funcIdList = loginService.selectFuncIdByRoleId(map);
		
		 List<SysFuncBean> parentList = new ArrayList<>();
			for (int i = 0; i < funcIdList.size(); i++) {
				
				SysFuncBean parent = funcIdList.get(i);
				
				//判断若是父类的菜单包含一级菜单的话，需要获取里面的子菜单
				if(parent.getFuncLevel().equals(new Short("1"))){
					
					List<SysFuncBean> childrenList = new ArrayList<>();
					
					for (int j = 0; j < funcIdList.size(); j++) {
						SysFuncBean children = funcIdList.get(j);
						
						//获取二级菜单
						if(parent.getFuncId().equals(children.getSupFuncId())){
							
							childrenList.add(children);
						}
					}
					parent.setChildren(childrenList);
					parentList.add(parent);
				}
			}
					
			return parentList;
				}

	
	/**
	 * 
	 * @方法名: getVerifyCode  
	 * @功能描述: 获取验证码方法
	 * @return
	 * @throws IOException 
	 * @作者 kqj 
	 * @日期 2016年11月25日
	 */
/*	@RequestMapping("getVerifyCode")
	public void getVerifyCode(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		
		*//**
		 *1.禁用缓冲
		 *2.生成随机的字符串，通过工具类中的generateTestCode方法
		 *3.将生成的字符串方法Session域中
		 *4.根据随机验证码生成图片流bufferedImage
		 *5.通过imageIo写入到reaponse.getOutputStream();
		 *//*
		response.setHeader("Cache-Control", "no-cache"); //告诉所有的缓存机制是否可以缓存及哪种类型
		response.setHeader("Pragma", "no-cache"); //包括实现特定的指令，它可应用到响应链上的任何接收方
		response.setDateHeader("Expires", 0); //响应过期的日期和时间
		
		//方法里面其中type是指验证码类型定义的是数字，后面的是验证码的长度定义的是四位，最后的是需要排除的字符
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		
		session.setAttribute("verifyCode", verifyCode);
		
		//根据验证码生成图片流
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 4, true, Color.WHITE, Color.BLACK, null);
		
		response.setContentType("image/jpeg");
		ImageIO.write(bufferedImage, "jpeg", response.getOutputStream());
		
	}
	
	//根据用户名和密码获取用户的登录信息
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username,
			            @RequestParam("password") String password,
			            @RequestParam("verifyCode") String verifyCode,
			            HttpSession session,
			            RedirectAttributes redirectAttributes) throws Exception{
		
		//验证验证码,主要是从session域中获取验证码
		String currentverifyCode = (String) session.getAttribute("verifyCode");
		//判断获取的用户的验证码是否和生成的验证码一致
		if(!currentverifyCode.equals(verifyCode)){
			
			redirectAttributes.addFlashAttribute("errMsg", "验证码验证失败！");
			return "redirect:/login";
		}
		
		//验证用户名和密码
		SysOp sysOp = new SysOp();
		sysOp.setLoginCode(username);
		sysOp.setLoginPasswd(password);
		
		SysOp currentUser = loginService.selectSysOpByNameAndPwd(sysOp);
		
		if(currentUser == null){
			
			redirectAttributes.addFlashAttribute("errMsg", "用户名和密码验证失败！");
			return "redirect:/login";
		}
		session.setAttribute("currentUser", currentUser);
		return "redirect:/index";
	}*/
	
	
	//根据用户的id 查询菜单信息 opId用户的账号
	@RequestMapping("getMenu")
	@ResponseBody
	public List<SysFuncBean> getMenu(HttpSession session) throws Exception{
		
		SysOp sysOp = (SysOp) session.getAttribute("currentUser");
		
		System.out.println(sysOp);
		
		Map<String, Object> map= new HashMap<String,Object>();
		
		//System.out.println(map);
		
		if(sysOp != null){
			map.put("opId", sysOp.getOpId());
		}
		//创建一个空的集合,将用户的账号传入进去，
		//map.put("opId", opId);
		
		//根据用户的账号，利用方法获取到所有的菜单列表
		List<SysFuncBean> list = loginService.selectSysFuncByOpId(map);
		
		//创建一个空的集合用来存放父类以及子类返回的所有菜单信息，进行封装返回给页面
		List<SysFuncBean> parentList = new ArrayList<>();
		
	    //遍历菜单列表的集合
		for (int i = 0; i < list.size(); i++) {
			
			//获取到父类的菜单
			SysFuncBean parent = list.get(i);
			
			//判断若是父类的菜单包含一级菜单的话，需要获取里面的子菜单
			if(parent.getFuncLevel().equals(new Short("1"))){
			
				//创建一个空的集合用来存放子类的菜单
				List<SysFuncBean> childrenList = new ArrayList<>();
				
				for (int j = 0; j < list.size(); j++) {
					
					//获取到子类菜单的信息
					SysFuncBean children = list.get(j);
					
					//获取二级菜单
					if(parent.getFuncId().equals(children.getSupFuncId())){
						
						childrenList.add(children);
					}
					
				}
				parent.setChildren(childrenList);
			}
			parentList.add(list.get(i));
		}
				
		return parentList;
	}


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 将token转化类型
				UsernamePasswordToken uToken = (UsernamePasswordToken) token;
				
				//获取用户名
				Object principal = uToken.getPrincipal();
				
				//定义密码
				String credentials = "";
				//根据用户名去数据库中查询对应的密码
				if("admin".equals(principal)){
					
					credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";
				}
				
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,getName());
				
				return info;
			}
	
		    public static void main(String[] args){
		    	
		    	//获取加密后的值
		    	String hashAlgorithmName = "MD5";
		    	Object credentials = "123456";
		    	int hashIterations = 1024;
		    	Object obj = new SimpleHash(hashAlgorithmName,credentials,null,hashIterations);
		    	System.out.println(obj);
		    	
		    }
	}
	


















