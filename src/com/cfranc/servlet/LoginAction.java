package com.cfranc.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Client;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/login")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// View
    public static String VIEW_PAGES_URL="/index.jsp";
    public Client CurrentClient = null;
    
    // Form fields
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PWD = "pwd";
    
    // Request attributs
    Map<String, String> error;// = new HashMap<String, String>();
    Map<String, String> form;// = new HashMap<String, String>();
    String statusMessage="";
    boolean statusOk=false;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Prepare model to view
        request.setAttribute("statusOK", false);
        request.setAttribute("statusMessage", "");
        
        // Build view
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
        // Get form fields
        String email = request.getParameter(FIELD_EMAIL);
        String pwd = request.getParameter(FIELD_PWD);
		System.out.println(pwd);
        // Prepare data for view (attributs)
        error = new HashMap<String, String>();
        form = new HashMap<String, String>();
        statusMessage=null;
        
        // Validate page
        String msgVal=null;
        if(msgVal==null){
        	form.put(FIELD_EMAIL, email);
        }
        else{
            error.put(FIELD_EMAIL, msgVal);
        }
        msgVal=validatePwd(pwd);
        if(msgVal==null){
        	form.put(FIELD_PWD, pwd);
        }
        else{
            error.put(FIELD_PWD, msgVal);
        }
        
        if(error.isEmpty() && authenticate(email, pwd)){
        	statusOk=true;
        	CurrentClient = new Client();
        	CurrentClient.setEmail(email);
        	CurrentClient.setPassword(pwd);
        	Login(CurrentClient.getEmail(),CurrentClient.getPassword());
        	
        }
        else{
        	statusOk=false;
        	statusMessage="Connexion refusée";
        }
        
        // Prepare model to view
        request.setAttribute("form", form);
        request.setAttribute("error", error);
        request.setAttribute("statusOK", statusOk);
        request.setAttribute("statusMessage", statusMessage);
        
        
        
        // Build view
		//this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include( request, response );
	}

	private void Login(String email,String pwd )
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserManager3");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query requete = em.createQuery("SELECT c FROM Client c WHERE c.email = :email AND c.password = :password");
		requete.setParameter("email", email);
		requete.setParameter("password", pwd);
		List<Client> results = requete.getResultList();
		if(results.size() != 0) {
			em.getTransaction().commit();
			System.out.println("bienvenue");
			statusMessage="Connecté";
		}else {
			System.out.println("le compte n'existe pas !!");
			
		}
		
		
		em.close();
		emf.close();
	}
	
	private String validateEmail( String email ) {
		if ( email != null && email.trim().length() != 0 ) {
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				return "Veuillez saisir une adresse mail valide";
			}
			else{
				if(!isUserExist(email)){
					return "Login inconu";
				}
			}
		}
		else {
			return "L'adresse mail est obligatoire";
		}
		return null;
	}
	
	private String validatePwd(String pwd) {
		return (pwd==null || pwd.equals(""))?"Le mot de passe doit être renseigné":null;
	}
	
	private boolean isUserExist(String login){
		return true;
	}
	
	private boolean authenticate(String login, String pwd){
		return true;
	}
}
