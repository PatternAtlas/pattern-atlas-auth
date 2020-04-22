package com.patternpedia.auth.security;

import com.patternpedia.auth.user.PatternPediaUser;
import com.patternpedia.auth.user.UserEntity;
import org.apache.catalina.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

//public class CustomTokenEnhancer implements TokenEnhancer {
//
//    @Override
//    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        PatternPediaUser user = (PatternPediaUser) authentication.getPrincipal();
//        final Map<String, Object> additionalInfo = new HashMap<>();
//
//        additionalInfo.put("id", user.getId());
//        additionalInfo.put("authorities", user.getAuthorities());
//
//        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//
//        return accessToken;
//    }
//
//}

