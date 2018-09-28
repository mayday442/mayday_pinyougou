package com.pinyougou.shop.service;

import com.pinyougou.manager.service.SellerService;
import com.pinyougou.pojo.TbSeller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mayday
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private final String SELLER_STATUS = "1";


    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbSeller seller = sellerService.findOne(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (seller != null) {
            if (SELLER_STATUS.equals(seller.getStatus())){
                return new User(username, seller.getPassword(), authorities);
            }else {
                throw new RuntimeException("登录失败");
            }
        }

        throw new RuntimeException("登录失败");
    }
}
