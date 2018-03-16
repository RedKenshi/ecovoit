package com.cfranc.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cfranc.model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register1")
public class Register1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public static String VIEW_PAGES_URL="/WEB-INF/register.jsp";
    
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PWD = "pwd1";
    public static final String FIELD_CONFIRM_PWD = "pwd2";
    public static final String FIELD_NAME = "name";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errorStatus", true);
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form fields
        String email = request.getParameter(FIELD_EMAIL);
        String pwd = request.getParameter(FIELD_PWD);
        String pwdConfirmation = request.getParameter(FIELD_CONFIRM_PWD);
        String name = request.getParameter(FIELD_NAME);

        // Prepare results
        Map<String, String> erreurs = new HashMap<String, String>();
        Map<String, String> form = new HashMap<String, String>();
        String actionMessage=null;
        String msgVal=null;
        
        msgVal=validateEmail(email);
        if(msgVal==null){
        	form.put(FIELD_EMAIL, email);
        }
        else{
            erreurs.put(FIELD_EMAIL, msgVal);
        }

        msgVal=validatePwd(pwd, pwdConfirmation);
        if(msgVal==null){
        	form.put(FIELD_PWD, pwd);
        }
        else{
            erreurs.put(FIELD_CONFIRM_PWD, msgVal);
        }
        
        msgVal=validateName(name);
        if(msgVal==null){
        	form.put(FIELD_NAME, name);
        }
        else{
            erreurs.put(FIELD_NAME, msgVal);
        }
            
        User newUser=null;
        boolean errorStatus=true;
        if ( erreurs.isEmpty() ) {
            newUser=new User(name,email,pwd);       
            actionMessage="Succès de l'inscription";            
            form = new HashMap<String, String>();
            errorStatus = false;
        }
        else{
        	actionMessage="Echec de l'inscription";
        	errorStatus = true;
        }
        request.setAttribute("newUser", newUser);
        request.setAttribute("errorStatus", errorStatus);
        request.setAttribute("form", form);
        request.setAttribute("erreurs", erreurs);
        request.setAttribute("actionMessage", actionMessage);
        this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include( request, response );
	}
	
	private String validateEmail( String email ) {
		if ( email != null && email.trim().length() != 0 ) {
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				return "Veuillez saisir une adresse mail valide";
			}
		}
		else {
			return "L'adresse mail est obligatoire";
		}
		return null;
	}
	private String validatePwd(String pwd1, String pwd2) {
		return (pwd1.equals(pwd2))?null:"Veuillez confirmer le mot de passe";
	}
	private String validateName(String name) {return null;}

}
