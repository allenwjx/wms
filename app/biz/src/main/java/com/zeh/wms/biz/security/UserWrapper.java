package com.zeh.wms.biz.security;

import java.util.Collection;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.zeh.wms.biz.model.UserBgVO;

/**
 * @author allen
 * @create $ ID: UserWrapper, 18/2/24 16:58 allen Exp $
 * @since 1.0.0
 */
public class UserWrapper extends User {
    /** system user */
    private UserBgVO user;

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     *
     * @param username
     * @param password
     * @param authorities
     */
    public UserWrapper(UserBgVO user, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.user = user;
    }

    /**
     * Construct the <code>User</code> with the details required by
     * {@link DaoAuthenticationProvider}.
     *
     * @param username              the username presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param password              the password that should be presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param enabled               set to <code>true</code> if the user is enabled
     * @param accountNonExpired     set to <code>true</code> if the account has not
     *                              expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials
     *                              have not expired
     * @param accountNonLocked      set to <code>true</code> if the account is not
     *                              locked
     * @param authorities           the authorities that should be granted to the caller
     *                              if they presented the correct username and password and the user
     *                              is enabled. Not null.
     * @throws IllegalArgumentException if a <code>null</code> value was passed
     *                                  either as a parameter or as an element in the
     *                                  <code>GrantedAuthority</code> collection
     */
    public UserWrapper(UserBgVO user, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public UserBgVO getUser() {
        return user;
    }
}