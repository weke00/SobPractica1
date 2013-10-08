package Controller;

import Hibernate.Usuario;
import Hibernate.UsuariosDAO;
import Model.MD5;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerardca
 */
public class ServletController extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //inicialitzar consulta bbdd

    }

    protected void redirect(String to, String Message) throws IOException, ServletException {

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/" + to);
        PrintWriter out = response.getWriter();
        out.println(Message);
        rd.include(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {

        this.request = request;
        this.response = response;

        HttpSession sessio = request.getSession(true);
        Usuario obj = (Usuario) sessio.getAttribute("sessionID");

        if (obj == null) {

            UsuariosDAO usuariosDAO = new UsuariosDAO();
            Usuario user = usuariosDAO.obtenUsuario(request.getParameter("user"));
            if (user != null) {

                if (user.getPasswd().equals(MD5.generateMD5Signature(request.getParameter("passwd")))) {

                    //UserBean userData = new UserBean(user.getNom(), sessio.getId());
                    //Usuario userSession = new Usuario(idUser,null, null, sessio.getId());
                    user.setIdsession(sessio.getId());
                    sessio.setAttribute("sessionID", user);
                    System.out.println("S'ha creat la sessio per aquest usuari");
                    //faltara carregar coses per a la vista
                    response.sendRedirect("loginPage.jsp");
                } else {

                    System.out.println("Usuari no valid");
                    String Message = "<font color=red>Either user name or password is wrong.</font>";
                    redirect("index.jsp", Message);
                }

            } else {

                System.out.println("Usuari no valid");
                String Message = "<font color=red>Either user name or password is wrong.</font>";
                redirect("index.jsp", Message);
            }

        } else {
            System.out.println("No fa falta registrarse :)");
            response.sendRedirect("loginPage.jsp");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
