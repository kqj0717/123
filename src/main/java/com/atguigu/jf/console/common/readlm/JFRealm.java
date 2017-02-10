package com.atguigu.jf.console.common.readlm;

import javax.security.sasl.AuthorizeCallback;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.LoginService;



public class JFRealm extends AuthorizingRealm{
    
	@Autowired
	private LoginService loginService;
	
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
		
		//根据用户名去查询对应的密码
		SysOp sysOp = new SysOp();
		sysOp.setLoginCode(principal.toString());
		SysOp currentUser = null;
		
		try{
			currentUser = loginService.selectSysOpByNameAndPwd(sysOp);
		} catch (Exception e){
			
			e.printStackTrace();
		}
		//通过用户的实体信息获取密码
		String credentials = currentUser.getLoginPasswd();
		
		//定义盐值
		ByteSource salt = ByteSource.Util.bytes(principal);
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(currentUser,credentials,salt,getName());
		
		//返回info对象
		return info;
	
    }
	public static void main(String[] args) {
		String algorithmName = "MD5";
		Object source = "123456";
		ByteSource salt = ByteSource.Util.bytes("admin");
		int hashIterations = 1024;
		SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
		System.out.println(simpleHash);
	}
}
