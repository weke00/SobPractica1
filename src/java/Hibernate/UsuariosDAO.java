package Hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuariosDAO
{  
    private Session sesion; 
    private Transaction tx;  

    public long guardaUsuario(Usuario usuario) throws HibernateException 
    { 
        long id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (Long) sesion.save(usuario);
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  

        return id; 
    }  

    public void actualizaUsuario(Usuario usuario) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(usuario); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public void eliminaUsuario(Usuario usuario) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(usuario); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  
    
    public Usuario obtenUsuario(String idusuari) throws HibernateException 
    { 
        Usuario usuario = null; 
        try 
        { 
            iniciaOperacion(); 
            Query q = sesion.createQuery("from Usuario user where user.idusuari=:idusuari").setParameter("idusuari", idusuari);
            usuario = (Usuario) q.uniqueResult();
            
        } finally 
        { 
            sesion.close(); 
        }  

        return usuario; 
    }  

    public List<Usuario> obtenListaUsuarios() throws HibernateException 
    { 
        List<Usuario> listaContactos = null;  

        try 
        { 
            iniciaOperacion(); 
            listaContactos = sesion.createQuery("from Usuario").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaContactos; 
    }
    
   
    

    private void iniciaOperacion() throws HibernateException 
    { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    }  

    private void manejaExcepcion(HibernateException he) throws HibernateException 
    { 
        tx.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    } 
}