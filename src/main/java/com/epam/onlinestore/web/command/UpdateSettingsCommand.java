package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.dao.impl.AccountDAOImpl;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update settings items.
 *
 * @author D.Kolesnikov
 */
public class UpdateSettingsCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");

        // UPDATE USER ////////////////////////////////////////////////////////

        Account account = (Account) request.getSession().getAttribute("user");
        boolean updateUser = false;

        // update first name
        String firstName = request.getParameter("firstName");
        if (firstName != null && !firstName.isEmpty()) {
            account.setLogin(firstName);
            updateUser = true;
        }

        // update last name
	/*	String lastName = request.getParameter("lastName");
		if (lastName != null && !lastName.isEmpty()) {
			user.setLastName(lastName);
			updateUser = true;
		}

		String localeToSet = request.getParameter("localeToSet");
		if (localeToSet != null && !localeToSet.isEmpty()) {
			HttpSession session = request.getSession();
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);			
			session.setAttribute("defaultLocale", localeToSet);
			user.setLocaleName(localeToSet);
			updateUser = true;
		}*/

        if (updateUser == true) {
            try {
                new AccountDAOImpl().updateAccount(account);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }


        log.debug("Command finished");
        return Path.PAGE__SETTINGS;
    }

}