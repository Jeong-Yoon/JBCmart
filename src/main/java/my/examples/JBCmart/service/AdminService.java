package my.examples.JBCmart.service;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.repository.AdminRepository;
import my.examples.JBCmart.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;

}
