package com.backbase.assignment.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		 clients.inMemory()
					.withClient("trusted-client")
					.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
            	.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            	.scopes("read", "write", "trust")
            	.secret("secret")
            	.accessTokenValiditySeconds(1200) //Access token is only valid for 20 minutes.
            	.refreshTokenValiditySeconds(1800); //Refresh token is only valid for 30 minutes.
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		 endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
					 .authenticationManager(authenticationManager);
	}

}
