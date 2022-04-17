package my.com.tcsens.vehiclemanagement.service;

import lombok.val;
import my.com.tcsens.vehiclemanagement.dto.Role;
import my.com.tcsens.vehiclemanagement.dto.User;
import my.com.tcsens.vehiclemanagement.model.RoleModel;
import my.com.tcsens.vehiclemanagement.model.UserModel;
import my.com.tcsens.vehiclemanagement.repository.RoleRepository;
import my.com.tcsens.vehiclemanagement.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers().stream().map(this::mapDto).collect(Collectors.toList());
    }

    public User getUserByLoginId(String loginId) {
        return mapDto(userRepository.getUserByLoginId(loginId));
    }

    private User mapDto(UserModel userProfile) {
        val userRoles = roleRepository.getUserByLoginId(userProfile.getId());
        val roles = userRoles.stream().map(this::mapRole).collect(Collectors.toList());

        return new User().id(Long.valueOf(userProfile.getId()))
                .loginId(userProfile.getLoginId())
                .roles(roles);
    }

    private Role mapRole(RoleModel roles) {
        return new Role().roleName(roles.getRoleName());
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        val userProfile = userRepository.getUserByLoginId(loginId);
        User user = mapDto(userProfile);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(userProfile.getLoginId(), userProfile.getUserPassword(), getAuthority(user));

    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
}
