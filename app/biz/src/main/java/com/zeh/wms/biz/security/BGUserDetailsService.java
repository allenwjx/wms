package com.zeh.wms.biz.security;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.Sets;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AuthorizationVO;
import com.zeh.wms.biz.model.RoleVO;
import com.zeh.wms.biz.model.UserBgVO;
import com.zeh.wms.biz.service.UserBgService;

/**
 * @author allen
 * @create $ ID: BGUserDetailsService, 18/2/24 11:40 allen Exp $
 * @since 1.0.0
 */
public class BGUserDetailsService implements UserDetailsService {
    /** 后台用户服务 */
    private UserBgService userBgService;

    /**
     * Locates the user based on the username. In the actual implementation, the search may possibly be case
     * sensitive, or case insensitive depending on how the implementation instance is configured. In this case, the
     * <code>UserDetails</code> object that comes back may have a username that is of a different case than what was
     * actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserBgVO userBg = userBgService.findUserBgDetailsByUsername(username);
            UserDetails user = createUser(userBg);
            return user;
        } catch (ServiceException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    /**
     * Create spring security user model with {@link UserBgVO}
     * @param userBg
     * @return
     */
    private UserDetails createUser(UserBgVO userBg) {
        Collection<? extends GrantedAuthority> authorities = createAuthorities(userBg);
        UserDetails user = new UserWrapper(userBg, userBg.getUsername(), userBg.getPassword(), authorities);
        return user;
    }

    /**
     * Create spring user authorities
     *
     * @param userBg
     * @return
     */
    private Collection<GrantedAuthority> createAuthorities(UserBgVO userBg) {
        Collection<RoleVO> roles = userBg.getRoles();
        Collection<GrantedAuthority> authorities = Sets.newHashSet();
        if (CollectionUtils.isEmpty(roles)) {
            return authorities;
        }
        for (RoleVO role : roles) {
            Collection<GrantedAuthority> authoritiesByRole = createAuthorities(role);
            authorities.addAll(authoritiesByRole);
        }
        return authorities;
    }

    /**
     * Create spring user authorities
     *
     * @param role
     * @return
     */
    private Collection<GrantedAuthority> createAuthorities(RoleVO role) {
        Collection<GrantedAuthority> authorities = Sets.newHashSet();
        Collection<AuthorizationVO> authorizations = role.getAuthorizations();
        if (CollectionUtils.isEmpty(authorizations)) {
            return authorities;
        }
        for (AuthorizationVO authorization : authorizations) {
            GrantedAuthority authority = new SimpleGrantedAuthority(authorization.getPath());
            authorities.add(authority);
        }
        return authorities;
    }

    public void setUserBgService(UserBgService userBgService) {
        this.userBgService = userBgService;
    }
}