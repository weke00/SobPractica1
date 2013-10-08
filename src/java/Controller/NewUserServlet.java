/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Hibernate.Usuario;
import Hibernate.UsuariosDAO;
import Model.MD5;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gerardca
 */
public class NewUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {

        HttpSession sessio = request.getSession(true);
        Usuario obj = (Usuario) sessio.getAttribute("sessionID");

        if (obj == null) {

            String user_id = request.getParameter("user_id");
            String user_name = request.getParameter("user_name");
            String passwd = request.getParameter("passwd");

            UsuariosDAO usuariosDAO = new UsuariosDAO();

            //generating the hash throws the user password, and save it into database
            passwd = MD5.generateMD5Signature(passwd);

            Usuario user = new Usuario(user_id, passwd, user_name, sessio.getId());
            usuariosDAO.guardaUsuario(user);
            sessio.setAttribute("sessionID", user);
            System.out.println("Crea l'usuari");
            
            System.out.println("Probem d'afegir un nou following");
            Usuario user2 = new Usuario("sebas", "1234", "Sebastian Beluzo", "asas");
            usuariosDAO.guardaUsuario(user2);
            
            Usuario user3 = new Usuario("gloria", "1234", "GloriaArenas", "asas");
            usuariosDAO.guardaUsuario(user3);
            
            user.getFollowings().add(user2);
            user.getFollowings().add(user3);
            usuariosDAO.actualizaUsuario(user);
                        
            response.sendRedirect("loginPage.jsp");
            //ha de redeireccionar directament a la pagina principal de l'aplicació

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
