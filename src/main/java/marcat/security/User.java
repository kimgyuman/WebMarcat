package marcat.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.members.vo.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
public class User implements UserDetails {

    private Long id;
//    private String uId;
    private String passwd;
    private String role;

    public User(Member member) {
        this.id = member.getId();
//        this.uId = member.getUId();
        this.passwd = member.getPasswd();
        this.role = String.valueOf(member.getRoleStatus());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(this.role));
        return collection;
    }

    @Override
    public String getPassword() {
        return this.passwd;
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
