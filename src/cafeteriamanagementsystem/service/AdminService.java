package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.Admin;
import cafeteriamanagementsystem.dao.AdminDAO;

public class AdminService {

    private AdminDAO adminDAO;

    public AdminService() {
        adminDAO = new AdminDAO();
    }


    // Register Admin
    public boolean registerAdmin(Admin admin) {

        if(admin.getAdminId() == null || admin.getAdminId().isEmpty()) {
            return false;
        }

        if(admin.getDesignation() == null || admin.getDesignation().isEmpty()) {
            return false;
        }

        if(admin.getAccessLevel() == null || admin.getAccessLevel().isEmpty()) {
            return false;
        }

        return adminDAO.addAdmin(admin);
    }


    // Find Admin
    public Admin getAdmin(String adminId) {

        if(adminId == null || adminId.isEmpty()) {
            return null;
        }

        return adminDAO.getAdminById(adminId);
    }


    // Update Admin
    public boolean updateAdmin(Admin admin) {
        return adminDAO.updateAdmin(admin);
    }


    // Delete Admin
    public boolean deleteAdmin(String adminId) {
        return adminDAO.deleteAdmin(adminId);
    }
}