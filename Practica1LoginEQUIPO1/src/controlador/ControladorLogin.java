package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import negocio.GestionarArchivo;

/**
 * Servlet implementation class ControladorLogin
 */
@WebServlet("/login")
public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario usuarioEncontrado;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorLogin() {
        super();
        usuarioEncontrado = new Usuario();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String ruta = request.getServletContext().getRealPath("/");
		GestionarArchivo archivoregistro = new GestionarArchivo(ruta+"/archivos");
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("contrasena");
		System.out.println("nombre: "+usuario+" contrasena "+pass);
		usuarioEncontrado = archivoregistro.buscarUsuario(usuario);
		System.out.println("Usuario: "+usuarioEncontrado.toString());
		if(usuarioEncontrado.getNombres()==null){
			/*System.out.println("Usuario no encontrado");
			response.setContentType("text/html");
			PrintWriter salida = response.getWriter();
			salida.println("<!DOCTYPE html><HTML>");
		    salida.println("<HEAD><TITLE>Inicio de sesion</TITLE></HEAD>");
		    salida.println("<BODY>");
		    salida.println("<h1>Usuario no valido</h1>");
		    //salida.println("<form id=\"formularioRegistro\" method=\"post\" action=\"registro\">");
		    //salida.println("<input type=\"text\" name=\"nombres\" id=\"nombres\" required=\"required\"> <br>");
		    salida.println("</form>");
		    salida.println("</BODY></HTML>");
		    salida.flush();
		    salida.close();*/
		    response.sendRedirect("loginError.html");
		}else if(usuarioEncontrado.getCorreo().equals(usuario) && usuarioEncontrado.getContrasena().equals(pass)){
			System.out.println("usuario encontrado");
			response.sendRedirect("loginExitoso.html");
		}
		System.gc();
	}

}
