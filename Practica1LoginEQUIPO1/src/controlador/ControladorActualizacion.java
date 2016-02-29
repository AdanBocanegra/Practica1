package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import negocio.GestionarArchivo;

/**
 * Servlet implementation class ControladorActualizacion
 */
@WebServlet("/ControladorActualizacion")
public class ControladorActualizacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession sesion;
	private Usuario usuarioActual, usuarioActualizado;
	private GestionarArchivo archivoActualizar;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorActualizacion() {
        super();
        // TODO Auto-generated constructor stub
        usuarioActual=new Usuario();
        usuarioActualizado = new Usuario();
        archivoActualizar = new GestionarArchivo();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		sesion =request.getSession();
		usuarioActual = (Usuario)sesion.getAttribute("ACTUALIZACION");
		
		//obtencion de datos
		String nombre = usuarioActual.getNombres();
		String apellidoPaterno = usuarioActual.getApellidoPaterno();
		String apellidoMaterno = usuarioActual.getApellidoMaterno();
		String correo = usuarioActual.getCorreo();
		String contrasena = usuarioActual.getContrasena();
		
		try{
			PrintWriter modificarDatos = response.getWriter();
			modificarDatos.println("<!DOCTYPE html><html>");
			modificarDatos.println("<head><h1>Actualizacion de datos </h1></head>");
			modificarDatos.println("<body>");
			modificarDatos.println("<div id='contenedorPrincipal'>");
			modificarDatos.println("<section id='seccionActualizacionDatos'>");
			modificarDatos.println("<form id='formularioActualizacion'>");
			modificarDatos.println("<input type='text' name='nombreActualizado' required='required' value='"+nombre +"'>");
			modificarDatos.println("<input type='text' name='apellidoPaternoActualizado' required='required' value='"+apellidoPaterno +"'>");
			modificarDatos.println("<input type='text' name='apellidoMaternoActualizado' required='required' value='"+apellidoMaterno +"'>");
			modificarDatos.println("<input type='email' name='correoActualizado' required='required' value='"+correo +"'>");
			modificarDatos.println("<input type='password' name='contrasenaActualizado' required='required' value='"+contrasena +"'>");
			modificarDatos.println("<button type='submit'>Actualizar</button>");
			modificarDatos.println("</form>");
			modificarDatos.println("</section>");
			modificarDatos.println("</div>");
			modificarDatos.println("</body>");
			modificarDatos.println("</html>");
			modificarDatos.close();
			
//			Obtencion de datos a actualizar
			String nombreActualizado = request.getParameter("nombreActualizado");
			String apellidoPaternoActualizado = request.getParameter("apellidoPaternoActualizado");
			String apellidoMaternoActualizado = request.getParameter("apellidoMaternoActualizado");
			String correoActualizado = request.getParameter("correoActualizado");
			String contrasenaActualizado = request.getParameter("contrasenaActualizado");
			
//			Envio de datos a actualizar
			usuarioActualizado.setNombres(nombreActualizado);
			usuarioActualizado.setApellidoPaterno(apellidoPaternoActualizado);
			usuarioActualizado.setApellidoMaterno(apellidoMaternoActualizado);
			usuarioActualizado.setCorreo(correoActualizado);
			usuarioActualizado.setContrasena(contrasenaActualizado);
			
			boolean actualizacion = archivoActualizar.actualizarUsuario(usuarioActual, usuarioActualizado);
			if(actualizacion){
				try{
					PrintWriter mostrarDatos = response.getWriter();
					mostrarDatos.println("<!DOCTYPE html><html>");
					mostrarDatos.println("<head><h1>Actualizacion de datos </h1></head>");
					mostrarDatos.println("<body>");
					mostrarDatos.println("<header>Actualizacion exitosa!!</header>");
					mostrarDatos.println("<div id='contenedorPrincipal'>");
					mostrarDatos.println("<section id='seccionMostrarDatos'>");
					mostrarDatos.println("<form id='formularioActualizacionMostrar' action='Login.html' >");
					mostrarDatos.println("<output name='nombreActualizadoMostrar' for='"+nombreActualizado +"'>");
					mostrarDatos.println("<output name='apellidoPaternoActualizadoMostrar' for='"+apellidoPaternoActualizado +"'>");
					mostrarDatos.println("<output name='apellidoMaternoActualizadoMostrar' for='"+apellidoPaternoActualizado +"'>");
					mostrarDatos.println("<output name='correoActualizadoMostrar' for='"+correoActualizado +"'>");
					mostrarDatos.println("<output name='contrasenaActualizadoMostrar' for='"+contrasenaActualizado +"'>");
					mostrarDatos.println("<button type='submit'>Inicio</button>");
					mostrarDatos.println("</form>");
					mostrarDatos.println("</section>");
					mostrarDatos.println("</div>");
					mostrarDatos.println("</body>");
					mostrarDatos.println("</html>");
					mostrarDatos.close();
				}catch(Exception e){
					
				}
			}else{
				response.sendRedirect("actualizacionUsuarioFallida.html");		
			}
			
		}catch(IOException e){
			
		}
		
	}

}
