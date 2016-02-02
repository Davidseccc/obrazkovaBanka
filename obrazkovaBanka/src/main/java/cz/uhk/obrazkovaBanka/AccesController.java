package cz.uhk.obrazkovaBanka;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import cz.uhk.obrazkovaBanka.entity.User;

@Controller
public class AccesController {


	// private static final Logger logger =
	// LoggerFactory.getLogger(CatogoryController.class);

	public static final int ADMINS_ONLY = 0;
	public static final int OWNER_ONLY = 1;
	public static final int OWNER_AND_ADMINS = 2;
	public static final int REGISTERED_USERS = 3;
	public static final int ALL_USERS = 4;

	/**
	 * 
	 * @param session
	 *            - user real role
	 * @param accessFor
	 *            - minimal role for access
	 * @return TRUE if user is selected role, else return FALSE
	 */
	public static boolean checkPermission(HttpSession session, int accessFor, User user) {

		String sessionNick = (String) session.getAttribute("loggedInUser");
		String role = null;
		if (sessionNick != null) {
			role = (String) session.getAttribute("loggedInUserRole");
		}
		// System.out.println("provìøuji uživatele: " + "nick: "+ sessionNick +
		// " role: "+role);
		if (accessFor == ALL_USERS) {
			return true;
		}
		if (role != null && sessionNick != null) {
			if (accessFor == OWNER_ONLY && user.getNickName().equals(sessionNick)) {
				return true;
			}
			if (accessFor == OWNER_AND_ADMINS
					&& (user.getNickName().equals(sessionNick) || role.equals("ROLE_ADMIN"))) {
				return true;
			}
			if (accessFor == ADMINS_ONLY && role.equals("ROLE_ADMIN")) {
				System.out.println("ADMINS_ONLY: " + "role: " + role + " nick: " + sessionNick);
				return true;
			}
			if (accessFor == REGISTERED_USERS && sessionNick != null) {
				System.out.println("ADMINS_ONLY: " + "role: " + role + " nick: " + sessionNick);
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}
}
