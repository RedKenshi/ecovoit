package com.cfranc.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import javax.servlet.http.HttpSession;

import com.cfranc.model.User;
import com.cfranc.service.UserManager;

import model.Client;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;	
	
    public static String VIEW_PAGES_URL="/index.jsp";
    public Client newClient= null;

    
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PWD = "pwd1";
    public static final String FIELD_CONFIRM_PWD = "pwd2";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PRENOM = "prenom";
    public static final String FIELD_TEL = "telephone";
    public static final String FIELD_VICES = "vices";
	
	public static final String ATT_USERS = "users";

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
        this.getServletContext().setAttribute( ATT_USERS, UserManager.getUserManager().getAllUser() );
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
        String prenom = request.getParameter(FIELD_PRENOM);
        String tel = request.getParameter(FIELD_TEL);
        
        String[] vices = request.getParameterValues(FIELD_VICES);
        
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
        
        
        
        
        boolean errorStatus=true;
        if ( erreurs.isEmpty() ) {
        	newClient = new Client(name,email,pwd,prenom,tel);       
        	boolean cli = CreateClient(newClient);
            //HttpSession session = request.getSession();            
            /* Ajout de l'utilisateur au service 
            UserManager.getUserManager().addUser(newUser);
            /* Et enfin (ré)enregistrement de la map en session */
            //session.setAttribute( ATT_USERS, UserManager.getUserManager().getAllUser() );
            //this.getServletContext().setAttribute( ATT_USERS, UserManager.getUserManager().getAllUser() );
            if(cli)
            {
            	actionMessage="Succès de l'inscription";            
                form = new HashMap<String, String>();
                errorStatus = false;
            }
            else
            {
            	actionMessage="Il existe déjà un utilisateur avec cette adresse email.";            
                form = new HashMap<String, String>();
                errorStatus = false;
            }
        }
        else{
        	actionMessage="Echec de l'inscription";
        	errorStatus = true;
        }
        request.setAttribute("newUser", newClient);
        request.setAttribute("form", form);
        request.setAttribute("erreurs", erreurs);
        request.setAttribute("errorStatus", errorStatus);
        request.setAttribute("actionMessage", actionMessage);
        
        
        
        //this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include( request, response );
	}
	
	public boolean CreateClient(Client myNewClient) {
		boolean con = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserManager3");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query requete = em.createQuery("SELECT c FROM Client c WHERE c.email = :email");
		requete.setParameter("email", myNewClient.getEmail());
		List<Client> results = requete.getResultList();
		if(results.size() == 0) {
			em.persist(newClient);
			em.getTransaction().commit();
			con = true;
		}
		
		em.close();
		emf.close();
		
		return con;
		
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
