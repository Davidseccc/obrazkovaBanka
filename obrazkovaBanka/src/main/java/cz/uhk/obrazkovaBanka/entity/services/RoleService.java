package cz.uhk.obrazkovaBanka.entity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Role;
import cz.uhk.obrazkovaBanka.entity.dao.RoleDao;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public long countAllRoles() {
        return roleDao.countRoles();
    }

	public void deleteRole(Role r) {
		roleDao.remove(r);
    }

	public Role findRole(Integer id) {
        return roleDao.findRole(id);
    }
	
	public Role findRoleEagerly(Long id) {
        return roleDao.findRoleEagerly(id);
    }

	public List<Role> getAllRoles() {
        return roleDao.findAllRoles();
    }

	public List<Role> findRoleEntries(int firstResult, int maxResults) {
        return roleDao.findRoleEntries(firstResult, maxResults);
    }

	public void saveRole(Role r) {
		roleDao.persist(r);
    }

	public Role updateRole(Role r) {
        return roleDao.merge(r);
    }
}
