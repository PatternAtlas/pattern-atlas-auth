//package com.patternpedia.auth.endpoints;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
//import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
//@Slf4j
////@FrameworkEndpoint
//@Controller
//public class RevokeTokenEndpoint {
//
//    @Resource(name="tokenServices")
//    DefaultTokenServices tokenServices;
//
//    @RequestMapping(method = RequestMethod.GET, value = "/oauth/revoke_token")
//    @ResponseBody
//    public boolean revokeToken(HttpServletRequest request) {
//        String authorization = request.getHeader("Authorization");
//        if (authorization != null && authorization.contains("Bearer")){
//            String tokenId = authorization.substring("Bearer".length()+1);
//            log.info(tokenId);
//            tokenServices.revokeToken(tokenId);
//            log.info("success");
//            return true;
//        }
//        return false;
//    }
//
//    @Resource(name="tokenStore")
//    TokenStore tokenStore;
//
//    @RequestMapping(method = RequestMethod.GET, value = "/tokens")
//    @ResponseBody
//    public List<String> getTokens() {
//        List<String> tokenValues = new ArrayList<String>();
//        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("pattern-pedia-private");
//        if (tokens!=null){
//            for (OAuth2AccessToken token:tokens){
//                tokenValues.add(token.getValue());
//            }
//        }
//        return tokenValues;
//    }
//}
